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
import org.suggs.sandbox.hibernate.basicentity.ReallyBasicEntity;

import javax.annotation.Resource;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Proves that the ehCache implementation is safe for use in concurrent processing of transactional data.
 * <p/>
 * User: suggitpe Date: 31/03/11 Time: 20:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:xml/ut-ehcache-concurrency.xml"})
public class EhCacheSimpleObjectConcurrencyTest {

    private static final Logger LOG = LoggerFactory.getLogger(EhCacheSimpleObjectConcurrencyTest.class);

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    private Long idForTest = null;

    private void seedDatabaseWithEntity() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            ReallyBasicEntity entity = new ReallyBasicEntity("Some string", 12345, new Date(System.currentTimeMillis()));
            session.save(entity);
            idForTest = entity.getId();
            transaction.commit();
            session.evict(entity);
        } catch (Exception exception) {
            transaction.rollback();
            throw new IllegalStateException("Failed to create persistent object in setup");
        } finally {
            session.close();
        }
        sessionFactory.getCache().evictEntityRegion(ReallyBasicEntity.class);
    }

    @Test
    public void entityIsAddedToSecondLevelCacheOnRead() {
        seedDatabaseWithEntity();
        LOG.debug("Reading cache data");
        Session session = sessionFactory.openSession();
        assertThat(sessionFactory.getCache().containsEntity(ReallyBasicEntity.class, idForTest), is(false));
        try {
            ReallyBasicEntity entity = (ReallyBasicEntity) session.get(ReallyBasicEntity.class, idForTest);
            assertThat(entity, notNullValue());
            assertThat(entity.getId(), equalTo(idForTest));
            assertThat(sessionFactory.getCache().containsEntity(ReallyBasicEntity.class, idForTest), is(true));
        } finally {
            session.close();
        }
        LOG.debug("Read cache data");
    }

    @Test
    public void twoThreadsDoNotInterfereWithEachOthersSimpleObjects() throws Throwable {
        TestFramework.runManyTimes(new SimpleObjectConcurrencyTest(), 20);
    }

    /**
     * MTC test that will test two threads interaction with ehCache with the following thread order:
     * <p/>
     * <ol>
     * <li>Tick0: Reader & writer threads set up</li>
     * <li>Tick1: Reader reads and flushes, writer reads, updates and flushes</li>
     * <li>Tick2: Writer does another update and then both threads try and commit</li>
     * </ol>
     */
    private class SimpleObjectConcurrencyTest extends MultithreadedTestCase {

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
                ReallyBasicEntity entity = (ReallyBasicEntity) session.get(ReallyBasicEntity.class, idForTest);
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
                ReallyBasicEntity entity = (ReallyBasicEntity) session.get(ReallyBasicEntity.class, idForTest);
                entity.setSomeString("Updated string");

                session.flush();
                waitForTick(2);

                entity.setSomeString("Updated again");
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
                ReallyBasicEntity entity = (ReallyBasicEntity) session.get(ReallyBasicEntity.class, idForTest);
                assertThat(entity.getVersion(), equalTo(2));
            } finally {
                session.close();
            }
            LOG.debug("---------- finish end");
        }
    }
}
