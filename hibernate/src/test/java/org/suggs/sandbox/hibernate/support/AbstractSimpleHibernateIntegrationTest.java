/*
 * AbstractSimpleHibernateIntegrationTest.java created on 25 Mar 2010 07:06:15 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.Serializable;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Abstract test to be used in conjunction with any hibernate entity test.
 *
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractSimpleHibernateIntegrationTest<PK extends Serializable, T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSimpleHibernateIntegrationTest.class);

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionfactory;

    @Before
    public void setUp() {
        LOG.debug("----------------------------------");
        executeInTransaction(new TransactionExecutable() {

            @Override
            public void execute(Session aSession) {
                cleanUpData(aSession);
            }
        });
    }

    /**
     * Perform a collection of cleanup operations within a dedicated transaction prior to any tests being run.
     */
    protected abstract void cleanUpData(Session aSession);

    /**
     * This method allows you to create additional dependent objects in the database
     */
    protected void createDependentObjectsForTest(Session aSession) {
    }

    /**
     * Create a template key object for the tests.
     */
    protected abstract PK createKeyTemplate();

    /**
     * Create a peristent entity for the tests.
     */
    protected abstract T createEntityTemplate(PK aKey, Session aSession);

    /**
     * This is needed so that we can delegate down to the implementing test to perform the underlying update
     * to the persisted entity. This is used in the update test (CRUD).
     */
    protected abstract void updateEntityForUpdateTest(T aEntity);

    /**
     * Creates the HQL string for retrieval of the persisted entities.
     */
    protected abstract String createEntitySearchHql();

    /**
     * Overloadable method that allows us to perform per validation activities.
     */
    protected void doInitialVerificationForCreateTest(Session aSession, T aExpected, T aResult) {
    }

    @Test
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.info("Testing the create CRUD operation ...");
        runGenericTest(new HibernateIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;

            @Override
            public void beforeTest(Session aSession) {
                createDependentObjectsForTest(aSession);
                entity = createEntityTemplate(key, aSession);
                verifyEntityCount(aSession, 0L);
            }

            @Override
            public void executeTest(Session aSession) {
                LOG.debug("Persiting: " + entity);
                aSession.save(entity);
                LOG.debug("Entity now saved into the db ... good");
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest(Session aSession) {
                verifyEntityCount(aSession, 1L);
                T result = (T) aSession.createQuery(createEntitySearchHql()).uniqueResult();
                doInitialVerificationForCreateTest(aSession, entity, result);
                verifyResult(entity, result);
            }
        });
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     */
    protected void doInitialVerificationForReadTest(Session aSession, T aExpected, T aResult) {
    }

    @Test
    public void basicReadOperationsInstantiatesCorrectObject() {
        LOG.info("Testing the read CRUD operation ...");
        runGenericTest(new HibernateIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T readEntity = null;

            @Override
            public void beforeTest(Session aSession) {
                createDependentObjectsForTest(aSession);
                entity = createEntityTemplate(key, aSession);
                verifyEntityCount(aSession, 0L);

                aSession.save(entity);
                verifyEntityCount(aSession, 1L);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest(Session aSession) {
                if (key != null) {
                    readEntity = (T) aSession.get(entity.getClass(), key);
                }
            }

            @Override
            public void verifyTest(Session aSession) {
                if (key != null) {
                    verifyEntityCount(aSession, 1L);
                    doInitialVerificationForReadTest(aSession, readEntity, entity);
                    verifyResult(entity, readEntity);
                }
            }

        });
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     */
    protected void doInitialVerificationForUpdateTest(Session aSession, T aExpected, T aResult) {
    }

    @Test
    public void basicUpdateOperationsUpdatesCorrectObject() {
        LOG.info("Testing the update CRUD operation ...");
        runGenericTest(new HibernateIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;
            T clone = null;

            @Override
            public void beforeTest(Session aSession) {
                createDependentObjectsForTest(aSession);
                entity = createEntityTemplate(key, aSession);
                clone = createEntityTemplate(key, aSession);
                verifyEntityCount(aSession, 0L);

                aSession.save(entity);
                verifyEntityCount(aSession, 1L);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest(Session aSession) {
                entity = (T) aSession.createQuery(createEntitySearchHql()).uniqueResult();
                updateEntityForUpdateTest(entity);
                aSession.save(entity);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest(Session aSession) {
                entity = (T) aSession.createQuery(createEntitySearchHql()).uniqueResult();
                doInitialVerificationForUpdateTest(aSession, clone, entity);
                assertThat(entity, not(nullValue()));
                assertThat(entity, not(sameInstance(clone)));
                assertThat(entity, not(equalTo(clone)));
            }
        });
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     */
    protected void doInitialVerificationForDeleteTest(Session aSession, T aDeleted) {
    }

    @Test
    public void basicDeleteOperationsDeletesCorrectObject() {
        LOG.info("Testing the delete CRUD operation ...");
        runGenericTest(new HibernateIntegrationTestCallback() {

            PK key = createKeyTemplate();
            T entity = null;

            @Override
            public void beforeTest(Session aSession) {
                createDependentObjectsForTest(aSession);
                entity = createEntityTemplate(key, aSession);
                verifyEntityCount(aSession, 0L);

                aSession.save(entity);
                verifyEntityCount(aSession, 1L);
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest(Session aSession) {
                T entityToDelete = (T) aSession.createQuery(createEntitySearchHql()).uniqueResult();
                aSession.delete(entityToDelete);
            }

            @Override
            public void verifyTest(Session aSession) {
                doInitialVerificationForDeleteTest(aSession, entity);
                verifyEntityCount(aSession, 0L);
            }
        });
    }

    protected void verifyEntityCount(Session aSession, long aCountOfEntities) {
        Long count = (Long) aSession.createQuery("select count(*) " + createEntitySearchHql())
                .uniqueResult();
        assertThat(count, equalTo(aCountOfEntities));
        LOG.debug(aCountOfEntities + " rows in the database ... good");
    }

    protected void verifyResult(T expected, T result) {
        assertThat(result, not(nullValue()));
        assertThat(result, not(sameInstance(expected)));
        assertThat(result.hashCode(), equalTo(expected.hashCode()));
        assertThat(result, equalTo(expected));
        LOG.debug("Objects of type [" + result.getClass().getSimpleName() + "] match up ... good");
        LOG.debug("Object debug:" + result);
    }

    // =====================================

    /**
     * Executes the called back around a transaction.
     */
    private void executeInTransaction(TransactionExecutable executable) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            executable.execute(session);
            transaction.commit();
        } catch (Exception e) {
            LOG.warn("Exception: " + e.getMessage());
            transaction.rollback();
            Assert.fail("Failed to execute in transaction");
        } finally {
            session.close();
        }
    }

    /**
     * Provides a basic framework for running a Hibernate integration test against a database.
     */
    protected void runGenericTest(HibernateIntegrationTestCallback aCallback) {
        runGenericTest(true, aCallback);
    }

    /**
     * Provides a basic framework for running a Hibernate integration test against a database.
     */
    protected void runGenericTest(boolean isFailOnException, HibernateIntegrationTestCallback aCallback) {
        Session session = sessionfactory.openSession();

        Transaction trans = session.beginTransaction();
        try {
            LOG.debug("--> Before Test");
            aCallback.beforeTest(session);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            session.close();
            if (isFailOnException) {
                Assert.fail("Exception caught in 'beforeTest' execution, transaction rolled back");
            }
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug("--> Execute Test");
            aCallback.executeTest(session);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            session.close();
            if (isFailOnException) {
                Assert.fail("Exception of type [" + e.getClass().getName()
                        + "] caught in 'executeTest' execution, transaction rolled back");
            }
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug("--> Verify Test");
            aCallback.verifyTest(session);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            session.close();
            if (isFailOnException) {
                Assert.fail("Exception caught in 'verifyTest' execution, transaction rolled back");
            }
        }

        session.close();
        LOG.debug("----------------------------------");
    }

    /**
     * Template to allow us to execute something within the context of a transaction.
     */
    protected interface TransactionExecutable {

        void execute(Session aSession);
    }
}
