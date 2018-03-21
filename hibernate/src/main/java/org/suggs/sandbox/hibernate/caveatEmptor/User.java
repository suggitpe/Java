/*
 * User.java created on 21 Mar 2007 17:37:14 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "CE_USER")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_USER_SEQ")
@SuppressWarnings("unused")
public class User extends AbstractPersistentBaseClass {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Column(name = "USER_FIRST_NAME", nullable = false, length = 64)
    private String firstName;

    @Column(name = "USER_LAST_NAME", nullable = false, length = 64)
    private String lastName;

    @Column(name = "USER_USERNAME", length = 10, nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "USER_PASSWORD", nullable = false, length = 16)
    private String password;

    @Column(name = "USER_EMAIL", nullable = false, length = 64)
    private String email;

    @Column(name = "USER_RANKING")
    private Integer ranking;

    @Column(name = "USER_CREATED", nullable = false, updatable = false)
    private Date created;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "street", column = @Column(name = "USER_HOME_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "USER_HOME_CITY")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "USER_HOME_ZIPCODE"))})
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "USER_BILLING_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "USER_BILLING_CITY")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "USER_BILLING_ZIPCODE"))})
    private Address billingAddress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BillingDetails> billingDetails = new HashSet<BillingDetails>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Set<Item> itemsForSale = new HashSet<Item>();

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY)
    private Set<Bid> bids = new HashSet<Bid>();

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<Comment>();

    public User() {
        created = Calendar.getInstance().getTime();
    }

    public User(String aFirstName, String aLastName, String aUserName, String aPassword, String aEmail,
                Address aHomeAddress, Address aBillingAddress) {
        firstName = aFirstName;
        lastName = aLastName;
        username = aUserName;
        password = aPassword;
        email = aEmail;
        ranking = 0;
        created = Calendar.getInstance().getTime();
        homeAddress = aHomeAddress;
        if (aBillingAddress == null) {
            LOG.info("Using home address as billing address as default");
            billingAddress = aHomeAddress;
        } else {
            billingAddress = aBillingAddress;
        }

    }

    public Set<BillingDetails> getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(Set<BillingDetails> aBillingDetails) {
        billingDetails = aBillingDetails;
    }

    public void addBillingDetails(BillingDetails aBillingDetails) {
        if (aBillingDetails == null) {
            throw new IllegalArgumentException("aBillingDetails is null");
        }
        billingDetails.add(aBillingDetails);
    }

    public Set<Item> getItemsForSale() {
        return itemsForSale;
    }

    public void setItemsForSale(Set<Item> aItems) {
        itemsForSale = aItems;
    }

    public void addItemForSale(Item aItem) {
        if (aItem == null) {
            throw new IllegalArgumentException("aItem is null");
        }
        aItem.setSeller(this);
        itemsForSale.add(aItem);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> aComments) {
        comments = aComments;
    }

    public void addComment(Comment aComment) {
        if (aComment == null) {
            throw new IllegalArgumentException("aComment is null");
        }
        aComment.setFromUser(this);
        comments.add(aComment);
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> aBids) {
        bids = aBids;
    }

    public void addBid(Bid aBid) {
        if (aBid == null) {
            throw new IllegalArgumentException("aBid is null");
        }
        aBid.setBidder(this);
        bids.add(aBid);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String aName) {
        firstName = aName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String aName) {
        lastName = aName;
    }

    @Transient
    public String getName() {
        return firstName + " " + lastName;
    }

    public void setName(String aName) {
        StringTokenizer t = new StringTokenizer(aName);
        firstName = t.nextToken();
        lastName = t.nextToken();
    }

    /**
     * getter for the username
     *
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username
     *
     * @param aName the username
     */
    public void setUsername(String aName) {
        username = aName;
    }

    /**
     * Getter for the home address object
     *
     * @return the home address
     */
    public Address getHomeAddress() {
        return homeAddress;
    }

    /**
     * setter for the home address
     *
     * @param aAddress the address to set
     */
    public void setHomeAddress(Address aAddress) {
        homeAddress = aAddress;
    }

    /**
     * Getter for tjhe billing address object
     *
     * @return the billing address
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * setter for the billing address
     *
     * @param aAddress the address to set
     */
    public void setBillingAddress(Address aAddress) {
        billingAddress = aAddress;
    }

    /**
     * getter for the creation date
     *
     * @return the date that the user was created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * setter for the creation date
     *
     * @param aCreated the date that the user was created
     */
    protected void setCreated(Date aCreated) {
        created = aCreated;
    }

    /**
     * getter for the user email
     *
     * @return the user email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for the uiser email address
     *
     * @param aEmail the email to set
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * getter for the user password
     *
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for the user password
     *
     * @param aPassword the user password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * getter for the user rank
     *
     * @return the user rank
     */
    public Integer getRanking() {
        return ranking;
    }

    /**
     * setter for the user rank
     *
     * @param aRanking the rank to set
     */
    public void setRanking(Integer aRanking) {
        ranking = aRanking;
    }
}
