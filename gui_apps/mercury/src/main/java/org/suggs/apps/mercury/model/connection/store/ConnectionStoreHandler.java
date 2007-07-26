/*
 * ConnectionStoreHandler.java created on 23 Jul 2007 18:44:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.MercuryRuntimeException;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Connection store sax parser handler
 * 
 * @author suggitpe
 * @version 1.0 23 Jul 2007
 */
final class ConnectionStoreHandler extends DefaultHandler
{

    private static final Log LOG = LogFactory.getLog( ConnectionStoreHandler.class );

    private boolean mFailOnError = true;

    /**
     * Constructs a new instance.
     */
    public ConnectionStoreHandler()
    {
        super();
        String go = System.getProperty( "mercury.failonerror" );
        if ( go != null && go.equals( "false" ) )
        {
            mFailOnError = false;
            LOG.debug( "FailOnError =[false]" );
        }
    }
    
    

    /**
     * @see org.xml.sax.helpers.DefaultHandler#resolveEntity(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public InputSource resolveEntity( String publicId, String systemId ) throws java.io.IOException
    {
        // now get the dtd filename from the systemId URL
        int separator = 0;
        int pos = 0;
        while ( -1 != separator )
        {
            separator = systemId.indexOf( '/', separator );
            if ( -1 != separator )
            {
                pos = separator;
                ++separator;
            }
        }

        String dtdFileName = "dtd/" + systemId.substring( pos + 1 );

        InputStream stream = ConnectionStoreHandler.class.getClassLoader().getResourceAsStream( dtdFileName );
        if ( stream == null )
        {
            String err = "Unable to load dtd file [" + dtdFileName + "]";
            LOG.error( err );
            throw new IOException( err );
        }

        return new InputSource( stream );
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
     */
    @Override
    public void error( SAXParseException e )
    {
        LOG.error( "Error picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( mFailOnError )
        {
            throw new MercuryRuntimeException( e );
        }
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
     */
    @Override
    public void warning( SAXParseException e )
    {
        LOG.warn( "Warning picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( mFailOnError )
        {
            throw new MercuryRuntimeException( e );
        }
    }

    /**
     * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
     */
    @Override
    public void fatalError( SAXParseException e )
    {
        LOG.error( "Fatal error picked up in the Connection Store Handler [" + e.getMessage() + "]" );
        if ( mFailOnError )
        {
            throw new MercuryRuntimeException( e );
        }
    }
}
