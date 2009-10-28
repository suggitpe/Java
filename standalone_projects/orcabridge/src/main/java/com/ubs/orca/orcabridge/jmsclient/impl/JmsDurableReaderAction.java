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
     */
    public JmsDurableReaderAction()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aProcessingCallback
     *            the callback from which we can process the messages
     * @param aDurableName
     *            the name of teh durable to connect to
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
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsAction#action(javax.jms.Session,
     *      javax.jms.Destination)
     */
    @Override
    public void action( Session aSession, Destination aDestinstion ) throws JmsClientException
    {
        createDurableSubscriptionAndProcess( aSession, aDestinstion );
        try
        {
            aSession.commit();
        }
        catch ( JMSException jmse )
        {
            throw new JmsClientException( "Failed to commit JMS transaction", jmse );
        }
    }

    private void createDurableSubscriptionAndProcess( Session aSession, Destination aDestination )
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
            retriveAndProcessMessages( aSession, subscriber );

        }
        catch ( JMSException jmse )
        {
            String err = "Failed to create Subscriber for destination [" + aDestination + "]";
            LOG.error( err, jmse );
            throw new JmsClientException( err, jmse );
        }
        finally
        {
            if ( subscriber != null )
            {
                try
                {
                    subscriber.close();
                }
                catch ( JMSException jmse )
                {
                    LOG.error( "Failed to close subscriber" );
                }
            }
        }
    }

    private void retriveAndProcessMessages( Session aSession, TopicSubscriber aSubscriber )
                    throws JmsClientException
    {
        Message message = null;
        try
        {
            // TODO: review this - all done in one transaction - think
            // it might be better to return to signify that a message
            // has been received
            while ( ( message = aSubscriber.receive() ) != null )
            {
                clientCallback_.onReceived( message );
                aSession.commit();
            }
        }
        catch ( JMSException jmse )
        {
            throw new JmsClientException( "Error occured when receiving message from destination",
                                          jmse );
        }
    }

    /**
     * Sets the callback field to the specified value.
     * 
     * @param aCallback
     *            The callback to set.
     */
    public void setCallback( IJmsClientSingleMsgCallback aCallback )
    {
        clientCallback_ = aCallback;
    }

    /**
     * Sets the durableName field to the specified value.
     * 
     * @param aDurableName
     *            The durableName to set.
     */
    public void setDurableName( String aDurableName )
    {
        durableName_ = aDurableName;
    }

    /**
     * Sets the durableMessageSelector field to the specified value.
     * 
     * @param aDurableMessageSelector
     *            The durableMessageSelector to set.
     */
    public void setDurableMessageSelector( String aDurableMessageSelector )
    {
        durableMessageSelector_ = aDurableMessageSelector;
    }

}
