/*
 * CreateConnectionWizard.java created on 17 Sep 2008 18:43:06 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.view.wizards.createconnection;

import org.suggs.apps.mercury.model.adapters.ibmmq.IbmMqAdapter;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.util.ImageManager;
import org.suggs.apps.mercury.view.wizards.createconnection.pages.ConnectionDataSummaryPage;
import org.suggs.apps.mercury.view.wizards.createconnection.pages.GenericConnectionDetailsPage;
import org.suggs.apps.mercury.view.wizards.createconnection.pages.IbmMqConnectionDataPage;
import org.suggs.apps.mercury.view.wizards.createconnection.pages.SelectConnectionTypePage;
import org.suggs.apps.mercury.view.wizards.createconnection.pages.WelcomePage;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * A wizard that allows you to create a new connection.
 * 
 * @author suggitpe
 * @version 1.0 17 Sep 2008
 */
public class CreateConnectionWizard extends Wizard
{

    private static final Log LOG = LogFactory.getLog( CreateConnectionWizard.class );

    public static final String CONN_NAME = "Connection Name";
    public static final String CONN_TYPE = "Connection Type";
    public static final String CONN_HOST = "Connection Hostname";
    public static final String CONN_PORT = "Connection Port";
    public static final String CONN_SEC = "Connection Security Set";
    public static final String CONN_USER = "Connection Username";
    public static final String CONN_PASS = "Connection Password";
    public static final String CONN_CHANNEL = "Connection Channel name";

    /**
     * Constructs a new instance.
     */
    public CreateConnectionWizard()
    {
        super();
        setWindowTitle( "Create Connection Wizard" );
        setDefaultPageImageDescriptor( ImageManager.getImageDescriptor( getClass().getClassLoader(),
                                                                        ImageManager.IMAGE_CONNECTION ) );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages()
    {
        addPage( new WelcomePage() );
        addPage( new SelectConnectionTypePage() );
        addPage( new GenericConnectionDetailsPage() );
        addPage( new IbmMqConnectionDataPage() );
        addPage( new ConnectionDataSummaryPage() );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage( IWizardPage page )
    {
        if ( page instanceof ConnectionDataSummaryPage )
        {
            ( (ConnectionDataSummaryPage) page ).updateTableContents();
        }
        return super.getNextPage( page );
    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish()
    {
        // get the pages that have ben filled
        SelectConnectionTypePage selConnPg = (SelectConnectionTypePage) getPage( SelectConnectionTypePage.PAGE_NAME );

        // create the connection details
        ConnectionDetails dtls = new ConnectionDetails( selConnPg.getConnectionName() );
        dtls.setType( selConnPg.getConnectionType() );
        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "In finish with: " + dtls.toString() );
        }

        // now we can put them into the store and lt the store manage
        // the persistence for us

        return true;
    }

    /**
     * This method will go through the pages and collect the
     * connection data.
     * 
     * @return a map of the connection data that has been entered in
     *         the wizard.
     */
    public Map<String, String> getConnectionData()
    {
        SelectConnectionTypePage selPg = (SelectConnectionTypePage) getPage( SelectConnectionTypePage.PAGE_NAME );
        GenericConnectionDetailsPage genConnPg = (GenericConnectionDetailsPage) getPage( GenericConnectionDetailsPage.PAGE_NAME );
        IbmMqConnectionDataPage ibmMqPg = (IbmMqConnectionDataPage) getPage( IbmMqConnectionDataPage.PAGE_NAME );

        Map<String, String> ret = new TreeMap<String, String>();

        ret.put( CONN_NAME, selPg.getConnectionName() );
        ret.put( CONN_TYPE, selPg.getConnectionType() );

        ret.put( CONN_HOST, genConnPg.getHostname() );
        ret.put( CONN_PORT, Integer.toString( genConnPg.getPort() ) );

        if ( selPg.getConnectionType().equals( IbmMqAdapter.TYPE ) )
        {
            ret.put( CONN_CHANNEL, ibmMqPg.getChannelName() );
        }

        ret.put( CONN_SEC, Boolean.toString( genConnPg.isSecurityEnabled() ) );
        if ( genConnPg.isSecurityEnabled() )
        {
            ret.put( CONN_USER, genConnPg.getUsername() );
            ret.put( CONN_PASS, genConnPg.getPassword() );
        }

        return ret;
    }

    /**
     * This method will
     * 
     * @return
     */
    public ConnectionDetails createConnectionDetails()
    {
        LOG.debug( "Building connection details from page data" );
        Map<String, String> map = getConnectionData();

        int port = 0;
        try
        {
            port = Integer.parseInt( map.get( CONN_PORT ) );
        }
        catch ( NumberFormatException nfe )
        {
            // only warn as the gui should ensure that is a
            // validinteger
            LOG.warn( "Failed to parse integer for port number" );
        }
        ConnectionDetails dtls = new ConnectionDetails( map.get( CONN_NAME ),
                                                        map.get( CONN_TYPE ),
                                                        map.get( CONN_HOST ),
                                                        port );

        return dtls;

    }

    /**
     * @see org.eclipse.jface.wizard.Wizard#performCancel()
     */
    @Override
    public boolean performCancel()
    {
        LOG.debug( "Cancelled from create connection wizard" );
        return true;
    }

}
