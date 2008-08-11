/*
 * WidgetWindow.java created on 11 Aug 2008 07:17:52 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Construct the basic window and its operations.
 * 
 * @author suggitpe
 * @version 1.0 11 Aug 2008
 */
public class WidgetWindow extends ApplicationWindow
{

    /**
     * Constructs a new instance.
     */
    public WidgetWindow()
    {
        // let parent set up shell
        super( null );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent )
    {
        getShell().setText( "Widget Window" );
        parent.setSize( 400, 250 );
        return parent;
    }

    /**
     * Main app
     * 
     * @param args
     */
    public static void main( String[] args )
    {
        WidgetWindow wwin = new WidgetWindow();
        wwin.setBlockOnOpen( true );
        wwin.open();
        Display.getCurrent().dispose();
    }

}
