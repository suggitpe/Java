/*
 * JmsClientException.java created on 29 Sep 2009 07:10:03 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient;

/**
 * Jms Client exception, used from within the JMS client.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2009
 */
public class JmsClientException extends Exception
{

    /**
     * Constructs a new instance.
     */
    public JmsClientException()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMsg
     *            the exception message
     */
    public JmsClientException( String aMsg )
    {
        super( aMsg );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aException
     *            an exception to nest
     */
    public JmsClientException( Throwable aException )
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
    public JmsClientException( String aMsg, Throwable aException )
    {
        super( aMsg, aException );
    }
}
