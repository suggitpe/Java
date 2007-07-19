/*
 * JmsHelper.java created on 20 Jun 2007 18:49:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package com.suggs.gui.jms.impl;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.suggs.gui.jms.IJmsHelper;
import com.suggs.gui.jms.JmsHelperException;
import com.suggs.gui.jms.controller.impl.ConnectionController;
import com.suggs.gui.jms.support.JmsHelperGuiBuilder;
import com.suggs.gui.jms.view.dialog.AboutDialog;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class manages the construction of the main GUI itself along
 * with the placement of teh main panels
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class JmsHelper implements IJmsHelper, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JmsHelper.class );

    private JPanel mConnectionStorePanel_;
    private JPanel mConnectionManagerPanel_;
    private JPanel mConnectionButtons_;
    private ConnectionController mConnectionController_;

    /**
     * Constructs a new instance.
     */
    public JmsHelper()
    {
        super();
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnectionStorePanel_, "There must be a connection store panel set in the main gui" );
        Assert.notNull( mConnectionButtons_, "There must be a connection store buttons panel set in the main gui" );
        Assert.notNull( mConnectionManagerPanel_, "There must be a connection manager panel set in the main gui" );
        Assert.notNull( mConnectionController_, "There must be a connection controller set in the main gui" );
    }

    /**
     * @see com.suggs.gui.jms.IJmsHelper#buildGui()
     */
    public void buildGui() throws JmsHelperException
    {

        LOG.debug( "Building GUI" );
        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e )
        {
            throw new JmsHelperException( "Failed to set UI look and feel", e );
        }

        JFrame.setDefaultLookAndFeelDecorated( true );

        final JFrame frame = new JFrame( "JMS Helper Tool" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        buildGuiPanels( frame.getContentPane() );

        buildMenuBar( frame );

        // finally show the GUI frame
        JmsHelperGuiBuilder.displayFrame( frame );

    }

    /**
     * Builds the main GUI panels
     * 
     * @param aCtnr
     *            the container to build upon
     * @throws JmsHelperException
     */
    private final void buildGuiPanels( Container aCntr ) throws JmsHelperException
    {
        LOG.debug( "Building GUI Panels" );

        GridBagLayout mainLayout = new GridBagLayout();
        aCntr.setLayout( mainLayout );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // add in the connection manager buttons
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        mainLayout.setConstraints( mConnectionButtons_, gbc );
        aCntr.add( mConnectionButtons_ );

        // add in the connection store panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        mainLayout.setConstraints( mConnectionStorePanel_, gbc );
        aCntr.add( mConnectionStorePanel_ );

        // add in the connection manager store
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainLayout.setConstraints( mConnectionManagerPanel_, gbc );
        aCntr.add( mConnectionManagerPanel_ );
    }

    /**
     * Create and add the menu bar to the GUI
     * 
     * @param aFrame
     *            the frame to add the menu to
     */
    private final void buildMenuBar( final JFrame aFrame )
    {
        LOG.debug( "Building menu bar" );

        final JMenuBar menu = new JMenuBar();

        // buid the exit item
        JMenuItem exit = new JMenuItem( "Exit" );
        exit.setMnemonic( 'x' );
        exit.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                System.exit( 0 );
            }
        } );

        // build the about item
        JMenuItem about = new JMenuItem( "About" );
        about.setMnemonic( 'a' );
        about.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                AboutDialog about = new AboutDialog( aFrame );
                about.setVisible( true );
            }
        } );

        // build the file menu
        JMenu file = new JMenu( "File" );
        file.setMnemonic( 'f' );
        file.add( exit );

        // huild the about menu
        JMenu help = new JMenu( "Help" );
        help.setMnemonic( 'h' );
        help.add( about );

        // now add the menus to the menu bar
        menu.add( file );
        menu.add( help );

        aFrame.setJMenuBar( menu );
    }

    // ============ GETTERS AND SETTERS ===========
    /**
     * Getter for the connection manager panel
     * 
     * @return the connection manager panel
     */
    public JPanel getConnectionManagerPanel()
    {
        return mConnectionManagerPanel_;
    }

    /**
     * Setter for the connection manager panel
     * 
     * @param aPanel
     *            the panel to set as the connection manager panel
     */
    public void setConnectionManagerPanel( JPanel aPanel )
    {
        mConnectionManagerPanel_ = aPanel;
    }

    /**
     * Getter for the connection buttons panel
     * 
     * @return the connection buttons panel
     */
    public JPanel getConnectionButtons()
    {
        return mConnectionButtons_;
    }

    /**
     * Setter for the connection buttons panel
     * 
     * @param aPanel
     *            the panel to set as the connection buttons panel
     */
    public void setConnectionButtons( JPanel aPanel )
    {
        mConnectionButtons_ = aPanel;
    }

    /**
     * Setter for the connection store panel
     * 
     * @param aPanel
     *            the panel to set as the connection store panel
     */
    public void setConnectionStorePanel( JPanel aPanel )
    {
        mConnectionStorePanel_ = aPanel;
    }

    /**
     * Getter for the connection store panel
     * 
     * @return the connection store panel
     */
    public JPanel getConnectionStorePanel()
    {
        return mConnectionStorePanel_;
    }

    /**
     * @return
     */
    public ConnectionController getConnectionConroller()
    {
        return mConnectionController_;
    }

    /**
     * @param aCtrl
     */
    public void setConnectionController( ConnectionController aCtrl )
    {
        mConnectionController_ = aCtrl;
    }
}
