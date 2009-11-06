/*
 * OrcaMessageSender.java created on 29 Oct 2009 17:39:26 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.processors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IConversationMessage;
import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.IOrcaIdentity;
import com.ubs.orca.client.api.OrcaClientFactory;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.IMessageFacade;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.OrcaBridgeMessageConversionException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Class to send messages to an Orca Token.
 * 
 * @author suggitpe
 * @version 1.0 29 Oct 2009
 */
public class OrcaMessageSender implements IMessageProcessor, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( OrcaMessageSender.class );

    private String orcaConnectionUrl_;
    private IOrcaIdentity orcaIdentity_;

    private IOrcaClient orcaClient_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( orcaConnectionUrl_,
                        "No Orca connection URL has been set in the OrcaSingleMessageReader" );
        Assert.notNull( orcaIdentity_,
                        "No Orca Identity has been set in the OrcaSingleMessageReader" );
    }

    /**
     * Initialise the sender.
     * 
     * @throws OrcaBridgeException
     */
    public void init() throws OrcaBridgeException
    {
        LOG.info( "Initialising the sender Orca connection" );

        try
        {
            orcaClient_ = OrcaClientFactory.createOrcaClient( orcaIdentity_,
                                                              orcaConnectionUrl_,
                                                              true );
        }
        catch ( OrcaException oe )
        {
            String err = "Failed to create Orca Client from init method";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
        }

        try
        {
            orcaClient_.connect();
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeException( "Failed to initialise the Orca client", oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.IMessageProcessor#processMessage(com.ubs.orca.orcabridge.IMessageFacade)
     */
    @Override
    public void processMessage( IMessageFacade aMessageFacade ) throws OrcaBridgeException
    {
        try
        {
            IConversationMessage orcaMessage = aMessageFacade.buildOrcaMesage( orcaClient_ );
            orcaClient_.send( orcaMessage );
        }
        catch ( OrcaException oe )
        {
            throw new OrcaBridgeException( "Failed to send Orca message", oe );
        }
        catch ( OrcaBridgeMessageConversionException obmce )
        {
            throw new OrcaBridgeException( "Failed to create Orca message from message facade",
                                           obmce );
        }

    }

    /**
     * Sets the orcaConnectionUrl field to the specified value.
     * 
     * @param aOrcaConnectionUrl
     *            The orcaConnectionUrl to set.
     */
    public void setOrcaConnectionUrl( String aOrcaConnectionUrl )
    {
        orcaConnectionUrl_ = aOrcaConnectionUrl;
    }

    /**
     * Sets the orcaIdentity field to the specified value.
     * 
     * @param aOrcaIdentity
     *            The orcaIdentity to set.
     */
    public void setOrcaIdentity( IOrcaIdentity aOrcaIdentity )
    {
        orcaIdentity_ = aOrcaIdentity;
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
        orcaClient_ = aOrcaClient;
    }

}
