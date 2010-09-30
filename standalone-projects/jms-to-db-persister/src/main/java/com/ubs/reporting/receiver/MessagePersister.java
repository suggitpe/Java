/*
 * MessagePersister.java created on 29 Sep 2010 08:26:55 by suggitpe for project masterfiles-receiver
 * 
 */
package com.ubs.reporting.receiver;

/**
 * TODO Write javadoc for MessagePersister
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2010
 */
public interface MessagePersister {

    void persistMessage( String aXmlMessage );

}
