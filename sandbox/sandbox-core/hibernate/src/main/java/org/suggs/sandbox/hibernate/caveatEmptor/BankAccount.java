/*
 * BankAccount.java created on 22 Mar 2007 17:51:48 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class to represent a bank account
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_BANK_ACCOUNT")
public class BankAccount extends BillingDetails {

    @Column(name = "BANK_NAME", nullable = false, length = 128)
    private String bankName;

    @Column(name = "BANK_SWIFT", nullable = false, length = 12)
    private String bankSwift;

    /**
     * Constructs a new instance.
     */
    public BankAccount() {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOwner
     *            owner from billing details
     * @param aNumber
     *            number from billing details
     * @param aCreateDate
     *            creation date of the bank account
     * @param aUser
     *            the user to whom the bank account is affiliated
     * @param aBankName
     *            the name of the bank
     * @param aBankSwift
     *            the dswift address of the bank
     */
    public BankAccount( String aOwner, String aNumber, Date aCreateDate, User aUser, String aBankName,
                        String aBankSwift ) {
        super( aOwner, aNumber, aCreateDate, aUser );
        bankName = aBankName;
        bankSwift = aBankSwift;

    }

    /**
     * getter for the bank name
     * 
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Setter for the bank name
     * 
     * @param aBankName
     *            the bank name to set
     */
    public void setBankName( String aBankName ) {
        bankName = aBankName;
    }

    /**
     * getter for the bank swift
     * 
     * @return the swift address
     */
    public String getBankSwift() {
        return bankSwift;
    }

    /**
     * setter for the bank swift address
     * 
     * @param aBankSwift
     *            the swift address
     */
    public void setBankSwift( String aBankSwift ) {
        bankSwift = aBankSwift;
    }
}
