/*
 * JmsSenderTest.java created on 14 Oct 2009 07:05:15 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.jmsclient.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * TODO Write javadoc for JmsSenderTest
 * 
 * @author suggitpe
 * @version 1.0 14 Oct 2009
 */
public class JmsSenderTest
{

    private static final Log LOG = LogFactory.getLog( JmsSenderTest.class );

    @BeforeClass
    public static void doBeforeClass()
    {
        LOG.debug( "=================== " + JmsSenderTest.class.getSimpleName() );
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
