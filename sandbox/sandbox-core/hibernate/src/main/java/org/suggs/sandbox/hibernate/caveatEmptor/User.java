/*
 * User.java created on 21 Mar 2007 17:37:14 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

/**
 * Hibernate object representing a user
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_USER")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_USER_SEQ")
public class User extends AbstractPersistentBaseClass {

    private static final Log LOG = LogFactory.getLog( User.class );

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
    @AttributeOverrides( { @AttributeOverride(name = "street", column = @Column(name = "USER_HOME_STREET")),
                          @AttributeOverride(name = "city", column = @Column(name = "USER_HOME_CITY")),
                          @AttributeOverride(name = "zipCode", column = @Column(name = "USER_HOME_ZIPCODE")) })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides( {
                          @AttributeOverride(name = "street", column = @Column(name = "USER_BILLING_STREET")),
                          @AttributeOverride(name = "city", column = @Column(name = "USER_BILLING_CITY")),
                          @AttributeOverride(name = "zipCode", column = @Column(name = "USER_BILLING_ZIPCODE")) })
    private Address billingAddress;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<BillingDetails> billingDetails = new HashSet<BillingDetails>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private Set<Item> itemsForSale = new HashSet<Item>();

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY)
    private Set<Bid> bids = new HashSet<Bid>();

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<Comment>();

    /**
     * Def ctor
     */
    public User() {
        super();
        created = Calendar.getInstance().getTime();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aFirstName
     *            the users first name
     * @param aLastName
     *            the users last name
     * @param aUserName
     *            the users username
     * @param aPassword
     *            the users password
     * @param aEmail
     *            the users email address
     * @param aHomeAddress
     *            the users home address
     * @param aBillingAddress
     *            the users billing address
     */
    public User( String aFirstName, String aLastName, String aUserName, String aPassword, String aEmail,
                 Address aHomeAddress, Address aBillingAddress ) {
        super();
        firstName = aFirstName;
        lastName = aLastName;
        Assert.notNull( aUserName, "Must set the username" );
        username = aUserName;
        password = aPassword;
        Assert.notNull( aEmail, "Must set the email address" );
        email = aEmail;
        ranking = new Integer( 0 );
        created = Calendar.getInstance().getTime();
        Assert.notNull( aHomeAddress, "Must set the home adress" );
        homeAddress = aHomeAddress;
        if ( aBillingAddress == null ) {
            LOG.info( "Using home address as billing address as default" );
            billingAddress = aHomeAddress;
        }
        else {
            billingAddress = aBillingAddress;
        }

    }

    /**
     * Getter for the billing details
     * 
     * @return the set of billing details
     */
    public Set<BillingDetails> getBillingDetails() {
        return billingDetails;
    }

    /**
     * setter for billing details
     * 
     * @param aBillingDetails
     *            the billing details set
     */
    public void setBillingDetails( Set<BillingDetails> aBillingDetails ) {
        billingDetails = aBillingDetails;
    }

    /**
     * Accessor method to add a simple billing details to the user
     * 
     * @param aBillingDetails
     *            the single billing details to add
     */
    public void addBillingDetails( BillingDetails aBillingDetails ) {
        if ( aBillingDetails == null ) {
            throw new IllegalArgumentException( "aBillingDetails is null" );
        }
        billingDetails.add( aBillingDetails );
    }

    /**
     * @return items for sale
     */
    public Set<Item> getItemsForSale() {
        return itemsForSale;
    }

    /**
     * @param aItems
     */
    public void setItemsForSale( Set<Item> aItems ) {
        itemsForSale = aItems;
    }

    /**
     * @param aItem
     */
    public void addItemForSale( Item aItem ) {
        if ( aItem == null ) {
            throw new IllegalArgumentException( "aItem is null" );
        }
        aItem.setSeller( this );
        itemsForSale.add( aItem );
    }

    /**
     * @return comments
     */
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * @param aComments
     */
    public void setComments( Set<Comment> aComments ) {
        comments = aComments;
    }

    /**
     * @param aComment
     */
    public void addComment( Comment aComment ) {
        if ( aComment == null ) {
            throw new IllegalArgumentException( "aComment is null" );
        }
        aComment.setFromUser( this );
        comments.add( aComment );
    }

    /**
     * Getter for the bids
     * 
     * @return the collection of bids that the user has made
     */
    public Set<Bid> getBids() {
        return bids;
    }

    /**
     * setter for the users bids
     * 
     * @param aBids
     *            the collection of bids that the user has made
     */
    public void setBids( Set<Bid> aBids ) {
        bids = aBids;
    }

    /**
     * Accessor method allowing us to add a singke bid to the collection of bids that this user has made
     * 
     * @param aBid
     *            the bid to make
     */
    public void addBid( Bid aBid ) {
        if ( aBid == null ) {
            throw new IllegalArgumentException( "aBid is null" );
        }
        aBid.setBidder( this );
        bids.add( aBid );
    }

    /**
     * getter for first name
     * 
     * @return the given name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for first name
     * 
     * @param aName
     *            the given name
     */
    public void setFirstName( String aName ) {
        firstName = aName;
    }

    /**
     * getter for the last name
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for last name
     * 
     * @param aName
     *            the family name
     */
    public void setLastName( String aName ) {
        lastName = aName;
    }

    /**
     * @return name
     */
    @Transient
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * @param aName
     */
    public void setName( String aName ) {
        StringTokenizer t = new StringTokenizer( aName );
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
     * @param aName
     *            the username
     */
    public void setUsername( String aName ) {
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
     * @param aAddress
     *            the address to set
     */
    public void setHomeAddress( Address aAddress ) {
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
     * @param aAddress
     *            the address to set
     */
    public void setBillingAddress( Address aAddress ) {
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
     * @param aCreated
     *            the date that the user was created
     */
    protected void setCreated( Date aCreated ) {
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
     * @param aEmail
     *            the email to set
     */
    public void setEmail( String aEmail ) {
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
     * @param aPassword
     *            the user password
     */
    public void setPassword( String aPassword ) {
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
     * @param aRanking
     *            the rank to set
     */
    public void setRanking( Integer aRanking ) {
        ranking = aRanking;
    }
}
