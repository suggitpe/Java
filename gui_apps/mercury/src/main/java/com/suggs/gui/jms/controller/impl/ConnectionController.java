/*
 * ConnectionController.java created on 12 Jul 2007 16:30:12 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.controller.impl;

import org.suggs.apps.mercury.JmsHelperException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.suggs.gui.jms.controller.IConnectionController;
import com.suggs.gui.jms.model.connection.IJmsConnectionManager;
import com.suggs.gui.jms.model.connection.IJmsConnectionStore;
import com.suggs.gui.jms.view.connection.JmsConnectionButtons;
import com.suggs.gui.jms.view.connection.JmsConnectionManagerPanel;
import com.suggs.gui.jms.view.connection.JmsConnectionStorePanel;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This is the main controller for theconnection part of the GUI.
 * 
 * @author suggitpe
 * @version 1.0 12 Jul 2007
 */
public class ConnectionController implements InitializingBean, IConnectionController
{

    private static final Log LOG = LogFactory.getLog( ConnectionController.class );
    private static final ImageIcon IMG_ = new ImageIcon( "jms.gif" );

    // models
    private IJmsConnectionStore mConnStoreModel_;
    private IJmsConnectionManager mConnManagerModel_;

    // views
    private JmsConnectionButtons mButtonsView_;
    private JmsConnectionManagerPanel mConnManagerView_;
    private JmsConnectionStorePanel mConnStoreView_;

    /**
     * Constructs a new instance.
     */
    private ConnectionController()
    {
        throw new IllegalStateException();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aStr
     *            the connection store
     * @param aMgr
     *            the connection manager
     * @param aButtons
     *            the buttons panel
     * @param aStrPanel
     *            the connection store panel
     * @param aMgrPanel
     *            the onnection manager panel
     */
    public ConnectionController( IJmsConnectionStore aStr, IJmsConnectionManager aMgr, JmsConnectionButtons aButtons,
                                 JmsConnectionStorePanel aStrPanel, JmsConnectionManagerPanel aMgrPanel )
    {
        super();
        mConnStoreModel_ = aStr;
        mConnManagerModel_ = aMgr;
        mButtonsView_ = aButtons;
        mConnStoreView_ = aStrPanel;
        mConnManagerView_ = aMgrPanel;

        init();
    }

    /**
     * Local method to initialise itself and all of the inter related
     * objects
     */
    private void init()
    {
        LOG.debug( "Initialising the Connection Controller" );
        mConnStoreView_.initialise( mConnStoreModel_.getState() );
        mConnStoreView_.loadDefaultValues();
        mConnManagerView_.initialise( mConnManagerModel_.getConnectionState().name() );

        mButtonsView_.addLoadActionListener( createLoadActionListener() );
        mButtonsView_.addSaveActionListener( createSaveActionListener() );
        mButtonsView_.addTestActionListener( createTestConnActionListener() );
        mButtonsView_.addConnectActionListener( createConnectActionListener() );
        mButtonsView_.addDisconnectActionListener( createDisconnectActionListener() );
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnStoreModel_, "Must set the connection store in the connection controller" );
        Assert.notNull( mConnManagerModel_, "Must set the connection manager in the connection controller" );
        Assert.notNull( mButtonsView_, "Must set the buttons view in the connection controller" );
        Assert.notNull( mConnStoreView_, "Must set the connection store view in the connection controller" );
        Assert.notNull( mConnManagerView_, "Must set the connection manager view in the connection controller" );
    }

    /**
     * Creates a new Test connection action listener
     * 
     * @return a new test connection action listener
     */
    private ActionListener createTestConnActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                mConnManagerModel_.testConnection( mConnStoreView_.getConnectionDetails() );
            }
        };
    }

    /**
     * Creates a new connect action listsner
     * 
     * @return a new connect action listener
     */
    private ActionListener createConnectActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                try
                {
                    mConnManagerModel_.connect( mConnStoreView_.getConnectionDetails() );
                }
                catch ( JmsHelperException jhe )
                {
                }
            }
        };
    }

    /**
     * Creates a new disconnect action listsner
     * 
     * @return a new disconnect action listener
     */
    private ActionListener createDisconnectActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                try
                {
                    mConnManagerModel_.disconnect();
                }
                catch ( JmsHelperException jhe )
                {
                }
            }
        };
    }

    /**
     * Creates an action listener for the loading of connection
     * pramters from a source
     * 
     * @return
     */
    private ActionListener createLoadActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                LOG.debug( "action performed is " + arg0.getActionCommand() );
                String[] connLIst = new String[] { "test1", "TEST2" };
                String input = (String) JOptionPane.showInputDialog( mConnStoreView_,
                                                                     "Please select the connection to load:",
                                                                     "Select connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     connLIst,
                                                                     "..." );
                if ( input != null )
                {
                    LOG.debug( "Loading connection [" + input + "]" );
                }
                else
                {
                    LOG.debug( "Load canceled" );
                }
            }
        };
    }

    /**
     * Creates a new action listener for the saving of a set of
     * connection parameters
     * 
     * @return the action listener
     */
    private ActionListener createSaveActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                // first we should make sure that the conn dedtails
                // have been saved correctly
                String input = (String) JOptionPane.showInputDialog( mConnStoreView_,
                                                                     "Please enter a name for this conection",
                                                                     "Save connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     null,
                                                                     "..." );
                LOG.debug( "Saving connection as [" + input + "] [" + arg0.getActionCommand() + "]" );
            }
        };
    }

    /**
     * Returns the value of ConnectionManager.
     * 
     * @return Returns the ConnectionManager.
     */
    public IJmsConnectionManager getConnectionManager()
    {
        return mConnManagerModel_;
    }

    /**
     * Returns the value of ConnectionStore.
     * 
     * @return Returns the ConnectionStore.
     */
    public IJmsConnectionStore getConnectionStore()
    {
        return mConnStoreModel_;
    }

    /**
     * Returns the value of ButtonsView.
     * 
     * @return Returns the ButtonsView.
     */
    public JmsConnectionButtons getButtonsView()
    {
        return mButtonsView_;
    }

    /**
     * Returns the value of ConnManagerView.
     * 
     * @return Returns the ConnManagerView.
     */
    public JmsConnectionManagerPanel getConnManagerView()
    {
        return mConnManagerView_;
    }

    /**
     * Returns the value of ConnStoreView.
     * 
     * @return Returns the ConnStoreView.
     */
    public JmsConnectionStorePanel getConnStoreView()
    {
        return mConnStoreView_;
    }

}
