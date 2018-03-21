/*
 * Bid.java created on 22 Mar 2007 17:49:47 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "CE_BID")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BID_SEQ")
@SuppressWarnings("unused")
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

    public Bid() {
        created = Calendar.getInstance().getTime();
    }

    public Bid(Double aAmount, Item aItem, User aBidder) {
        created = Calendar.getInstance().getTime();
        amount = aAmount;
        item = aItem;
        bidder = aBidder;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double aAmount) {
        amount = aAmount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date aCreated) {
        created = aCreated;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item aItem) {
        item = aItem;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User aBidder) {
        bidder = aBidder;
    }
}
