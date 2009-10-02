/*
 * OrcaMessageReader.java created on 22 Sep 2009 20:14:52 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IAttributesConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.IOrcaIdentity;
import com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback;
import com.ubs.orca.client.api.ITextConversationMessage;
import com.ubs.orca.client.api.OrcaClientFactory;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.common.bus.IOrcaMessage;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.MessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import org.springframework.util.Assert;

/**
 * Message reader class that will extract a message from the Orca
 * source and will pass it to a message processor.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public class OrcaSingleMessageReader extends AbstractMessageReader
{

    private static final Log LOG = LogFactory.getLog( OrcaSingleMessageReader.class );

    private String mOrcaConnectionUrl_;

    private IOrcaClient mOrcaClient_;
    private IOrcaIdentity mOrcaIdentity_;

    /**
     * Constructs a new instance.
     */
    public OrcaSingleMessageReader()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aOrcaIdentity
     *            Orca token
     * @param aOrcaConnectionUrl
     *            URL to the Orca broker
     * @param aMessageProcessor
     *            the message Processor
     */
    public OrcaSingleMessageReader( IOrcaIdentity aOrcaIdentity, String aOrcaConnectionUrl,
                                    IMessageProcessor aMessageProcessor )
    {
        super( aMessageProcessor );
        mOrcaIdentity_ = aOrcaIdentity;
        mOrcaConnectionUrl_ = aOrcaConnectionUrl;
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    public void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull( mOrcaConnectionUrl_,
                        "No Orca connection URL has been set in the OrcaSingleMessageReader" );
        Assert.notNull( mOrcaIdentity_,
                        "No Orca Identity has been set in the OrcaSingleMessageReader" );
    }

    /**
     * This init method is called once the class has been initialised.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException
    {
        LOG.info( "Initialising OrcaMessageReader ... " );

        try
        {
            mOrcaClient_ = OrcaClientFactory.createOrcaClient( mOrcaIdentity_,
                                                               mOrcaConnectionUrl_,
                                                               true,
                                                               new OrcaReaderCallback() );
        }
        catch ( OrcaException oe )
        {
            String err = "Failed to create Orca Client from init method";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStartReader()
     */
    @Override
    public void doStartReader() throws OrcaBridgeException
    {
        if ( mOrcaClient_ == null )
        {
            throw new OrcaBridgeException( "Must initialise the OrcaBridge prior to test execution" );
        }

        try
        {
            mOrcaClient_.connect();
            mOrcaClient_.start();
        }
        catch ( OrcaException oe )
        {
            String err = "Failed to start Orca Client";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStopReader()
     */
    @Override
    protected void doStopReader() throws OrcaBridgeException
    {
        if ( mOrcaClient_ != null )
        {
            try
            {
                mOrcaClient_.stop();
            }
            catch ( OrcaException oe )
            {
                String err = "Failed to stop Orca Client";
                LOG.error( err, oe );
            }
            finally
            {
                try
                {
                    mOrcaClient_.disconnect();
                }
                catch ( OrcaException oe )
                {
                    String err = "Failed to disconnect from Orca Client";
                    LOG.error( err, oe );
                    throw new OrcaBridgeException( err, oe );
                }
            }
        }
    }

    /**
     * Returns the value of orcaConnectionUrl.
     * 
     * @return Returns the orcaConnectionUrl.
     */
    public String getOrcaConnectionUrl()
    {
        return mOrcaConnectionUrl_;
    }

    /**
     * Sets the orcaConnectionUrl field to the specified value.
     * 
     * @param aOrcaConnectionUrl
     *            The orcaConnectionUrl to set.
     */
    public void setOrcaConnectionUrl( String aOrcaConnectionUrl )
    {
        mOrcaConnectionUrl_ = aOrcaConnectionUrl;
    }

    /**
     * Returns the value of orcaIdentity.
     * 
     * @return Returns the orcaIdentity.
     */
    public IOrcaIdentity getOrcaIdentity()
    {
        return mOrcaIdentity_;
    }

    /**
     * Sets the orcaIdentity field to the specified value.
     * 
     * @param aOrcaIdentity
     *            The orcaIdentity to set.
     */
    public void setOrcaIdentity( IOrcaIdentity aOrcaIdentity )
    {
        mOrcaIdentity_ = aOrcaIdentity;
    }

    /**
     * Sets the orcaClient. This method is here to support replacing
     * the orca client with mocks for unit tests.
     * 
     * @param aOrcaClient
     *            the orca client
     */
    void setOrcaClient( IOrcaClient aOrcaClient )
    {
        mOrcaClient_ = aOrcaClient;
    }

    /**
     * This is the core orca callback that we are passing to the
     * OrcaClient. It is called each time a new message is received
     * onto the sink client token.<br/>
     * <b>We have to be careful with transactions in this callback.
     * The completion of the Orca onReceived methods will end up with
     * a rollback and ultimate failure on the Orca client itself.</b>
     * 
     * @author suggitpe
     * @version 1.0 23 Sep 2009
     */
    private class OrcaReaderCallback implements IOrcaSinkSingleMsgCallback
    {

        /**
         * @see com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback#onReceived(com.ubs.orca.client.api.IAttributesConversationMessage)
         */
        @Override
        public void onReceived( IAttributesConversationMessage aAttributesMesage ) throws Throwable
        {
            passOrcaMessageToTheMessageProcessor( aAttributesMesage );
        }

        /**
         * @see com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback#onReceived(com.ubs.orca.client.api.ITextConversationMessage)
         */
        @Override
        public void onReceived( ITextConversationMessage aTextConversationMessage )
                        throws Throwable
        {
            passOrcaMessageToTheMessageProcessor( aTextConversationMessage );
        }

        private void passOrcaMessageToTheMessageProcessor( IOrcaMessage aMessage )
                        throws OrcaBridgeException
        {
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Passing received message [" + aMessage + "] to message Processor." );
            }

            try
            {
                getMessageProcessor().processMessage( new MessageFacade( aMessage ) );
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
                          + "] completed.  Allowing callback to complete so that Orca transaction can be committed." );
            }
        }
    }
}
