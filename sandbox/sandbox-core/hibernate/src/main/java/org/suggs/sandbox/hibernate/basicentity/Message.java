/*
 * Message.java created on 19 Mar 2007 16:37:37 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MESSAGE_SQ")
public class Message extends EntityBase {

    @Column(name = "MESSAGE_TEXT", nullable = false, length = 255)
    private String text;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "NEXT_MESSAGE_ID", nullable = true)
    private Message nextMessage;

    @SuppressWarnings("unused")
    private Message() {}

    /**
     * Constructs a new instance.
     * 
     * @param aText
     */
    public Message( String aText ) {
        text = aText;
    }

    /**
     * Returns the value of text.
     * 
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text field to the specified value.
     * 
     * @param aText
     *            The text to set.
     */
    public void setText( String aText ) {
        text = aText;
    }

    /**
     * Returns the value of nextMessage.
     * 
     * @return Returns the nextMessage.
     */
    public Message getNextMessage() {
        return nextMessage;
    }

    /**
     * Sets the nextMessage field to the specified value.
     * 
     * @param aNextMessage
     *            The nextMessage to set.
     */
    public void setNextMessage( Message aNextMessage ) {
        nextMessage = aNextMessage;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString() + " Message [nextMessage=" + nextMessage + ", text=" + text + "]";
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( nextMessage == null ) ? 0 : nextMessage.hashCode() );
        result = prime * result + ( ( text == null ) ? 0 : text.hashCode() );
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( !super.equals( obj ) )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Message other = (Message) obj;
        if ( nextMessage == null ) {
            if ( other.nextMessage != null )
                return false;
        }
        else if ( !nextMessage.equals( other.nextMessage ) )
            return false;
        if ( text == null ) {
            if ( other.text != null )
                return false;
        }
        else if ( !text.equals( other.text ) )
            return false;
        return true;
    }

}
