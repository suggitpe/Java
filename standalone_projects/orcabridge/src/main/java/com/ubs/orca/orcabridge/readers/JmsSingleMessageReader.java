/*
 * JmsMessageReader.java created on 29 Sep 2009 06:54:09 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import javax.jms.Message;
import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.jmsclient.impl.ContextBuilder;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsClientFactory;
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

    private String mContextFactory_;
    private String mBrokerUrl_;
    private String mConnectionFactoryName_;
    private String mDestinationName_;

    private IJmsClient mJmsClient_;

    /**
     * Constructs a new instance.
     */
    public JmsSingleMessageReader()
    {
        super();
    }

    public JmsSingleMessageReader( String aContextFactory, String aBrokerUrl )
    {
        super();
        mContextFactory_ = aContextFactory;
        mBrokerUrl_ = aBrokerUrl;
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    protected void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull( mContextFactory_,
                        "No Context factory has been set on the JmsSingleMessageReader" );
        Assert.notNull( mBrokerUrl_, "No Broker URL has been set on the JmsSingleMessageReader" );
    }

    /**
     * This init class is called once the class has been initialised.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException
    {

        try
        {
            Context ctx = ContextBuilder.buildInitialContextToJndi( mContextFactory_, mBrokerUrl_ );
            mJmsClient_ = JmsClientFactory.createSingleMessageClient( ctx,
                                                                      mConnectionFactoryName_,
                                                                      mDestinationName_ );
        }
        catch ( JmsClientException je )
        {
            final String err = "Failed to create JMS Client from init method";
            LOG.error( err, je );
            throw new OrcaBridgeException( err, je );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStartReader()
     */
    @Override
    protected void doStartReader() throws OrcaBridgeException
    {
        if ( mJmsClient_ == null )
        {
            throw new OrcaBridgeException( "Must initialise the OrcaBridge prior to test execution" );
        }

        try
        {
            mJmsClient_.connect();
            mJmsClient_.startDurableSubscription( new JmsReaderCallback() );
        }
        catch ( JmsClientException je )
        {
            final String err = "Failed to start JMS Client";
            LOG.error( err );
            throw new OrcaBridgeException( err, je );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStopReader()
     */
    @Override
    protected void doStopReader() throws OrcaBridgeException
    {
        if ( mJmsClient_ != null )
        {
            try
            {
                mJmsClient_.stopDurbleSubscription();
                mJmsClient_.disconnect();
            }
            catch ( JmsClientException je )
            {
                LOG.error( "Errors occured when trying to stop the JMS client", je );
            }
        }
    }

    /**
     * Returns the value of contextFactory.
     * 
     * @return Returns the contextFactory.
     */
    public String getContextFactory()
    {
        return mContextFactory_;
    }

    /**
     * Sets the contextFactory field to the specified value.
     * 
     * @param aContextFactory
     *            The contextFactory to set.
     */
    public void setContextFactory( String aContextFactory )
    {
        mContextFactory_ = aContextFactory;
    }

    /**
     * Returns the value of brokerUrl.
     * 
     * @return Returns the brokerUrl.
     */
    public String getBrokerUrl()
    {
        return mBrokerUrl_;
    }

    /**
     * Sets the brokerUrl field to the specified value.
     * 
     * @param aBrokerUrl
     *            The brokerUrl to set.
     */
    public void setBrokerUrl( String aBrokerUrl )
    {
        mBrokerUrl_ = aBrokerUrl;
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
    private class JmsReaderCallback implements IJmsClientSingleMsgCallback
    {

        /**
         * @see com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback#onReceived(javax.jms.Message)
         */
        @Override
        public void onReceived( Message aMessage ) throws Throwable
        {
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Passing received message [" + aMessage + "] to message Processor." );
            }

            try
            {
                getMessageProcessor().processMessage( MessageFacadeFactory.createMessageAdapter( aMessage ) );
            }
            catch ( Throwable throwable )
            {
                LOG.error( "Issue ocurred in the sending of a message", throwable );
                throw new OrcaBridgeException( throwable );
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
