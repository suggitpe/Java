/*
 * Item.java created on 21 Mar 2007 17:58:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_ITEM_SEQ")
@Table(name = "CE_ITEM", uniqueConstraints = { @UniqueConstraint(columnNames = { "SUCCESSFUL_BID_ID" }),
                                              @UniqueConstraint(columnNames = "SELLER_USER_ID") })
public class Item extends AbstractPersistentBaseClass
{

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
        mCreated_ = Calendar.getInstance().getTime();
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

    @Column(name = "ITEM_NAME", nullable = false, length = 64)
    public String getName()
    {
        return mName_;

    }

    /**
     * setter for the name of the item
     * 
     * @param aName
     *            the name of the item
     */
    public void setName( String aName )
    {
        mName_ = aName;
    }

    /**
     * getter for the item description
     * 
     * @return the description of the item
     */
    @Column(name = "ITEM_DESCRIPTION", nullable = false, length = 255)
    public String getDescription()
    {
        return mDescription_;
    }

    /**
     * setter for the description
     * 
     * @param description
     *            the description to set
     */
    public void setDescription( String description )
    {
        mDescription_ = description;
    }

    /**
     * Getter for the item categories: many-to-many with category
     * 
     * @return the set of categories tht this item relates to
     */
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = com.suggs.sandbox.hibernate.caveatEmptor.Category.class, cascade = {
                                                                                                                           CascadeType.PERSIST,
                                                                                                                           CascadeType.MERGE })
    @JoinTable(name = "CE_ITM_CAT_BRDG", joinColumns = { @JoinColumn(name = "ITMCAT_ITEMS_ID") }, inverseJoinColumns = { @JoinColumn(name = "ITMCAT_CATEGORIES_ID") })
    public Set<Category> getCategories()
    {
        return mCategories_;
    }

    /**
     * setter for the categories set
     * 
     * @param aCategories
     *            the st of categories that this item belongs to
     */
    public void setCategories( Set<Category> aCategories )
    {
        mCategories_ = aCategories;
    }

    /**
     * Accessor helper method to add a category to the list of
     * categories
     * 
     * @param aCatagory
     *            the category to add
     */
    public void addCategory( Category aCatagory )
    {
        if ( aCatagory == null )
        {
            throw new IllegalArgumentException( "aCategory is null" );
        }

        aCatagory.getItems().add( this );
        mCategories_.add( aCatagory );
    }

    /**
     * getter for the bids assocuated with this item
     * 
     * @return all the bids made on this item
     */
    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    public Set<Bid> getBids()
    {
        return mBids_;
    }

    /**
     * Setter for the set of bids made against this item
     * 
     * @param aBids
     *            the bids made againt this item
     */
    public void setBids( Set<Bid> aBids )
    {
        mBids_ = aBids;
    }

    /**
     * Accessor to add a new bid to the lit of bids for this item
     * 
     * @param aBid
     *            a bid to add to the current set of bids for this
     *            item
     */
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

    /**
     * Getter for the comments made about this item
     * 
     * @return the set of comments made about this item
     */
    @OneToMany(mappedBy = "aboutItem", fetch = FetchType.LAZY)
    public Set<Comment> getComments()
    {
        return mComments_;
    }

    /**
     * setter for the item comments
     * 
     * @param aComments
     *            the comments set to add
     */
    public void setComments( Set<Comment> aComments )
    {
        mComments_ = aComments;
    }

    /**
     * Accessor method to make the addition of comments much easier
     * 
     * @param aComment
     *            the comment to add to the existing set
     */
    public void addComment( Comment aComment )
    {
        if ( aComment == null )
        {
            throw new IllegalArgumentException( "aComment is null" );
        }
        aComment.setAboutItem( this );
        mComments_.add( aComment );
    }

    /**
     * getter for the created timestamp
     * 
     * @return the time that the item was created
     */
    @Column(name = "ITEM_CREATED", nullable = false, updatable = false)
    public Date getCreated()
    {
        return mCreated_;
    }

    /**
     * setter for the created timestamp
     * 
     * @param created
     *            the datetime the item was created
     */
    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    /**
     * getter for the end date
     * 
     * @return the end date
     */
    @Column(name = "ITEM_END_DATE", nullable = false)
    public Date getEndDate()
    {
        return mEndDate_;
    }

    /**
     * setter for the end date
     * 
     * @param endDate
     *            the end date
     */
    public void setEndDate( Date endDate )
    {
        mEndDate_ = endDate;
    }

    /**
     * getter for the initial price
     * 
     * @return the initial price
     */
    @Column(name = "ITEM_INITIAL_PRICE", updatable = false)
    public Double getInitialPrice()
    {
        return mInitialPrice_;
    }

    /**
     * setter for the initial price
     * 
     * @param initialPrice
     *            the initial price
     */
    public void setInitialPrice( Double initialPrice )
    {
        mInitialPrice_ = initialPrice;
    }

    /**
     * getter for the reserve price
     * 
     * @return the reserve price
     */
    @Column(name = "ITEM_RESERVE_PRICE", nullable = false)
    public Double getReservePrice()
    {
        return mReservePrice_;
    }

    /**
     * setter for the reserve price
     * 
     * @param reservePrice
     *            the reserve price
     */
    public void setReservePrice( Double reservePrice )
    {
        mReservePrice_ = reservePrice;
    }

    /**
     * getter for the start date
     * 
     * @return the date that the item starts
     */
    @Column(name = "ITEM_START_DATE", nullable = false, updatable = false)
    public Date getStartDate()
    {
        return mStartDate_;
    }

    /**
     * setter for the start date
     * 
     * @param startDate
     *            the date that the item starts
     */
    public void setStartDate( Date startDate )
    {
        mStartDate_ = startDate;
    }

    /**
     * getter for the successful bid
     * 
     * @return the successful bid
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUCCESSFUL_BID_ID")
    public Bid getSuccessfulBid()
    {
        return mSuccessfulBid_;
    }

    /**
     * seter for the successfull bid
     * 
     * @param successfulBid
     *            the successful bid to set
     */
    public void setSuccessfulBid( Bid successfulBid )
    {
        mSuccessfulBid_ = successfulBid;
    }

    /**
     * getter for the seller of the item
     * 
     * @return the seller
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_USER_ID")
    public User getSeller()
    {
        return mSeller_;
    }

    /**
     * setter for the seller of the item
     * 
     * @param seller
     *            the seller
     */
    public void setSeller( User seller )
    {
        mSeller_ = seller;
    }

}
