/*
 * Comment.java created on 22 Mar 2007 17:49:22 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CE_COMMENT")
@SequenceGenerator(name = "CE_SEQ_STR", sequenceName = "CE_COMMENT_SEQ")
@SuppressWarnings("unused")
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

    public Comment() {
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date aCreated) {
        created = aCreated;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer aRating) {
        rating = aRating;
    }

    public String getText() {
        return text;
    }

    public void setText(String aText) {
        text = aText;
    }

    public Item getAboutItem() {
        return aboutItem;
    }

    public void setAboutItem(Item aAboutItem) {
        aboutItem = aAboutItem;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User aFromUser) {
        fromUser = aFromUser;
    }
}
