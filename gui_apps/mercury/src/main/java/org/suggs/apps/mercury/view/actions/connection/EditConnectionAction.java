/*
 * EditConnectionAction.java created on 16 Jan 2009 08:16:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.view.dialogs.SelectConnectionDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.springframework.util.Assert;

/**
 * This action will allow you to update an existing connection within
 * the connection store.
 * 
 * @author suggitpe
 * @version 1.0 16 Jan 2009
 */
public class EditConnectionAction extends Action
{

    private IConnectionStore mConnectionStore_;
    private String mConnectionToEdit_;

    {
        setToolTipText( "Edit an existing connection" );
    }

    /**
     * Constructs a new instance.
     */
    public EditConnectionAction()
    {
        super();
        setText( "&Edit Connection" );
    }

    /**
     * Constructs a new instance.
     * 
     * @param mConnectionToEdit_
     *            the connecrtion to edit
     */
    public EditConnectionAction( IConnectionStore aConnStr, String aConnectionToEdit )
    {
        super();
        setText( "&Edit " + aConnectionToEdit );
        mConnectionStore_ = aConnStr;
        mConnectionToEdit_ = aConnectionToEdit;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mConnectionStore_,
                        "No connection store set on the create connection wizard" );
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        Shell s = Display.getCurrent().getActiveShell();

        // only get the connection if we need to, else we will proceed
        // with removal
        if ( mConnectionToEdit_ == null || mConnectionToEdit_.length() == 0 )
        {
            SelectConnectionDialog rcd = new SelectConnectionDialog( s,
                                                                     mConnectionStore_.getListOfKnownConnectionNames(),
                                                                     "Edit existing connection",
                                                                     "Select the connection that you wish to edit from the below list" );
            int ok = rcd.open();
            mConnectionToEdit_ = rcd.getChoice();

            if ( ok != Dialog.OK )
            {
                return;
            }
        }

        // here we check we have valid data
        if ( mConnectionToEdit_ != null && mConnectionToEdit_.length() > 0 )
        {
        }
    }

    /**
     * Setter for the connection store
     * 
     * @param store
     *            the store to set
     */
    public void setConnectionStore( IConnectionStore store )
    {
        mConnectionStore_ = store;
    }

}
