/*
 * JmsMessageReader.java created on 29 Sep 2009 06:54:09 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsDurableReaderAction;
import com.ubs.orca.orcabridge.message.MessageFacadeFactory;

import org.springframework.util.Assert;

/**
 * Message reader class that will read a single message from a JMS
 * source and pass it on to a message processor.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public class JmsSingleMessageReader extends AbstractMessageReader
{

    private static final Log LOG = LogFactory.getLog( JmsSingleMessageReader.class );

    private IJmsClient jmsClient_;
    private String durableName_;
    private String messageSelector_;

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    protected void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull( jmsClient_, "No JMS client has been set on the JMS reader" );
        Assert.notNull( durableName_, "No durable name has been set on the JMS reader" );
        Assert.notNull( messageSelector_, "No message selector has been set on the JMS reader" );
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStartReader()
     */
    @Override
    protected void doStartReader() throws OrcaBridgeException
    {
        IJmsAction action = new JmsDurableReaderAction( new JmsMessageProcessorCallback(),
                                                        durableName_,
                                                        messageSelector_ );
        try
        {
            jmsClient_.connect();
            jmsClient_.processActionUntilStopped( action );
        }
        catch ( JmsClientException je )
        {
            final String err = "Failed to start JMS Client";
            LOG.error( err, je );
            throw new OrcaBridgeException( err, je );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStopReader()
     */
    @Override
    protected void doStopReader() throws OrcaBridgeException
    {
        try
        {
            jmsClient_.disconnect();
        }
        catch ( JmsClientException je )
        {
            String err = "Errors occured when trying to stop the JMS client";
            LOG.error( err, je );
            throw new OrcaBridgeException( err, je );
        }
    }

    /**
     * Sets the jmsClient. This method is here to support replacing
     * the jms client with mocks for unit tests.
     * 
     * @param aJmsClient
     *            the JMS client
     */
    public void setJmsClient( IJmsClient aJmsClient )
    {
        jmsClient_ = aJmsClient;
    }

    /**
     * Sets the durableName field to the specified value.
     * 
     * @param durableName
     *            The durableName to set.
     */
    public void setDurableName( String durableName )
    {
        durableName_ = durableName;
    }

    /**
     * Sets the messageSelector field to the specified value.
     * 
     * @param messageSelector
     *            The messageSelector to set.
     */
    public void setMessageSelector( String messageSelector )
    {
        messageSelector_ = messageSelector;
    }

    // ===============================================
    /**
     * This is the callback class that will be passed into the
     * JMSClient so that we can pass the message received back to the
     * message processor.
     * 
     * @author suggitpe
     * @version 1.0 30 Sep 2009
     */
    class JmsMessageProcessorCallback implements IJmsClientSingleMsgCallback
    {

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
                getMessageProcessor().processMessage( MessageFacadeFactory.createMessageAdapter( aMessage ) );
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
    }

}
