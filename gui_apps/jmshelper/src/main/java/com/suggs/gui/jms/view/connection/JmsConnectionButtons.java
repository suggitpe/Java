/*
 * JmsConnectionStoreButtons.java created on 11 Jul 2007 18:18:35 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.view.connection;

import com.suggs.gui.jms.support.AbstractGridbagPanel;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Buttons for the connection store.
 * 
 * @author suggitpe
 * @version 1.0 11 Jul 2007
 */
public class JmsConnectionButtons extends AbstractGridbagPanel
{

    private JButton mSaveButton_ = new JButton( "Save" );
    private JButton mLoadButton_ = new JButton( "Load" );
    private JButton mConnectButton_ = new JButton( "Connect" );
    private JButton mDisconnectButton_ = new JButton( "Disconnect" );
    private JButton mTestButton_ = new JButton( "Test" );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionButtons()
    {
        super( "Ctrl" );

        mLoadButton_.setToolTipText( "Load a previously saved connection into the window" );
        addFilledComponent( mLoadButton_, 1, 1 );

        mSaveButton_.setToolTipText( "Save the current connection settings as a named connection" );
        addFilledComponent( mSaveButton_, 2, 1 );

        // padding
        addFilledComponent( new JLabel( " " ), 3, 1 );
        addFilledComponent( new JLabel( " " ), 4, 1 );
        //addFilledComponent( new JLabel( " " ), 5, 1 );
        //addFilledComponent( new JLabel( " " ), 6, 1 );

        mTestButton_.setToolTipText( "Test connection settings with current setting" );
        addFilledComponent( mTestButton_, 15, 1 );

        mConnectButton_.setToolTipText( "Connect with the defined connection parameters" );
        addFilledComponent( mConnectButton_, 16, 1 );

        mDisconnectButton_.setToolTipText( "Disconnect from the current connection" );
        addFilledComponent( mDisconnectButton_, 17, 1 );
    }

    /**
     * Add an action listener to the save button
     * 
     * @param al
     *            the action listsner to add
     */
    public void addSaveActionListener( ActionListener al )
    {
        mSaveButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the load button
     * 
     * @param al
     *            the action listener to add
     */
    public void addLoadActionListener( ActionListener al )
    {
        mLoadButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the test button
     * 
     * @param al
     *            the action listener to add
     */
    public void addTestActionListener( ActionListener al )
    {
        mTestButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the connect button
     * 
     * @param al
     *            the action listener to add
     */
    public void addConnectActionListener( ActionListener al )
    {
        mConnectButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the disconnect button
     * 
     * @param al
     *            the action listener to add
     */
    public void addDisconnectActionListener( ActionListener al )
    {
        mDisconnectButton_.addActionListener( al );
    }
}
