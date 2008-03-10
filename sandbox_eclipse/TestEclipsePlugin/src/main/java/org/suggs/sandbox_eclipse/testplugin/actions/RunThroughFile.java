package org.suggs.sandbox_eclipse.testplugin.actions;

import java.util.Iterator;

import org.eclipse.core.internal.resources.File;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

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

        Shell shell = new Shell();
        // MessageDialog.openInformation( shell, "DeleeMe Plug-in",
        // "New Action was executed." );

        FormToolkit toolKit = new FormToolkit( shell.getDisplay() );
        ScrolledForm form = toolKit.createScrolledForm( shell );

        form.setText( "This is a test form" );

        Layout lay = new GridLayout( 2, false );
        form.getBody().setLayout( lay );

        form.pack();
        form.redraw();

        toolKit.paintBordersFor( form.getBody() );
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
