/*
 * JmsConnectionManagerPanel.java created on 22 Jun 2007 07:47:15 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.JmsHelperException;
import org.suggs.gui.connection.EConnectionState;
import org.suggs.gui.connection.EConnectionType;
import org.suggs.gui.connection.IJmsConnectionDetails;
import org.suggs.gui.connection.IJmsConnectionManager;
import org.suggs.gui.connection.IJmsConnectionStore;
import org.suggs.gui.connection.impl.JmsConnectionDetails;
import org.suggs.gui.support.AbstractGridbagPanel;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class will manage the user interaction with the Connection
 * manager object.
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class JmsConnectionManagerPanel extends AbstractGridbagPanel implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionManagerPanel.class );

    private IJmsConnectionManager mConnMgr_;
    private IJmsConnectionStore mConnStr_;

    private static final Dimension SHORT_FIELD = new Dimension( 40, 20 );
    private static final Dimension MEDIUM_FIELD = new Dimension( 120, 20 );
    private static final Dimension LONG_FIELD = new Dimension( 240, 20 );
    // private static final Dimension HUGE_FIELD = new Dimension( 240,
    // 80 );

    private static final ImageIcon IMG_ = new ImageIcon( "jms.gif" );

    private JTextField mName_ = new JTextField();
    private JComboBox mType_ = new JComboBox();
    private JTextField mServer_ = new JTextField();
    private JTextField mPort_ = new JTextField();
    private JTextField mStatus_ = new JTextField( EConnectionState.INITIAL.name() );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerPanel()
    {
        super();

        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        this.setBorder( new TitledBorder( new EtchedBorder(), "Connection details" ) );

        // add in the connection name
        final JLabel lName = new JLabel( "Name:" );
        lName.setBorder( eb );
        addComponent( lName, 1, 1 );

        mName_.setEditable( false );
        mName_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mName_, 1, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection type
        final JLabel lType = new JLabel( "Type:" );
        lType.setBorder( eb );
        addComponent( lType, 2, 1 );

        mType_.setEditable( true );
        mType_.addItem( EConnectionType.MQ );
        mType_.addItem( EConnectionType.EMS );
        mType_.setPreferredSize( MEDIUM_FIELD );
        addComponent( mType_, 2, 2 );

        // add in the server
        final JLabel lServer = new JLabel( "Server:" );
        lServer.setBorder( eb );
        addComponent( lServer, 3, 1 );

        mServer_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mServer_, 3, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the port
        final JLabel lPort = new JLabel( "Port:" );
        lServer.setBorder( eb );
        addComponent( lPort, 4, 1 );

        mPort_.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( mPort_, 4, 2, 1, 1, GridBagConstraints.HORIZONTAL );

        // status field
        final JLabel lStatus = new JLabel( "Connection Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, 5, 1 );

        mStatus_.setEditable( false );
        mStatus_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mStatus_, 5, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add buttons at the right hand side
        final JButton bSave = new JButton( "Save" );
        bSave.setToolTipText( "Save named connection details" );
        bSave.addActionListener( createSaveActionListener() );
        addFilledComponent( bSave, 1, 5 );

        final JButton bLoad = new JButton( "Load" );
        bLoad.setToolTipText( "Load a previous connection detail" );
        bLoad.addActionListener( createLoadActionListener() );
        addFilledComponent( bLoad, 2, 5 );

        final JButton bTest = new JButton( "Test" );
        bTest.addActionListener( createTestConnActionListener() );
        bTest.setToolTipText( "Test connection settings with current setting" );
        addFilledComponent( bTest, 3, 5 );

        final JButton bConnect = new JButton( "Connect" );
        bConnect.addActionListener( createConnectActionListener() );
        bConnect.setToolTipText( "Connect with the defined connection parameters" );
        addFilledComponent( bConnect, 4, 5 );

        final JButton bDisconnect = new JButton( "Disconnect" );
        bDisconnect.addActionListener( createDisconnectActionListener() );
        bDisconnect.setToolTipText( "Disconnect from the current connection" );
        addFilledComponent( bDisconnect, 5, 5 );
    }

    /**
     * Creates an action listsner that will disconnect from the
     * current cnnection
     * 
     * @return the action listener
     */
    private ActionListener createDisconnectActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                try
                {
                    getConnectionManager().disconnect();
                }
                catch ( JmsHelperException jhe )
                {
                    showErrorDialog( "Failed to disconnect to JMS resource \n" + jhe.getMessage(), "Disconnection Error" );
                }
                refreshState();
            }
        };
    }

    /**
     * Creates an action listsner to connection to a given connection
     * 
     * @return the action listsner to connect
     */
    private ActionListener createConnectActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                try
                {
                    getConnectionManager().connect( buildJmsConnDetails() );
                }
                catch ( JmsHelperException jhe )
                {
                    showErrorDialog( "Failed to connect to JMS resource \n" + jhe.getMessage(), "Connection Error" );
                }
                refreshState();
            }
        };
    }

    /**
     * Creates an action listener to test the connection for the
     * loaded parameters
     * 
     * @return the action listener for testing a connection
     */
    private ActionListener createTestConnActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                if ( getConnectionManager().getConnectionState() == EConnectionState.CONNECTED )
                {
                    showErrorDialog( "Cannot test the connecton whilst there is an active connection", "User error" );
                    return;
                }

                if ( !getConnectionManager().testConnection( buildJmsConnDetails() ) )
                {
                    showErrorDialog( "Failed to connect to JMS provider", "Connection Test Failure" );
                }
                refreshState();
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
                String[] connLIst = getConnectionStore().getListOfKnownConnectionNames();
                String input = (String) JOptionPane.showInputDialog( JmsConnectionManagerPanel.this,
                                                                     "Please select the connection to load:",
                                                                     "Select connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     connLIst,
                                                                     "..." );
                LOG.debug( "Loading connection [" + input + "]" );
                IJmsConnectionDetails details = getConnectionStore().loadConnectionParameters( input );
                mName_.setText( details.getConnectionName() );
                mStatus_.setText( "Loaded details" );
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
                String input = (String) JOptionPane.showInputDialog( JmsConnectionManagerPanel.this,
                                                                     "Please enter a name for this conection",
                                                                     "Save connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     null,
                                                                     "..." );
                LOG.debug( "Saving connection as [" + input + "]" );
                getConnectionStore().saveConnectionParameters( buildJmsConnDetails() );
            }
        };
    }

    /**
     * Builds a connection details object from the text areas
     * 
     * @return the new connection details object
     */
    private IJmsConnectionDetails buildJmsConnDetails()
    {
        return new JmsConnectionDetails( mName_.getText(), (EConnectionType) mType_.getSelectedItem(), mServer_.getText(), mPort_.getText() );
    }

    /**
     * Refreshes the state of the status field with the state of the
     * connection manager
     */
    private void refreshState()
    {
        mStatus_.setText( mConnMgr_.getConnectionState().name() );
    }

    /**
     * Shows an error message dialog box
     * 
     * @param aMessage
     *            the message to show
     * @param aHeader
     *            the header of the dialog box
     */
    private void showErrorDialog( String aMessage, String aHeader )
    {
        JOptionPane.showMessageDialog( this, aMessage, aHeader, JOptionPane.ERROR_MESSAGE );
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnMgr_, "Must set the connection manager into the connection manager panel" );
        Assert.notNull( mConnStr_, "Must set the connection store into the connection manager panel" );
    }

    // ============== GETTERS AND SETTERS ============
    /**
     * Getter for the connection manager
     * 
     * @return the connection manager
     */
    public IJmsConnectionManager getConnectionManager()
    {
        return mConnMgr_;
    }

    /**
     * Setter for the connection manager
     * 
     * @param aConnMgr
     *            the connection manager to set
     */
    public void setConnectionManager( IJmsConnectionManager aConnMgr )
    {
        mConnMgr_ = aConnMgr;
    }

    /**
     * Getter for the connection store
     * 
     * @return the connection store
     */
    public IJmsConnectionStore getConnectionStore()
    {
        return mConnStr_;
    }

    /**
     * Setter for the connection store
     * 
     * @param aConnStr
     *            the connection store to set
     */
    public void setConnectionStore( IJmsConnectionStore aConnStr )
    {
        mConnStr_ = aConnStr;
    }

}
