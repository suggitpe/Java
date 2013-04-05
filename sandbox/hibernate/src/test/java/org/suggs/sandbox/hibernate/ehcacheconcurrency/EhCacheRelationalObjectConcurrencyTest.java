package org.suggs.sandbox.hibernate.ehcacheconcurrency;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.suggs.sandbox.hibernate.basicentity.BasicRelationshipEntity;
import org.suggs.sandbox.hibernate.basicentity.BasicRelationshipOtherEntity;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:xml/ut-ehcache-concurrency.xml"})
public class EhCacheRelationalObjectConcurrencyTest {

    private static final Logger LOG = LoggerFactory.getLogger(EhCacheRelationalObjectConcurrencyTest.class);

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    private Long idForTest = null;
    private Long relatedIdForTest = null;

    private void seedDatabaseWithEntity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            BasicRelationshipEntity entity = new BasicRelationshipEntity("something else");
            BasicRelationshipOtherEntity other = new BasicRelationshipOtherEntity("something else");
            entity.addOther(other);
            session.save(entity);

            idForTest = entity.getId();
            relatedIdForTest = other.getId();

            transaction.commit();
            session.evict(entity);
        } catch (Exception exception) {
            transaction.rollback();
            throw new IllegalStateException("Failed to create persistent object in setup");
        } finally {
            session.close();
        }
    }

    @Test
    public void twoThreadsDoNotInterfereWithEachOthersRelationalObjects() throws Throwable {
        TestFramework.runManyTimes(new RelationalObjectConcurrencyTest(), 20);
    }

    class RelationalObjectConcurrencyTest extends MultithreadedTestCase {

        @Override
        public void initialize() {
            LOG.debug("---------- initialise start");
            seedDatabaseWithEntity();
            LOG.debug("---------- test start");
        }

        @SuppressWarnings("unused")
        public void threadReader() {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            waitForTick(1);

            try {
                BasicRelationshipEntity entity = (BasicRelationshipEntity) session.get(BasicRelationshipEntity.class, idForTest);
                for (BasicRelationshipOtherEntity other : entity.getOthers()) {
                    other.getStringData();
                }
                session.flush();
                waitForTick(2);

                transaction.commit();
            } catch (Exception exception) {
                String err = "Failure from reader thread";
                LOG.error(err, exception);
                transaction.rollback();
                fail(err);
            } finally {
                session.close();
            }
        }

        @SuppressWarnings("unused")
        public void threadWriter() {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            waitForTick(1);

            try {
                BasicRelationshipOtherEntity entity = (BasicRelationshipOtherEntity) session.get(BasicRelationshipOtherEntity.class, idForTest);
                entity.setStringData("Some string data");

                session.flush();
                waitForTick(2);

                entity.setStringData("Updated again");
                transaction.commit();
            } catch (Exception exception) {
                String err = "Failure from reader thread";
                LOG.error(err, exception);
                transaction.rollback();
                fail(err);
            } finally {
                session.close();
            }
        }

        @Override
        public void finish() {
            LOG.debug("---------- finish start");
            Session session = sessionFactory.openSession();
            try {
                BasicRelationshipEntity entity = (BasicRelationshipEntity) session.get(BasicRelationshipEntity.class, idForTest);
                assertThat(entity.getVersion(), equalTo(0));
                BasicRelationshipOtherEntity other = (BasicRelationshipOtherEntity) session.get(BasicRelationshipOtherEntity.class, relatedIdForTest);
                assertThat(other.getVersion(), equalTo(2));
            } finally {
                session.close();
            }
            LOG.debug("---------- finish end");
        }

    }
}
