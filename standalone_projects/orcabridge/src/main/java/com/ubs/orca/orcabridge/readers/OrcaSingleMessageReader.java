/*
 * OrcaMessageReader.java created on 22 Sep 2009 20:14:52 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.IOrcaIdentity;
import com.ubs.orca.client.api.IOrcaSinkSingleMsgCallback;
import com.ubs.orca.client.api.OrcaClientFactory;
import com.ubs.orca.client.api.OrcaException;
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

    private String orcaConnectionUrl_;
    private IOrcaIdentity orcaIdentity_;
    private IOrcaSinkSingleMsgCallback orcaCallback_;

    private IOrcaClient orcaClient_;

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    public void doAfterPropertiesSet() throws Exception
    {
        Assert.notNull( orcaConnectionUrl_,
                        "No Orca connection URL has been set in the OrcaSingleMessageReader" );
        Assert.notNull( orcaIdentity_,
                        "No Orca Identity has been set in the OrcaSingleMessageReader" );
        Assert.notNull( orcaCallback_,
                        "No Orca callback has been set on the OrcaSingleMessageReader" );
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
            orcaClient_ = OrcaClientFactory.createOrcaClient( orcaIdentity_,
                                                              orcaConnectionUrl_,
                                                              true,
                                                              orcaCallback_ );
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
        if ( orcaClient_ == null )
        {
            throw new OrcaBridgeException( "Must initialise the OrcaBridge prior to test execution" );
        }

        try
        {
            orcaClient_.connect();
            orcaClient_.start();
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
        if ( orcaClient_ != null )
        {
            try
            {
                orcaClient_.stop();
            }
            catch ( OrcaException oe )
            {
                LOG.error( "Errors occurred when trying to stop the Orca client", oe );
            }
            finally
            {
                completeDisconnect();
            }
        }
    }

    private void completeDisconnect() throws OrcaBridgeException
    {
        try
        {
            orcaClient_.disconnect();
        }
        catch ( OrcaException oe )
        {
            String err = "Failed to disconnect from Orca Client";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
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

    /**
     * Sets the orcaCallback field to the specified value.
     * 
     * @param orcaCallback
     *            The orcaCallback to set.
     */
    public void setOrcaCallback( IOrcaSinkSingleMsgCallback orcaCallback )
    {
        orcaCallback_ = orcaCallback;
    }

}
