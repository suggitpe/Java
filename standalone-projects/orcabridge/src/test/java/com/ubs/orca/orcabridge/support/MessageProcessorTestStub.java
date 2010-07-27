/*
 * MessageReaderTestStub.java created on 27 Sep 2009 10:10:10 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;

/**
 * Test stub class for Message Senders
 * 
 * @author suggitpe
 * @version 1.0 27 Sep 2009
 */
public class MessageProcessorTestStub implements IMessageProcessor {

    private static final Log LOG = LogFactory.getLog( MessageProcessorTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) {
        LOG.debug( "Test sending a message on the Test Stub Message Sender" );
    }
}
