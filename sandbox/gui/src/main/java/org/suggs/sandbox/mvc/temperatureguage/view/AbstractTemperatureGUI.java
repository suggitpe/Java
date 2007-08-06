/*
 * TemperatureGUI.java created on 9 Jul 2007 07:57:25 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.mvc.temperatureguage.view;

import org.suggs.sandbox.mvc.temperatureguage.model.TemperatureModel;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Abstract class for the temperature guage demol
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public abstract class AbstractTemperatureGUI implements Observer
{

    private static final Log LOG = LogFactory.getLog( AbstractTemperatureGUI.class );

    private String mLabel_;
    private TemperatureModel mModel_;
    private JFrame mTempFrame_;
    private JTextField mDisplay_ = new JTextField();
    private JButton mUpButton_ = new JButton( "Up" );;
    private JButton mDownButton_ = new JButton( "Down" );

    /**
     * Constructs a new instance.
     * 
     * @param label
     *            the gui label
     * @param model
     *            the model to observe
     * @param h
     * @param v
     */
    public AbstractTemperatureGUI( String label, TemperatureModel model, int h, int v )
    {
        mLabel_ = label;
        mModel_ = model;
        mTempFrame_ = new JFrame( mLabel_ );
        mTempFrame_.add( "North", new JLabel( mLabel_ ) );
        mTempFrame_.add( "Center", mDisplay_ );
        JPanel buttons = new JPanel();
        buttons.add( mUpButton_ );
        buttons.add( mDownButton_ );
        mTempFrame_.add( "South", buttons );
        mTempFrame_.addWindowListener( new CloseListener() );
        mModel_.addObserver( this );
        mTempFrame_.setSize( 200, 100 );
        mTempFrame_.setLocation( h, v );
        mTempFrame_.setVisible( true );
    }

    /**
     * Setter for the text box display
     * 
     * @param s
     *            the string to display
     */
    public void setDisplay( String s )
    {
        mDisplay_.setText( s );
    }

    /**
     * Getter for the display
     * 
     * @return the display converted to a double
     */
    public double getDisplay()
    {
        double ret = 0.0;
        try
        {
            ret = Double.valueOf( mDisplay_.getText() ).doubleValue();
        }
        catch ( NumberFormatException nfe )
        {
            LOG.warn( "Failed to convert text [" + mDisplay_.getText() + "] to a number (double)" );
        }

        return ret;
    }

    /**
     * Add a listsner to the display box
     * 
     * @param a
     *            the listsner to add
     */
    public void addDisplayListener( ActionListener a )
    {
        mDisplay_.addActionListener( a );
    }

    /**
     * Add a listener to the up button
     * 
     * @param a
     *            the action listener to add
     */
    public void addUpListener( ActionListener a )
    {
        mUpButton_.addActionListener( a );
    }

    /**
     * Add a listener to the down button
     * 
     * @param a
     *            the action listener to add
     */
    public void addDownListsner( ActionListener a )
    {
        mDownButton_.addActionListener( a );
    }

    /**
     * Getter for the model
     * 
     * @return the model
     */
    protected TemperatureModel getModel()
    {
        return mModel_;
    }

    /**
     * Inner class to manage the closing of the current window
     * 
     * @author suggitpe
     * @version 1.0 9 Jul 2007
     */
    public static class CloseListener extends WindowAdapter
    {

        /**
         * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
         */
        @Override
        public void windowClosing( WindowEvent e )
        {
            e.getWindow().setVisible( false );
            System.exit( 0 );

        }
    }
}
