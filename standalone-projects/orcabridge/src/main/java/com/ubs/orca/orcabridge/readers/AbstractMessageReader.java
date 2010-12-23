/*
 * AbstractMessageReader.java created on 22 Sep 2009 20:07:31 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.orca.orcabridge.IMessageReader;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * Abstract class that will manage the message reader interaction with the message processors.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public abstract class AbstractMessageReader implements IMessageReader {

    private static final Logger LOG = LoggerFactory.getLogger( AbstractMessageReader.class );
    static final String STATE_UNINITIALISED = "Uninintialised";
    static final String STATE_STARTING = "Starting";
    static final String STATE_RUNNING = "Running";
    static final String STATE_STOPPING = "Stopping";
    static final String STATE_STOPPED = "Stopped";

    private String state = STATE_UNINITIALISED;

    /**
     * @see com.ubs.orca.orcabridge.IMessageReader#startReader()
     */
    @Override
    public synchronized void startReader() throws OrcaBridgeException {
        LOG.debug( "Starting Orca Bridge." );
        state = STATE_STARTING;
        doStartReader();
        state = STATE_RUNNING;
    }

    /**
     * Template delegation of the actual reader start process to the underlying specific middleware reader
     * 
     * @throws OrcaBridgeException
     */
    protected abstract void doStartReader() throws OrcaBridgeException;

    /**
     * @see com.ubs.orca.orcabridge.IMessageReader#stopReader()
     */
    @Override
    public synchronized void stopReader() throws OrcaBridgeException {
        state = STATE_STOPPING;
        LOG.debug( "Stopping Orca Bridge." );
        doStopReader();
        state = STATE_STOPPED;
    }

    /**
     * Template delegation of the actual reader stop process to the underlying specific middleware reader.
     * 
     * @throws OrcaBridgeException
     */
    protected abstract void doStopReader() throws OrcaBridgeException;

    /**
     * Getter for the state of the reader.
     * 
     * @return the state of the reader
     */
    public synchronized String getState() {
        return state;
    }
}
