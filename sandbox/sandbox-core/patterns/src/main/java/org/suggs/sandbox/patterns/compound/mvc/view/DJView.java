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
public class DJView implements ActionListener, IBeatObserver, IBpmObserver {

    private IBeatModel model;
    private IController controller;
    private JFrame viewFrame = new JFrame( "View" );
    private JPanel viewPanel = new JPanel( new GridLayout( 1, 2 ) );
    // BeatBar beatBar = new BeatBar();
    private JLabel bpmOutputLabel = new JLabel( "Off-line", SwingConstants.CENTER );

    private JFrame controlFrame = new JFrame( "Control" );
    private JPanel controlPanel = new JPanel( new GridLayout( 1, 2 ) );
    private JLabel bpmLabel = new JLabel( "Enter BPM:", SwingConstants.CENTER );
    private JTextField bpmTextField = new JTextField( 2 );
    private JButton setBpmButton = new JButton( "Set" );
    private JButton increaseBpmButton = new JButton( ">>" );
    private JButton decreaseBpmButton = new JButton( "<<" );
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu( "DJ Control" );
    private JMenuItem startMenuItem = new JMenuItem( "Start" );
    private JMenuItem stopMenuItem = new JMenuItem( "Stop" );

    /**
     * Constructs a new instance.
     * 
     * @param aController
     *            a controller
     * @param aBeatModel
     *            a model
     */
    public DJView( IController aController, IBeatModel aBeatModel ) {
        controller = aController;
        model = aBeatModel;
        model.registerObserver( (IBeatObserver) this );
        model.registerObserver( (IBpmObserver) this );
    }

    /**
     * Create all of the swing components in this method
     */
    public void createView() {
        viewFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        viewFrame.setSize( new Dimension( 100, 80 ) );
        // mBeatBar_.setValue( 0 );

        JPanel bpmPanel = new JPanel( new GridLayout( 2, 1 ) );
        // bpmPanel.add( mBeatBar_ );
        bpmPanel.add( bpmOutputLabel );
        viewPanel.add( bpmPanel );

        viewFrame.getContentPane().add( viewPanel, BorderLayout.CENTER );
        viewFrame.pack();
        viewFrame.setVisible( true );
    }

    /**
     * 
     */
    public void createControls() {
        JFrame.setDefaultLookAndFeelDecorated( true );
        controlFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        controlFrame.setSize( new Dimension( 100, 80 ) );

        menu.add( startMenuItem );
        startMenuItem.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent arg0 ) {
                controller.start();
            }
        } );

        menu.add( stopMenuItem );
        stopMenuItem.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent arg0 ) {
                controller.stop();
                // mBpmOutputLabel_.setText( "Off-line" );
            }
        } );

        JMenuItem exit = new JMenuItem( "Exit" );
        exit.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent arg0 ) {
                System.exit( 0 );
            }
        } );

        menu.add( exit );
        menuBar.add( menu );
        controlFrame.setJMenuBar( menuBar );

        setBpmButton.setSize( new Dimension( 10, 40 ) );
        setBpmButton.addActionListener( this );
        increaseBpmButton.addActionListener( this );
        decreaseBpmButton.addActionListener( this );

        // build a panel for the buttons
        JPanel buttonPanel = new JPanel( new GridLayout( 1, 2 ) );
        buttonPanel.add( decreaseBpmButton );
        buttonPanel.add( increaseBpmButton );

        JPanel enterPanel = new JPanel( new GridLayout( 1, 2 ) );
        enterPanel.add( bpmLabel );
        enterPanel.add( bpmTextField );

        JPanel insideControlPanel = new JPanel( new GridLayout( 3, 1 ) );
        insideControlPanel.add( enterPanel );
        insideControlPanel.add( setBpmButton );
        insideControlPanel.add( buttonPanel );
        controlPanel.add( insideControlPanel );

        bpmLabel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        bpmOutputLabel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );

        controlFrame.getRootPane().setDefaultButton( setBpmButton );
        controlFrame.getContentPane().add( controlPanel, BorderLayout.CENTER );

        controlFrame.pack();
        controlFrame.setVisible( true );
    }

    /**
     * Enables the stop menu item
     */
    public void enableStopMenuItem() {
        stopMenuItem.setEnabled( true );
    }

    /**
     * Disables the stop menu item
     */
    public void disableStopMenuItem() {
        stopMenuItem.setEnabled( false );
    }

    /**
     * Enables the start menu item
     */
    public void enableStartMenuItem() {
        startMenuItem.setEnabled( true );
    }

    /**
     * Disables the start menu item
     */
    public void disableStartMenuItem() {
        startMenuItem.setEnabled( false );
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed( ActionEvent event ) {
        if ( event.getSource() == setBpmButton ) {
            int bpm = Integer.parseInt( bpmTextField.getText() );
            controller.setBpm( bpm );
        }
        else if ( event.getSource() == increaseBpmButton ) {
            controller.increaseBpm();
        }
        else if ( event.getSource() == decreaseBpmButton ) {
            controller.decreaseBpm();
        }

    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBpmObserver#updateBpm()
     */
    public void updateBpm() {
        int bpm = model.getBpm();
        if ( bpm == 0 ) {
            bpmOutputLabel.setText( "Off-line" );
        }
        else {
            bpmOutputLabel.setText( "Current BPM: " + bpm );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatObserver#updateBeat()
     */
    public void updateBeat() {
    // mBeatBar_.setValue( 100 );
    }

}
