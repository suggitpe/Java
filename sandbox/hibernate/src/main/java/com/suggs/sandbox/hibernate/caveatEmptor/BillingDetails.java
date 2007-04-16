/*
 * BillingDetails.java created on 22 Mar 2007 17:50:42 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BillingDetails extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( BillingDetails.class );

    private String mOwner_;
    private String mNumber_;
    private Date mCreated_;

    public BillingDetails()
    {
        super();
        LOG.debug( "Creating a new Billing Details" );
    }

    public Date getCreated()
    {
        return mCreated_;
    }

    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    public String getNumber()
    {
        return mNumber_;
    }

    public void setNumber( String number )
    {
        mNumber_ = number;
    }

    public String getOwner()
    {
        return mOwner_;
    }

    public void setOwner( String owner )
    {
        mOwner_ = owner;
    }
}
