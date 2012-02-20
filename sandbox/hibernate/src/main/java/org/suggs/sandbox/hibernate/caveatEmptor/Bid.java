/*
 * Bid.java created on 22 Mar 2007 17:49:47 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Class to represent a Bid
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_BID")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BID_SEQ")
public class Bid extends AbstractPersistentBaseClass {

    @Column(name = "BID_AMOUNT", nullable = false, updatable = false)
    private Double amount;

    @Column(name = "BID_CREATED", nullable = false, updatable = false)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "BID_ITEM_ID", nullable = false, updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "BID_USER_ID", nullable = false, updatable = false)
    private User bidder;

    /**
     * Constructs a new instance.
     */
    public Bid() {
        super();
        created = Calendar.getInstance().getTime();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aAmount
     * @param aItem
     * @param aBidder
     */
    public Bid( Double aAmount, Item aItem, User aBidder ) {
        super();
        created = Calendar.getInstance().getTime();
        amount = aAmount;
        item = aItem;
        bidder = aBidder;
    }

    /**
     * getter for the bid amount
     * 
     * @return the bid amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * setter for the bid amount
     * 
     * @param aAmount
     *            the amount of the bid
     */
    public void setAmount( Double aAmount ) {
        amount = aAmount;
    }

    /**
     * getter for the created timestamp
     * 
     * @return the created timestamp
     */
    public Date getCreated() {
        return created;
    }

    /**
     * setter for the created timestamp
     * 
     * @param aCreated
     *            the timestamp that the bid was created
     */
    public void setCreated( Date aCreated ) {
        created = aCreated;
    }

    /**
     * getter for the item that this bid relates to
     * 
     * @return the bid that this item relates to
     */
    public Item getItem() {
        return item;
    }

    /**
     * setter for the item
     * 
     * @param aItem
     *            the item to set
     */
    public void setItem( Item aItem ) {
        item = aItem;
    }

    /**
     * Getter for the user
     * 
     * @return the bidder user details
     */
    public User getBidder() {
        return bidder;
    }

    /**
     * setter for the bidder
     * 
     * @param aBidder
     *            the bidder to set
     */
    public void setBidder( User aBidder ) {
        bidder = aBidder;
    }
}
