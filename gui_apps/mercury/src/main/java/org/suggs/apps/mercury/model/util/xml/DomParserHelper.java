/*
 * DomParserHelper.java created on 27 Nov 2008 07:37:37 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.xml;

import org.suggs.apps.mercury.model.util.MercuryUtilityException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This utility class will abstract all DOM based function away from
 * the rest of the application. This implementation will create a
 * thread safe parser member the first time one of the parsers are
 * required. This can then be reused by other threads that need to use
 * a parser.
 * 
 * @author suggitpe
 * @version 1.0 27 Nov 2008
 */
public class DomParserHelper
{

    private static final Log LOG = LogFactory.getLog( DomParserHelper.class );

    private Object lock = new Object();
    private Map<String, DocumentBuilder> mBuilderMap_ = new HashMap<String, DocumentBuilder>();

    /**
     * Create an XML document from a file.
     * 
     * @param aFilename
     *            the filename of the document
     * @param aSchemaLocation
     *            a valid URL pointing to the schema to validate the
     *            XML file
     * @return the XML document object that was contained in the file
     */
    public Document createDocFromXmlFile( String aFilename, String aSchemaLocation )
                    throws MercuryUtilityException
    {

        synchronized ( lock )
        {
            if ( !mBuilderMap_.containsKey( aSchemaLocation ) )
            {
                DocumentBuilder builder = null;
                try
                {
                    DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
                    fact.setNamespaceAware( true );
                    fact.setValidating( true );
                    fact.setAttribute( "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                                       "http://www.w3.org/2001/XMLSchema" );
                    fact.setAttribute( "http://java.sun.com/xml/jaxp/properties/schemaSource",
                                       aSchemaLocation );

                    builder = fact.newDocumentBuilder();
                }
                catch ( ParserConfigurationException pce )
                {
                    throw new IllegalStateException( "Failed to create parser, pce" );
                }

                builder.setErrorHandler( new MercuryDomErrorHandler() );
                mBuilderMap_.put( aSchemaLocation, builder );
            }
        }

        DocumentBuilder b = mBuilderMap_.get( aSchemaLocation );

        try
        {
            return b.parse( new File( aFilename ) );
        }
        catch ( IOException ioe )
        {
            throw new MercuryUtilityException( ioe );
        }
        catch ( SAXException se )
        {
            throw new MercuryUtilityException( se );
        }

    }

    /**
     * Mercury version of the error handler.
     * 
     * @author suggitpe
     * @version 1.0 27 Nov 2008
     */
    class MercuryDomErrorHandler implements ErrorHandler
    {

        boolean mFailFast_ = true;

        /**
         * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
         */
        public void error( SAXParseException exception ) throws SAXException
        {
            LOG.error( "SAX Parse excepion caught: " + exception.getMessage() );
            if ( mFailFast_ )
            {
                throw new IllegalStateException( "Exception thrown from xml parsing", exception );
            }
        }

        /**
         * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
         */
        public void fatalError( SAXParseException exception ) throws SAXException
        {
            LOG.error( "FATAL: SAX Parse excepion caught: " + exception.getMessage() );
            if ( mFailFast_ )
            {
                throw new IllegalStateException( "Exception thrown from xml parsing", exception );
            }
        }

        /**
         * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
         */
        public void warning( SAXParseException exception ) throws SAXException
        {
            LOG.warn( "SAX Parse excepion caught: " + exception.getMessage() );
        }
    }

}
