/*
 * CompositeShellDemo.java created on 8 Aug 2008 06:16:33 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.shell;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * TODO Write javadoc for CompositeShellDemo
 * 
 * @author suggitpe
 * @version 1.0 8 Aug 2008
 */
public class CompositeShellDemo {

    private static final Log LOG = LogFactory.getLog( CompositeShellDemo.class );

    // this needs to be finished
    private static void createInnerShell( Shell aShell ) {
        aShell.getLayout();
    }

    public static void main( String[] args ) {
        LOG.debug( "Creating SWT composite shell demo" );

        // --------------------------
        Display mDisplay_ = new Display();
        Shell mShell_ = new Shell( mDisplay_ );
        mShell_.setText( "Composite shell test" );

        // --------------------------

        // now set up the high level layout
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        mShell_.setLayout( layout );

        new Label( mShell_, SWT.NULL ).setText( "Outer shell1:" );
        new Label( mShell_, SWT.NULL ).setText( "Outer shell2:" );

        createInnerShell( mShell_ );

        // --------------------------
        LOG.debug( "Opening GUI" );
        // now get the dialog up and running
        mShell_.pack();
        mShell_.open();
        while ( !mShell_.isDisposed() ) {
            if ( !mDisplay_.readAndDispatch() ) {
                mDisplay_.sleep();
            }
        }
        LOG.debug( "All done" );

    }

}
