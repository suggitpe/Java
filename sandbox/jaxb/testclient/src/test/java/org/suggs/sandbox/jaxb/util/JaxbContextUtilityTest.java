/*
 * JaxbContextUtilityTest.java created on 7 Mar 2010 09:47:10 by suggitpe for project sandbox-jaxb-test-client
 * 
 */
package org.suggs.sandbox.jaxb.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

/**
 * Test class for the JaxbContext Utility.
 * 
 * @author suggitpe
 * @version 1.0 7 Mar 2010
 */
public class JaxbContextUtilityTest
{

    private static final Log LOG = LogFactory.getLog( JaxbContextUtilityTest.class );

    @Test
    public void singletonProducesSameObjectInMemory()
    {
        JaxbContextUtility util1 = JaxbContextUtility.instance();
        JaxbContextUtility util2 = JaxbContextUtility.instance();
        assertThat( util1, sameInstance( util2 ) );
    }

    @Test
    public void marshallerProducesCorrectOutput()
    {}
}
