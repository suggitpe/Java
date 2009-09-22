/*
 * OrcaBridgeException.java created on 15 Sep 2009 07:04:24 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge;

/**
 * Exception to be used within the context of the Orca Bridge. This is
 * a general exception class for use within the Orca Bridge. This
 * class may be extended for any specialisations that are deemed
 * needed.
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2009
 */
public class OrcaBridgeException extends Exception
{

    /**
     * Constructs a new instance.
     */
    public OrcaBridgeException()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public OrcaBridgeException( String aMsg )
    {
        super( aMsg );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aException
     *            an exception to nest
     */
    public OrcaBridgeException( Throwable aException )
    {
        super( aException );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the message of the exception
     * @param aException
     *            a nested exception to embed in this exception
     */
    public OrcaBridgeException( String aMsg, Throwable aException )
    {
        super( aMsg, aException );
    }
}
