/*
 * LoggerTest.java created on 18 Sep 2009 19:41:32 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.logger;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

/**
 * TODO Write javadoc for LoggerTest
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2009
 */
public class LoggerTest
{

    private static final Log LOG = LogFactory.getLog( LoggerTest.class );

    @Test
    public void testLoggingForSimpleMessage()
    {
        LOG.debug( "hello" );
    }

    @Test
    public void testLoggingForExceptions()
    {
        LOG.debug( "check for exception %1s %2s [%3s]",
                   "args1",
                   "args2",
                   new IllegalStateException( "test exception" ) );
    }

    @Test
    public void testLoggingForStrings()
    {

        LOG.debug( "hello1 %1s %2s", "hello2", "hello3" );
    }

    @Test
    public void testLoggingForSimpleTypes()
    {
        LOG.debug( "Message with int=[%1d], double=[%2f], float=[%3f],  boolean=[%4b]",
                   new Integer( 1 ),
                   new Double( 2.3 ),
                   new Float( 3.2 ),
                   new Boolean( false ) );
    }

    @Test
    public void testLoggingForDates()
    {
        LOG.debug( "Message with date=[%1$tA %1$td/%1$tm/%1$ty %1$tH:%1$tM:%1$tS]",
                   Calendar.getInstance( TimeZone.getTimeZone( "GMT" ) ) );
    }

}
