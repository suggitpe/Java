/*
 * Message.java created on 19 Mar 2007 16:37:37 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.chapter2;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Data object for persistence
 * 
 * @author suggitpe
 * @version 1.0 8 May 2007
 */
@Entity
@Table(name = "MESSAGES")
@SequenceGenerator(name = "MSG_SEQ_STR", sequenceName = "MESSAGE_SEQ")
public class Message
{

    private Long id;
    private String text_;
    private Message nextMessage_;

    @SuppressWarnings("unused")
    private Message()
    {
    }

    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer();

        buff.append( "ID:[" ).append( getId() );
        buff.append( "] TEXT:[" ).append( getText() );
        buff.append( "] next_id:[" ).append( getNextMessage() ).append( "]" );
        return buff.toString();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aText
     */
    public Message( String aText )
    {
        text_ = aText;
    }

    /**
     * Getter for the ID
     * 
     * @return the id of the object
     */
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_SEQ_STR")
    public Long getId()
    {
        return id;
    }

    /**
     * @param aId
     */
    public void setId( Long aId )
    {
        id = aId;
    }

    /**
     * @return
     */
    @Column(name = "MESSAGE_TEXT", nullable = false, length = 255)
    public String getText()
    {
        return text_;
    }

    /**
     * @param aText
     */
    public void setText( String aText )
    {
        text_ = aText;
    }

    /**
     * @return
     */
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "NEXT_MESSAGE_ID", nullable = true)
    public Message getNextMessage()
    {
        return nextMessage_;
    }

    /**
     * @param aMsg
     */
    public void setNextMessage( Message aMsg )
    {
        nextMessage_ = aMsg;
    }

}
