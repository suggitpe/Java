/*
 * LayoutExample.java created on 8 Apr 2008 06:45:42 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.rowlayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * This example is a very simple implementation of the RowLayout
 * 
 * @author suggitpe
 * @version 1.0 8 Apr 2008
 */
public class SimpleRowLayoutExample
{

    private static final Log LOG = LogFactory.getLog( SimpleRowLayoutExample.class );

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        LOG.info( "Building dialog" );
        Display d = new Display();
        Shell shell = new Shell( d );

        // create the layout
        RowLayout lay = new RowLayout();
        // optionally set some of the layout fields
        lay.wrap = true;
        lay.marginLeft = 5;
        lay.marginRight = 5;
        lay.marginTop = 5;
        lay.marginBottom = 5;
        shell.setLayout( lay );

        // now populate
        new Button( shell, SWT.PUSH ).setText( "B1" );
        new Button( shell, SWT.PUSH ).setText( "Wide Button ?" );
        new Button( shell, SWT.PUSH ).setText( "Button 3" );

        shell.pack();
        shell.open();
        LOG.debug( "Dialog opened" );
        while ( !shell.isDisposed() )
        {
            if ( !d.readAndDispatch() )
            {
                d.sleep();
            }
        }
        LOG.debug( "All done" );
    }

}
