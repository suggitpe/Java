/*
 * JmsConnectionDetails.java created on 28 Jun 2007 06:07:29 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.connection.impl;

import org.suggs.gui.connection.IJmsConnectionDetails;

/**
 * Implementation bean of the connection details interface
 * 
 * @author suggitpe
 * @version 1.0 28 Jun 2007
 */
public class JmsConnectionDetails implements IJmsConnectionDetails
{

    private String mName_;
    private String mType_;
    private String mServer_;
    private String mPort_;
    private String mDestination_;

    /**
     * Constructs a new instance.
     * 
     * @param aName
     */
    public JmsConnectionDetails( String aName )
    {
        mName_ = aName;

    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionDetails#getConnectionName()
     */
    public String getConnectionName()
    {
        return mName_;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionDetails#getConnectionType()
     */
    public String getConnectionType()
    {
        return mType_;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionDetails#getConnectionServer()
     */
    public String getConnectionServer()
    {
        return mServer_;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionDetails#getConnectionPort()
     */
    public String getConnectionPort()
    {
        return mPort_;
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionDetails#getConnectionDestination()
     */
    public String getConnectionDestination()
    {
        return mDestination_;
    }
}
