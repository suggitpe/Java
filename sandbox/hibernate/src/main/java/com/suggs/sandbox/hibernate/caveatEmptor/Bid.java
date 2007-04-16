/*
 * Bid.java created on 22 Mar 2007 17:49:47 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Bid extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( Bid.class );

    private Double mAmount_;
    private Date mCreated_;
    private Item mItem_;
    private User mBidder_;

    public Bid()
    {
        super();
        LOG.debug( "Creating a new Bid" );
    }

    public Double getAmount()
    {
        return mAmount_;
    }

    public void setAmount( Double amount )
    {
        mAmount_ = amount;
    }

    public Date getCreated()
    {
        return mCreated_;
    }

    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    public Item getItem()
    {
        return mItem_;
    }

    public void setItem( Item item )
    {
        mItem_ = item;
    }

    
    public User getBidder()
    {
        return mBidder_;
    }

    
    public void setBidder( User bidder )
    {
        mBidder_ = bidder;
    }
}
