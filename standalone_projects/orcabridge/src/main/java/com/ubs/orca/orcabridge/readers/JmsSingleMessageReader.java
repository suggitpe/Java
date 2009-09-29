/*
 * JmsMessageReader.java created on 29 Sep 2009 06:54:09 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.jmsclient.JmsClientFactory;

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

    private String mBrokerUrl_;
    private IJmsClient mJmsClient_;

    /**
     * Constructs a new instance.
     */
    public JmsSingleMessageReader()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aBrokerUrl
     */
    public JmsSingleMessageReader( String aBrokerUrl )
    {
        super();
        mBrokerUrl_ = aBrokerUrl;
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doAfterPropertiesSet()
     */
    @Override
    protected void doAfterPropertiesSet() throws Exception
    {
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
            mJmsClient_ = JmsClientFactory.createJmsClient();
        }
        catch ( JmsClientException je )
        {
            String err = "Failed to create JMS Client from init method";
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
            mJmsClient_.connectAndStart();
        }
        catch ( JmsClientException je )
        {
            String err = "Failed to start JMS Client";
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
        if ( mJmsClient_ != null )
        {
            try
            {
                mJmsClient_.stopAndDisconnect();
            }
            catch ( JmsClientException je )
            {
                String err = "Failed to stop Orca Client";
                LOG.error( err, je );
            }
        }

    }

}
