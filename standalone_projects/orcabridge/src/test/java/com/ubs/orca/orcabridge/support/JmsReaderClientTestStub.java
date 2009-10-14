/*
 * JmsReaderClientTestStub.java created on 13 Oct 2009 19:37:27 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * TODO Write javadoc for JmsReaderClientTestStub
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2009
 */
public class JmsReaderClientTestStub implements IJmsReaderClient
{

    private static final Log LOG = LogFactory.getLog( JmsReaderClientTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient#startDurableSubscription(com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback)
     */
    @Override
    public void startDurableSubscription( IJmsClientSingleMsgCallback aCallback )
                    throws JmsClientException
    {
        LOG.debug( "***** Stub JMS Client - Performing startDurableSubscription" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsReaderClient#stopDurbleSubscription()
     */
    @Override
    public void stopDurbleSubscription() throws JmsClientException
    {
        LOG.debug( "***** Stub JMS Client - Performing stopDurableSubscription" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect()
     */
    @Override
    public void connect() throws JmsClientException
    {
        LOG.debug( "***** Stub JMS Client - Performing connect" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void connect( String aUsername, String aPassword ) throws JmsClientException
    {
        LOG.debug( "***** Stub JMS Client - Performing connect with credentials" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#disconnect()
     */
    @Override
    public void disconnect() throws JmsClientException
    {
        LOG.debug( "***** Stub JMS Client - Performing disconnect" );
    }
}
