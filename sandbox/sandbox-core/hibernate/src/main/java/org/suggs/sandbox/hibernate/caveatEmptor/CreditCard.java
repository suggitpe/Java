/*
 * CreditCard.java created on 22 Mar 2007 17:51:24 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Hibernate object for a credit card
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_CREDIT_CARD")
public class CreditCard extends BillingDetails {

    @Column(name = "CC_TYPE", nullable = false, updatable = false)
    private Integer type;

    @Column(name = "CC_EXP_MONTH", nullable = false, length = 2)
    private String expMonth;

    @Column(name = "CC_EXP_YEAR", nullable = false, length = 4)
    private String expYear;

    /**
     * Constructs a new instance.
     */
    public CreditCard() {
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
    public CreditCard( String aOwner, String aNumber, Date aCreateDate, User aUser, Integer aType,
                       String aExpMonth, String aExpYear ) {
        super( aOwner, aNumber, aCreateDate, aUser );
        type = aType;
        expMonth = aExpMonth;
        expYear = aExpYear;
    }

    /**
     * getter for the expiry month
     * 
     * @return the expiry month
     */
    public String getExpMonth() {
        return expMonth;
    }

    /**
     * setter for the expiry month
     * 
     * @param aExpMonth
     *            the month to set
     */
    public void setExpMonth( String aExpMonth ) {
        expMonth = aExpMonth;
    }

    /**
     * getter for the expiry year
     * 
     * @return the year of expiry
     */
    public String getExpYear() {
        return expYear;
    }

    /**
     * setter for the year of expiry
     * 
     * @param aExpYear
     *            the year that the card expires
     */
    public void setExpYear( String aExpYear ) {
        expYear = aExpYear;
    }

    /**
     * getter for the type of card
     * 
     * @return the type of card
     */
    public Integer getType() {
        return type;
    }

    /**
     * setter for the type of card
     * 
     * @param aType
     *            the type to set
     */
    public void setType( Integer aType ) {
        type = aType;
    }
}
