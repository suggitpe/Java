/*
 * AbstractXmlActionDelegate.java created on 11 Mar 2008 07:42:17 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.actions;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;

/**
 * Abstract class to manage some of the base functionality
 * 
 * @author suggitpe
 * @version 1.0 11 Mar 2008
 */
public class AbstractXmlActionDelegate
{

    private IStructuredSelection mSelection_;

    /**
     * Constructs a new instance.
     */
    public AbstractXmlActionDelegate()
    {
        super();
    }

    @SuppressWarnings("unused")
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
     * Returns the value of selection.
     * 
     * @return Returns the selection.
     */
    public IStructuredSelection getSelection()
    {
        return mSelection_;
    }

    /**
     * Sets the selection field to the specified value.
     * 
     * @param selection
     *            The selection to set.
     */
    public void setSelection( IStructuredSelection selection )
    {
        mSelection_ = selection;
    }

    private void debug()
    {
        System.out.println( "======================================" );
        System.out.println( "Selection: " + mSelection_.getClass().getName() );
        Iterator iter = mSelection_.iterator();
        System.out.println( "selection contents: " + iter.next().getClass().getName() );
    }

}
