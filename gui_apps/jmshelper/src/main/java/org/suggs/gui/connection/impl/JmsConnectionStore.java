/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.connection.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.connection.IJmsConnectionDetails;
import org.suggs.gui.connection.IJmsConnectionStore;

/**
 * Implementation of the IJmsConnectionStore. This implementation will
 * use an XML file stored in the users home directory (in a .jmshelper
 * dir), for the persistence of the connection details.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class JmsConnectionStore implements IJmsConnectionStore
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionStore.class );

    /**
     * @see org.suggs.gui.connection.IJmsConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName )
    {
        // firstly validate that all of the conn parameters are there,
        // and then we can load them into the class
        LOG.debug( "Loaded connecvtion parameters" );

        return new JmsConnectionDetails( aName );

    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        return new String[] { "Conn1", "Conn2" };
    }

    /**
     * @see org.suggs.gui.connection.IJmsConnectionStore#saveConnectionParameters(org.suggs.gui.connection.IJmsConnectionDetails)
     */
    public void saveConnectionParameters( IJmsConnectionDetails aDetails )
    {
        LOG.warn( "Save impl has not been done yet" );
    }

}
