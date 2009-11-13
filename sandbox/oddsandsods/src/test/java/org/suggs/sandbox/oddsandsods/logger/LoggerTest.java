/*
 * LoggerTest.java created on 18 Sep 2009 19:41:32 by suggitpe for project SandBox - OddsAndSods
 * 
 */
package org.suggs.sandbox.oddsandsods.logger;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

/**
 * Test suite to test the Logger classes. These tests should cover all
 * of the various types that can be used within a logger.
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2009
 */
public class LoggerTest
{

    private static final Log LOG = LogFactory.getLog( LoggerTest.class );

    /**
     * 
     */
    @Test
    public void testLoggingForExceptions()
    {
        LOG.debug( "check for exception %1s %2s [%3s]",
                   "args1",
                   "args2",
                   new IllegalStateException( "test exception" ) );
    }

    /**
     * 
     */
    @Test
    public void testLoggingForSimpleMessage()
    {
        LOG.debug( "This is a simple log message" );
    }

    /**
     * 
     */
    @Test
    public void testLoggingForStrings()
    {

        LOG.debug( "hello1 string=[%1s], string=[%2s]", "hello2", "hello3" );
    }

    /**
     * 
     */
    @Test
    public void testLoggingForSimpleTypes()
    {
        LOG.debug( "Message with int=[%1d], double=[%2f], float=[%3f],  boolean=[%4b]",
                   new Integer( 1 ),
                   new Double( 2.3 ),
                   new Float( 3.2 ),
                   new Boolean( false ) );
    }

    /**
     * 
     */
    @Test
    public void testLoggingForDates()
    {
        LOG.debug( "Message with date=[%1$tA %1$td/%1$tm/%1$ty %1$tH:%1$tM:%1$tS]",
                   Calendar.getInstance( TimeZone.getTimeZone( "GMT" ) ) );
    }

    /**
     * 
     */
    @Test
    public void testClassWithToString()
    {
        SimpleToStringClass toStringClass = new SimpleToStringClass( "toStringClass", 7, false );
        LOG.debug( "Message with class=[%1s]", toStringClass );
    }

    /**
     * 
     */
    @Test
    public void testClassWithNoToString()
    {
        SimpleNoToStringClass toStringClass = new SimpleNoToStringClass( "noToStringClass",
                                                                         7,
                                                                         false );
        LOG.debug( "Message with class=[%1s]", toStringClass );
    }

    /**
     * Simple class with the toString
     * 
     * @author suggitpe
     * @version 1.0 22 Sep 2009
     */
    private class SimpleToStringClass
    {

        private String mInnerString_;
        private int mInnerInt_;
        private boolean mInnerFlag_;

        /**
         * Constructs a new instance.
         * 
         * @param aString
         * @param aInt
         * @param aFlag
         */
        public SimpleToStringClass( String aString, int aInt, boolean aFlag )
        {
            super();
            mInnerString_ = aString;
            mInnerInt_ = aInt;
            mInnerFlag_ = aFlag;
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder( "SimpleClass: " );
            builder.append( "string=[" ).append( mInnerString_ ).append( "], " );
            builder.append( "int=[" ).append( mInnerInt_ ).append( "], " );
            builder.append( "flag=[" ).append( mInnerFlag_ ).append( "]" );
            return builder.toString();
        }
    }

    /**
     * Simple class with no toString method on it to test class based
     * logging.
     * 
     * @author suggitpe
     * @version 1.0 22 Sep 2009
     */
    private class SimpleNoToStringClass
    {

        @SuppressWarnings("unused")
        private String mInnerString_;
        @SuppressWarnings("unused")
        private int mInnerInt_;
        @SuppressWarnings("unused")
        private boolean mInnerFlag_;

        /**
         * Constructs a new instance.
         * 
         * @param aString
         * @param aInt
         * @param aFlag
         */
        public SimpleNoToStringClass( String aString, int aInt, boolean aFlag )
        {
            mInnerString_ = aString;
            mInnerInt_ = aInt;
            mInnerFlag_ = aFlag;
        }
    }

}
