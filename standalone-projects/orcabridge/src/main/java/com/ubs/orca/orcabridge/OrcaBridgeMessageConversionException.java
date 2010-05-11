/*
 * OrcaBridgeMessageConversionException.java created on 15 Sep 2009 07:04:24 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

/**
 * Exception to be used within the context of any Message Conversion. This is a general exception class for
 * use within the Orca Bridge.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2009
 */
public class OrcaBridgeMessageConversionException extends Exception {

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public OrcaBridgeMessageConversionException( String aMsg ) {
        super( aMsg );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the message of the exception
     * @param aException
     *            a nested exception to embed in this exception
     */
    // public OrcaBridgeMessageConversionException( String aMsg,
    // Throwable aException )
    // {
    // super( aMsg, aException );
    // }
}
