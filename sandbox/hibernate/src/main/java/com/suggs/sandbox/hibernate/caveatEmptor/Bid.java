/*
 * Bid.java created on 22 Mar 2007 17:49:47 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CE_BID")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BID_SEQ")
public class Bid extends AbstractPersistentBaseClass
{

    private Double mAmount_;
    private Date mCreated_;
    private Item mItem_;
    private User mBidder_;

    /**
     * Constructs a new instance.
     */
    public Bid()
    {
        super();
        mCreated_ = Calendar.getInstance().getTime();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aAmount
     * @param aItem
     * @param aBidder
     */
    public Bid( Double aAmount, Item aItem, User aBidder )
    {
        super();
        mCreated_ = Calendar.getInstance().getTime();
        mAmount_ = aAmount;
        mItem_ = aItem;
        mBidder_ = aBidder;
    }

    /**
     * getter for the bid amount
     * 
     * @return the bid amount
     */
    @Column(name = "BID_AMOUNT")
    public Double getAmount()
    {
        return mAmount_;
    }

    /**
     * setter for the bid amount
     * 
     * @param amount
     *            the amount of the bid
     */
    public void setAmount( Double amount )
    {
        mAmount_ = amount;
    }

    /**
     * getter for the created timestamp
     * 
     * @return the created timestamp
     */
    @Column(name = "BID_CREATED")
    public Date getCreated()
    {
        return mCreated_;
    }

    /**
     * setter for the created timestamp
     * 
     * @param created
     *            the timestamp that the bid was created
     */
    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    /**
     * getter for the item that this bid relates to
     * 
     * @return the bid that this item relates to
     */
    @ManyToOne
    @JoinColumn(name = "BID_ITEM_ID")
    public Item getItem()
    {
        return mItem_;
    }

    /**
     * setter for the item
     * 
     * @param item
     *            the item to set
     */
    public void setItem( Item item )
    {
        mItem_ = item;
    }

    /**
     * Getter for the user
     * 
     * @return the bidder user details
     */
    @ManyToOne
    @JoinColumn(name = "BID_USER_ID")
    public User getBidder()
    {
        return mBidder_;
    }

    /**
     * setter for the bidder
     * 
     * @param bidder
     *            the bidder to set
     */
    public void setBidder( User bidder )
    {
        mBidder_ = bidder;
    }
}
