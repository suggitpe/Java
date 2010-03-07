/*
 * JaxbContextUtility.java created on 7 Mar 2010 09:36:29 by suggitpe for project sandbox-jaxb-test-client
 * 
 */
package org.suggs.sandbox.jaxb.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
