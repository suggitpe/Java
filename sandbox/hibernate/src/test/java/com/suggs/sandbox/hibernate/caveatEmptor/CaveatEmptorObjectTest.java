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

import org.hibernate.SessionFactory;
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

    public void testMe()
    {
        LOG.info( "----------------------------------- testMe start" );
        LOG.info( "----------------------------------- testMe end" );
    }

}
