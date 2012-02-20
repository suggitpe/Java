/*
 * Item.java created on 21 Mar 2007 17:58:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

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

/**
 * Hibernate object representing an Item that can be auctioned
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_ITEM_SEQ")
@Table(name = "CE_ITEM", uniqueConstraints = { @UniqueConstraint(columnNames = { "SUCCESSFUL_BID_ID" }),
                                              @UniqueConstraint(columnNames = "SELLER_USER_ID") })
public class Item extends AbstractPersistentBaseClass {

    @Column(name = "ITEM_NAME", nullable = false, length = 64)
    private String name;

    @Column(name = "ITEM_DESCRIPTION", nullable = false, length = 255)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = org.suggs.sandbox.hibernate.caveatEmptor.Category.class, cascade = {
                                                                                                                           CascadeType.PERSIST,
                                                                                                                           CascadeType.MERGE })
    @JoinTable(name = "CE_ITM_CAT_BRDG", joinColumns = { @JoinColumn(name = "ITMCAT_ITEMS_ID") }, inverseJoinColumns = { @JoinColumn(name = "ITMCAT_CATEGORIES_ID") })
    private Set<Category> categories = new HashSet<Category>();

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private Set<Bid> bids = new HashSet<Bid>();

    @OneToMany(mappedBy = "aboutItem", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @Column(name = "ITEM_INITIAL_PRICE", updatable = false)
    private Double initialPrice;

    @Column(name = "ITEM_RESERVE_PRICE", nullable = false)
    private Double reservePrice;

    @Column(name = "ITEM_START_DATE", nullable = false, updatable = false)
    private Date startDate;

    @Column(name = "ITEM_END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "ITEM_CREATED", nullable = false, updatable = false)
    private Date created;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUCCESSFUL_BID_ID")
    private Bid successfulBid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_USER_ID")
    private User seller;

    /**
     * Constructs a new instance.
     */
    public Item() {
        super();
        created = Calendar.getInstance().getTime();
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
    public Item( String aName, String aDescription, Double aInitialPrice, Double aReservePrice,
                 Date aStartDate, Date aEndDate ) {
        super();
        name = aName;
        description = aDescription;
        initialPrice = aInitialPrice;
        reservePrice = aReservePrice;
        startDate = aStartDate;
        endDate = aEndDate;
        created = Calendar.getInstance().getTime();
    }

    /**
     * Getter for the ame field
     * 
     * @return name
     */
    public String getName() {
        return name;

    }

    /**
     * setter for the name of the item
     * 
     * @param aName
     *            the name of the item
     */
    public void setName( String aName ) {
        name = aName;
    }

    /**
     * getter for the item description
     * 
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for the description
     * 
     * @param aDescription
     *            the description to set
     */
    public void setDescription( String aDescription ) {
        description = aDescription;
    }

    /**
     * Getter for the item categories: many-to-many with category
     * 
     * @return the set of categories tht this item relates to
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * setter for the categories set
     * 
     * @param aCategories
     *            the st of categories that this item belongs to
     */
    public void setCategories( Set<Category> aCategories ) {
        categories = aCategories;
    }

    /**
     * Accessor helper method to add a category to the list of categories
     * 
     * @param aCatagory
     *            the category to add
     */
    public void addCategory( Category aCatagory ) {
        if ( aCatagory == null ) {
            throw new IllegalArgumentException( "aCategory is null" );
        }

        aCatagory.getItems().add( this );
        categories.add( aCatagory );
    }

    /**
     * getter for the bids assocuated with this item
     * 
     * @return all the bids made on this item
     */
    public Set<Bid> getBids() {
        return bids;
    }

    /**
     * Setter for the set of bids made against this item
     * 
     * @param aBids
     *            the bids made againt this item
     */
    public void setBids( Set<Bid> aBids ) {
        bids = aBids;
    }

    /**
     * Accessor to add a new bid to the lit of bids for this item
     * 
     * @param aBid
     *            a bid to add to the current set of bids for this item
     */
    public void addBid( Bid aBid ) {
        if ( aBid == null ) {
            throw new IllegalArgumentException( "aBid is null" );
        }
        // this ensurs that we do not get a bid that has been created
        // against another item being incorrectly associated
        aBid.setItem( this );
        bids.add( aBid );
    }

    /**
     * Getter for the comments made about this item
     * 
     * @return the set of comments made about this item
     */
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * setter for the item comments
     * 
     * @param aComments
     *            the comments set to add
     */
    public void setComments( Set<Comment> aComments ) {
        comments = aComments;
    }

    /**
     * Accessor method to make the addition of comments much easier
     * 
     * @param aComment
     *            the comment to add to the existing set
     */
    public void addComment( Comment aComment ) {
        if ( aComment == null ) {
            throw new IllegalArgumentException( "aComment is null" );
        }
        aComment.setAboutItem( this );
        comments.add( aComment );
    }

    /**
     * getter for the created timestamp
     * 
     * @return the time that the item was created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * setter for the created timestamp
     * 
     * @param aCreated
     *            the datetime the item was created
     */
    public void setCreated( Date aCreated ) {
        created = aCreated;
    }

    /**
     * getter for the end date
     * 
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * setter for the end date
     * 
     * @param aEndDate
     *            the end date
     */
    public void setEndDate( Date aEndDate ) {
        endDate = aEndDate;
    }

    /**
     * getter for the initial price
     * 
     * @return the initial price
     */
    public Double getInitialPrice() {
        return initialPrice;
    }

    /**
     * setter for the initial price
     * 
     * @param aInitialPrice
     *            the initial price
     */
    public void setInitialPrice( Double aInitialPrice ) {
        initialPrice = aInitialPrice;
    }

    /**
     * getter for the reserve price
     * 
     * @return the reserve price
     */
    public Double getReservePrice() {
        return reservePrice;
    }

    /**
     * setter for the reserve price
     * 
     * @param aReservePrice
     *            the reserve price
     */
    public void setReservePrice( Double aReservePrice ) {
        reservePrice = aReservePrice;
    }

    /**
     * getter for the start date
     * 
     * @return the date that the item starts
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * setter for the start date
     * 
     * @param aStartDate
     *            the date that the item starts
     */
    public void setStartDate( Date aStartDate ) {
        startDate = aStartDate;
    }

    /**
     * getter for the successful bid
     * 
     * @return the successful bid
     */
    public Bid getSuccessfulBid() {
        return successfulBid;
    }

    /**
     * Setter for the successful bid
     * 
     * @param aSuccessfulBid
     *            the successful bid to set
     */
    public void setSuccessfulBid( Bid aSuccessfulBid ) {
        successfulBid = aSuccessfulBid;
    }

    /**
     * getter for the seller of the item
     * 
     * @return the seller
     */
    public User getSeller() {
        return seller;
    }

    /**
     * setter for the seller of the item
     * 
     * @param aSeller
     *            the seller
     */
    public void setSeller( User aSeller ) {
        seller = aSeller;
    }

}
