/*
 * DiffJarsActionDelegate.java created on 15 Mar 2010 07:11:19 by suggitpe for project JardiffPlugin
 * 
 */
package org.suggs.eclipse.jardiff.actions;

import java.util.Iterator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * This class will po
 * 
 * @author suggitpe
 * @version 1.0 15 Mar 2010
 */
public class DiffJarsActionDelegate
    implements IWorkbenchWindowActionDelegate, IObjectActionDelegate
{

    private IWorkbenchWindow window;
    private IStructuredSelection selection;

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    @Override
    public void run( IAction aAction )
    {
        MessageDialog.openInformation( window.getShell(),
                                       "JardiffPlugin",
                                       "Hello, Eclipse world gggg" );
    }

    /**
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
     */
    @Override
    public void dispose()
    {}

    /**
     * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
     */
    @Override
    public void init( IWorkbenchWindow aWorkbenchWindow )
    {
        window = aWorkbenchWindow;
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged( IAction aAction, ISelection aSelection )
    {
        selection = (IStructuredSelection) aSelection;
    }

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
     *      org.eclipse.ui.IWorkbenchPart)
     */
    @Override
    public void setActivePart( IAction aAction, IWorkbenchPart aWorkbenchpart )
    {}

    /**
     * Simple one for debugging the selected items
     */
    @SuppressWarnings("unchecked")
    void debug()
    {
        System.out.println( "======================================" );
        System.out.println( "Selection: " + selection.getClass().getName() );
        Iterator iter = selection.iterator();
        System.out.println( "selection contents: " + iter.next().getClass().getName() );
    }
}
