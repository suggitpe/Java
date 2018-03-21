/*
 * CreditCard.java created on 22 Mar 2007 17:51:24 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CE_CREDIT_CARD")
@SuppressWarnings("unused")
public class CreditCard extends BillingDetails {

    @Column(name = "CC_TYPE", nullable = false, updatable = false)
    private Integer type;

    @Column(name = "CC_EXP_MONTH", nullable = false, length = 2)
    private String expMonth;

    @Column(name = "CC_EXP_YEAR", nullable = false, length = 4)
    private String expYear;

    public CreditCard() {
    }

    public CreditCard(String aOwner, String aNumber, Date aCreateDate, User aUser, Integer aType,
                      String aExpMonth, String aExpYear) {
        super(aOwner, aNumber, aCreateDate, aUser);
        type = aType;
        expMonth = aExpMonth;
        expYear = aExpYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String aExpMonth) {
        expMonth = aExpMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String aExpYear) {
        expYear = aExpYear;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer aType) {
        type = aType;
    }
}
