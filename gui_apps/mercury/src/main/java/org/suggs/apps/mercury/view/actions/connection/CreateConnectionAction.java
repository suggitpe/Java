/*
 * CreateConnectionAction.java created on 15 Sep 2008 18:42:43 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.actions.connection;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.IConnectionStore;
import org.suggs.apps.mercury.model.util.image.ImageManager;
import org.suggs.apps.mercury.view.wizards.createconnection.CreateConnectionWizard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This action is responsible for the creation of new connections
 * 
 * @author suggitpe
 * @version 1.0 15 Sep 2008
 */
public class CreateConnectionAction extends Action implements InitializingBean
{

    private static final Log LOG = LogFactory.getLog( CreateConnectionAction.class );

    private IConnectionStore mConnectionStore_;

    /**
     * Constructs a new instance.
     */
    public CreateConnectionAction()
    {
        super( "&Create Connection" );
        setToolTipText( "Create new connection" );
        setImageDescriptor( ImageManager.getImageDescriptor( ImageManager.IMAGE_CONN_NEW_CONN ) );
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
        CreateConnectionWizard ccw = new CreateConnectionWizard();
        WizardDialog d = new WizardDialog( Display.getCurrent().getActiveShell(), ccw );
        int ret = d.open();
        if ( Dialog.OK == ret )
        {
            ConnectionDetails dtls = ccw.getConnectionDetails();
            if ( dtls == null )
            {
                MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                         "Wizard Failed",
                                         "For some reason we have not been able to create a connection details from this wizard.  This is a system error." );
                return;
            }

            String name = dtls.getName();
            if ( mConnectionStore_.doesConnectionExist( name ) )
            {
                final IInputValidator validator = new IInputValidator()
                {

                    public String isValid( String txt )
                    {
                        if ( txt.length() < 5 )
                        {
                            return "You must enter at least 5 characters";
                        }
                        else if ( txt.length() > 22 )
                        {
                            return "You cannot enter more than 22 characters";
                        }
                        else
                        {
                            return null;
                        }
                    }
                };

                InputDialog id = new InputDialog( Display.getCurrent().getActiveShell(),
                                                  "New Connection name",
                                                  "The connection name you have chosen has already been used",
                                                  "newConnection_" + System.currentTimeMillis(),
                                                  validator );

                while ( mConnectionStore_.doesConnectionExist( dtls.getName() ) )
                {
                    int i = id.open();
                    if ( i == Window.OK )
                    {
                        dtls.setName( id.getValue() );
                    }
                    else if ( i == Window.CANCEL )
                    {
                        LOG.info( "Cancelled connection save" );
                        return;
                    }

                }
            }

            try
            {
                mConnectionStore_.saveConnectionParameters( dtls.getName(), dtls );
                LOG.info( "Created new connection [" + dtls.getName() + "] in connection store" );
                return;
            }
            catch ( ConnectionStoreException cse )
            {
                MessageDialog.openWarning( Display.getCurrent().getActiveShell(),
                                           "Connection Store error",
                                           "Failed to store new connection:\n" + cse.getMessage() );
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
