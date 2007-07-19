/*
 * JmsConnectionStorePanel.java created on 9 Jul 2007 17:32:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.view.connection;

import com.suggs.gui.jms.model.connection.EConnectionType;
import com.suggs.gui.jms.model.connection.IJmsConnectionDetails;
import com.suggs.gui.jms.model.connection.impl.JmsConnectionDetails;
import com.suggs.gui.jms.model.connection.impl.JmsConnectionStore;
import com.suggs.gui.jms.support.AbstractGridbagPanel;

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
public class JmsConnectionStorePanel extends AbstractGridbagPanel implements InitializingBean, Observer
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionStorePanel.class );
    private JmsConnectionStore mConnectionStore_;

    private JTextField mStatus = new JTextField();
    private JTextField mName_ = new JTextField();
    private JComboBox mType_ = new JComboBox();
    private JTextField mServer_ = new JTextField();
    private JTextField mPort_ = new JTextField();

    /**
     * Constructs a new instance.
     */
    private JmsConnectionStorePanel()
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
    public JmsConnectionStorePanel( JmsConnectionStore aConnectionStore )
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
        // addComponent( lStatus, row, 1 );

        mStatus.setText( aInitialStatus );
        mStatus.setEditable( false );
        mStatus.setPreferredSize( MEDIUM_FIELD );
        // addFilledComponent( mStatus, row++, 2, 3, 1,
        // GridBagConstraints.HORIZONTAL );

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
     */
    public IJmsConnectionDetails getConnectionDetails()
    {
        if ( mServer_.getText().length() == 0 || mPort_.getText().length() == 0 )
        {
            return null;
        }

        return new JmsConnectionDetails( mName_.getText(), (EConnectionType) mType_.getSelectedItem(), mServer_.getText(), mPort_.getText() );
    }

    /**
     * Sets all of the values in the store to specific defaults
     */
    public void loadDefaultValues()
    {
        mPort_.setText( "7222" );
        mServer_.setText( "localhost" );
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
    }

    /**
     * Getter for the connection store
     * 
     * @return the connection store
     */
    public JmsConnectionStore getConnectionStore()
    {
        return mConnectionStore_;
    }

    /**
     * Setter for the connection store
     * 
     * @param aConnStr
     *            the connection store to set
     */
    public void setConnectionStore( JmsConnectionStore aConnStr )
    {
        mConnectionStore_ = aConnStr;
    }
}
