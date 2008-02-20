/*
 * StaticMethodTest.java created on 21 Sep 2007 06:47:24 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.specific;

import junit.framework.TestCase;

/**
 * Test case to test the use of a static method.
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class StaticMethodTest extends TestCase
{

    public void testStaticMethod()
    {
        Logger.log( "=====================================" );
        Logger.log( "Test Static Method" );
        Logger.log( "------------------" );

        @SuppressWarnings("unused")
        Initialisee target = Initialisee.instance();
        target = null;

        Logger.output();
        Logger.clearLogs();
        Logger.log( "=====================================" );
    }

}
