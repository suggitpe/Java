/*
 * BillingDetails.java created on 22 Mar 2007 17:50:42 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

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

/**
 * Class to represent a billing detail
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_BILLING_DETAILS")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BILLING_DETAILS_SEQ")
public abstract class BillingDetails extends AbstractPersistentBaseClass {

    @Column(name = "BILLING_OWNER", nullable = false, length = 128)
    private String owner;

    @Column(name = "BILLING_NUMBER", nullable = false, length = 32)
    private String number;

    @Column(name = "BILLING_CREATED", nullable = false, updatable = false)
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILLING_USER_ID")
    private User user;

    /**
     * Constructs a new instance.
     */
    public BillingDetails() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOwner
     * @param aNumber
     * @param aCreatDate
     * @param aUser
     */
    public BillingDetails( String aOwner, String aNumber, Date aCreatDate, User aUser ) {
        super();

        owner = aOwner;
        number = aNumber;
        created = aCreatDate;
        user = aUser;
    }

    /**
     * getter for created timestamp
     * 
     * @return the created timestamp
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Setter for the created timestamp
     * 
     * @param aCreated
     *            the timestamp that the object was created
     */
    public void setCreated( Date aCreated ) {
        created = aCreated;
    }

    /**
     * getter for the number
     * 
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * setter for the number
     * 
     * @param aNumber
     *            the number to set
     */
    public void setNumber( String aNumber ) {
        number = aNumber;
    }

    /**
     * getter for the owner
     * 
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setter for owner
     * 
     * @param aOwner
     *            the owner to set
     */
    public void setOwner( String aOwner ) {
        owner = aOwner;
    }

    /**
     * getter for the user
     * 
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * setter for the user
     * 
     * @param aUser
     *            the user to set
     */
    public void setUser( User aUser ) {
        user = aUser;
    }
}
