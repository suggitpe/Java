/*
 * TestTibcoAdminUtil.java created on 7 Aug 2008 06:20:33 by suggitpe for project SandBox - Tibco
 * 
 */
package org.suggs.sandbox.tibco;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for TestTibcoAdminUtil
 * 
 * @author suggitpe
 * @version 1.0 7 Aug 2008
 */
public class TestTibcoAdminUtil {

    private static final Logger LOG = LoggerFactory.getLogger( TestTibcoAdminUtil.class );

    @Test
    public void testGetConectionFactories() {
        List<String> facts = TibcoAdminUtil.getConnectionFactoryNames();
        for ( String s : facts ) {
            LOG.debug( "Connection Factory [" + s + "] found" );
        }
    }

    @Test
    public void testDurables() {
        List<String> durs = TibcoAdminUtil.getDurableNames();
        for ( String s : durs ) {
            LOG.debug( "Durable name [" + s + "]" );
        }
    }

}
