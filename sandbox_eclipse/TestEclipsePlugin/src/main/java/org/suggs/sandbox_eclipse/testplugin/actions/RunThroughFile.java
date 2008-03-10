package org.suggs.sandbox_eclipse.testplugin.actions;

import java.util.Iterator;

import org.eclipse.core.internal.resources.File;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class RunThroughFile implements IObjectActionDelegate
{

    private IStructuredSelection mSelection_;
    private IWorkbenchPart mActivePart_;

    /**
     * Constructor for Action1.
     */
    public RunThroughFile()
    {
        super();
    }

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run( IAction action )
    {
        if ( mSelection_ != null && !mSelection_.isEmpty() )
        {
            System.out.println( "Selection:" + mSelection_.getClass().getName() );
            System.out.println( "Active Part: " + mActivePart_.getClass().getName() );
            Iterator iter = mSelection_.iterator();
            System.out.println( "selection contents: " + iter.next().getClass().getName() );

            File f;
        }

    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged( IAction action, ISelection selection )
    {
        mSelection_ = (IStructuredSelection) selection;
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction,
     *      IWorkbenchPart)
     */
    public void setActivePart( IAction action, IWorkbenchPart targetPart )
    {
        mActivePart_ = targetPart;
    }

}
