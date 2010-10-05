/*
 * AbstractSimpleHibernateIntegrationTest.java created on 25 Mar 2010 07:06:15 by suggitpe for project sandbox-hibernate
 * 
 */
package com.ubs.reporting.support;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Abstract test to be used in conjuction with any hibernate entity test.
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractSimpleHibernateIntegrationTest<K extends Serializable, E> {

    private static final Log LOG = LogFactory.getLog( AbstractSimpleHibernateIntegrationTest.class );
    protected static final long TEST_TIMEOUT = 10000;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionfactory;

    /**
     * Runs prior to test execution.
     */
    @Before
    public void setUp() {
        LOG.debug( "----------------------------------" );
        executeInTransaction( new TransactionExecutable() {

            @Override
            public void execute( Session aSession ) {
                cleanUpData( aSession );
            }
        } );
    }

    /**
     * Perform a collection of cleanup operations within a dedicated transaction prior to any tests being run.
     * 
     * @param aSession
     *            to access the hibernate layer.
     */
    protected abstract void cleanUpData( Session aSession );

    /**
     * This method allows you to create additional dependent objects in the database
     * 
     * @param aSession
     *            to actually create the objects.
     */
    protected void createDependentObjectsForTest( Session aSession ) {}

    /**
     * Create a template key object for the tests.
     * 
     * @return a Key that relates to the entity
     */
    protected abstract K createKeyTemplate();

    /**
     * Create a peristent entity for the tests.
     * 
     * @param aKey
     *            the key for the entity.
     * @param aSession
     *            so that other depdendent objects can be looked up
     * @return a persistent entity
     */
    protected abstract E createEntityTemplate( K aKey, Session aSession );

    /**
     * This is needed so that we can delegate down to the implementing test to perform the underlying update
     * to the persisted entity. This is used in the update test (CRUD).
     */
    protected abstract void updateEntityForUpdateTest( E aEntity );

    /**
     * Creates the HQL string for retrieval of the persisted entities.
     * 
     * @return the HQL string that will retrieve the entities for the test verification.
     */
    protected abstract String createEntitySearchHql();

    /**
     * Over loadable method that allows us to perform per validation activities.
     * 
     * @param aSession
     *            the session from which you can (if needed) get access to the database
     * @param aExpected
     *            the expected result
     * @param aResult
     *            the actualt result
     */
    protected void doInitialVerificationForCreateTest( Session aSession, E aExpected, E aResult ) {}

    @Test(timeout = TEST_TIMEOUT)
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.info( "Testing the create CRUD operation ..." );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = null;

            @Override
            public void beforeTest( Session aSession ) {
                createDependentObjectsForTest( aSession );
                entity = createEntityTemplate( key, aSession );
                verifyEntityCount( aSession, 0L );
            }

            @Override
            public void executeTest( Session aSession ) {
                LOG.debug( "Persiting: " + entity );
                aSession.save( entity );
                LOG.debug( "Entity now saved into the db ... good" );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest( Session aSession ) {
                verifyEntityCount( aSession, 1L );
                E result = (E) aSession.createQuery( createEntitySearchHql() ).uniqueResult();
                doInitialVerificationForCreateTest( aSession, entity, result );
                verifyResult( entity, result );
            }
        } );
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     * 
     * @param aSession
     *            the session from which you can (if needed) get access to the database
     * @param aExpected
     *            the expected result
     * @param aResult
     *            the actualt result
     */
    protected void doInitialVerificationForReadTest( Session aSession, E aExpected, E aResult ) {}

    @Test(timeout = TEST_TIMEOUT)
    public void basicReadOperationsInstantiatesCorrectObject() {
        LOG.info( "Testing the read CRUD operation ..." );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = null;
            E readEntity = null;

            @Override
            public void beforeTest( Session aSession ) {
                createDependentObjectsForTest( aSession );
                entity = createEntityTemplate( key, aSession );
                verifyEntityCount( aSession, 0L );

                aSession.save( entity );
                verifyEntityCount( aSession, 1L );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest( Session aSession ) {
                if ( key != null ) {
                    readEntity = (E) aSession.get( entity.getClass(), key );
                }
            }

            @Override
            public void verifyTest( Session aSession ) {
                if ( key != null ) {
                    verifyEntityCount( aSession, 1L );
                    doInitialVerificationForReadTest( aSession, readEntity, entity );
                    verifyResult( entity, readEntity );
                }
            }

        } );
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     * 
     * @param aSession
     *            the session from which you can (if needed) get access to the database
     * @param aExpected
     *            the expected result
     * @param aResult
     *            the actualt result
     */
    protected void doInitialVerificationForUpdateTest( Session aSession, E aExpected, E aResult ) {}

    @Test(timeout = TEST_TIMEOUT)
    public void basicUpdateOperationsUpdatesCorrectObject() {
        LOG.info( "Testing the update CRUD operation ..." );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = null;
            E clone = null;

            @Override
            public void beforeTest( Session aSession ) {
                createDependentObjectsForTest( aSession );
                entity = createEntityTemplate( key, aSession );
                clone = createEntityTemplate( key, aSession );
                verifyEntityCount( aSession, 0L );

                aSession.save( entity );
                verifyEntityCount( aSession, 1L );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest( Session aSession ) {
                entity = (E) aSession.createQuery( createEntitySearchHql() ).uniqueResult();
                updateEntityForUpdateTest( entity );
                aSession.save( entity );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest( Session aSession ) {
                entity = (E) aSession.createQuery( createEntitySearchHql() ).uniqueResult();
                doInitialVerificationForUpdateTest( aSession, clone, entity );
                assertThat( entity, not( nullValue() ) );
                assertThat( entity, not( sameInstance( clone ) ) );
                assertThat( entity, not( equalTo( clone ) ) );
            }
        } );
    }

    /**
     * Over loadable method that allows us to perform per validation activities.
     * 
     * @param aSession
     *            the session from which you can (if needed) get access to the database
     * @param aDeleted
     *            the actual deleted entity.
     */
    protected void doInitialVerificationForDeleteTest( Session aSession, E aDeleted ) {}

    @Test(timeout = TEST_TIMEOUT)
    public void basicDeleteOperationsDeletesCorrectObject() {
        LOG.info( "Testing the delete CRUD operation ..." );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = null;

            @Override
            public void beforeTest( Session aSession ) {
                createDependentObjectsForTest( aSession );
                entity = createEntityTemplate( key, aSession );
                verifyEntityCount( aSession, 0L );

                aSession.save( entity );
                verifyEntityCount( aSession, 1L );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void executeTest( Session aSession ) {
                E entityToDelete = (E) aSession.createQuery( createEntitySearchHql() ).uniqueResult();
                aSession.delete( entityToDelete );
            }

            @Override
            public void verifyTest( Session aSession ) {
                doInitialVerificationForDeleteTest( aSession, entity );
                verifyEntityCount( aSession, 0L );
            }
        } );
    }

    protected void verifyEntityCount( Session aSession, long aCountOfEntities ) {
        Long count = (Long) aSession.createQuery( "select count(*) " + createEntitySearchHql() )
            .uniqueResult();
        assertThat( count, equalTo( Long.valueOf( aCountOfEntities ) ) );
        LOG.debug( aCountOfEntities + " rows in the database ... good" );
    }

    protected void verifyResult( E expected, E result ) {
        assertThat( result, not( nullValue() ) );
        assertThat( result, not( sameInstance( expected ) ) );
        assertThat( result, equalTo( expected ) );
        LOG.debug( "Objects of type [" + result.getClass().getSimpleName() + "] match up ... good" );
        LOG.debug( "Object debug:" + result );
    }

    // =====================================

    /**
     * Executes the called back around a transaction.
     * 
     * @param executable
     *            the callback that wraps up the execution.
     */
    private void executeInTransaction( TransactionExecutable executable ) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            executable.execute( session );
            transaction.commit();
        }
        catch ( Exception e ) {
            LOG.warn( "Exception: " + e.getMessage() );
            transaction.rollback();
            Assert.fail( "Failed to execute in transaction" );
        }
        finally {
            session.close();
        }
    }

    /**
     * Provides a basic framework for running a Hibernate integration test against a database.
     * 
     * @param aCallback
     */
    protected void runGenericTest( HibernateIntegrationTestCallback aCallback ) {
        Session session = sessionfactory.openSession();

        Transaction trans = session.beginTransaction();
        try {
            LOG.debug( "--> Before Test" );
            aCallback.beforeTest( session );
            trans.commit();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            trans.rollback();
            session.close();
            Assert.fail( "Exception caught in 'beforeTest' execution, transaction rolled back" );
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug( "--> Execute Test" );
            aCallback.executeTest( session );
            trans.commit();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            trans.rollback();
            session.close();
            Assert.fail( "Exception caught in 'executeTest' execution, transaction rolled back" );
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug( "--> Verify Test" );
            aCallback.verifyTest( session );
            trans.commit();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            trans.rollback();
            session.close();
            Assert.fail( "Exception caught in 'verifyTest' execution, transaction rolled back" );
        }

        session.close();
        LOG.debug( "----------------------------------" );
    }

    /**
     * Template to allow us to execute something within the context of a transaction.
     * 
     * @author suggitpe
     * @version 1.0 25 Mar 2010
     */
    protected interface TransactionExecutable {

        /**
         * Execution callback.
         * 
         * @param aSession
         */
        void execute( Session aSession );
    }
}
