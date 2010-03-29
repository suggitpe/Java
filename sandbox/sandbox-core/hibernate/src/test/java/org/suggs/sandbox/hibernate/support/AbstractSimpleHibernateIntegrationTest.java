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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * TODO Write javadoc for AbstractSimpleHibernateIntegrationTest
 * 
 * @author suggitpe
 * @version 1.0 25 Mar 2010
 */
public abstract class AbstractSimpleHibernateIntegrationTest {

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
     * Perform a collection of cleanup operations within a dedicated
     * transaction prior to any tests being run.
     * 
     * @param aSession
     *            to access the hibernate layer.
     */
    protected abstract void cleanUpData( Session aSession );

    /**
     * Test to create the schema for the entities under test. This is
     * a helper method to make life easier all round.
     */
    @Test
    public void createSchema() {
        LOG.debug( "Creating schema for class under test" );
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        for ( Class<?> clazz : getEntityList() ) {
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
    protected abstract List<Class<?>> getEntityList();

    @Test
    public void basicCreateOperationCreatesCorrectObject() {
        LOG.debug( "basicCreateOperationCreatesCorrectObject" );
        runGenericTest( createBasicCreateTest() );
    }

    protected abstract IHibernateIntegrationTestCallback createBasicCreateTest();

    @Test
    public void basicReadOperationsInstantiatesCorrectObject() {
        LOG.debug( "basicReadOperationsInstantiatesCorrectObject" );
        runGenericTest( createBasicReadTest() );
    }

    protected abstract IHibernateIntegrationTestCallback createBasicReadTest();

    @Test
    public void basicUpdateOperationsUpdatesCorrectObject() {
        LOG.debug( "basicUpdateOperationsUpdatesCorrectObject" );
        runGenericTest( createBasicUpdateTest() );
    }

    protected abstract IHibernateIntegrationTestCallback createBasicUpdateTest();

    @Test
    public void basicDeleteOperationsDeletesCorrectObject() {
        LOG.debug( "basicDeleteOperationsUpdatesCorrectObject" );
        runGenericTest( createBasicUpdateTest() );
    }

    protected abstract IHibernateIntegrationTestCallback createBasicDeleteTest();

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
    }

    /**
     * Provides a basic framework for running a Hibernate integration
     * test against a database.
     * 
     * @param aCallback
     */
    protected void runGenericTest( IHibernateIntegrationTestCallback aCallback ) {
        Session session = sessionfactory.openSession();

        Transaction trans = session.beginTransaction();
        try {
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
     * Template to allow us to execute something within the context of
     * a transaction.
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
