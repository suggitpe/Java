/*
 * JmsReaderClient.java created on 7 Oct 2009 19:39:01 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * JMS client callback that only allows for the receiving of messages.
 * 
 * @author suggitpe
 * @version 1.0 7 Oct 2009
 */
public class JmsDurableReaderAction implements IJmsAction
{

    private static final Log LOG = LogFactory.getLog( JmsDurableReaderAction.class );
    private IJmsClientSingleMsgCallback clientCallback_;
    private String durableName_;
    private String durableMessageSelector_;

    /**
     * Constructs a new instance.
     * 
     * @param aProcessingCallback
     *            the callback from which we can process the messages
     * @param aDurableName
     *            the name of the durable to connect to
     * @param aDurableMessageSelector
     *            the message selector to use with the durable
     */
    public JmsDurableReaderAction( IJmsClientSingleMsgCallback aProcessingCallback,
                                   String aDurableName, String aDurableMessageSelector )
    {
        super();
        clientCallback_ = aProcessingCallback;
        durableName_ = aDurableName;
        durableMessageSelector_ = aDurableMessageSelector;
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsAction#actionInTransaction(javax.jms.Session,
     *      javax.jms.Destination)
     */
    @Override
    public void actionInTransaction( Session aSession, Destination aDestination )
                    throws JmsClientException
    {
        if ( !( aDestination instanceof Topic ) )
        {
            throw new JmsClientException( "Trying to build a durable subscription from a ["
                                          + aDestination.getClass().getName()
                                          + "], destination should be a topic" );
        }

        Topic topic = (Topic) aDestination;
        TopicSubscriber subscriber = null;
        try
        {
            subscriber = aSession.createDurableSubscriber( topic,
                                                           durableName_,
                                                           durableMessageSelector_,
                                                           true );
            retriveAndProcessMessage( aSession, subscriber );

        }
        catch ( JMSException jmse )
        {
            String err = "Failed to create Subscriber for destination [" + aDestination + "]: "
                         + jmse.getMessage();
            LOG.error( err, jmse );
            throw new JmsClientException( err, jmse );
        }
        finally
        {
            closeSubscriber( subscriber );
        }
    }

    private void closeSubscriber( TopicSubscriber aSubscriber )
    {
        if ( aSubscriber != null )
        {
            try
            {
                aSubscriber.close();
            }
            catch ( JMSException jmse )
            {
                LOG.error( "Failed to close subscriber", jmse );
            }
        }
    }

    private void retriveAndProcessMessage( Session aSession, TopicSubscriber aSubscriber )
                    throws JmsClientException
    {
        Message message = null;
        long start = 0;
        long end = 0;
        try
        {
            while ( ( message = aSubscriber.receiveNoWait() ) != null )
            {
                if ( LOG.isInfoEnabled() )
                {
                    start = System.currentTimeMillis();
                }

                clientCallback_.onReceived( message );

                if ( LOG.isInfoEnabled() )
                {
                    end = System.currentTimeMillis();
                    LOG.info( "Time to process message was [" + Long.toString( end - start )
                              + "] milliseconds" );
                }
                aSession.commit();
            }
        }
        catch ( JMSException jmse )
        {
            throw new JmsClientException( "Error occured when receiving message from destination",
                                          jmse );
        }
    }

}
