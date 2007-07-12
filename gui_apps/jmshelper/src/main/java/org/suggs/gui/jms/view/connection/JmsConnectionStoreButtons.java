/*
 * JmsConnectionStoreButtons.java created on 11 Jul 2007 18:18:35 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.view.connection;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.suggs.gui.jms.support.AbstractGridbagPanel;

/**
 * Buttons for the connection store.
 * 
 * @author suggitpe
 * @version 1.0 11 Jul 2007
 */
public class JmsConnectionStoreButtons extends AbstractGridbagPanel
{

    private JButton mSaveButton_ = new JButton( "Save" );
    private JButton mLoadButton_ = new JButton( "Load" );

    /**
     * Constructs a new instance.
     */
    public JmsConnectionStoreButtons()
    {
        super();

        mLoadButton_.setToolTipText( "Load a previously saved connection into the window" );
        addFilledComponent( mLoadButton_, 1, 1 );
        mSaveButton_.setToolTipText( "Save the current connection settings as a named connection" );
        addFilledComponent( mSaveButton_, 2, 1 );

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
}
