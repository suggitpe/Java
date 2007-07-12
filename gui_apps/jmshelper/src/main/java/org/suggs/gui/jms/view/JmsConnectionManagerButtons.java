/*
 * JmsConnectionManagerButtons.java created on 11 Jul 2007 18:18:17 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.suggs.gui.jms.support.AbstractGridbagPanel;

/**
 * Buttons panel for the connection manager
 * 
 * @author suggitpe
 * @version 1.0 11 Jul 2007
 */
public class JmsConnectionManagerButtons extends AbstractGridbagPanel
{

    private JButton mConnectButton_ = new JButton( "Connect" );
    private JButton mDisconnectButton_ = new JButton( "Disconnect" );
    private JButton mTestButton_ = new JButton( "  Test  " );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerButtons()
    {
        super();

        mTestButton_.setToolTipText( "Test connection settings with current setting" );
        addFilledComponent( mTestButton_, 1, 1 );

        mConnectButton_.setToolTipText( "Connect with the defined connection parameters" );
        addFilledComponent( mConnectButton_, 2, 1 );

        mDisconnectButton_.setToolTipText( "Disconnect from the current connection" );
        addFilledComponent( mDisconnectButton_, 3, 1 );

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
