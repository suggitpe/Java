/*
 * TestConstruction.java created on 21 Sep 2007 06:50:33 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.detailed;

import junit.framework.TestCase;

/**
 * Test construction of an object
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class TestConstruction extends TestCase
{

    public void testConstruction()
    {
        System.out.println( "=====================================" );
        Logger.log( "Test construction" );
        Logger.log( "-----------------" );

        @SuppressWarnings("unused")
        Initialisee target = new Initialisee();
        target = null;

        Logger.output();
        Logger.clearLogs();
        System.out.println( "=====================================" );
    }

}
