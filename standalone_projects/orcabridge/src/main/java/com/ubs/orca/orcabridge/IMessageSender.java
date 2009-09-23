/*
 * IMessageSender.java created on 22 Sep 2009 19:50:59 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;


/**
 * TODO Write javadoc for IMessageSender
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public interface IMessageSender
{

    void sendMessage( MessageFacade aMessageFacade ) throws OrcaBridgeException;

}
