/*
 * BillingDetails.java created on 22 Mar 2007 17:50:42 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CE_BILLING_DETAILS")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_BILLING_DETAILS_SEQ")
@SuppressWarnings("unused")
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

    public BillingDetails() {
    }

    public BillingDetails(String aOwner, String aNumber, Date aCreatDate, User aUser) {
        owner = aOwner;
        number = aNumber;
        created = aCreatDate;
        user = aUser;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date aCreated) {
        created = aCreated;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String aNumber) {
        number = aNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String aOwner) {
        owner = aOwner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User aUser) {
        user = aUser;
    }
}
