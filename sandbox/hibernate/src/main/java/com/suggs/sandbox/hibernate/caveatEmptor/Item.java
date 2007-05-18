/*
 * Item.java created on 21 Mar 2007 17:58:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Item extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( Item.class );

    private String mName_;
    private String mDescription_;
    private Set<Category> mCategories_ = new HashSet<Category>();
    private Set<Bid> mBids_ = new HashSet<Bid>();
    private Set<Comment> mComments_;
    private Double mInitialPrice_;
    private Double mReservePrice_;
    private Date mStartDate_;
    private Date mEndDate_;
    private Date mCreated_;
    private Bid mSuccessfulBid_;
    private User mSeller_;

    /**
     * Constructs a new instance.
     */
    public Item()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aName
     * @param aDescription
     * @param aInitialPrice
     * @param aReservePrice
     * @param aStartDate
     * @param aEndDate
     */
    public Item( String aName, String aDescription, Double aInitialPrice, Double aReservePrice, Date aStartDate, Date aEndDate )
    {
        super();
        mName_ = aName;
        mDescription_ = aDescription;
        mInitialPrice_ = aInitialPrice;
        mReservePrice_ = aReservePrice;
        mStartDate_ = aStartDate;
        mEndDate_ = aEndDate;
        mCreated_ = Calendar.getInstance().getTime();
    }

    public String getName()
    {
        return mName_;

    }

    public void setName( String aName )
    {
        mName_ = aName;
    }

    public String getDescription()
    {
        return mDescription_;
    }

    public void setDescription( String description )
    {
        mDescription_ = description;
    }

    public Set<Category> getCategories()
    {
        return mCategories_;
    }

    public void setCategories( Set<Category> aCategories )
    {
        mCategories_ = aCategories;
    }

    public void addCategory( Category aCatagory )
    {
        if ( aCatagory == null )
        {
            throw new IllegalArgumentException( "aCategory is null" );
        }

        aCatagory.getItems().add( this );
        mCategories_.add( aCatagory );
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
        // this ensurs that we do not get a bid that has been created
        // against another item being incorrectly associated
        aBid.setItem( this );
        mBids_.add( aBid );
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
        aComment.setAboutItem( this );
        mComments_.add( aComment );
    }

    public Date getCreated()
    {
        return mCreated_;
    }

    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    public Date getEndDate()
    {
        return mEndDate_;
    }

    public void setEndDate( Date endDate )
    {
        mEndDate_ = endDate;
    }

    public Double getInitialPrice()
    {
        return mInitialPrice_;
    }

    public void setInitialPrice( Double initialPrice )
    {
        mInitialPrice_ = initialPrice;
    }

    public Double getReservePrice()
    {
        return mReservePrice_;
    }

    public void setReservePrice( Double reservePrice )
    {
        mReservePrice_ = reservePrice;
    }

    public Date getStartDate()
    {
        return mStartDate_;
    }

    public void setStartDate( Date startDate )
    {
        mStartDate_ = startDate;
    }

    public Bid getSuccessfulBid()
    {
        return mSuccessfulBid_;
    }

    public void setSuccessfulBid( Bid successfulBid )
    {
        mSuccessfulBid_ = successfulBid;
    }

    public User getSeller()
    {
        return mSeller_;
    }

    public void setSeller( User seller )
    {
        mSeller_ = seller;
    }

}
