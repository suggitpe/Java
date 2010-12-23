/*
 * OrcaMessageReader.java created on 22 Sep 2009 20:14:52 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * Message reader class that will extract a message from the Orca source and will pass it to a message
 * processor.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public class OrcaSingleMessageReader extends AbstractMessageReader {

    private static final Logger LOG = LoggerFactory.getLogger( OrcaSingleMessageReader.class );

    private IOrcaClient orcaClient;

    /**
     * Constructs a new instance.
     */
    public OrcaSingleMessageReader( IOrcaClient aOrcaClient ) {
        orcaClient = aOrcaClient;
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStartReader()
     */
    @Override
    public void doStartReader() throws OrcaBridgeException {
        try {
            orcaClient.connect();
            orcaClient.start();
        }
        catch ( OrcaException oe ) {
            String err = "Failed to start Orca Client";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
        }
    }

    /**
     * @see com.ubs.orca.orcabridge.readers.AbstractMessageReader#doStopReader()
     */
    @Override
    protected void doStopReader() throws OrcaBridgeException {
        try {
            orcaClient.stop();
        }
        catch ( OrcaException oe ) {
            LOG.error( "Errors occurred when trying to stop the Orca client", oe );
        }
        finally {
            completeDisconnect();
        }
    }

    private void completeDisconnect() throws OrcaBridgeException {
        try {
            orcaClient.disconnect();
        }
        catch ( OrcaException oe ) {
            String err = "Failed to disconnect from Orca Client";
            LOG.error( err, oe );
            throw new OrcaBridgeException( err, oe );
        }
    }

    /**
     * Sets the orcaClient. This method is here to support replacing the orca client with mocks for unit
     * tests.
     * 
     * @param aOrcaClient
     *            the orca client
     */
    public void setOrcaClient( IOrcaClient aOrcaClient ) {
        orcaClient = aOrcaClient;
    }

}
