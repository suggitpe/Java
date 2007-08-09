/*
 * JmsConnectionStorePanel.java created on 9 Jul 2007 17:32:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.view.connection;

import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IConnectionDetails;
import org.suggs.apps.mercury.model.connection.IConnectionStore;
import org.suggs.apps.mercury.model.connection.MercuryConnectionStoreException;
import org.suggs.apps.mercury.model.connection.store.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.store.ConnectionStore;
import org.suggs.apps.mercury.support.AbstractGridbagPanel;

import java.awt.GridBagConstraints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Panel to represent the connection store.
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public class ConnectionStorePanel extends AbstractGridbagPanel implements InitializingBean, Observer
{

    private static final Log LOG = LogFactory.getLog( ConnectionStorePanel.class );
    private ConnectionStore mConnectionStore_;

    private JTextField mStatus = new JTextField();
    private JTextField mName_ = new JTextField();
    private JComboBox mType_ = new JComboBox();
    private JTextField mServer_ = new JTextField();
    private JTextField mPort_ = new JTextField();

    /**
     * Constructs a new instance.
     */
    private ConnectionStorePanel()
    {
        throw new IllegalStateException();
    }

    /**
     * Constructs a new instance.
     * 
     * @param aConnectionStore
     *            the connection store that this class will be
     *            observing
     */
    public ConnectionStorePanel( ConnectionStore aConnectionStore )
    {
        super( "Connection Store" );

        // set up the observable
        mConnectionStore_ = aConnectionStore;
        mConnectionStore_.addObserver( this );
    }

    /**
     * Initialise the panel
     * 
     * @param aInitialStatus
     *            the initial status of the row
     */
    public void initialise( String aInitialStatus )
    {
        int row = 1;
        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

        // add in the status label
        final JLabel lStatus = new JLabel( "Save Status:" );
        lStatus.setBorder( eb );
        addComponent( lStatus, row, 1 );

        mStatus.setText( aInitialStatus );
        mStatus.setEditable( false );
        mStatus.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( mStatus, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection name
        final JLabel lName = new JLabel( "Name:" );
        lName.setBorder( eb );
        addComponent( lName, row, 1 );

        mName_.setEditable( false );
        mName_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mName_, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the connection type
        final JLabel lType = new JLabel( "Type:" );
        lType.setBorder( eb );
        addComponent( lType, row, 1 );

        mType_.setEditable( true );
        mType_.addItem( null );
        mType_.addItem( EConnectionType.EMS );
        mType_.addItem( EConnectionType.MQ );
        mType_.setPreferredSize( MEDIUM_FIELD );
        addComponent( mType_, row++, 2 );

        // add in the server
        final JLabel lServer = new JLabel( "Server:" );
        lServer.setBorder( eb );
        addComponent( lServer, row, 1 );

        mServer_.setPreferredSize( LONG_FIELD );
        addFilledComponent( mServer_, row++, 2, 3, 1, GridBagConstraints.HORIZONTAL );

        // add in the port
        final JLabel lPort = new JLabel( "Port:" );
        lServer.setBorder( eb );
        addComponent( lPort, row, 1 );

        mPort_.setPreferredSize( MEDIUM_FIELD );
        addFilledComponent( mPort_, row++, 2, 1, 1, GridBagConstraints.HORIZONTAL );
    }

    /**
     * Creates a new JMS connection details object
     * 
     * @return a new JMS connection details object
     * @throws MercuryConnectionStoreException
     *             if the data collected is not enough for a
     *             connection
     */
    public IConnectionDetails getConnectionDetails() throws MercuryConnectionStoreException
    {
        if ( mServer_.getText().length() == 0 || mPort_.getText().length() == 0 || mType_.getSelectedItem() == null )
        {
            throw new MercuryConnectionStoreException( "Invalid data in the connection store fields" );
        }

        return new ConnectionDetails( mName_.getText(), (EConnectionType) mType_.getSelectedItem(), mServer_.getText(), mPort_.getText() );
    }

    /**
     * LOad the details into the panel (populates the text fields)
     * 
     * @param aDtls
     *            the jms connection detaiols object from which we can
     *            populate the panel components
     */
    public void loadValues( IConnectionDetails aDtls )
    {
        mName_.setText( aDtls.getName() );
        mServer_.setText( aDtls.getHostname() );
        mPort_.setText( aDtls.getPort() );
        mType_.setSelectedItem( aDtls.getType() );
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnectionStore_, "Must set the connection store into the connection manager panel" );
    }

    /**
     * @see java.util.Observer#update(java.util.Observable,
     *      java.lang.Object)
     */
    public void update( Observable aObserved, Object aObj )
    {
        LOG.info( "Observable has changed [" + aObserved.getClass().getName() + "]" );
        if ( aObserved instanceof IConnectionStore )
        {
            mStatus.setText( ( (IConnectionStore) aObserved ).getState() );
        }
    }

    /**
     * Getter for the connection store
     * 
     * @return the connection store
     */
    public ConnectionStore getConnectionStore()
    {
        return mConnectionStore_;
    }

    /**
     * Setter for the connection store
     * 
     * @param aConnStr
     *            the connection store to set
     */
    public void setConnectionStore( ConnectionStore aConnStr )
    {
        mConnectionStore_ = aConnStr;
    }
}
