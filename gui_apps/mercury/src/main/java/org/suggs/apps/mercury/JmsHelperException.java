/*
 * JmsHelperException.java created on 21 Jun 2007 08:07:19 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.mercury;

/**
 * This exception class is used by all elements within the application
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class JmsHelperException extends Exception
{

    /**
     * Constructs a new instance.
     */
    public JmsHelperException()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public JmsHelperException( String aMessage )
    {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public JmsHelperException( String aMessage, Throwable aError )
    {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public JmsHelperException( Throwable aError )
    {
        super( aError );
    }
}
