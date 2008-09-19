/*
 * MercuryMain.java created on 13 Sep 2008 08:52:01 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury;

import org.suggs.apps.mercury.view.IMenuFactory;
import org.suggs.apps.mercury.view.IToolBarFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * This is the main screen for the Mercury application. This is here
 * you can coordinate all of the main application activities.
 * 
 * @author suggitpe
 * @version 1.0 13 Sep 2008
 */
public class MercuryMain extends ApplicationWindow
{

    private static final Log LOG = LogFactory.getLog( MercuryMain.class );

    /**
     * Constructs a new instance.
     * 
     * @param parentShell
     */
    public MercuryMain()
    {
        // let the parent set up the shell
        super( null );
        addMenuBar();
        addToolBar( SWT.FLAT | SWT.WRAP );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite parent )
    {
        getShell().setText( "Mercury messenger" );
        getShell().setImage( new Image( getShell().getDisplay(), getClass().getClassLoader()
            .getResourceAsStream( "images/mercury.png" ) ) );
        // remove this
        parent.setSize( 400, 200 );
        return parent;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager()
    {
        IMenuFactory fact = (IMenuFactory) ContextProvider.instance().getBean( "menuFactory" );
        return fact.createMenuManager( "MAIN" );

    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
     */
    @Override
    protected ToolBarManager createToolBarManager( int style )
    {
        IToolBarFactory fact = (IToolBarFactory) ContextProvider.instance()
            .getBean( "toolBarFactory" );
        return fact.createToolbar( "MAIN", style );
    }

    /**
     * This is the main method. It will simply creates an instance of
     * this class and opens it up for use.
     * 
     * @param args
     *            the arguments passed in from the command line
     */
    public static void main( String[] args )
    {
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "*************************************" );
            LOG.debug( "Classpath: " + System.getProperty( "java.class.path" ) );
            LOG.debug( "Java version: " + System.getProperty( "java.version" ) );
            LOG.debug( "*************************************" );
        }

        MercuryMain m = new MercuryMain();
        m.setBlockOnOpen( true );
        m.open();
        Display.getCurrent().dispose();
    }
}
