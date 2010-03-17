/*
 * Explorer.java created on 19 Dec 2008 21:35:15 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import java.io.File;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

/**
 * This is the main application window class
 * 
 * @author suggitpe
 * @version 1.0 19 Dec 2008
 */
public class Explorer extends ApplicationWindow
{

    private TableViewer mTable_;
    private TreeViewer mTree_;
    private OpenAction mOpen_;
    private CopyFileNamesToClipboardAction mCopy_;
    private ExitAction mExit_;

    /**
     * Constructs a new instance.
     */
    public Explorer()
    {
        super( null );
        mOpen_ = new OpenAction( this );
        mCopy_ = new CopyFileNamesToClipboardAction( this );
        mExit_ = new ExitAction( this );

        addStatusLine();
        addMenuBar();
        addToolBar( SWT.FLAT | SWT.WRAP );
    }

    /**
     * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents( Composite composite )
    {
        getShell().setText( "JFace File Explorer" );
        // create sash form
        SashForm sf = new SashForm( composite, SWT.HORIZONTAL | SWT.NULL );

        // build tree viewer
        mTree_ = new TreeViewer( sf );
        mTree_.setContentProvider( new FileTreeContentProvider() );
        mTree_.setLabelProvider( new FileTreeLabelProvider() );
        mTree_.setInput( new File( "C:\\" ) );
        mTree_.addFilter( new AllowOnlyFoldersFilter() );

        // create the table viewer
        mTable_ = new TableViewer( sf, SWT.BORDER | SWT.MULTI );
        mTable_.setContentProvider( new FileTableContentProvider() );
        mTable_.setLabelProvider( new FileTableLabelProvider() );
        mTable_.setSorter( new FileSorter() );

        TableColumn col1 = new TableColumn( mTable_.getTable(), SWT.LEFT );
        col1.setText( "Name" );
        col1.setWidth( 200 );

        TableColumn col2 = new TableColumn( mTable_.getTable(), SWT.RIGHT );
        col2.setText( "Size (k)" );
        col2.setWidth( 100 );

        mTable_.getTable().setHeaderVisible( true );

        // now set the listener on the tree view to change the input
        // to the table view
        mTree_.addSelectionChangedListener( new ISelectionChangedListener()
        {

            public void selectionChanged( SelectionChangedEvent event )
            {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                Object selectedFile = sel.getFirstElement();
                mTable_.setInput( selectedFile );
            }
        } );

        mTable_.addSelectionChangedListener( new ISelectionChangedListener()
        {

            public void selectionChanged( SelectionChangedEvent event )
            {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                setStatus( "Number of rows selected is " + sel.size() );

            }
        } );

        mTable_.addSelectionChangedListener( mOpen_ );

        // also we need
        MenuManager menu = new MenuManager();
        mTable_.getTable().setMenu( menu.createContextMenu( mTable_.getTable() ) );
        menu.add( mCopy_ );
        menu.add( mOpen_ );

        return sf;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createMenuManager()
     */
    @Override
    protected MenuManager createMenuManager()
    {
        MenuManager ret = new MenuManager( "" );

        MenuManager file = new MenuManager( "&File" );
        MenuManager edit = new MenuManager( "&Edit" );
        MenuManager view = new MenuManager( "&View" );

        ret.add( file );
        ret.add( edit );
        ret.add( view );

        file.add( mExit_ );

        edit.add( mCopy_ );
        edit.add( mOpen_ );

        return ret;
    }

    /**
     * @see org.eclipse.jface.window.ApplicationWindow#createToolBarManager(int)
     */
    @Override
    protected ToolBarManager createToolBarManager( int style )
    {
        ToolBarManager ret = new ToolBarManager( style );

        ret.add( mExit_ );
        ret.add( mCopy_ );

        ret.add( mOpen_ );

        return ret;
    }

    /**
     * Getter for the table selection
     * 
     * @return the table selection
     */
    protected IStructuredSelection getTableSelection()
    {
        return (IStructuredSelection) mTable_.getSelection();
    }

    public static void main( String[] aArgv )
    {
        Explorer e = new Explorer();
        e.setBlockOnOpen( true );
        e.open();
        Display.getCurrent().dispose();
        if ( !ImageUtil.getClipboard().isDisposed() )
        {
            ImageUtil.getClipboard().dispose();
        }
    }

}
