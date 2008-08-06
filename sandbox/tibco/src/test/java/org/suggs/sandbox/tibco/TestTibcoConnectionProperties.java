/*
 * TestTibcoConnectionProperties.java created on 6 Aug 2008 06:01:01 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import javax.naming.NamingException;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Unit test for the Tibco connection properties class
 * 
 * @author suggitpe
 * @version 1.0 6 Aug 2008
 */
public class TestTibcoConnectionProperties extends TestCase
{

    private static final Log LOG = LogFactory.getLog( TestTibcoConnectionProperties.class );

    /**
     * Tests the connection details class
     */
    public void testTibcoConnection()
    {
        LOG.debug( "Creating instance of Tibco Conection Details" );
        TibcoConnectionProperties conn = TibcoConnectionProperties.instance();
        try
        {
            conn.getInitialContext();
        }
        catch ( NamingException ne )
        {
            fail( "Naming exception thrown from getter" );
        }
    }
}
