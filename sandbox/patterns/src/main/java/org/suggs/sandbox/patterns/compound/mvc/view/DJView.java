/*
 * DJView.java created on 20 Sep 2007 07:08:43 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.view;

import org.suggs.sandbox.patterns.compound.mvc.IBeatModel;
import org.suggs.sandbox.patterns.compound.mvc.IBeatObserver;
import org.suggs.sandbox.patterns.compound.mvc.IBpmObserver;
import org.suggs.sandbox.patterns.compound.mvc.IController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * View of the midi impl for a DJ
 * 
 * @author suggitpe
 * @version 1.0 20 Sep 2007
 */
public class DJView implements ActionListener, IBeatObserver, IBpmObserver
{

    private IBeatModel mModel_;
    private IController mController_;
    JFrame mViewFrame_ = new JFrame( "View" );
    JPanel mViewPanel_ = new JPanel( new GridLayout( 1, 2 ) );
    // BeatBar mBeatBar_ = new BeatBar();
    JLabel mBpmOutputLabel_ = new JLabel( "Off-line", SwingConstants.CENTER );

    JFrame mControlFrame_ = new JFrame( "Control" );
    JPanel mControlPanel_ = new JPanel( new GridLayout( 1, 2 ) );
    JLabel mBpmLabel_ = new JLabel( "Enter BPM:", SwingConstants.CENTER );
    JTextField mBpmTextField_ = new JTextField( 2 );
    JButton mSetBpmButton_ = new JButton( "Set" );
    JButton mIncreaseBpmButton_ = new JButton( ">>" );
    JButton mDecreaseBpmButton_ = new JButton( "<<" );
    JMenuBar mMenuBar_ = new JMenuBar();
    JMenu mMenu_ = new JMenu( "DJ Control" );
    JMenuItem mStartMenuItem_ = new JMenuItem( "Start" );
    JMenuItem mStopMenuItem_ = new JMenuItem( "Stop" );

    /**
     * Constructs a new instance.
     * 
     * @param aController
     *            a controller
     * @param aBeatModel
     *            a model
     */
    public DJView( IController aController, IBeatModel aBeatModel )
    {
        mController_ = aController;
        mModel_ = aBeatModel;
        mModel_.registerObserver( (IBeatObserver) this );
        mModel_.registerObserver( (IBpmObserver) this );
    }

    /**
     * Create all of the swing components in this method
     */
    public void createView()
    {
        mViewFrame_.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mViewFrame_.setSize( new Dimension( 100, 80 ) );
        // mBeatBar_.setValue( 0 );

        JPanel bpmPanel = new JPanel( new GridLayout( 2, 1 ) );
        // bpmPanel.add( mBeatBar_ );
        bpmPanel.add( mBpmOutputLabel_ );
        mViewPanel_.add( bpmPanel );

        mViewFrame_.getContentPane().add( mViewPanel_, BorderLayout.CENTER );
        mViewFrame_.pack();
        mViewFrame_.setVisible( true );
    }

    /**
     * 
     */
    public void createControls()
    {
        JFrame.setDefaultLookAndFeelDecorated( true );
        mControlFrame_.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mControlFrame_.setSize( new Dimension( 100, 80 ) );

        mMenu_.add( mStartMenuItem_ );
        mStartMenuItem_.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                mController_.start();
            }
        } );

        mMenu_.add( mStopMenuItem_ );
        mStopMenuItem_.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                mController_.stop();
                // mBpmOutputLabel_.setText( "Off-line" );
            }
        } );

        JMenuItem exit = new JMenuItem( "Exit" );
        exit.addActionListener( new ActionListener()
        {

            public void actionPerformed( ActionEvent arg0 )
            {
                System.exit( 0 );
            }
        } );

        mMenu_.add( exit );
        mMenuBar_.add( mMenu_ );
        mControlFrame_.setJMenuBar( mMenuBar_ );

        mSetBpmButton_.setSize( new Dimension( 10, 40 ) );
        mSetBpmButton_.addActionListener( this );
        mIncreaseBpmButton_.addActionListener( this );
        mDecreaseBpmButton_.addActionListener( this );

        // build a panel for the buttons
        JPanel buttonPanel = new JPanel( new GridLayout( 1, 2 ) );
        buttonPanel.add( mDecreaseBpmButton_ );
        buttonPanel.add( mIncreaseBpmButton_ );

        JPanel enterPanel = new JPanel( new GridLayout( 1, 2 ) );
        enterPanel.add( mBpmLabel_ );
        enterPanel.add( mBpmTextField_ );

        JPanel insideControlPanel = new JPanel( new GridLayout( 3, 1 ) );
        insideControlPanel.add( enterPanel );
        insideControlPanel.add( mSetBpmButton_ );
        insideControlPanel.add( buttonPanel );
        mControlPanel_.add( insideControlPanel );

        mBpmLabel_.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        mBpmOutputLabel_.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );

        mControlFrame_.getRootPane().setDefaultButton( mSetBpmButton_ );
        mControlFrame_.getContentPane().add( mControlPanel_, BorderLayout.CENTER );

        mControlFrame_.pack();
        mControlFrame_.setVisible( true );
    }

    /**
     * Enables the stop menu item
     */
    public void enableStopMenuItem()
    {
        mStopMenuItem_.setEnabled( true );
    }

    /**
     * Disables the stop menu item
     */
    public void disableStopMenuItem()
    {
        mStopMenuItem_.setEnabled( false );
    }

    /**
     * Enables the start menu item
     */
    public void enableStartMenuItem()
    {
        mStartMenuItem_.setEnabled( true );
    }

    /**
     * Disables the start menu item
     */
    public void disableStartMenuItem()
    {
        mStartMenuItem_.setEnabled( false );
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent event )
    {
        if ( event.getSource() == mSetBpmButton_ )
        {
            int bpm = Integer.parseInt( mBpmTextField_.getText() );
            mController_.setBpm( bpm );
        }
        else if ( event.getSource() == mIncreaseBpmButton_ )
        {
            mController_.increaseBpm();
        }
        else if ( event.getSource() == mDecreaseBpmButton_ )
        {
            mController_.decreaseBpm();
        }

    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBpmObserver#updateBpm()
     */
    public void updateBpm()
    {
        int bpm = mModel_.getBpm();
        if ( bpm == 0 )
        {
            mBpmOutputLabel_.setText( "Off-line" );
        }
        else
        {
            mBpmOutputLabel_.setText( "Current BPM: " + bpm );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatObserver#updateBeat()
     */
    public void updateBeat()
    {
        // mBeatBar_.setValue( 100 );
    }

}
