/*
 * DisplaySpringXmlDialog.java created on 3 Apr 2008 18:16:58 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog to show to the user the results from the xml generation
 * 
 * @author suggitpe
 * @version 1.0 3 Apr 2008
 */
public class DisplaySpringXmlDialog extends Dialog
{

    String mXml_;

    /**
     * Constructs a new instance.
     */
    public DisplaySpringXmlDialog( Shell aParentShell, String aXml )
    {
        super( aParentShell );
        mXml_ = aXml;
    }

    /**
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent )
    {
        Composite ret = (Composite) super.createDialogArea( parent );

        // set the group
        Group grp = new Group( parent, SWT.None );
        grp.setText( "Spring XML" );

        // set the layout
        GridLayout lay = new GridLayout( 1, false );
        grp.setLayout( lay );

        // set the layout data
        GridData data = new GridData( GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL );
        grp.setLayoutData( data );

        // now we can build the internal widgets
        Text res = new Text( grp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL
                                  | SWT.READ_ONLY );
        res.setText( mXml_ );
        res.selectAll();

        return ret;
    }

    /**
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell newShell )
    {
        super.configureShell( newShell );
        newShell.setText( "FSM Spring XML Results" );
    }

}
