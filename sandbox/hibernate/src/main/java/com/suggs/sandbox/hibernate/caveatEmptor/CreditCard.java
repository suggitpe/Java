/*
 * CreditCard.java created on 22 Mar 2007 17:51:24 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODO Write javadoc for CreditCard
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_CREDIT_CARD")
public class CreditCard extends BillingDetails
{

    private Integer mType_;
    private String mExpMonth_;
    private String mExpYear_;

    /**
     * Constructs a new instance.
     */
    public CreditCard()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOwner
     * @param aNumber
     * @param aCreateDate
     * @param aUser
     * @param aType
     * @param aExpMonth
     * @param aExpYear
     */
    public CreditCard( String aOwner, String aNumber, Date aCreateDate, User aUser, Integer aType, String aExpMonth, String aExpYear )
    {
        super( aOwner, aNumber, aCreateDate, aUser );
        mType_ = aType;
        mExpMonth_ = aExpMonth;
        mExpYear_ = aExpYear;
    }

    /**
     * getter for the expiry month
     * 
     * @return the expiry month
     */
    @Column(name = "CC_EXP_MONTH", nullable = false, length = 2)
    public String getExpMonth()
    {
        return mExpMonth_;
    }

    /**
     * setter for the expiry month
     * 
     * @param expMonth
     *            the month to set
     */
    public void setExpMonth( String expMonth )
    {
        mExpMonth_ = expMonth;
    }

    /**
     * getter for the expiry year
     * 
     * @return the year of expiry
     */
    @Column(name = "CC_EXP_YEAR", nullable = false, length = 4)
    public String getExpYear()
    {
        return mExpYear_;
    }

    /**
     * setter for the year of expiry
     * 
     * @param expYear
     *            the year that the card expires
     */
    public void setExpYear( String expYear )
    {
        mExpYear_ = expYear;
    }

    /**
     * getter for the type of card
     * 
     * @return the type of card
     */
    @Column(name = "CC_TYPE", nullable = false, updatable = false)
    public Integer getType()
    {
        return mType_;
    }

    /**
     * setter for the type of card
     * 
     * @param type
     *            the type to set
     */
    public void setType( Integer type )
    {
        mType_ = type;
    }
}
