/*
 * TestTibcoConnectionProperties.java created on 6 Aug 2008 06:01:01 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import javax.naming.NamingException;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

/**
 * Unit test for the Tibco connection properties class
 * 
 * @author suggitpe
 * @version 1.0 6 Aug 2008
 */
public class TestTibcoConnectionProperties
{

    private static final Log LOG = LogFactory.getLog( TestTibcoConnectionProperties.class );

    /**
     * Tests the connection details class
     */
    @Test
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
            Assert.fail( "Naming exception thrown from getter" );
        }
    }

    /**
     * Tests that we can connect to the EMS broker as admin
     */
    @Test
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
            Assert.fail( "Something bad happened" );
        }
    }
}
