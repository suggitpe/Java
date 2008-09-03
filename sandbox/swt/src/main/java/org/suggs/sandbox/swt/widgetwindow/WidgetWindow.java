/*
 * WidgetWindow.java created on 11 Aug 2008 07:17:52 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow;

import org.suggs.sandbox.swt.widgetwindow.ch3.Ch3Composite;
import org.suggs.sandbox.swt.widgetwindow.ch4.Ch4Composite;
import org.suggs.sandbox.swt.widgetwindow.ch5.Ch5Composite;
import org.suggs.sandbox.swt.widgetwindow.ch6.Ch6FormLayoutComposite;
import org.suggs.sandbox.swt.widgetwindow.ch7.Ch7Composite;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

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
        TabFolder f = new TabFolder( parent, SWT.NONE );

        TabItem chap3b = new TabItem( f, SWT.NONE );
        chap3b.setText( "Chapter 3" );
        chap3b.setControl( new Ch3Composite( f ) );

        TabItem chap4 = new TabItem( f, SWT.NONE );
        chap4.setText( "Chapter 4" );
        chap4.setControl( new Ch4Composite( f ) );

        TabItem chap5 = new TabItem( f, SWT.NONE );
        chap5.setText( "Chapter 5 widgets" );
        chap5.setControl( new Ch5Composite( f ) );

        TabItem chap6 = new TabItem( f, SWT.NONE );
        chap6.setText( "Chapter 6 layout" );
        chap6.setControl( new Ch6FormLayoutComposite( f ) );

        TabItem chap7 = new TabItem( f, SWT.NONE );
        chap7.setText( "Chapter 7 graphics" );
        chap7.setControl( new Ch7Composite( f ) );

        getShell().setText( "Widget Window" );
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
