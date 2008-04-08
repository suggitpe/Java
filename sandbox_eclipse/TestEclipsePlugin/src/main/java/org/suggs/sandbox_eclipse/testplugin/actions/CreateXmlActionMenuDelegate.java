package org.suggs.sandbox_eclipse.testplugin.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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

public class CreateXmlActionMenuDelegate extends AbstractXmlActionDelegate
    implements IWorkbenchWindowActionDelegate
{

    @SuppressWarnings("unused")
    private IWorkbenchWindow window;

    /**
     * The constructor.
     */
    public CreateXmlActionMenuDelegate()
    {
        super();
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
        this.setSelection( (IStructuredSelection) selection );
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
}
