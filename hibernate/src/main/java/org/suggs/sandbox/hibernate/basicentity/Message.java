/*
 * Message.java created on 19 Mar 2007 16:37:37 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.basicentity;

import org.suggs.sandbox.hibernate.support.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@SequenceGenerator(name = "ENTITYBASE_SEQ_STR", sequenceName = "MESSAGE_SQ")
@SuppressWarnings("unused")
public class Message extends EntityBase {

    @Column(name = "MESSAGE_TEXT", nullable = false, length = 255)
    private String text;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "NEXT_MESSAGE_ID", nullable = true)
    private Message nextMessage;

    private Message() {
    }

    public Message(String aText) {
        text = aText;
    }

    public String getText() {
        return text;
    }

    public void setText(String aText) {
        text = aText;
    }

    public Message getNextMessage() {
        return nextMessage;
    }

    public void setNextMessage(Message aNextMessage) {
        nextMessage = aNextMessage;
    }

    @Override
    public String toString() {
        return super.toString() + " Message [nextMessage=" + nextMessage + ", text=" + text + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((nextMessage == null) ? 0 : nextMessage.hashCode());
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (nextMessage == null) {
            if (other.nextMessage != null)
                return false;
        } else if (!nextMessage.equals(other.nextMessage))
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        return true;
    }
}
