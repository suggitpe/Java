package org.suggs.sandbox_eclipse.testplugin.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Our sample action implements workbench action delegate. The action
 * proxy will be created by the workbench and shown in the UI. When
 * the user tries to use the action, this delegate will be created and
 * execution will be delegated to it.
 * 
 * @see IWorkbenchWindowActionDelegate
 */

public class CreateXmlActionDelegate
    implements IWorkbenchWindowActionDelegate, IObjectActionDelegate
{

    @SuppressWarnings("unused")
    private IWorkbenchWindow window;
    private IStructuredSelection mSelection_;

    /**
     * The constructor.
     */
    public CreateXmlActionDelegate()
    {
        super();
    }

    /**
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run( IAction action )
    {
        if ( mSelection_ != null && !mSelection_.isEmpty() )
        {
            debug();
            Object element = mSelection_.getFirstElement();
            if ( element instanceof IFile )
            {
                CreateXmlFileAction ca = new CreateXmlFileAction( (IFile) element );
                ca.run();
            }
            else
            {
                MessageDialog.openError( Display.getDefault().getActiveShell(),
                                         "Incorrect resource selection",
                                         "In order to generate the FSM Spring XML you need to actually select a model" );
            }
        }
    }

    /**
     * Selection in the workbench has been changed. We can change the
     * state of the 'real' action here if we want, but this can only
     * happen after the delegate has been created.
     * 
     * @see IWorkbenchWindowActionDelegate#selectionChanged
     */
    public void selectionChanged( IAction action, ISelection selection )
    {
        mSelection_ = (IStructuredSelection) selection;
    }

    /**
     * We can use this method to dispose of any system resources we
     * previously allocated.
     * 
     * @see IWorkbenchWindowActionDelegate#dispose
     */
    public void dispose()
    {
    }

    /**
     * We will cache window object in order to be able to provide
     * parent shell for the message dialog.
     * 
     * @see IWorkbenchWindowActionDelegate#init
     */
    public void init( IWorkbenchWindow aWindow )
    {
        this.window = aWindow;
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction,
     *      IWorkbenchPart)
     */
    public void setActivePart( IAction action, IWorkbenchPart targetPart )
    {
    }

    /**
     * Simple one for debugging the selected items
     */
    private void debug()
    {
        System.out.println( "======================================" );
        System.out.println( "Selection: " + mSelection_.getClass().getName() );
        Iterator iter = mSelection_.iterator();
        System.out.println( "selection contents: " + iter.next().getClass().getName() );
    }
}
