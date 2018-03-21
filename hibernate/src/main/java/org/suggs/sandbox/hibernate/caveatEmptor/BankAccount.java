/*
 * BankAccount.java created on 22 Mar 2007 17:51:48 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CE_BANK_ACCOUNT")
@SuppressWarnings("unused")
public class BankAccount extends BillingDetails {

    @Column(name = "BANK_NAME", nullable = false, length = 128)
    private String bankName;

    @Column(name = "BANK_SWIFT", nullable = false, length = 12)
    private String bankSwift;

    public BankAccount() {
    }

    public BankAccount(String aOwner, String aNumber, Date aCreateDate, User aUser, String aBankName,
                       String aBankSwift) {
        super(aOwner, aNumber, aCreateDate, aUser);
        bankName = aBankName;
        bankSwift = aBankSwift;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String aBankName) {
        bankName = aBankName;
    }

    public String getBankSwift() {
        return bankSwift;
    }

    public void setBankSwift(String aBankSwift) {
        bankSwift = aBankSwift;
    }
}
