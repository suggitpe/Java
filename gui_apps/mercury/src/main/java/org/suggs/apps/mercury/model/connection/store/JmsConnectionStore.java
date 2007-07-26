/*
 * JmsConnectionStore.java created on 28 Jun 2007 18:36:21 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.MercuryException;
import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.IJmsConnectionStore;
import org.suggs.apps.mercury.model.connection.MercuryConnectionException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

/**
 * Implementation of the IJmsConnectionStore. This implementation will
 * use an XML file stored in the users home directory (in a .jmshelper
 * dir), for the persistence of the connection details.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class JmsConnectionStore extends Observable implements IJmsConnectionStore
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionStore.class );
    private static final String MERCURY_HOME_DIR = System.getProperty( "user.home" ) + "/.mercury";
    private static final File MERCURY_FILE = new File( MERCURY_HOME_DIR + "/connectionStore.xml" );

    private String mStoreState_ = new String( "Unsaved" );
    private Map<String, IJmsConnectionDetails> mConnStore_ = new HashMap<String, IJmsConnectionDetails>();

    /**
     * Constructs a new instance.
     */
    public JmsConnectionStore()
    {
        super();
        verifyPersistenceLayerExists();
        try
        {
            readPersistenceLayer();
        }
        catch ( MercuryException jhe )
        {
            LOG.warn( "UNable to read in connection store details [" + jhe.getMessage() + "]" );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#loadConnectionParameters(java.lang.String)
     */
    public IJmsConnectionDetails loadConnectionParameters( String aName ) throws MercuryConnectionException
    {
        IJmsConnectionDetails ret = mConnStore_.get( aName );
        if ( ret == null )
        {
            throw new MercuryConnectionException( "Connection [" + aName + "] does not exist in the connection store" );
        }
        return ret;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#getListOfKnownConnectionNames()
     */
    public String[] getListOfKnownConnectionNames()
    {
        Set<String> keys = mConnStore_.keySet();
        return keys.toArray( new String[keys.size()] );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#saveConnectionParameters(org.suggs.apps.mercury.model.connection.IJmsConnectionDetails)
     */
    public void saveConnectionParameters( IJmsConnectionDetails aDetails ) throws MercuryConnectionException
    {
        LOG.warn( "Save impl has not been done yet" );
        mStoreState_ = "Saved";
        setChanged();
        notifyObservers();
    }

    /**
     * Reads in the persistence layer file and constructs the
     * connection store map
     */
    private static final void readPersistenceLayer() throws MercuryException
    {
        try
        {
            SAXParserFactory fact = SAXParserFactory.newInstance();
            fact.setValidating( true );
            SAXParser saxp = fact.newSAXParser();
            saxp.parse( MERCURY_FILE, new ConnectionStoreHandler() );

        }
        catch ( SAXException se )
        {
            throw new MercuryException( se );
        }
        catch ( ParserConfigurationException pce )
        {
            throw new MercuryException( pce );
        }
        catch ( IOException ioe )
        {
            throw new MercuryException( ioe );
        }
    }

    /**
     * Verify that the persistence directory exists for the mercury
     * application
     */
    private static final void verifyPersistenceLayerExists()
    {
        // check that the persistence dir exists
        File dir = new File( MERCURY_HOME_DIR );
        if ( !( dir.exists() ) )
        {
            LOG.warn( "Mercury home dir does not exist or is not writable ... assuming firt application execution" );
            LOG.info( "Creating MERCURY xml persistence store dir [" + dir.getAbsolutePath() + "]" );
            if ( !( dir.mkdir() ) )
            {
                throw new IllegalStateException( "Failed to create persistence dir [" + MERCURY_HOME_DIR + "]" );
            }
            createPersistenceFile();
        }
        else
        {
            if ( !( dir.isDirectory() ) || !( dir.canWrite() ) )
            {
                String err = "Cannot write to the mercury persistence directory [" + MERCURY_HOME_DIR + "]";
                LOG.error( err );
                throw new IllegalStateException( err );
            }
        }

        // now check that the actual file exists
        if ( !( MERCURY_FILE.exists() ) )
        {
            createPersistenceFile();
        }
        else
        {
            if ( !( MERCURY_FILE.isFile() ) || !( MERCURY_FILE.canWrite() ) )
            {
                String err = "Cannot write to mercury persistence layer [" + MERCURY_FILE.getAbsolutePath() + "]";
                LOG.error( err );
                throw new IllegalStateException( err );
            }
        }

        LOG.debug( "Mercury connection store persistence layer correctly set up" );
    }

    /**
     * Create the underlying xml persistence file
     */
    private static final void createPersistenceFile()
    {
        LOG.info( "Creating MERCURY xml persistence store [" + MERCURY_FILE.getAbsolutePath() + "]" );
        try
        {
            if ( !( MERCURY_FILE.createNewFile() ) )
            {
                throw new IllegalStateException( "Failed to create the persistence xml file [" + MERCURY_FILE.getAbsolutePath() + "]" );
            }
        }
        catch ( IOException ioe )
        {
            LOG.error( "Failed to create persistence file [" + ioe.getMessage() + "]" );
            throw new IllegalStateException( ioe );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.IJmsConnectionStore#getState()
     */
    public String getState()
    {
        return mStoreState_;
    }

    /**
     * Setter for the store state
     * 
     * @param aState
     *            the state to set
     */
    public void setState( String aState )
    {
        mStoreState_ = aState;
    }

}
