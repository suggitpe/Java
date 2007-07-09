/*
 * JmsHelper.java created on 20 Jun 2007 18:49:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.jms.impl;

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
import org.suggs.gui.jms.IJmsHelper;
import org.suggs.gui.jms.JmsHelperException;
import org.suggs.gui.jms.dialog.AboutDialog;
import org.suggs.gui.jms.support.JmsHelperGuiBuilder;

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

    private JPanel mConnectionManagerPanel_;

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
        Assert.notNull( mConnectionManagerPanel_, "There must be a connection manager panel set in the main gui" );
    }

    /**
     * @see org.suggs.gui.jms.IJmsHelper#buildGui()
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

        // add in the conn manager
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
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
    public JPanel getConnectionPanel()
    {
        return mConnectionManagerPanel_;
    }

    /**
     * Setter for the connection manager panel
     * 
     * @param aPanel
     *            the panel to set as the connection manager panel
     */
    public void setConnectionPanel( JPanel aPanel )
    {
        mConnectionManagerPanel_ = aPanel;
    }

}
