/*
 * CachedXmlConnectionStore.java created on 29 Sep 2008 07:05:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;

import java.io.File;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for CachedXmlConnectionStore
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2008
 */
public class JaxbXmlConnectionStoreManager implements IXmlConnectionStoreManager
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManager.class );
    private static final String MERCURY_HOME_DIR = System.getProperty( "user.home" ) + "/.mercury";
    private static final File MERCURY_FILE = new File( MERCURY_HOME_DIR + "/connectionStore.xml" );

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    public Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException
    {
        return null;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    public void saveConnectionData( Map<String, ConnectionDetails> map )
                    throws ConnectionStoreException
    {
    }

}
