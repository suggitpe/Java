/*
 * Item.java created on 21 Mar 2007 17:58:25 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_ITEM_SEQ")
@Table(name = "CE_ITEM", uniqueConstraints = {@UniqueConstraint(columnNames = {"SUCCESSFUL_BID_ID"}),
        @UniqueConstraint(columnNames = "SELLER_USER_ID")})
@SuppressWarnings("unused")
public class Item extends AbstractPersistentBaseClass {

    @Column(name = "ITEM_NAME", nullable = false, length = 64)
    private String name;

    @Column(name = "ITEM_DESCRIPTION", nullable = false, length = 255)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = org.suggs.sandbox.hibernate.caveatEmptor.Category.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "CE_ITM_CAT_BRDG", joinColumns = {@JoinColumn(name = "ITMCAT_ITEMS_ID")}, inverseJoinColumns = {@JoinColumn(name = "ITMCAT_CATEGORIES_ID")})
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

    public Item() {
        created = Calendar.getInstance().getTime();
    }

    public Item(String aName, String aDescription, Double aInitialPrice, Double aReservePrice,
                Date aStartDate, Date aEndDate) {
        name = aName;
        description = aDescription;
        initialPrice = aInitialPrice;
        reservePrice = aReservePrice;
        startDate = aStartDate;
        endDate = aEndDate;
        created = Calendar.getInstance().getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> aCategories) {
        categories = aCategories;
    }

    public void addCategory(Category aCatagory) {
        if (aCatagory == null) {
            throw new IllegalArgumentException("aCategory is null");
        }

        aCatagory.getItems().add(this);
        categories.add(aCatagory);
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
        // this ensurs that we do not get a bid that has been created
        // against another item being incorrectly associated
        aBid.setItem(this);
        bids.add(aBid);
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
        aComment.setAboutItem(this);
        comments.add(aComment);
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date aCreated) {
        created = aCreated;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date aEndDate) {
        endDate = aEndDate;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double aInitialPrice) {
        initialPrice = aInitialPrice;
    }

    public Double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(Double aReservePrice) {
        reservePrice = aReservePrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date aStartDate) {
        startDate = aStartDate;
    }

    public Bid getSuccessfulBid() {
        return successfulBid;
    }

    public void setSuccessfulBid(Bid aSuccessfulBid) {
        successfulBid = aSuccessfulBid;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User aSeller) {
        seller = aSeller;
    }

}
