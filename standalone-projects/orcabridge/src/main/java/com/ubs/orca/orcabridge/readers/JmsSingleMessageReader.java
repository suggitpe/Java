/*
 * JmsMessageReader.java created on 29 Sep 2009 06:54:09 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ubs.orca.orcabridge.OrcaBridgeException;
import com.ubs.orca.orcabridge.jmsclient.IJmsAction;
import com.ubs.orca.orcabridge.jmsclient.IJmsClient;
import com.ubs.orca.orcabridge.jmsclient.IJmsClientSingleMsgCallback;
import com.ubs.orca.orcabridge.jmsclient.JmsClientException;
import com.ubs.orca.orcabridge.jmsclient.impl.JmsDurableReaderAction;

/**
 * Message reader class that will read a single message from a JMS source and pass it on to a message
 * processor.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public class JmsSingleMessageReader extends AbstractMessageReader {

    private static final Log LOG = LogFactory.getLog( JmsSingleMessageReader.class );

    private IJmsClient jmsClient;
    private String durableName;
    private String messageSelector;
    private IJmsClientSingleMsgCallback jmsCallback;

    /**
     * Constructs a new instance.
     */
    public JmsSingleMessageReader( IJmsClient aJmsClient,
                                   IJmsClientSingleMsgCallback aJmsClientSingleMsgCallback,
                                   String aDurableName, String aMessageSelector ) {
        jmsClient = aJmsClient;
        jmsCallback = aJmsClientSingleMsgCallback;
        durableName = aDurableName;
        messageSelector = aMessageSelector;
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStartReader()
     */
    @Override
    protected void doStartReader() throws OrcaBridgeException {
        connectToJmsClient();
        startReaderAction();
    }

    private void connectToJmsClient() throws OrcaBridgeException {
        try {
            jmsClient.connect();
        }
        catch ( JmsClientException je ) {
            final String err = "Failed to connect to JMS client";
            LOG.error( err );
            throw new OrcaBridgeException( err, je );
        }
    }

    private void startReaderAction() throws OrcaBridgeException {
        IJmsAction action = new JmsDurableReaderAction( jmsCallback, durableName, messageSelector );
        try {
            jmsClient.processAction( action );
        }
        catch ( JmsClientException jce ) {
            final String err = "Failed to start reader against JMS client";
            LOG.error( err );
            throw new OrcaBridgeException( err, jce );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStopReader()
     */
    @Override
    protected void doStopReader() throws OrcaBridgeException {
        try {
            jmsClient.disconnect();
        }
        catch ( JmsClientException je ) {
            String err = "Errors occured when trying to stop the JMS client";
            LOG.error( err, je );
            throw new OrcaBridgeException( err, je );
        }
    }

    /**
     * Sets the jmsClient. This method is here to support replacing the jms client with mocks for unit tests.
     * 
     * @param aJmsClient
     *            the JMS client
     */
    public void setJmsClient( IJmsClient aJmsClient ) {
        jmsClient = aJmsClient;
    }

    /**
     * Sets the durableName field to the specified value.
     * 
     * @param aDurableName
     *            The durableName to set.
     */
    public void setDurableName( String aDurableName ) {
        durableName = aDurableName;
    }

    /**
     * Sets the messageSelector field to the specified value.
     * 
     * @param aMessageSelector
     *            The messageSelector to set.
     */
    public void setMessageSelector( String aMessageSelector ) {
        messageSelector = aMessageSelector;
    }

    /**
     * Sets the jmsCallback field to the specified value.
     * 
     * @param aJmsCallback
     *            The jmsCallback to set.
     */
    public void setJmsCallback( IJmsClientSingleMsgCallback aJmsCallback ) {
        jmsCallback = aJmsCallback;
    }

}
