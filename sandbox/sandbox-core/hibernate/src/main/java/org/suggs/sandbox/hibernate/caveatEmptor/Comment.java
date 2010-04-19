/*
 * Comment.java created on 22 Mar 2007 17:49:22 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Abstract class to create a base class for persistent objects
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@Entity
@Table(name = "CE_COMMENT")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_COMMENT_SEQ")
public class Comment extends AbstractPersistentBaseClass {

    @Column(name = "CMNT_RATING")
    private Integer rating;

    @Column(name = "CMNT_TEXT", length = 255)
    private String text;

    @Column(name = "CMNT_CREATED", nullable = false, updatable = false)
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CMNT_ABOUT_ITEM_ID")
    private Item aboutItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CMNT_FROM_USER_ID")
    private User fromUser;

    /**
     * Constructs a new instance.
     */
    public Comment() {
        super();
    }

    /**
     * Getter for the comment creation timesatmp
     * 
     * @return the creation timestamp
     */
    public Date getCreated() {
        return created;
    }

    /**
     * setter for the comment creation timestamp
     * 
     * @param aCreated
     *            the timestamp
     */
    public void setCreated( Date aCreated ) {
        created = aCreated;
    }

    /**
     * getter for the comment rating
     * 
     * @return the rating of the comment
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * setter for the rating
     * 
     * @param aRating
     *            the rating
     */
    public void setRating( Integer aRating ) {
        rating = aRating;
    }

    /**
     * getter for the comment text
     * 
     * @return the comment text
     */
    public String getText() {
        return text;
    }

    /**
     * setter for the comment text
     * 
     * @param aText
     *            the text to set on the comment
     */
    public void setText( String aText ) {
        text = aText;
    }

    /**
     * getter for the item that this comment refers to
     * 
     * @return the uitem that this comment refers to
     */
    public Item getAboutItem() {
        return aboutItem;
    }

    /**
     * setter for the item that this coimment refers to
     * 
     * @param aAboutItem
     *            the item that this comment refers to
     */
    public void setAboutItem( Item aAboutItem ) {
        aboutItem = aAboutItem;
    }

    /**
     * Getter for the user that made this comment
     * 
     * @return the user that made this comment
     */
    public User getFromUser() {
        return fromUser;
    }

    /**
     * Setter for the user that this comment was made by
     * 
     * @param aFromUser
     *            the user that made the comment
     */
    public void setFromUser( User aFromUser ) {
        fromUser = aFromUser;
    }
}
