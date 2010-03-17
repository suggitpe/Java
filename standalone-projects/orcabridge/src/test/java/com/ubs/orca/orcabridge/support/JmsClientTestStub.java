package com.ubs.orca.orcabridge.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;

/**
 * Stub class for the IJmsClient interface
 * 
 * @author suggitpe
 * @version 1.0 26 Oct 2009
 */
public class JmsClientTestStub implements IJmsClient
{

    private static final Log LOG = LogFactory.getLog( JmsClientTestStub.class );

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect()
     */
    @Override
    public void connect() throws JmsClientException
    {
        LOG.debug( "***** Stub JMS client - Performing Connect" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#connect(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void connect( String aUsername, String aPassword ) throws JmsClientException
    {
        LOG.debug( "***** Stub JMS client - Performning Connect with username=[" + aUsername
                   + "] and password=[" + aPassword + "]" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#disconnect()
     */
    @Override
    public void disconnect() throws JmsClientException
    {
        LOG.debug( "***** Stub JMS client - Performing disconnect" );
    }

    /**
     * @see com.ubs.orca.orcabridge.jmsclient.IJmsClient#processAction(com.ubs.orca.orcabridge.jmsclient.IJmsAction)
     */
    @Override
    public void processAction( IJmsAction aActionCallback ) throws JmsClientException
    {
        LOG.debug( "***** Stub JMS client - Performing processActionOnce with action=["
                   + aActionCallback + "]" );
    }

}
