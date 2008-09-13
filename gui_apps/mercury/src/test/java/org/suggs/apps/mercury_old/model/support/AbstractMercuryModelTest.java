/*
 * AbstractMercuryModelTest.java created on 2 Aug 2007 06:35:50 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
public abstract class AbstractMercuryModelTest extends AbstractDependencyInjectionSpringContextTests
{

    private static final Log LOG = LogFactory.getLog( AbstractMercuryModelTest.class );

    /**
     * The main test that can be called from within the derived
     * classes
     * 
     * @param aCallBack
     *            the call back to drag in the tests
     */
    protected void runMercuryTest( IMercuryTestCallBack aCallBack )
    {
        LOG.info( "---------------- [" + aCallBack.getName() + "] start" );
        try
        {
            aCallBack.runTest();
        }
        catch ( Exception e )
        {
            LOG.error( "Caught exception in the test run [" + e.getMessage() + "]" );
            e.printStackTrace();
        }
        LOG.info( "---------------- [" + aCallBack.getName() + "] end" );
    }

    /**
     * Test interface so that we have a standard way of conducting a
     * test
     * 
     * @author suggitpe
     * @version 1.0 2 Aug 2007
     */
    protected interface IMercuryTestCallBack
    {

        /**
         * Gets the name of the test
         * 
         * @return the name of the test
         */
        String getName();

        /**
         * Run the actual test
         */
        void runTest();

    }
}
