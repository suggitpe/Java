/*
 * AbstractSimpleHibernateIntegrationTest.java created on 25 Mar 2010 07:06:15 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.support;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    @Before
    public void setUp() {
        executeInTransaction( new TransactionExecutable() {

            @Override
            public void execute( Session aSession ) {
                cleanUpData( aSession );
            }
        } );
    }

    protected abstract void cleanUpData( Session aSession );

    private void executeInTransaction( TransactionExecutable executable ) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.beginTransaction();
        executable.execute( session );
        transaction.commit();
    }

    protected void runGenericTest( HibernateTestCallback aCallback ) {
        LOG.debug( "----------------------------------" );
        Session session = sessionfactory.openSession();

        Transaction trans = session.beginTransaction();
        aCallback.beforeTest( session );
        trans.commit();

        session.clear();

        trans.begin();
        aCallback.executeTest( session );
        trans.commit();

        session.clear();

        trans.begin();
        aCallback.verifyTest( session );
        trans.commit();

        session.close();
        LOG.debug( "----------------------------------" );
    }

    /**
     * Interface for a callback to be used for testing Hibernate
     * objects with segregated transactional boundaries. the really
     * neat thing about this is that it allows you to pass the impl of
     * the tests around and manage the actual execution a little
     * later.
     * 
     * @author suggitpe
     * @version 1.0 25 Mar 2010
     */
    protected interface HibernateTestCallback {

        /**
         * Run before the test exceution.
         */
        void beforeTest( Session aSession );

        /**
         * Execute the actual test.
         */
        void executeTest( Session aSession );

        /**
         * Verify the test results.
         * 
         * @param aSession
         */
        void verifyTest( Session aSession );
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
