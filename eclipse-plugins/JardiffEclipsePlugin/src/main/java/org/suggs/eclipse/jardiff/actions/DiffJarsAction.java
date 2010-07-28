/*
 * DiffJarsAction.java created on 15 Mar 2010 19:44:16 by suggitpe for project JardiffPlugin
 * 
 */
package org.suggs.eclipse.jardiff.actions;

import org.suggs.eclipse.jardiff.dialogs.GetJarInformationDialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

/**
 * @author suggitpe
 * @version 1.0 15 Mar 2010
 */
public class DiffJarsAction extends Action {

    private static final Log LOG = LogFactory.getLog( DiffJarsAction.class );

    /**
     * Constructs a new instance.
     */
    public DiffJarsAction() {}

    public DiffJarsAction( IFile aFile ) {}

    @Override
    public void run() {

        GetJarInformationDialog dialog = new GetJarInformationDialog( Display.getDefault().getActiveShell() );
        dialog.open();

        if ( dialog.getReturnCode() == Window.CANCEL ) {
            return;
        }

        String toJar = dialog.getToJarName();
        String fromJar = dialog.getFromJarName();
        String output = dialog.getDiffOutput();
    }

}
