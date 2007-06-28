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
import org.suggs.gui.connection.IJmsConnectionDetails;
import org.suggs.gui.connection.IJmsConnectionManager;
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

    private static final Dimension SHORT_FIELD = new Dimension( 40, 20 );
    private static final Dimension MEDIUM_FIELD = new Dimension( 120, 20 );
    private static final Dimension LONG_FIELD = new Dimension( 240, 20 );
    //private static final Dimension HUGE_FIELD = new Dimension( 240, 80 );

    private static final ImageIcon IMG_ = new ImageIcon( "jms.gif" );

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

        final JTextField tName = new JTextField();
        tName.setEditable( false );
        tName.setPreferredSize( LONG_FIELD );
        addFilledComponent( tName, 1, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection type
        final JLabel lType = new JLabel( "Type:" );
        lType.setBorder( eb );
        addComponent( lType, 2, 1 );

        final JComboBox cType = new JComboBox();
        cType.addItem( "MQ" );
        cType.addItem( "EMS" );
        cType.setPreferredSize( SHORT_FIELD );
        addComponent( cType, 2, 2 );

        // add in the server
        final JLabel lServer = new JLabel( "Server:" );
        lServer.setBorder( eb );
        addComponent( lServer, 3, 1 );

        final JTextField tServer = new JTextField();
        tServer.setPreferredSize( LONG_FIELD );
        addFilledComponent( tServer, 3, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the port
        final JLabel lPort = new JLabel( "Port:" );
        lServer.setBorder( eb );
        addComponent( lPort, 4, 1 );

        final JTextField tPort = new JTextField();
        tPort.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( tPort, 4, 2, 1, 1, GridBagConstraints.HORIZONTAL );

        // add in the destination
        final JLabel lDest = new JLabel( "Destination:" );
        lDest.setBorder( eb );
        addComponent( lDest, 5, 1 );

        final JTextField tDest = new JTextField();
        tDest.setPreferredSize( LONG_FIELD );
        addFilledComponent( tDest, 5, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // status field
        final JLabel lStatus = new JLabel( "Connection Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, 6, 1 );

        final JTextField tStatus = new JTextField( "Unknown" );
        tStatus.setEditable( false );
        tStatus.setPreferredSize( LONG_FIELD );
        addFilledComponent( tStatus, 6, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add buttons at the right hand side
        final JButton bSave = new JButton( "Save" );
        bSave.setToolTipText( "Save named connection details" );
        bSave.addActionListener( new ActionListener()
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
            }
        } );
        addComponent( bSave, 1, 5 );

        final JButton bLoad = new JButton( "Load" );
        bLoad.setToolTipText( "Load a previous connection detail" );
        bLoad.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                String[] connLIst = getConnectionManager().getListOfKnownConnectionNames();
                String input = (String) JOptionPane.showInputDialog( JmsConnectionManagerPanel.this,
                                                                     "Please select the connection to load:",
                                                                     "Select connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     connLIst,
                                                                     "..." );
                LOG.debug( "Loading connection [" + input + "]" );
                IJmsConnectionDetails details = getConnectionManager().loadConnectionParameters( input );
                tName.setText( details.getConnectionName() );
                tStatus.setText( "Loaded details" );
            }
        } );
        addComponent( bLoad, 2, 5 );

        final JButton bTest = new JButton( "Test" );
        bTest.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                getConnectionManager().testConnection();
            }
        } );
        bTest.setToolTipText( "Test connection settings with current setting" );
        addComponent( bTest, 3, 5 );

    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnMgr_, "Must set the connection manager into the connection manager panel" );
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

}
