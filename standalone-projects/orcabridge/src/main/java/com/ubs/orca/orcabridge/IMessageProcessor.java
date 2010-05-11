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
public interface IMessageProcessor {

    /**
     * This is the core method on the Message Processor and its key reason for existence.
     * 
     * @param aMessageFacade
     * @throws OrcaBridgeException
     */
    void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException;

}
