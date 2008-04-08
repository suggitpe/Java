package org.suggs.sandbox_eclipse.testplugin.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CreateXmlActionPopupDelegate extends AbstractXmlActionDelegate
    implements IObjectActionDelegate
{

    /**
     * Constructor for Action1.
     */
    public CreateXmlActionPopupDelegate()
    {
        super();
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged( IAction action, ISelection selection )
    {
        this.setSelection( (IStructuredSelection) selection );
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction,
     *      IWorkbenchPart)
     */
    public void setActivePart( IAction action, IWorkbenchPart targetPart )
    {
    }

}
