/*
 * AbstractHibernateSpringTest.java created on 19 Apr 2007 18:27:36 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.support;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class AbstractHibernateSpringTest extends AbstractDependencyInjectionSpringContextTests
{

    private static final Log LOG = LogFactory.getLog( AbstractHibernateSpringTest.class );
    private SessionFactory sessionFactory_;

    /**
     * This allows any derived classses to define the hbm files that
     * are required for a particular test.
     * 
     * @return string array of hibernate files names
     */
    protected abstract String[] getHibernateMapFilenames();

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#onSetUp()
     */
    protected void onSetUp() throws Exception
    {
        Configuration cfg = new Configuration().configure( "hibernate.cfg.xml" );
        String[] files = getHibernateMapFilenames();
        for ( int i = 0; i < files.length; ++i )
        {
            cfg.addResource( files[i] );
        }

        setSessionFactory( cfg.buildSessionFactory() );
    }

    /**
     * Constructs a new instance.
     */
    public AbstractHibernateSpringTest()
    {
        super();
        setPopulateProtectedVariables( true );
    }

    /**
     * This will clean up all of the tables that
     * 
     * @param aClassList
     */
    private void cleanTestTables( Class[] aClassList )
    {
        Session s = getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        for ( int i = 0; i < aClassList.length; ++i )
        {
            LOG.debug( "Cleaning table [" + aClassList[i].getSimpleName() + "]" );
            Criteria c = s.createCriteria( aClassList[i] );
            List l = c.list();
            for ( Iterator iter = l.iterator(); iter.hasNext(); )
            {
                s.delete( iter.next() );
            }
        }

        t.commit();
        s.close();
    }

    /**
     * This is the core test impl that will allow us to wrap up the
     * session and transaction to a single impl
     * 
     * @param testMetaData
     *            the test metadata impl including the name and core
     *            test impl that will be called by this method.
     */
    protected void runTest( TestCallback testMetaData )
    {

        String testName = testMetaData.getTestName();
        LOG.debug( "---------- " + testName + " cleaning class tables for tests" );
        cleanTestTables( testMetaData.getClassesForCleaning() );

        LOG.debug( "---------- " + testName + " preTest" );
        Session sess = sessionFactory_.openSession();
        Transaction tran = sess.beginTransaction();

        testMetaData.preTestSetup( sess );

        sess.flush();

        LOG.info( "---------- " + testName + " start" );
        testMetaData.runTest( sess );

        tran.commit();
        sess.close();
        LOG.info( "---------- " + testName + " end" );
        LOG.info( "=============================================================" );
    }

    /**
     * This is a simple interface to allow you to drop in a test to a
     * defined hibernate transaction.
     * 
     * @author suggitpe
     * @version 1.0 19 Apr 2007
     */
    protected interface TestCallback
    {

        /**
         * This method is used to get a collection of classes so that
         * we can clean down any tables used in the test.
         * 
         * @return an array of classes
         */
        Class[] getClassesForCleaning();

        /**
         * This is called by a test execution so that they can set up
         * any pre test activities such as persisting an object to the
         * database
         * 
         * @param aSession
         *            a session to the database
         */
        void preTestSetup( Session aSession );

        /**
         * This is used by the defining impl to allow the test name to
         * be shown
         * 
         * @return the test name
         */
        String getTestName();

        /**
         * This is the core test interface. Here we pass in a session
         * object so that we can get access to the persistent layer.
         * 
         * @param aTrans
         */
        void runTest( Session aSession );

    }

    /**
     * getter for the session factory
     * 
     * @return the session factory
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory_;
    }

    /**
     * Setter for the session factory
     * 
     * @param aFactory
     *            the factor to set
     */
    public void setSessionFactory( SessionFactory aFactory )
    {
        sessionFactory_ = aFactory;
    }

}
