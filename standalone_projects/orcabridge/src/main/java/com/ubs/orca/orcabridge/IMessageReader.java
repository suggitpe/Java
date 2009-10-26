/*
 * IMessageReader.java created on 22 Sep 2009 19:45:17 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

/**
 * High level interface to a message reader. The key role that the
 * message reader will perform will be to read in a message and
 * provide a capability to process it in some way. It is envisaged
 * that this will predominantly involve the process of routing the
 * message to a destination.
 * 
 * @author suggitpe
 * @version 1.0 22 Sep 2009
 */
public interface IMessageReader
{

    /**
     * Starts the reader process.
     * 
     * @throws OrcaBridgeException
     */
    void startReader() throws OrcaBridgeException;

    /**
     * Stops the reader process.
     * 
     * @throws OrcaBridgeException
     */
    void stopReader() throws OrcaBridgeException;

}
