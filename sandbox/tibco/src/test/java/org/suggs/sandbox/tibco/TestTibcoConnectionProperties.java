/*
 * TestTibcoConnectionProperties.java created on 6 Aug 2008 06:01:01 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tibco.tibjms.admin.TibjmsAdmin;
import com.tibco.tibjms.admin.TibjmsAdminException;

import static org.junit.Assert.fail;

/**
 * Unit test for the Tibco connection properties class
 */
public class TestTibcoConnectionProperties {

    private static final Logger LOG = LoggerFactory.getLogger( TestTibcoConnectionProperties.class );

    @Test
    public void testTibcoConnection() {
        LOG.debug( "Creating instance of Tibco Conection Details" );
        TibcoConnectionProperties conn = TibcoConnectionProperties.instance();
        try {
            conn.getInitialContext();
        }
        catch ( NamingException ne ) {
            ne.printStackTrace();
            fail("Naming exception thrown from getter");
        }
    }

    @Test
    public void testTibcoAdminConnection() {
        try {
            LOG.debug( "Creating instance of Tibco admin connection" );
            TibcoAdminConnectionProperties conn = TibcoAdminConnectionProperties.instance();
            TibjmsAdmin admin = conn.getAdmin();
            admin.close();
        }
        catch ( TibjmsAdminException e ) {
            e.printStackTrace();
            fail("Something bad happened");
        }
    }
}
