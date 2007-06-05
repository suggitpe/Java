/*
 * BillingDetails.java created on 22 Mar 2007 17:50:42 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CE_BILLING_DETAILS")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BILLING_DETAILS_SEQ")
public abstract class BillingDetails extends AbstractPersistentBaseClass
{

    private String mOwner_;
    private String mNumber_;
    private Date mCreated_;
    private User mUser_;

    /**
     * Constructs a new instance.
     */
    public BillingDetails()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOwner
     * @param aNumber
     * @param aCreatDate
     */
    public BillingDetails( String aOwner, String aNumber, Date aCreatDate, User aUser )
    {
        super();

        mOwner_ = aOwner;
        mNumber_ = aNumber;
        mCreated_ = aCreatDate;
        mUser_ = aUser;
    }

    /**
     * getter for created timestamp
     * 
     * @return the created timestamp
     */
    @Column(name = "BILLING_CREATED", nullable = false, updatable = false)
    public Date getCreated()
    {
        return mCreated_;
    }

    /**
     * Setter for the created timestamp
     * 
     * @param created
     *            the timestamp that the object was created
     */
    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    /**
     * getter for the number
     * 
     * @return the number
     */
    @Column(name = "BILLING_NUMBER", nullable = false, length = 32)
    public String getNumber()
    {
        return mNumber_;
    }

    /**
     * setter for the number
     * 
     * @param number
     *            the number to set
     */
    public void setNumber( String number )
    {
        mNumber_ = number;
    }

    /**
     * getter for the owner
     * 
     * @return the owner
     */
    @Column(name = "BILLING_OWNER", nullable=false, length=128)
    public String getOwner()
    {
        return mOwner_;
    }

    /**
     * setter for owner
     * 
     * @param owner
     *            the owner to set
     */
    public void setOwner( String owner )
    {
        mOwner_ = owner;
    }

    /**
     * getter for the user
     * 
     * @return the user
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILLING_USER_ID")
    public User getUser()
    {
        return mUser_;
    }

    /**
     * setter for the user
     * 
     * @param aUser
     *            the user to set
     */
    public void setUser( User aUser )
    {
        mUser_ = aUser;
    }
}
