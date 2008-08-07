/*
 * TestTibcoConnectionProperties.java created on 6 Aug 2008 06:01:01 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import javax.naming.NamingException;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

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
            ne.printStackTrace();
            fail( "Naming exception thrown from getter" );
        }
    }

    /**
     * Tests that we can connect to the EMS broker as admin
     */
    public void testTibcoAdminConnection()
    {
        try
        {
            LOG.debug( "Creating instance of Tibco admin connection" );
            TibcoAdminConnectionProperties conn = TibcoAdminConnectionProperties.instance();
            TibjmsAdmin admin = conn.getAdmin();
            admin.close();
        }
        catch ( TibjmsAdminException e )
        {
            e.printStackTrace();
            fail( "Something bad happened" );
        }
    }
}
