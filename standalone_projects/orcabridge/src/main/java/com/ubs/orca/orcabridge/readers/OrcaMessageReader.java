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
import com.ubs.orca.orcabridge.MessageFacade;
import com.ubs.orca.orcabridge.OrcaBridgeException;

import org.springframework.util.Assert;

/**
 * Message reader class that will extract a message from the Orca
 * source and will pass it to a message reader.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public class OrcaMessageReader extends AbstractMessageReader
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageReader.class );

    private String mOrcaConnectionUrl_;

    private String mOrcaClientToken_;
    private IOrcaClient mOrcaClient_;
    private IOrcaIdentity mOrcaIdentity_;

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    public void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull( mOrcaConnectionUrl_,
                        "No Orca connection URL has been set in the OrcaMessageReader" );
        Assert.notNull( mOrcaClientToken_,
                        "No Orca client token has been set in the OrcaMessageReader" );
        Assert.notNull( mOrcaClient_,
                        "Orca Client is null, has the init method been called on the OrcaMessageReader?" );
    }

    /**
     * Public method to be called by the creator of the class.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException
    {
        try
        {
            // TODO: can this be injected through spring so the class
            // only knows about the orca interfaces?
            mOrcaClient_ = OrcaClientFactory.createOrcaClient( mOrcaIdentity_,
                                                               mOrcaConnectionUrl_,
                                                               true,
                                                               new OrcaReaderCallback() );
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeException( oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageReader#startReader()
     */
    @Override
    public void startReader() throws OrcaBridgeException
    {
        if ( mOrcaClient_ == null )
        {
            throw new OrcaBridgeException( "Cannot start OrcaMessageReader: Orca Client has not yet been initalised on the class" );
        }

        if ( LOG.isInfoEnabled() )
        {
            LOG.info( "Starting Orca client with url=[" + mOrcaConnectionUrl_ + "] and token=["
                      + mOrcaClientToken_ + "]" );
        }

        try
        {
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
     * @see com.ubs.orca.orcabridge.IMessageReader#stopReader()
     */
    @Override
    public void stopReader() throws OrcaBridgeException
    {
        try
        {
            mOrcaClient_.stop();
        }
        catch ( OrcaException oe )
        {
            String err = "Failed to stop Orca Client";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
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
     * Returns the value of orcaClientToken.
     * 
     * @return Returns the orcaClientToken.
     */
    public String getOrcaClientToken()
    {
        return mOrcaClientToken_;
    }

    /**
     * Sets the orcaClientToken field to the specified value.
     * 
     * @param aOrcaClientToken
     *            The orcaClientToken to set.
     */
    public void setOrcaClientToken( String aOrcaClientToken )
    {
        mOrcaClientToken_ = aOrcaClientToken;
    }

    /**
     * Returns the value of orcaClient.
     * 
     * @return Returns the orcaClient.
     */
    public IOrcaClient getOrcaClient()
    {
        return mOrcaClient_;
    }

    /**
     * Sets the orcaClient field to the specified value.
     * 
     * @param aOrcaClient
     *            The orcaClient to set.
     */
    public void setOrcaClient( IOrcaClient aOrcaClient )
    {
        mOrcaClient_ = aOrcaClient;
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
     * This is the core callback that we are passing to the
     * OrcaClient. It is called each time a new message is received
     * onto the sink client token.
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
            passOrcaMessageToTheMessageSender( aAttributesMesage );
        }

        /**
         * @see com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback#onReceived(com.ubs.orca.client.api.ITextConversationMessage)
         */
        @Override
        public void onReceived( ITextConversationMessage aTextConversationMessage )
                        throws Throwable
        {
            passOrcaMessageToTheMessageSender( aTextConversationMessage );
        }

        private void passOrcaMessageToTheMessageSender( IOrcaMessage aMessage )
                        throws OrcaBridgeException
        {
            try
            {
                getMessageSender().sendMessage( new MessageFacade( aMessage ) );
            }
            catch ( Throwable throwable )
            {
                LOG.error( "Issue ocurred in the sending of a message", throwable );
                throw new OrcaBridgeException( throwable );
            }
        }
    }

}
