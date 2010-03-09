/*
 * JaxbContextUtility.java created on 7 Mar 2010 09:36:29 by suggitpe for project sandbox-jaxb-test-client
 * 
 */
package org.suggs.sandbox.jaxb.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Singleton class that encapsulates JAXB XML utilities, such as
 * marshalling and unmarshalling. This class is thread safe to its
 * internal state.
 * 
 * @author suggitpe
 * @version 1.0 7 Mar 2010
 */
public final class JaxbContextUtility
{

    private static final Log LOG = LogFactory.getLog( JaxbContextUtility.class );

    private static final JaxbContextUtility INSTANCE = new JaxbContextUtility();

    private Map<Class<?>, JAXBContext> jaxbContextCache = new HashMap<Class<?>, JAXBContext>();
    private final Object jaxCacheLock = new Object();
    private Map<String, Schema> schemacCache = new HashMap<String, Schema>();
    private final Object schemaCacheLock = new Object();

    /**
     * Private to ensure singleton.
     */
    private JaxbContextUtility()
    {}

    /**
     * Singleton method.
     * 
     * @return the singleton instance of the JaxbContextUtility.
     */
    public static JaxbContextUtility instance()
    {
        return INSTANCE;
    }

    public <T> T unmarshalObject( String aXmlString, Class<?> aClazz )
    {
        try
        {
            Unmarshaller unmarshaller = createUnmarshaller( aClazz );
            T returnableObject = (T) unmarshaller.unmarshal( new StringReader( aXmlString ) );
            return returnableObject;
        }
        catch ( ClassCastException cce )
        {
            throw new IllegalArgumentException();
        }
        catch ( JAXBException jaxbe )
        {
            throw new IllegalArgumentException();
        }
    }

    private Unmarshaller createUnmarshaller( Class<?> aClazz ) throws JAXBException
    {
        JAXBContext ctx = createJaxbContext( aClazz );
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        return unmarshaller;
    }

    public String marshalObject( Object aObject )
    {
        StringWriter writer = new StringWriter();
        try
        {
            Marshaller marshaller = createMarshaller( aObject.getClass() );
            marshaller.marshal( aObject, writer );
        }
        catch ( JAXBException jaxbe )
        {
            throw new IllegalArgumentException( "Object passed for marshalling is not marshallable.",
                                                jaxbe );
        }
        return writer.toString();
    }

    private Marshaller createMarshaller( Class<?> aClazz ) throws JAXBException
    {
        JAXBContext ctx = createJaxbContext( aClazz );
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
        return marshaller;
    }

    private JAXBContext createJaxbContext( Class<?> aClazz )
    {
        synchronized ( jaxCacheLock )
        {
            if ( !jaxbContextCache.containsKey( aClazz ) )
            {
                try
                {
                    jaxbContextCache.put( aClazz, JAXBContext.newInstance( aClazz ) );
                }
                catch ( JAXBException jaxbe )
                {
                    throw new IllegalArgumentException( "Failed to create JAXBContext for class ["
                                                        + aClazz.getName() + "]", jaxbe );
                }
            }
            return jaxbContextCache.get( aClazz );
        }
    }

}
