/*
 * Comment.java created on 22 Mar 2007 17:49:22 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import com.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CE_COMMENT")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_COMMENT_SEQ")
public class Comment extends AbstractPersistentBaseClass
{

    private Integer mRating_;
    private String mText_;
    private Date mCreated_;
    private Item mAboutItem_;
    private User mFromUser_;

    public Comment()
    {
        super();
    }

    /**
     * Getter for the comment creation timesatmp
     * 
     * @return the creation timestamp
     */
    @Column(name = "CMNT_CREATED", nullable = false, updatable = false)
    public Date getCreated()
    {
        return mCreated_;
    }

    /**
     * setter for the comment creation timestamp
     * 
     * @param created
     *            the timestamp
     */
    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    /**
     * getter for the comment rating
     * 
     * @return the rating of the comment
     */
    @Column(name = "CMNT_RATING")
    public Integer getRating()
    {
        return mRating_;
    }

    /**
     * setter for the rating
     * 
     * @param rating
     *            the rating
     */
    public void setRating( Integer rating )
    {
        mRating_ = rating;
    }

    /**
     * getter for the comment text
     * 
     * @return the comment text
     */
    @Column(name = "CMNT_TEXT", length = 255)
    public String getText()
    {
        return mText_;
    }

    /**
     * setter for the comment text
     * 
     * @param text
     *            the text to set on the comment
     */
    public void setText( String text )
    {
        mText_ = text;
    }

    /**
     * getter for the item that this comment refers to
     * 
     * @return the uitem that this comment refers to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CMNT_ABOUT_ITEM_ID")
    public Item getAboutItem()
    {
        return mAboutItem_;
    }

    /**
     * setter for the item that this coimment refers to
     * 
     * @param aboutItem
     *            the item that this comment refers to
     */
    public void setAboutItem( Item aboutItem )
    {
        mAboutItem_ = aboutItem;
    }

    /**
     * Getter for the user that made this comment
     * 
     * @return the user that made this comment
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CMNT_FROM_USER_ID")
    public User getFromUser()
    {
        return mFromUser_;
    }

    /**
     * Setter for the user that this comment was made by
     * 
     * @param fromUser
     *            the user that made the comment
     */
    public void setFromUser( User fromUser )
    {
        mFromUser_ = fromUser;
    }
}
