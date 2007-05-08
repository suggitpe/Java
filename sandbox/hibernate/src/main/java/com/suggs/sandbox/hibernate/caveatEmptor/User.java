/*
 * User.java created on 21 Mar 2007 17:37:14 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

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
    public User( String aFirstName, String aLastName, String aUserName, String aPassword, String aEmail, Address aHomeAddress,
                 Address aBillingAddress )
    {
        super();
        mFirstName_ = aFirstName;
        mLastName_ = aLastName;
        Assert.notNull( aUserName, "Must set the username" );
        mUsername_ = aUserName;
        mPassword_ = aPassword;
        Assert.notNull( aEmail, "Must set the email address" );
        mEmail_ = aEmail;
        mRanking_ = 0;
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

    public Set<BillingDetails> getBillingDetails()
    {
        return mBillingDetails_;
    }

    public void setBillingDetails( Set<BillingDetails> billingDetails )
    {
        mBillingDetails_ = billingDetails;
    }

    public void addBillingDetails( BillingDetails aBillingDetails )
    {
        if ( aBillingDetails == null )
        {
            throw new IllegalArgumentException( "aBillingDetails is null" );
        }
        mBillingDetails_.add( aBillingDetails );
    }

    public Set<Item> getItemsForSale()
    {
        return mItemsForSale_;
    }

    public void setItemsForSale( Set<Item> aItems )
    {
        mItemsForSale_ = aItems;
    }

    public void addItemForSale( Item aItem )
    {
        if ( aItem == null )
        {
            throw new IllegalArgumentException( "aItem is null" );
        }
        aItem.setSeller( this );
        mItemsForSale_.add( aItem );
    }

    public Set<Comment> getComments()
    {
        return mComments_;
    }

    public void setComments( Set<Comment> aComments )
    {
        mComments_ = aComments;
    }

    public void addComment( Comment aComment )
    {
        if ( aComment == null )
        {
            throw new IllegalArgumentException( "aComment is null" );
        }
        aComment.setFromUser( this );
        mComments_.add( aComment );
    }

    public String getFirstName()
    {
        return mFirstName_;
    }

    public Set<Bid> getBids()
    {
        return mBids_;
    }

    public void setBids( Set<Bid> aBids )
    {
        mBids_ = aBids;
    }

    public void addBid( Bid aBid )
    {
        if ( aBid == null )
        {
            throw new IllegalArgumentException( "aBid is null" );
        }
        aBid.setBidder( this );
        mBids_.add( aBid );
    }

    public void setFirstName( String aName )
    {
        mFirstName_ = aName;
    }

    public String getLastName()
    {
        return mLastName_;
    }

    public void setLastName( String aName )
    {
        mLastName_ = aName;
    }

    public String getName()
    {
        return mFirstName_ + " " + mLastName_;
    }

    public void setName( String aName )
    {
        StringTokenizer t = new StringTokenizer( aName );
        mFirstName_ = t.nextToken();
        mLastName_ = t.nextToken();
    }

    public String getUsername()
    {
        return mUsername_;
    }

    public void setUsername( String aName )
    {
        mUsername_ = aName;
    }

    public Address getHomeAddress()
    {
        return mHomeAddress_;
    }

    public void setHomeAddress( Address aAddress )
    {
        mHomeAddress_ = aAddress;
    }

    public Address getBillingAddress()
    {
        return mBillingAddress_;
    }

    public void setBillingAddress( Address aAddress )
    {
        mBillingAddress_ = aAddress;
    }

    public Date getCreated()
    {
        return mCreated_;
    }

    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    public String getEmail()
    {
        return mEmail_;
    }

    public void setEmail( String email )
    {
        mEmail_ = email;
    }

    public String getPassword()
    {
        return mPassword_;
    }

    public void setPassword( String password )
    {
        mPassword_ = password;
    }

    public Integer getRanking()
    {
        return mRanking_;
    }

    public void setRanking( Integer ranking )
    {
        mRanking_ = ranking;
    }
}
