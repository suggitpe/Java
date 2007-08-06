/*
 * XmlPersistenceLayer.java created on 27 Jul 2007 06:27:26 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.store.persistence;

import org.suggs.apps.mercury.MercuryException;
import org.suggs.apps.mercury.model.connection.EConnectionType;
import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.MercuryConnectionStoreException;
import org.suggs.apps.mercury.model.connection.store.IPersistenceLayer;
import org.suggs.apps.mercury.model.connection.store.JmsConnectionDetails;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.DOMImplementationImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * TODO Write javadoc for XmlPersistenceLayer
 * 
 * @author suggitpe
 * @version 1.0 27 Jul 2007
 */
public class XmlPersistenceLayer implements IPersistenceLayer
{

    private static final Log LOG = LogFactory.getLog( XmlPersistenceLayer.class );
    private static final String MERCURY_HOME_DIR = System.getProperty( "user.home" ) + "/.mercury";
    private static final File MERCURY_FILE = new File( MERCURY_HOME_DIR + "/connectionStore.xml" );

    /**
     * @see org.suggs.apps.mercury.model.connection.store.IPersistenceLayer#readPersistenceLayer()
     */
    public Map<String, IJmsConnectionDetails> readPersistenceLayer() throws MercuryConnectionStoreException
    {
        Map<String, IJmsConnectionDetails> ret = new HashMap<String, IJmsConnectionDetails>();
        try
        {
            XmlPersistenceLayerHandler handler = new XmlPersistenceLayerHandler();
            DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
            fact.setValidating( true );
            fact.setIgnoringElementContentWhitespace( true );
            DocumentBuilder builder = fact.newDocumentBuilder();
            builder.setEntityResolver( handler );
            builder.setErrorHandler( handler );

            LOG.debug( "Parsing configuration file [" + MERCURY_FILE + "]" );
            Document d = builder.parse( MERCURY_FILE );

            NodeList list = d.getElementsByTagName( "connection" );
            for ( int i = 0; i < list.getLength(); ++i )
            {
                Node c = list.item( i );
                NamedNodeMap atts = c.getAttributes();
                String name = atts.getNamedItem( "name" ).getTextContent();
                EConnectionType type = EConnectionType.createTypeFromString( atts.getNamedItem( "type" ).getTextContent() );
                NodeList childList = list.item( i ).getChildNodes();
                String host = null;
                String port = null;
                for ( int j = 0; j < childList.getLength(); ++j )
                {
                    Node n = childList.item( j );
                    if ( n.getTextContent() != null )
                    {
                        if ( n.getNodeName().equals( "hostname" ) )
                        {
                            host = n.getTextContent();
                        }
                        else if ( n.getNodeName().equals( "port" ) )
                        {
                            port = n.getTextContent();
                        }
                    }
                }

                ret.put( name, new JmsConnectionDetails( name, type, host, port ) );
            }

            return ret;
        }
        catch ( SAXException se )
        {
            throw new MercuryConnectionStoreException( se );
        }
        catch ( ParserConfigurationException pce )
        {
            throw new MercuryConnectionStoreException( pce );
        }
        catch ( IOException ioe )
        {
            throw new MercuryConnectionStoreException( ioe );
        }
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.store.IPersistenceLayer#savePersistenceLayer(java.util.Map)
     */
    public void savePersistenceLayer( Map<String, IJmsConnectionDetails> aMap ) throws MercuryConnectionStoreException
    {
        LOG.info( "Saving prsistence layer" );

        DOMImplementation impl = DOMImplementationImpl.getDOMImplementation();
        DocumentType type = impl.createDocumentType( "connectionStore", "-//MERCURY//DTD CONNECTION STORE//EN", "mercury-configuration.dtd" );
        Document newDoc = impl.createDocument( null, "connectionStore", type );

        // we do this to get a sorted set of key values
        SortedSet<String> keys = new TreeSet<String>( aMap.keySet() );
        for ( String key : keys )
        {
            LOG.debug( "Building key [" + key + "]" );
            IJmsConnectionDetails dtls = aMap.get( key );

            Element conn = newDoc.createElement( "connection" );
            conn.setAttribute( "name", dtls.getConnectionName() );
            conn.setAttribute( "type", dtls.getConnectionType().name() );
            Element svr = newDoc.createElement( "server" );
            svr.setTextContent( dtls.getConnectionHostname() );
            conn.appendChild( svr );
            Element port = newDoc.createElement( "port" );
            port.setTextContent( dtls.getConnectionPort() );
            conn.appendChild( port );

            newDoc.getDocumentElement().appendChild( conn );
        }

        String xml = null;
        try
        {
            xml = serializeXmlDoc( newDoc );
        }
        catch ( MercuryException me )
        {
            throw new MercuryConnectionStoreException( "Failed to serialise xml doc", me );
        }

        LOG.debug( "New XML doc:\n" + xml );

        // now we persiste the new values to the persistent xml file
        LOG.debug( "Overwriting existing xml persistence layer with new file at [" + MERCURY_FILE + "]" );
        FileOutputStream out = null;
        FileChannel chan = null;
        try
        {
            out = new FileOutputStream( MERCURY_FILE );
            chan = out.getChannel();
            int xmlSize = xml.getBytes().length;

            ByteBuffer buff = ByteBuffer.allocate( xmlSize );
            buff.put( xml.getBytes() );
            // flip will move the buffer limit to where the buffer
            // pointer now sits
            buff.flip();

            chan.write( buff );
        }
        catch ( IOException ioe )
        {
            throw new MercuryConnectionStoreException( ioe );
        }
        finally
        {
            try
            {
                chan.close();
                out.close();
            }
            catch ( IOException ioe )
            {
                LOG.warn( "Caught IOException when closing file channel [" + ioe.getMessage() + "]" );
            }
        }

    }

    /**
     * @see org.suggs.apps.mercury.model.connection.store.IPersistenceLayer#verifyPersistenceLayer()
     */
    public void verifyPersistenceLayer()
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
     * Serializes an xml document to a string
     * 
     * @param aDocument
     *            the document to serialize
     * @return the xml document as a string
     */
    private final String serializeXmlDoc( Document aDocument ) throws MercuryException
    {
        ByteArrayOutputStream strm = new ByteArrayOutputStream();
        OutputFormat out = new OutputFormat( aDocument );
        out.setIndenting( true );
        XMLSerializer serializer = new XMLSerializer( strm, out );

        try
        {
            serializer.serialize( aDocument );
        }
        catch ( IOException ioe )
        {
            throw new MercuryException( ioe );
        }
        return strm.toString();
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
}
