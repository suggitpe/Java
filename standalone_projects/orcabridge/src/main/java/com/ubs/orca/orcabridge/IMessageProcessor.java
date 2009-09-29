/*
 * IMessageSender.java created on 22 Sep 2009 19:50:59 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

/**
 * Provides the ability to process a message in some way.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public interface IMessageProcessor
{

    void processMessage( MessageFacade aMessageFacade ) throws OrcaBridgeException;

}
