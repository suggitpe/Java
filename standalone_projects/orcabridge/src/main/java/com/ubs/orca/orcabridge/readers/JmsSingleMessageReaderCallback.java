/*
 * JmsSingleMessageReaderCallback.java created on 9 Nov 2009 08:18:26 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.message.MessageFacadeFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This is the callback class that will be passed into the JMSClient
 * so that we can pass the message received back to the message
 * processor.
 * 
 * @author suggitpe
 * @version 1.0 9 Nov 2009
 */
public class JmsSingleMessageReaderCallback
    implements IJmsClientSingleMsgCallback, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JmsSingleMessageReaderCallback.class );

    private IMessageProcessor messageProcessor;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( messageProcessor,
                        "No message processor has been set on the JmsSingleMessageReaderCallback" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback#onReceived(javax.jms.Message)
     */
    @Override
    public void onReceived( Message aMessage ) throws JmsClientException
    {
        if ( LOG.isInfoEnabled() )
        {
            LOG.info( "Passing received message [" + aMessage + "] to message Processor." );
        }

        try
        {
            messageProcessor.processMessage( MessageFacadeFactory.createMessageAdapter( aMessage ) );
        }
        catch ( OrcaBridgeException throwable )
        {
            LOG.error( "Issue ocurred in the sending of a message", throwable );
            throw new JmsClientException( throwable );
        }

        if ( LOG.isInfoEnabled() )
        {
            LOG.info( "Message routing for message ["
                      + aMessage
                      + "] completed.  Allowing callback to complete so that JMS transaction can be committed." );
        }
    }

    /**
     * Sets the messageProcessor field to the specified value.
     * 
     * @param aMessageProcessor
     *            The messageProcessor to set.
     */
    public void setMessageProcessor( IMessageProcessor aMessageProcessor )
    {
        messageProcessor = aMessageProcessor;
    }

}
