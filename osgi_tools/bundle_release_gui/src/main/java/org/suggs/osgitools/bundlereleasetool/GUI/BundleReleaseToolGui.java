/*
 * BundleReleaseToolFrame.java created on 2 Jul 2009 07:04:13 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Main frame for the release tool GUI.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2009
 */
public class BundleReleaseToolGui
{

    // static logger
    private static final Log LOG = LogFactory.getLog( BundleReleaseToolGui.class );

    private boolean mExitOnClose_ = false;

    /**
     * Constructs a new instance (builds the frame amnd inserts a new
     * pane).
     */
    public BundleReleaseToolGui()
    {
    }

    /**
     * Constructs a new instance.
     * 
     * @param aExitOnClose
     *            specify whether the GUI should exit on close of GUI
     *            window
     */
    public BundleReleaseToolGui( boolean aExitOnClose )
    {
        mExitOnClose_ = aExitOnClose;
    }

    public void buildGui( WindowListener aWindowListener )
    {
        LOG.debug( "Building Bundle Release Tool GUI" );

        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e )
        {
            // ah well!!
            LOG.warn( "Unable to set look and feel ... do not consider this something to worry about" );
        }

        final JFrame desktop = new JFrame( "Bundle release tool" );
        if ( mExitOnClose_ )
        {
            desktop.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        }
        desktop.addWindowListener( aWindowListener );

        // delegate the construction of the panel
        buildPane( desktop.getContentPane() );

        displayFrame( desktop );
    }

    /**
     * 
     */
    private void displayFrame( JFrame aFrame )
    {
        // first center the frame
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point c = e.getCenterPoint();
        Rectangle bounds = e.getMaximumWindowBounds();

        int w = Math.max( bounds.width / 2, Math.min( aFrame.getWidth(), bounds.width ) );
        int h = Math.max( bounds.height / 2, Math.min( aFrame.getHeight(), bounds.height ) );
        int x = c.x - w / 2;
        int y = c.y - h / 2;
        aFrame.setBounds( x, y, w, h );
        if ( w == bounds.width && h == bounds.height )
        {
            aFrame.setExtendedState( Frame.MAXIMIZED_BOTH );
        }

        aFrame.validate();

        // then show it
        aFrame.pack();
        aFrame.setVisible( true );
        aFrame.setResizable( true );
        aFrame.toFront();
    }

    /**
     * Builds up the pane.
     */
    private void buildPane( final Container aDesktop )
    {
        // set up for using the gridbag layout
        aDesktop.setLayout( new GridBagLayout() );

        // set up the defaults to use throughout
        EmptyBorder border = new EmptyBorder( new Insets( 0, 0, 0, 10 ) );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets( 2, 2, 2, 2 );
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        JLabel l = new JLabel( "Please select a bundle jar to release" );
        l.setBorder( border );
        aDesktop.add( l, c );

        // add in the text box
        c.gridx = 0;
        c.gridy = 1;
        final JTextField text = new JTextField();
        text.setPreferredSize( new Dimension( 440, 20 ) );
        text.setEditable( false );
        aDesktop.add( text, c );

        // add a button to select the file
        c.gridx = 1;
        c.gridy = 1;
        JButton button = new JButton( "..." );
        button.addActionListener( new ActionListener()
        {

            /**
             * Open up a file chooser dialog so that we can get a file
             * name.
             * 
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            @Override
            public void actionPerformed( ActionEvent event )
            {
                ChooseBundleJarAction action = new ChooseBundleJarAction();
                action.run();
                if ( action.getBundleJarName() != null && action.getBundleJarName().length() > 0 )
                {
                    text.setText( action.getBundleJarName() );
                }
            }
        } );
        aDesktop.add( button, c );
    }

    /**
     * This is just so we can easily test it (not worth building a
     * JUnit for a Swing layer)
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        final BundleReleaseToolGui gui = new BundleReleaseToolGui( true );
        javax.swing.SwingUtilities.invokeLater( new Runnable()
        {

            public void run()
            {
                LOG.debug( "Executing Run to build GUI" );
                gui.buildGui( new WindowAdapter()
                {} );
                LOG.debug( "GUI Run execution complete" );
            }

        } );
    }
}
