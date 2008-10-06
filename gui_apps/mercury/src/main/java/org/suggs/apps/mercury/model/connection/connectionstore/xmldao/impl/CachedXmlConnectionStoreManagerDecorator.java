/*
 * CachedXmlConnectionStoreManagerDecorator.java created on 2 Oct 2008 18:47:56 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class decorates an existing implementation of an
 * IXmlConnectionStoreManager with a cache mechanism.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class CachedXmlConnectionStoreManagerDecorator
    implements InitializingBean, IXmlConnectionStoreManager
{

    private static final Log LOG = LogFactory.getLog( CachedXmlConnectionStoreManagerDecorator.class );

    private IXmlConnectionStoreManager mXmlConnectionStoreManager_;
    private Map<String, ConnectionDetails> mCache_;

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mXmlConnectionStoreManager_,
                        "Must set an xml connectionstore manager to cache the data with" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    public Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException
    {
        if ( mCache_ == null )
        {
            LOG.debug( "Loading data from persistent store" );
            mCache_ = mXmlConnectionStoreManager_.readConnectionData();
        }
        return mCache_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    public void saveConnectionData( Map<String, ConnectionDetails> map )
                    throws ConnectionStoreException
    {
        // refresh the cache
        LOG.debug( "Refreshing local cache" );
        mCache_ = map;
        mXmlConnectionStoreManager_.saveConnectionData( map );
    }

    /**
     * Getter for the connection store manager
     * 
     * @return the connection store manager
     */
    public IXmlConnectionStoreManager getXmlConnectionStoreManager()
    {
        return mXmlConnectionStoreManager_;
    }

    /**
     * Setter for the connection store manager
     * 
     * @param connStrManager
     *            the connection store manager to set
     */
    public void setXmlConnectionStoreManager( IXmlConnectionStoreManager connStrManager )
    {
        mXmlConnectionStoreManager_ = connStrManager;
    }

}
