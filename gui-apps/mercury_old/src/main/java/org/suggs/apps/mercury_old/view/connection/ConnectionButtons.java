/*
 * JmsConnectionStoreButtons.java created on 11 Jul 2007 18:18:35 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old.view.connection;

import org.suggs.apps.mercury_old.support.AbstractGridbagPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Buttons for the connection store.
 * 
 * @author suggitpe
 * @version 1.0 11 Jul 2007
 */
public class ConnectionButtons extends AbstractGridbagPanel
{

    private JButton mSaveButton_ = new JButton( "Save" );
    private JButton mLoadButton_ = new JButton( "Load" );
    private JButton mDeleteButton_ = new JButton( "Delete" );
    private JButton mSearchButton_ = new JButton( "Search" );
    private JButton mConnectButton_ = new JButton( "Connect" );
    private JButton mDisconnectButton_ = new JButton( "Disconnect" );
    private JButton mTestButton_ = new JButton( "Test" );

    AbstractButton b;

    /**
     * Constructs a new instance.
     */
    public ConnectionButtons()
    {
        super( "Ctrl" );
        initButons();

        addFilledComponent( new JSeparator(), 1, 1 );
        addFilledComponent( mLoadButton_, 2, 1 );
        addFilledComponent( mSaveButton_, 3, 1 );
        addFilledComponent( mDeleteButton_, 4, 1 );
        addFilledComponent( new JSeparator(), 9, 1 );

        addFilledComponent( mSearchButton_, 10, 1 );

        addFilledComponent( new JSeparator(), 11, 1 );
        addFilledComponent( mConnectButton_, 13, 1 );
        addFilledComponent( mDisconnectButton_, 14, 1 );

        addFilledComponent( new JSeparator(), 30, 1 );
        addFilledComponent( mTestButton_, 31, 1 );
        addFilledComponent( new JSeparator(), 40, 1 );
    }

    /**
     * Initialises the buttons with tool tips and a default action
     * listener
     */
    private void initButons()
    {
        mLoadButton_.setToolTipText( "Load a previously saved connection into the window" );
        mSaveButton_.setToolTipText( "Save the current connection settings as a named connection" );
        mDeleteButton_.setToolTipText( "Allows you to remove a named connection from the connection store" );
        mSearchButton_.setToolTipText( "Searches for destinations and connection factories" );
        mConnectButton_.setToolTipText( "Connect with the defined connection parameters" );
        mDisconnectButton_.setToolTipText( "Disconnect from the current connection" );
        mTestButton_.setToolTipText( "Test connection settings with current setting" );

        ActionListener l = createDefaultActionListener();
        mSaveButton_.addActionListener( l );
        mLoadButton_.addActionListener( l );
        mDeleteButton_.addActionListener( l );
        mSearchButton_.addActionListener( l );
        mConnectButton_.addActionListener( l );
        mDisconnectButton_.addActionListener( l );
        mTestButton_.addActionListener( l );
    }

    /**
     * Creates a default action listsner so that we can feed back to
     * the user that this has no impl.
     * 
     * @return the defauly action listener
     */
    private ActionListener createDefaultActionListener()
    {
        return new ActionListener()
        {

            public void actionPerformed( ActionEvent e )
            {
                JOptionPane.showMessageDialog( ConnectionButtons.this,
                                               "This button has not been implemented",
                                               "No implementation",
                                               JOptionPane.INFORMATION_MESSAGE );
            }
        };
    }

    /**
     * Cleans all action listeners from a given JButton
     * 
     * @param aBut
     *            the button to remove the action listeners from
     */
    private void cleanListeners( JButton aBut )
    {
        ActionListener[] als = aBut.getActionListeners();
        for ( ActionListener a : als )
        {
            aBut.removeActionListener( a );
        }
    }

    /**
     * add an action listsnere to the delete button
     * 
     * @param al
     *            the action litsner to add to the delete button
     */
    public void addDeleteActionListener( ActionListener al )
    {
        cleanListeners( mDeleteButton_ );
        mDeleteButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the save button
     * 
     * @param al
     *            the action listsner to add
     */
    public void addSaveActionListener( ActionListener al )
    {
        cleanListeners( mSaveButton_ );
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
        cleanListeners( mLoadButton_ );
        mLoadButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the Search button
     * 
     * @param al
     *            the action listener to add
     */
    public void addSearchActionListener( ActionListener al )
    {
        cleanListeners( mSearchButton_ );
        mSearchButton_.addActionListener( al );
    }

    /**
     * Add an action listener to the test button
     * 
     * @param al
     *            the action listener to add
     */
    public void addTestActionListener( ActionListener al )
    {
        cleanListeners( mTestButton_ );
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
        cleanListeners( mConnectButton_ );
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
        cleanListeners( mDisconnectButton_ );
        mDisconnectButton_.addActionListener( al );
    }
}
