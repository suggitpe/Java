/*
 * ConnectionController.java created on 12 Jul 2007 16:30:12 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.controller.impl;

import org.suggs.gui.jms.model.connection.IJmsConnectionManager;
import org.suggs.gui.jms.model.connection.IJmsConnectionStore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.suggs.gui.jms.controller.IConnectionController;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * TODO Write javadoc for ConnectionController
 * 
 * @author suggitpe
 * @version 1.0 12 Jul 2007
 */
public class ConnectionController implements InitializingBean, IConnectionController
{

    private static final Log LOG = LogFactory.getLog( ConnectionController.class );

    private IJmsConnectionStore mConnectionStore_;
    private IJmsConnectionManager mConnectionManager_;

    /**
     * Constructs a new instance.
     * 
     * @param aStr
     *            the connection store
     * @param aMgr
     *            the connection manager
     */
    public ConnectionController( IJmsConnectionStore aStr, IJmsConnectionManager aMgr )
    {
        super();
        mConnectionStore_ = aStr;
        mConnectionManager_ = aMgr;

    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnectionStore_, "Must set the connection store in the connection controller" );
        Assert.notNull( mConnectionManager_, "Must set the connection manager in the connection controller" );
    }

    /**
     * Returns the value of ConnectionManager.
     * 
     * @return Returns the ConnectionManager.
     */
    public IJmsConnectionManager getConnectionManager()
    {
        return mConnectionManager_;
    }

    /**
     * Sets the ConnectionManager field to the specified value.
     * 
     * @param connectionManager
     *            The ConnectionManager to set.
     */
    public void setConnectionManager( IJmsConnectionManager connectionManager )
    {
        mConnectionManager_ = connectionManager;
    }

    /**
     * Returns the value of ConnectionStore.
     * 
     * @return Returns the ConnectionStore.
     */
    public IJmsConnectionStore getConnectionStore()
    {
        return mConnectionStore_;
    }

    /**
     * Sets the ConnectionStore field to the specified value.
     * 
     * @param connectionStore
     *            The ConnectionStore to set.
     */
    public void setConnectionStore( IJmsConnectionStore connectionStore )
    {
        mConnectionStore_ = connectionStore;
    }

}
