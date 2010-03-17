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
public class User extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( User.class );

    private String mFirstName_;
    private String mLastName_;
    private String mUsername_;
    private String mPassword_;
    private String mEmail_;
    private Integer mRanking_;
    private Date mCreated_;
    private Address mHomeAddress_;
    private Address mBillingAddress_;
    private Set<BillingDetails> mBillingDetails_ = new HashSet<BillingDetails>();
    private Set<Item> mItemsForSale_ = new HashSet<Item>();
    private Set<Bid> mBids_ = new HashSet<Bid>();
    private Set<Comment> mComments_ = new HashSet<Comment>();

    /**
     * Def ctor
     */
    public User()
    {
        super();
        mCreated_ = Calendar.getInstance().getTime();
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
     *            tyhe users password
     * @param aEmail
     *            the users email address
     * @param aHomeAddress
     *            the users home address
     * @param aBillingAddress
     *            the users billing address
     */
    public User( String aFirstName, String aLastName, String aUserName, String aPassword,
                 String aEmail, Address aHomeAddress, Address aBillingAddress )
    {
        super();
        mFirstName_ = aFirstName;
        mLastName_ = aLastName;
        Assert.notNull( aUserName, "Must set the username" );
        mUsername_ = aUserName;
        mPassword_ = aPassword;
        Assert.notNull( aEmail, "Must set the email address" );
        mEmail_ = aEmail;
        mRanking_ = new Integer( 0 );
        mCreated_ = Calendar.getInstance().getTime();
        Assert.notNull( aHomeAddress, "Must set the home adress" );
        mHomeAddress_ = aHomeAddress;
        if ( aBillingAddress == null )
        {
            LOG.info( "Using home address as billing address as default" );
            mBillingAddress_ = aHomeAddress;
        }
        else
        {
            mBillingAddress_ = aBillingAddress;
        }

    }

    /**
     * Getter for the billing details
     * 
     * @return the set of billing details
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public Set<BillingDetails> getBillingDetails()
    {
        return mBillingDetails_;
    }

    /**
     * setter for billing details
     * 
     * @param billingDetails
     *            the billing details set
     */
    public void setBillingDetails( Set<BillingDetails> billingDetails )
    {
        mBillingDetails_ = billingDetails;
    }

    /**
     * Accessor method to add a simple billing details to the user
     * 
     * @param aBillingDetails
     *            the single billing details to add
     */
    public void addBillingDetails( BillingDetails aBillingDetails )
    {
        if ( aBillingDetails == null )
        {
            throw new IllegalArgumentException( "aBillingDetails is null" );
        }
        mBillingDetails_.add( aBillingDetails );
    }

    /**
     * @return items for sale
     */
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    public Set<Item> getItemsForSale()
    {
        return mItemsForSale_;
    }

    /**
     * @param aItems
     */
    public void setItemsForSale( Set<Item> aItems )
    {
        mItemsForSale_ = aItems;
    }

    /**
     * @param aItem
     */
    public void addItemForSale( Item aItem )
    {
        if ( aItem == null )
        {
            throw new IllegalArgumentException( "aItem is null" );
        }
        aItem.setSeller( this );
        mItemsForSale_.add( aItem );
    }

    /**
     * @return comments
     */
    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY)
    public Set<Comment> getComments()
    {
        return mComments_;
    }

    /**
     * @param aComments
     */
    public void setComments( Set<Comment> aComments )
    {
        mComments_ = aComments;
    }

    /**
     * @param aComment
     */
    public void addComment( Comment aComment )
    {
        if ( aComment == null )
        {
            throw new IllegalArgumentException( "aComment is null" );
        }
        aComment.setFromUser( this );
        mComments_.add( aComment );
    }

    /**
     * Getter for the bids
     * 
     * @return the collection of bids that the user has made
     */
    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY)
    public Set<Bid> getBids()
    {
        return mBids_;
    }

    /**
     * setter for the users bids
     * 
     * @param aBids
     *            the collection of bids that the user has made
     */
    public void setBids( Set<Bid> aBids )
    {
        mBids_ = aBids;
    }

    /**
     * Accessor method allowing us to add a singke bid to the
     * collection of bids that this user has made
     * 
     * @param aBid
     *            the bid to make
     */
    public void addBid( Bid aBid )
    {
        if ( aBid == null )
        {
            throw new IllegalArgumentException( "aBid is null" );
        }
        aBid.setBidder( this );
        mBids_.add( aBid );
    }

    /**
     * getter for first name
     * 
     * @return the given name
     */
    @Column(name = "USER_FIRST_NAME", nullable = false, length = 64)
    public String getFirstName()
    {
        return mFirstName_;
    }

    /**
     * setter for first name
     * 
     * @param aName
     *            the given name
     */
    public void setFirstName( String aName )
    {
        mFirstName_ = aName;
    }

    /**
     * getter for the last name
     * 
     * @return the last name
     */
    @Column(name = "USER_LAST_NAME", nullable = false, length = 64)
    public String getLastName()
    {
        return mLastName_;
    }

    /**
     * setter for last name
     * 
     * @param aName
     *            the family name
     */
    public void setLastName( String aName )
    {
        mLastName_ = aName;
    }

    /**
     * @return name
     */
    @Transient
    public String getName()
    {
        return mFirstName_ + " " + mLastName_;
    }

    /**
     * @param aName
     */
    public void setName( String aName )
    {
        StringTokenizer t = new StringTokenizer( aName );
        mFirstName_ = t.nextToken();
        mLastName_ = t.nextToken();
    }

    /**
     * getter for the username
     * 
     * @return the user name
     */
    @Column(name = "USER_USERNAME", length = 10, nullable = false, updatable = false, unique = true)
    public String getUsername()
    {
        return mUsername_;
    }

    /**
     * set the username
     * 
     * @param aName
     *            the username
     */
    public void setUsername( String aName )
    {
        mUsername_ = aName;
    }

    /**
     * Getter for the home address object
     * 
     * @return the home address
     */
    @Embedded
    @AttributeOverrides( {
                          @AttributeOverride(name = "street", column = @Column(name = "USER_HOME_STREET")),
                          @AttributeOverride(name = "city", column = @Column(name = "USER_HOME_CITY")),
                          @AttributeOverride(name = "zipCode", column = @Column(name = "USER_HOME_ZIPCODE")) })
    public Address getHomeAddress()
    {
        return mHomeAddress_;
    }

    /**
     * setter for the home address
     * 
     * @param aAddress
     *            the address to set
     */
    public void setHomeAddress( Address aAddress )
    {
        mHomeAddress_ = aAddress;
    }

    /**
     * Getter for tjhe billing address object
     * 
     * @return the billing address
     */
    @Embedded
    @AttributeOverrides( {
                          @AttributeOverride(name = "street", column = @Column(name = "USER_BILLING_STREET")),
                          @AttributeOverride(name = "city", column = @Column(name = "USER_BILLING_CITY")),
                          @AttributeOverride(name = "zipCode", column = @Column(name = "USER_BILLING_ZIPCODE")) })
    public Address getBillingAddress()
    {
        return mBillingAddress_;
    }

    /**
     * setter for the billing address
     * 
     * @param aAddress
     *            the address to set
     */
    public void setBillingAddress( Address aAddress )
    {
        mBillingAddress_ = aAddress;
    }

    /**
     * getter for the creation date
     * 
     * @return the date that the user was created
     */
    @Column(name = "USER_CREATED", nullable = false, updatable = false)
    public Date getCreated()
    {
        return mCreated_;
    }

    /**
     * setter for the creation date
     * 
     * @param created
     *            the date that the user was created
     */
    protected void setCreated( Date created )
    {
        mCreated_ = created;
    }

    /**
     * getter for the user email
     * 
     * @return the user email address
     */
    @Column(name = "USER_EMAIL", nullable = false, length = 64)
    public String getEmail()
    {
        return mEmail_;
    }

    /**
     * setter for the uiser email address
     * 
     * @param email
     *            the email to set
     */
    public void setEmail( String email )
    {
        mEmail_ = email;
    }

    /**
     * getter for the user password
     * 
     * @return the user password
     */
    @Column(name = "USER_PASSWORD", nullable = false, length = 16)
    public String getPassword()
    {
        return mPassword_;
    }

    /**
     * setter for the user password
     * 
     * @param password
     *            the user password
     */
    public void setPassword( String password )
    {
        mPassword_ = password;
    }

    /**
     * getter for the user rank
     * 
     * @return the user rank
     */
    @Column(name = "USER_RANKING")
    public Integer getRanking()
    {
        return mRanking_;
    }

    /**
     * setter for the user rank
     * 
     * @param ranking
     *            the rank to set
     */
    public void setRanking( Integer ranking )
    {
        mRanking_ = ranking;
    }
}
