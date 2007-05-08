/*
 * Message.java created on 19 Mar 2007 16:37:37 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.chapter2;

/**
 * Data object for persistence
 * 
 * @author suggitpe
 * @version 1.0 8 May 2007
 */
public class Message
{

    private Long id_;
    private String text_;
    private Message nextMessage_;

    private Message()
    {
    }

    public String toString()
    {
        StringBuffer buff = new StringBuffer();

        buff.append( "ID:[" ).append( getId() );
        buff.append( "] TEXT:[" ).append( getText() );
        buff.append( "] next_id:[" ).append( getNextMessage() ).append( "]" );
        return buff.toString();
    }

    public Message( String aText )
    {
        text_ = aText;
    }

    public Long getId()
    {
        return id_;
    }

    public void setId( Long aId )
    {
        id_ = aId;
    }

    public String getText()
    {
        return text_;
    }

    public void setText( String aText )
    {
        text_ = aText;
    }

    public Message getNextMessage()
    {
        return nextMessage_;
    }

    public void setNextMessage( Message aMsg )
    {
        nextMessage_ = aMsg;
    }

}
