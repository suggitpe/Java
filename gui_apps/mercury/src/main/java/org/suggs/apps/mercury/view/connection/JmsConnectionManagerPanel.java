/*
 * JmsConnectionManagerPanel.java created on 22 Jun 2007 07:47:15 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.view.connection;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.manager.JmsConnectionManager;
import org.suggs.apps.mercury.support.AbstractGridbagPanel;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class will manage the user interaction with the Connection
 * manager object.
 * 
 * @author suggitpe
 * @version 1.0 22 Jun 2007
 */
public class JmsConnectionManagerPanel extends AbstractGridbagPanel implements InitializingBean, Observer
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionManagerPanel.class );

    private JmsConnectionManager mConnMgr_;

    private JTextField mStatus_ = new JTextField();
    private JComboBox mType_ = new JComboBox();
    private JComboBox mConnectionFactories_ = new JComboBox();
    private JComboBox mDestinations_ = new JComboBox();

    private Map<String, Set<String>> mAvailableConnFacts_;
    private Map<String, Set<String>> mAvailableDests_;

    /**
     * Constructs a new instance. This is hidden as it is silly to
     * have an observer with nothing to observe.
     */
    private JmsConnectionManagerPanel()
    {
        throw new IllegalStateException();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aConnectionManager
     *            The connection manager that we will be observing
     */
    public JmsConnectionManagerPanel( JmsConnectionManager aConnectionManager )
    {
        super( "Connection Manager" );
        mConnMgr_ = aConnectionManager;
        mConnMgr_.addObserver( this );
    }

    /**
     * Called through the controller to initialise the panel
     * 
     * @param aInitialStatus
     *            the initial status for the panel to display
     */
    public void initialise( String aInitialStatus )
    {
        int i = 1;
        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        // status field
        final JLabel lStatus = new JLabel( "Connection Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, i, 1 );

        mStatus_.setText( aInitialStatus );
        mStatus_.setEditable( false );
        mStatus_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mStatus_, i, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // connection type
        final JLabel lType = new JLabel( "Conn Type" );
        addFilledComponent( lType, ++i, 1 );
        mType_.setPreferredSize( MEDIUM_FIELD );
        addComponent( mType_, i, 2 );

        // connection factories
        final JLabel lConnFact = new JLabel( "Conn Factories:" );
        addFilledComponent( lConnFact, ++i, 1 );
        mConnectionFactories_.setPreferredSize( LONG_FIELD );
        addComponent( mConnectionFactories_, i, 2 );

        // destinations
        final JLabel lDestinations = new JLabel( "Destinations:" );
        addFilledComponent( lDestinations, ++i, 1 );
        mDestinations_.setPreferredSize( LONG_FIELD );
        addComponent( mDestinations_, i, 2 );

        mType_.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent e )
            {
                String choice = (String) ( (JComboBox) e.getSource() ).getSelectedItem();
                LOG.debug( "Connection type =[" + choice + "]" );
                loadConnectionValues( choice );
            }
        } );
    }

    /**
     * @see java.util.Observer#update(java.util.Observable,
     *      java.lang.Object)
     */
    public void update( Observable aObserved, Object arg1 )
    {
        mStatus_.setText( mConnMgr_.getConnectionState().name() );
    }

    /**
     * Populate the combo boxes with the relevant data
     * 
     * @param aDetails
     *            the details from which you can derive the required
     *            data
     */
    public void loadTypeValues( IJmsConnectionDetails aDetails )
    {
        mAvailableConnFacts_ = aDetails.getConnectionFactories();
        mAvailableDests_ = aDetails.getDestinations();
        Set<String> s = mAvailableConnFacts_.keySet();
        updateType( s.toArray( new String[s.size()] ) );
    }

    /**
     * Loads the connection data into the comboboxes
     * 
     * @param aType
     *            the connection type
     */
    public void loadConnectionValues( String aType )
    {
        if ( aType == null )
        {
            mConnectionFactories_.removeAllItems();
            mConnectionFactories_.setEditable( false );
            mDestinations_.removeAllItems();
            mDestinations_.setEditable( false );
        }
        else
        {
            Set<String> conns = mAvailableConnFacts_.get( aType.toLowerCase() );
            updateConnectionFactories( conns.toArray( new String[conns.size()] ) );
            Set<String> dests = mAvailableDests_.get( aType.toLowerCase() );
            updateDestinations( dests.toArray( new String[dests.size()] ) );
        }
    }

    /**
     * Populate the connection type combo box
     * 
     * @param aItems
     *            the values to add to the combo box
     */
    public void updateType( String[] aItems )
    {
        mType_.removeAllItems();
        mType_.addItem( null );
        for ( String item : aItems )
        {
            mType_.addItem( item.toUpperCase() );
        }
        mType_.setEditable( true );
    }

    /**
     * Populate the connection factory combo box with values
     * 
     * @param aItems
     *            the values to add to the combo box
     */
    public void updateConnectionFactories( String[] aItems )
    {
        mConnectionFactories_.removeAllItems();
        for ( String item : aItems )
        {
            mConnectionFactories_.addItem( item );
        }
        mConnectionFactories_.setEditable( true );
    }

    /**
     * Populate the destinations combo box
     * 
     * @param aItems
     *            the items to add to the destinations combo box
     */
    public void updateDestinations( String[] aItems )
    {
        mDestinations_.removeAllItems();
        for ( String item : aItems )
        {
            mDestinations_.addItem( item );
        }
        mDestinations_.setEditable( true );
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
    public JmsConnectionManager getConnectionManager()
    {
        return mConnMgr_;
    }

    /**
     * Setter for the connection manager
     * 
     * @param aConnMgr
     *            the connection manager to set
     */
    public void setConnectionManager( JmsConnectionManager aConnMgr )
    {
        mConnMgr_ = aConnMgr;
    }

}
