/*
 * GetJarInformationDialog.java created on 16 Mar 2010 07:11:50 by suggitpe for project JardiffPlugin
 * 
 */
package org.suggs.eclipse.jardiff.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog to get the jar information for diffing.
 * 
 * @author suggitpe
 * @version 1.0 16 Mar 2010
 */
public class GetJarInformationDialog extends Dialog
{

    private String fromJarName;
    private String toJarName;

    /**
     * Constructs a new instance.
     * 
     * @param aParentShell
     */
    public GetJarInformationDialog( Shell aParentShell )
    {
        super( aParentShell );
    }

    /**
     * Getter for the name of the From jar
     * 
     * @return the from jar name
     */
    public String getFromJarName()
    {
        return fromJarName;
    }

    /**
     * Getter for name of the To Jar
     * 
     * @return the to jar name
     */
    public String getToJarName()
    {
        return toJarName;
    }

}
