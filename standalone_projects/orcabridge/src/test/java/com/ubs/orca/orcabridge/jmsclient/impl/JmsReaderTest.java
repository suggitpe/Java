/*
 * JmsReaderTest.java created on 14 Oct 2009 07:05:28 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO Write javadoc for JmsReaderTest
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsReaderTest
{

    private static final Log LOG = LogFactory.getLog( JmsReaderTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsConnectionTest.class.getSimpleName() );
    }

    @Before
    public void doBefore()
    {
        LOG.debug( "-------------" );
    }

    @Test
    public void testMe()
    {}
}
