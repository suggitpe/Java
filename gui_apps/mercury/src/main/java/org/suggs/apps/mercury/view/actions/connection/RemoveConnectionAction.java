/*
 * RemoveConnectionAction.java created on 14 Jan 2009 19:15:11 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.util.image.ImageManager;
import org.suggs.apps.mercury.view.dialogs.SelectConnectionDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This action will manage the removal of a connection from the
 * connection store.
 * 
 * @author suggitpe
 * @version 1.0 14 Jan 2009
 */
public class RemoveConnectionAction extends Action implements InitializingBean
{

    private IConnectionStore mConnectionStore_;
    private String mConnectionToRemove_;

    {
        setToolTipText( "Remove an existing connection" );
        setImageDescriptor( ImageManager.getImageDescriptor( ImageManager.IMAGE_DELETE ) );
    }

    /**
     * Constructs a new instance.
     */
    public RemoveConnectionAction()
    {
        super();
        setText( "&Remove Connection" );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aConnectionToRemove
     *            the name of the connection to remove
     */
    public RemoveConnectionAction( IConnectionStore aConnStr, String aConnectionToRemove )
    {
        super();
        mConnectionStore_ = aConnStr;
        mConnectionToRemove_ = aConnectionToRemove;
        setText( "&Remove " + aConnectionToRemove );
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
        if ( mConnectionToRemove_ == null || mConnectionToRemove_.length() == 0 )
        {
            SelectConnectionDialog rcd = new SelectConnectionDialog( s,
                                                                     mConnectionStore_.getKnownConnections()
                                                                         .keySet(),
                                                                     "Remove existing connection",
                                                                     "Select the connection that you wish to remove from the below list" );
            int ok = rcd.open();
            mConnectionToRemove_ = rcd.getChoice();

            if ( ok != Dialog.OK )
            {
                return;
            }
        }

        // here we check we have valid data
        if ( mConnectionToRemove_ != null && mConnectionToRemove_.length() > 0 )
        {
            // check they do actually want it removed
            if ( !MessageDialog.openConfirm( s,
                                             "Confirm connection removal",
                                             "Please confirm that you wish to remove connection ["
                                                             + mConnectionToRemove_ + "]" ) )
            {
                return;
            }

            // be bullet proof
            if ( !mConnectionStore_.doesConnectionExist( mConnectionToRemove_ ) )
            {
                MessageDialog.openError( s,
                                         "Connection removal error",
                                         "The connection [" + mConnectionToRemove_
                                                         + "] does not actually exist" );
                return;
            }

            // now we actually remove the connection
            try
            {
                mConnectionStore_.deleteNamedConnection( mConnectionToRemove_ );
            }
            catch ( ConnectionStoreException e )
            {
                MessageDialog.openError( s,
                                         "Connection removal error",
                                         "Failed to remove connection [conn] because of the following error\n"
                                                         + e.getMessage() );
            }
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
