/*
 * CachedXmlConnectionStore.java created on 29 Sep 2008 07:05:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.connectionstore.ConnectionStoreType;
import org.suggs.apps.mercury.connectionstore.ConnectionType;
import org.suggs.apps.mercury.connectionstore.ObjectFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * This class is the main JAXB implementation of the xml connection
 * manager.
 * 
 * @author suggitpe
 * @version 1.0 29 Sep 2008
 */
public class JaxbXmlConnectionStoreManager implements IXmlConnectionStoreManager, InitializingBean
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManager.class );

    private IFileManager mFileManager_ = null;
    private static JAXBContext mJaxbContext_ = null;
    private Schema mSchemaCache_;
    private String mPersistentFile_;
    private String mConnectionDataCache_;

    static
    {
        try
        {
            // the reason this is done at the static level is that for
            // this class we are only concerned with the one
            mJaxbContext_ = JAXBContext.newInstance( ConnectionStoreType.class.getPackage()
                .getName() );
        }
        catch ( JAXBException je )
        {
            throw new IllegalStateException( "Unable to create JAXB context in ConnectionContext Store Manager",
                                             je );
        }

    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception
    {
        Assert.notNull( mFileManager_, "The file manager cannot be null, this needs to be injected" );
        Assert.notNull( mPersistentFile_, "The name of the persisten file cannot be null" );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#getRawXml()
     */
    public String getRawXml() throws ConnectionStoreException
    {
        if ( mConnectionDataCache_ == null || mConnectionDataCache_.length() == 0 )
        {
            try
            {
                mConnectionDataCache_ = mFileManager_.retrieveClobFromFile( new File( mPersistentFile_ ) );

            }
            catch ( IOException ioe )
            {
                String err = "IOException caught when trying to retrieve data from the ";
                LOG.error( err, ioe );
                throw new ConnectionStoreException( err, ioe );
            }
        }
        return mConnectionDataCache_;
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#readConnectionData()
     */
    @SuppressWarnings("unchecked")
    public Map<String, ConnectionDetails> readConnectionData() throws ConnectionStoreException
    {
        String xmlClob = getRawXml();

        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "Retrieved clob with length [" + xmlClob.length()
                       + "] from the file manager" );
        }

        ConnectionStoreType connStr = null;
        try
        {
            Unmarshaller uMrsh = mJaxbContext_.createUnmarshaller();

            if ( mSchemaCache_ == null )
            {
                try
                {
                    SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
                    URL url = getClass().getClassLoader().getResource( "connection-store.xsd" );
                    mSchemaCache_ = sf.newSchema( url );
                }
                catch ( SAXException sx )
                {
                    throw new ConnectionStoreException( "Unable to retrieve schema for validation of connction store xml",
                                                        sx );
                }
            }

            if ( mSchemaCache_ == null )
            {
                throw new ConnectionStoreException( "Failed to create validating schema for marshaller" );
            }

            uMrsh.setSchema( mSchemaCache_ );

            // now we need to load the xml schema so we can validate
            // the input xml
            InputStream is = getClass().getClassLoader()
                .getResourceAsStream( "connection-store.xsd" );
            if ( is == null )
            {
                throw new ConnectionStoreException( "Unable to load connection store schema" );
            }

            JAXBElement<ConnectionStoreType> elem = (JAXBElement<ConnectionStoreType>) uMrsh.unmarshal( new StringReader( xmlClob ) );
            connStr = elem.getValue();
        }
        catch ( JAXBException je )
        {
            String err = "Error found when trying to unmarshal connection store data";
            LOG.error( err + ":\n" + xmlClob );
            throw new ConnectionStoreException( err, je );
        }

        if ( connStr.getConnection().size() == 0 )
        {
            LOG.warn( "Have parsed connection store XML but cannot find any valid connections within: returning an empty connection store (either this is first time use or the connection store xml is corrupt in some way)" );
        }

        return JaxbXmlConnectionStoreManagerHelper.createDetailsFromConnection( connStr );
    }

    /**
     * @see org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager#saveConnectionData(java.util.Map)
     */
    public void saveConnectionData( Map<String, ConnectionDetails> map )
                    throws ConnectionStoreException
    {
        if ( map == null )
        {
            throw new ConnectionStoreException( "Trying to save down a null connection data map" );
        }

        String xmlClob = null;
        try
        {
            Marshaller mshlr = mJaxbContext_.createMarshaller();
            ObjectFactory fact = new ObjectFactory();

            // create the main connection store
            ConnectionStoreType ct = fact.createConnectionStoreType();
            for ( String s : map.keySet() )
            {
                ConnectionType conn = JaxbXmlConnectionStoreManagerHelper.createConnectionFromDetails( fact,
                                                                                                       map.get( s ) );
                ct.getConnection().add( conn );
            }

            JAXBElement<ConnectionStoreType> elem = fact.createConnectionStore( ct );
            StringWriter writer = new StringWriter();
            LOG.debug( "Marshalling data into XML document" );
            mshlr.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            mshlr.marshal( elem, writer );
            xmlClob = writer.toString();
        }
        catch ( JAXBException je )
        {
            throw new ConnectionStoreException( "Failed to marshall (JAXB) xml document", je );
        }

        // update internal cache with copy
        mConnectionDataCache_ = new String( xmlClob );

        // now we need to persist the xml clob
        try
        {
            LOG.debug( "Persisting XML of length [" + xmlClob.length() + "]" );
            mFileManager_.persistClobToFile( xmlClob, new File( mPersistentFile_ ) );
        }
        catch ( IOException ioe )
        {
            throw new ConnectionStoreException( "Failed to persiste the clob file", ioe );
        }
    }

    /**
     * Setter for the file manager
     * 
     * @param aFileManager
     *            the file manager to set
     */
    public void setFileManager( IFileManager aFileManager )
    {
        mFileManager_ = aFileManager;
    }

    /**
     * Getter for the file manager
     * 
     * @return the file manager
     */
    public IFileManager getFileManager()
    {
        return mFileManager_;
    }

    /**
     * Setter for the persistent filename to set
     * 
     * @param aFilename
     *            the name of the persistent file
     */
    public void setPersistentFile( String aFilename )
    {
        mPersistentFile_ = aFilename;
    }

}
