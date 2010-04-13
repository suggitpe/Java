/*
 * AbstractSimpleHibernateIntegrationTest.java created on 25 Mar 2010 07:06:15 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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
public abstract class AbstractSimpleHibernateIntegrationTest<K, E> {

    private static final Log LOG = LogFactory.getLog( AbstractSimpleHibernateIntegrationTest.class );

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
     * Test to create the schema for the entities under test. This is a helper method to make life easier all
     * round.
     */
    @Test
    public void createSchema() {
        LOG.debug( "Creating schema for class under test" );
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        for ( Class<?> clazz : getEntityListForSchemaCreation() ) {
            cfg.addAnnotatedClass( clazz );
        }
        SchemaExport export = new SchemaExport( cfg );
        export.setDelimiter( ";" );
        export.create( true, false );
    }

    /**
     * Builds a collection of classes to create the schema for.
     * 
     * @return all classes to create a schema from.
     */
    protected abstract List<Class<?>> getEntityListForSchemaCreation();

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
     * @return a persistent entity
     */
    protected abstract E createEntityTemplate( K aKey );

    /**
     * Creates the HQL string for retrieval of the persisted entities.
     * 
     * @return the HQL string that will retrieve the entities for the test verification.
     */
    protected abstract String createEntitySearchHql();

    // -----------------
    @Test
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.debug( "basicCreateOperationCreatesCorrectObject" );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = createEntityTemplate( key );

            @Override
            public void beforeTest( Session aSession ) {
                verifyEntityCount( aSession, 0L );
            }

            @Override
            public void executeTest( Session aSession ) {
                aSession.save( entity );
            }

            @SuppressWarnings("unchecked")
            @Override
            public void verifyTest( Session aSession ) {
                verifyEntityCount( aSession, 1L );
                E result = (E) aSession.createQuery( createEntitySearchHql() ).uniqueResult();
                verifyResult( entity, result );
            }
        } );
    }

    // protected abstract HibernateIntegrationTestCallback createBasicCreateTest();

    // -----------------
    @Test
    public void basicReadOperationsInstantiatesCorrectObject() {
        runGenericTest( createBasicReadTest() );
    }

    protected abstract HibernateIntegrationTestCallback createBasicReadTest();

    // -----------------
    @Test
    public void basicUpdateOperationsUpdatesCorrectObject() {
        LOG.debug( "basicUpdateOperationsUpdatesCorrectObject" );
        runGenericTest( createBasicUpdateTest() );
    }

    protected abstract HibernateIntegrationTestCallback createBasicUpdateTest();

    // -----------------
    @Test
    public void basicDeleteOperationsDeletesCorrectObject() {
        LOG.debug( "basicDeleteOperationsUpdatesCorrectObject" );
        runGenericTest( new HibernateIntegrationTestCallback() {

            K key = createKeyTemplate();
            E entity = createEntityTemplate( key );

            @Override
            public void beforeTest( Session aSession ) {
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
                verifyEntityCount( aSession, 0L );
            }
        } );
    }

    // protected abstract HibernateIntegrationTestCallback createBasicDeleteTest();

    private void verifyEntityCount( Session aSession, long aCountOfEntities ) {
        Long count = (Long) aSession.createQuery( "select count(*) " + createEntitySearchHql() )
            .uniqueResult();
        assertThat( count, equalTo( Long.valueOf( aCountOfEntities ) ) );
    }

    public void verifyResult( E expected, E result ) {
        assertThat( result, not( nullValue() ) );
        assertThat( result, not( sameInstance( expected ) ) );
        assertThat( result, equalTo( expected ) );

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
        executable.execute( session );
        transaction.commit();
        session.close();
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
            trans.rollback();
            throw new IllegalStateException( "Exception caught in 'beforeTest' execution, transaction rolled back",
                                             e );
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug( "--> Execute Test" );
            aCallback.executeTest( session );
            trans.commit();
        }
        catch ( Exception e ) {
            trans.rollback();
            throw new IllegalStateException( "Exception caught in 'executeTest' execution, transaction rolled back",
                                             e );
        }

        session.clear();

        try {
            trans.begin();
            LOG.debug( "--> Verify Test" );
            aCallback.verifyTest( session );
            trans.commit();
        }
        catch ( Exception e ) {
            trans.rollback();
            throw new IllegalStateException( "Exception caught in 'verifyTest' execution, transaction rolled back",
                                             e );
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
