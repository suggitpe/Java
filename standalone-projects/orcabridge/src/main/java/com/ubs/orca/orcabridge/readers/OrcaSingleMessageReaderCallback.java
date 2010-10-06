/*
 * OrcaSingleMessageReaderCallback.java created on 9 Nov 2009 08:12:50 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IAttributesConversationMessage;
import com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback;
import com.ubs.orca.client.api.ITextConversationMessage;
import com.ubs.orca.common.bus.IOrcaMessage;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.message.MessageFacadeFactory;

/**
 * This is the core orca callback that we are passing to the OrcaClient. It is called each time a new message
 * is received onto the sink client token.<br/>
 * <b>We have to be careful with transactions in this callback. The completion of the Orca onReceived methods
 * will end up with a rollback and ultimate failure on the Orca client itself.</b>
 * 
 * @author suggitpe
 * @version 1.0 9 Nov 2009
 */
public class OrcaSingleMessageReaderCallback implements IOrcaSinkSingleMsgCallback {

    private IMessageProcessor messageProcessor;

    private static final Log LOG = LogFactory.getLog( OrcaSingleMessageReaderCallback.class );

    /**
     * Constructs a new instance.
     */
    public OrcaSingleMessageReaderCallback( IMessageProcessor aMessageProcessor ) {
        messageProcessor = aMessageProcessor;
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback#onReceived(com.ubs.orca.client.api.IAttributesConversationMessage)
     */
    @Override
    public void onReceived( IAttributesConversationMessage aAttributesMesage ) throws OrcaBridgeException {
        passOrcaMessageToTheMessageProcessor( aAttributesMesage );
    }

    /**
     * @see com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback#onReceived(com.ubs.orca.client.api.ITextConversationMessage)
     */
    @Override
    public void onReceived( ITextConversationMessage aTextConversationMessage ) throws OrcaBridgeException {
        passOrcaMessageToTheMessageProcessor( aTextConversationMessage );
    }

    private void passOrcaMessageToTheMessageProcessor( IOrcaMessage aMessage ) throws OrcaBridgeException {
        if ( LOG.isInfoEnabled() ) {
            LOG.info( "Passing received message [" + aMessage + "] to message Processor." );
        }

        messageProcessor.processMessage( MessageFacadeFactory.createMessageAdapter( aMessage ) );

        if ( LOG.isInfoEnabled() ) {
            LOG.info( "Message routing for message ["
                      + aMessage
                      + "] completed.  Allowing callback to complete so that Orca transaction can be committed." );
        }
    }

    /**
     * Sets the messageProcessor field to the specified value.
     * 
     * @param aMessageProcessor
     *            The messageProcessor to set.
     */
    public void setMessageProcessor( IMessageProcessor aMessageProcessor ) {
        messageProcessor = aMessageProcessor;
    }

}
