/*
 * CachedXmlConnectionStore.java created on 29 Sep 2008 07:05:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.IFileManager;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * TODO Write javadoc for CachedXmlConnectionStore
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2008
 */
public class JaxbXmlConnectionStoreManager implements IXmlConnectionStoreManager, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManager.class );

    private static final String MERCURY_HOME_DIR = System.getProperty( "user.home" ) + "/.mercury";
    private static final File MERCURY_FILE = new File( MERCURY_HOME_DIR + "/connectionStore.xml" );

    private IFileManager mFileManager_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mFileManager_, "The file manager cannot be null, this needs to be injected" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    public Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException
    {
        String xmlClob = null;
        try
        {
            xmlClob = mFileManager_.retrieveClob( new File( MERCURY_HOME_DIR + MERCURY_FILE ) );
        }
        catch ( IOException ioe )
        {
            String err = "IOException caught when trying to retrieve data from the ";
            LOG.error( err, ioe );
            throw new ConnectionStoreException( err, ioe );
        }

        return null;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    public void saveConnectionData( Map<String, ConnectionDetails> map )
                    throws ConnectionStoreException
    {
        String xmlClob = "";

        // now we need to persist the xml clob
        try
        {
            mFileManager_.persistClob( xmlClob, new File( MERCURY_HOME_DIR + MERCURY_FILE ) );
        }
        catch ( IOException ioe )
        {
            throw new ConnectionStoreException( "Failed to persiste the clob file", ioe );
        }
    }

    /**
     * Setter for the file manager
     * 
     * @param aFileManager
     *            the file manager to set
     */
    public void setFileManager( IFileManager aFileManager )
    {
        mFileManager_ = aFileManager;
    }

    /**
     * Getter for the file manager
     * 
     * @return the file manager
     */
    public IFileManager getFileManager()
    {
        return mFileManager_;
    }

}
