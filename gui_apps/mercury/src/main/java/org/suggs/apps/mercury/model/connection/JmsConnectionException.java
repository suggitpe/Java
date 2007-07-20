/*
 * JmsConnectionException.java created on 20 Jul 2007 18:04:16 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection;

import org.suggs.apps.mercury.JmsHelperException;

/**
 * Exception to be used when there is an issue regarding the jms
 * connection (manager and store)
 * 
 * @author suggitpe
 * @version 1.0 20 Jul 2007
 */
public class JmsConnectionException extends JmsHelperException
{

    /**
     * Constructs a new instance.
     */
    public JmsConnectionException()
    {
        super();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     */
    public JmsConnectionException( String aMessage )
    {
        super( aMessage );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aMessage
     * @param aError
     */
    public JmsConnectionException( String aMessage, Throwable aError )
    {
        super( aMessage, aError );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aError
     */
    public JmsConnectionException( Throwable aError )
    {
        super( aError );
    }

}
