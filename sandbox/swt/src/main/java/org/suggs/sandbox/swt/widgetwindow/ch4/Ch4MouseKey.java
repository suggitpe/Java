/*
 * Ch4MouseKey.java created on 13 Aug 2008 07:02:53 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch4;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * TODO Write javadoc for Ch4MouseKey
 * 
 * @author suggitpe
 * @version 1.0 13 Aug 2008
 */
public class Ch4MouseKey extends Composite
{

    Label mOutput_;

    /**
     * Constructs a new instance.
     */
    public Ch4MouseKey( Composite parent )
    {
        super( parent, SWT.NULL );

        Button typed = new Button( this, SWT.PUSH );
        typed.setText( "Typed" );
        typed.setLocation( 2, 10 );
        typed.pack();

        typed.addKeyListener( new KeyAdapter()
        {

            @Override
            public void keyPressed( KeyEvent e )
            {
                keyHandler();
            }

        } );

        Button untyped = new Button( this, SWT.PUSH );
        untyped.setText( "Untyped" );
        untyped.setLocation( 80, 10 );
        untyped.pack();

        untyped.addListener( SWT.MouseEnter, UntypedListener );
        untyped.addListener( SWT.MouseExit, UntypedListener );

        mOutput_ = new Label( this, SWT.SHADOW_ETCHED_OUT );
        mOutput_.setBounds( 40, 70, 90, 40 );
        mOutput_.setText( "No output" );

        pack();
    }

    Listener UntypedListener = new Listener()
    {

        public void handleEvent( Event event )
        {
            switch ( event.type )
            {
                case SWT.MouseEnter:
                    mOutput_.setText( "Mouse Enter" );
                    break;
                case SWT.MouseExit:
                    mOutput_.setText( "Mouse Exit" );
                    break;
            }
        }
    };

    void keyHandler()
    {
        mOutput_.setText( "Key Event" );
    };

}
