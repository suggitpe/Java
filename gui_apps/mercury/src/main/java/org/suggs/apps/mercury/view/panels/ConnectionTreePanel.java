/*
 * ConnectionTreePanel.java created on 24 Nov 2008 09:14:02 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.panels;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * In this panel we look at the connections from the connection store
 * as a tree that we can traverse
 * 
 * @author suggitpe
 * @version 1.0 24 Nov 2008
 */
public class ConnectionTreePanel extends Composite
{

    TreeViewer mViewer_ = null;

    /**
     * Constructs a new instance.
     */
    public ConnectionTreePanel( Composite parent )
    {
        super( parent, SWT.BORDER );
        setLayout( new FillLayout() );
        populateCtrls();

    }

    private void populateCtrls()
    {
        mViewer_ = new TreeViewer( this, SWT.NONE );
        mViewer_.getTree().setToolTipText( "Select a connection" );

    }

}
