/*
 * CaveatEmptorObjectTest.java created on 13 Apr 2007 19:33:17 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.util.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CaveatEmptorObjectTest extends AbstractDependencyInjectionSpringContextTests implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( CaveatEmptorObjectTest.class );
    protected SessionFactory sessionFactory_;

    /**
     * Constructs a new instance.
     */
    public CaveatEmptorObjectTest()
    {
        super();
        setPopulateProtectedVariables( true );
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
     */
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-caveat-emptor-objects.xml" };
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( sessionFactory_, "Must initialise an instance of session factory for this test" );
    }

    /**
     * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#onSetUp()
     */
    protected void onSetUp() throws Exception
    {
        LOG.debug( "running on setup" );
        Configuration cfg = new Configuration();
        cfg.addResource( "hbm/manual/billing-details.hbm.xml" );
        cfg.addResource( "hbm/manual/credit-card.hbm.xml" );
        cfg.addResource( "hbm/manual/bank-account.hbm.xml" );
        cfg.addResource( "hbm/manual/bid.hbm.xml" );
        cfg.addResource( "hbm/manual/category.hbm.xml" );
        cfg.addResource( "hbm/manual/comment.hbm.xml" );
        cfg.addResource( "hbm/manual/item.hbm.xml" );
        cfg.addResource( "hbm/manual/user.hbm.xml" );
        sessionFactory_ = cfg.buildSessionFactory();
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

    /**
     * This is a simple interface to allow you to drop in a test to a
     * defined hibernate transaction.
     * 
     * @author suggitpe
     * @version 1.0 19 Apr 2007
     */
    private interface TestCallback
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
    private void runTest( TestCallback testMetaData )
    {
        LOG.info( "----------------------------------- " + testMetaData.getTestName() + " start" );
        Session sess = sessionFactory_.openSession();
        Transaction tran = sess.beginTransaction();

        testMetaData.runTest( sess );

        tran.commit();
        sess.close();
        LOG.info( "----------------------------------- " + testMetaData.getTestName() + " end" );
    }

    public void testCreateNewCreditCard()
    {
        runTest( new TestCallback()
        {

            public String getTestName()
            {
                return "testCreateNewCreditCard";
            }

            public void runTest( Session aSession )
            {
                
            }
        } );
    }

}
