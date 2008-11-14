/*
 * HelpDebugDialog.java created on 16 Oct 2008 18:33:04 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.dialogs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * This class provides the ability to debug the connections that have
 * been configured in the system. It is really there only for testing
 * the connections are correct in test.
 * 
 * @author suggitpe
 * @version 1.0 16 Oct 2008
 */
public class HelpConnectionDebugDialog extends Dialog
{

    private static final Log LOG = LogFactory.getLog( HelpConnectionDebugDialog.class );

    /**
     * Constructs a new instance.
     */
    public HelpConnectionDebugDialog( Shell parentShell )
    {
        super( parentShell );
    }

    /**
     * Sets up the dialog area
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent )
    {
        Composite comp = (Composite) super.createDialogArea( parent );
        comp.getShell().setText( "Connections debug" );
        comp.getShell().setSize( 200, 200 );

        GridLayout layout = (GridLayout) comp.getLayout();
        // here we should build a tree and populate with the
        // connection data

        return comp;

    }

    /**
     * Sets up the OK button for the about dialog
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar( Composite parent )
    {
        createButton( parent, IDialogConstants.OK_ID, "OK", true );
    }

}
