/*
 * Comment.java created on 22 Mar 2007 17:49:22 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.caveatEmptor;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Comment extends AbstractPersistentBaseClass
{

    private static final Log LOG = LogFactory.getLog( Comment.class );

    private Integer mRating_;
    private String mText_;
    private Date mCreated_;
    private Item mAboutItem_;
    private User mFromUser_;

    public Comment()
    {
        super();
        LOG.debug( "Creating a new Comment" );
    }

    public Date getCreated()
    {
        return mCreated_;
    }

    public void setCreated( Date created )
    {
        mCreated_ = created;
    }

    public Integer getRating()
    {
        return mRating_;
    }

    public void setRating( Integer rating )
    {
        mRating_ = rating;
    }

    public String getText()
    {
        return mText_;
    }

    public void setText( String text )
    {
        mText_ = text;
    }

    public Item getAboutItem()
    {
        return mAboutItem_;
    }

    public void setAboutItem( Item aboutItem )
    {
        mAboutItem_ = aboutItem;
    }

    public User getFromUser()
    {
        return mFromUser_;
    }

    public void setFromUser( User fromUser )
    {
        mFromUser_ = fromUser;
    }
}
