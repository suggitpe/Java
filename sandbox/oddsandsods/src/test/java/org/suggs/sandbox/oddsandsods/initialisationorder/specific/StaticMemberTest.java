/*
 * StaticMemberTest.java created on 21 Sep 2007 06:41:07 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.initialisationorder.specific;

import org.junit.Test;

/**
 * Test case to test the use of a static member
 * 
 * @author suggitpe
 * @version 1.0 21 Sep 2007
 */
public class StaticMemberTest
{

    @Test
    public void testStaticMember()
    {
        Logger.log( "=====================================" );
        Logger.log( "Test Static Member" );
        Logger.log( "------------------" );

        @SuppressWarnings("unused")
        Initialisee target = Initialisee.instance;

        target = null;

        Logger.output();
        Logger.clearLogs();
        Logger.log( "=====================================" );
    }
}
