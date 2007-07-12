/*
 * JmsConnectionStorePanel.java created on 9 Jul 2007 17:32:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.view;

import java.awt.GridBagConstraints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.jms.connection.EConnectionType;
import org.suggs.gui.jms.connection.IJmsConnectionStore;
import org.suggs.gui.jms.support.AbstractGridbagPanel;

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
    private IJmsConnectionStore mConnectionStore_;

    private JTextField mName_ = new JTextField();
    private JComboBox mType_ = new JComboBox();
    private JTextField mServer_ = new JTextField();
    private JTextField mPort_ = new JTextField();

    /**
     * Constructs a new instance.
     */
    public JmsConnectionStorePanel()
    {
        super();

        EmptyBorder eb = new EmptyBorder( 0, 0, 0, 10 );

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
        mType_.addItem( EConnectionType.EMS );
        mType_.addItem( EConnectionType.MQ );
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

        loadDefaultValues();
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
    public IJmsConnectionStore getConnectionStore()
    {
        return mConnectionStore_;
    }

    /**
     * Setter for the connection store
     * 
     * @param aConnStr
     *            the connection store to set
     */
    public void setConnectionStore( IJmsConnectionStore aConnStr )
    {
        mConnectionStore_ = aConnStr;
    }
}
