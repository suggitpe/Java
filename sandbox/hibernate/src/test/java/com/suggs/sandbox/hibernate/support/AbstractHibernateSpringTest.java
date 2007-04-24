/*
 * AbstractHibernateSpringTest.java created on 19 Apr 2007 18:27:36 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class AbstractHibernateSpringTest extends AbstractDependencyInjectionSpringContextTests implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( AbstractHibernateSpringTest.class );
    private SessionFactory sessionFactory_;

    /**
     * This method is used so that we can enforce the derived classes
     * to have a hook into the spring injection checks.
     * 
     * @throws Exception
     */
    protected abstract void doAfterPropertiesSet() throws Exception;

    /**
     * This allows any derived classses to define the hbm files that
     * are required for a particular test.
     * 
     * @return string array of hibernate files names
     */
    protected abstract String[] getHibernateMapFilenames();

    /**
     * This is used as an extension to the onSetup method call. The
     * onSetup call will create the configuration object and pass in
     * the hbm files to it. Onec this is done the doCleanUpOldData is
     * called that allows you to clean up the database. The theory
     * here is that we have configured up the hibernate env to allow
     * for the relevant objects.
     */
    protected abstract void doCleanUpOldData();

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( sessionFactory_, "Must initialise an instance of session factory for this test" );
        doAfterPropertiesSet();
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#onSetUp()
     */
    protected void onSetUp() throws Exception
    {
        LOG.debug( "running on setup" );
        Configuration cfg = new Configuration();
        String[] files = getHibernateMapFilenames();
        for ( int i = 0; i < files.length; ++i )
        {
            cfg.addResource( files[i] );
        }

        setSessionFactory( cfg.buildSessionFactory() );

        doCleanUpOldData();
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
     * This is a simple interface to allow you to drop in a test to a
     * defined hibernate transaction.
     * 
     * @author suggitpe
     * @version 1.0 19 Apr 2007
     */
    protected interface TestCallback
    {

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
     * This is the core test impl that will allow us to wrap up the
     * session and transaction to a single impl
     * 
     * @param testMetaData
     *            the test metadata impl including the name and core
     *            test impl that will be called by this method.
     */
    protected void runTest( TestCallback testMetaData )
    {
        LOG.info( "----------------------------------- " + testMetaData.getTestName() + " start" );
        Session sess = sessionFactory_.openSession();
        Transaction tran = sess.beginTransaction();

        testMetaData.runTest( sess );

        tran.commit();
        sess.close();
        LOG.info( "----------------------------------- " + testMetaData.getTestName() + " end" );
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
