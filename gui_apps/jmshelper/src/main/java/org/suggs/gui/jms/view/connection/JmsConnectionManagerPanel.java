/*
 * JmsConnectionManagerPanel.java created on 22 Jun 2007 07:47:15 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.view.connection;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.jms.model.connection.EConnectionState;
import org.suggs.gui.jms.model.connection.IJmsConnectionManager;
import org.suggs.gui.jms.support.AbstractGridbagPanel;

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

    private static final ImageIcon IMG_ = new ImageIcon( "jms.gif" );

    private JTextField mStatus_ = new JTextField( EConnectionState.INITIAL.name() );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerPanel()
    {
        super();

        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        // status field
        final JLabel lStatus = new JLabel( "Connection Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, 1, 1 );

        mStatus_.setEditable( false );
        mStatus_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mStatus_, 1, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // connection factories
        final JLabel lConnFact = new JLabel( "Conn Factories:" );
        addFilledComponent( lConnFact, 2, 1 );

        // destinations
        final JLabel lDestinations = new JLabel( "Destinations:" );
        addFilledComponent( lDestinations, 3, 1 );

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
                String[] connLIst = new String[] { "test1", "TEST2" };
                String input = (String) JOptionPane.showInputDialog( JmsConnectionManagerPanel.this,
                                                                     "Please select the connection to load:",
                                                                     "Select connection",
                                                                     JOptionPane.INFORMATION_MESSAGE,
                                                                     IMG_,
                                                                     connLIst,
                                                                     "..." );
                LOG.debug( "Loading connection [" + input + "]" );
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
            }
        };
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
