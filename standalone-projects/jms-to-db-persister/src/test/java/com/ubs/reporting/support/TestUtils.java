/*
 * TestUtils.java created on 1 Oct 2010 18:54:46 by suggitpe for project jms-to-db-persister
 * 
 */
package com.ubs.reporting.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Set of static utility classes for testing only.
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2010
 */
public final class TestUtils {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( TestUtils.class );
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat( "ddMMyyyy" );

    /**
     * Hiden constructor as this is a non instantiable class.
     */
    private TestUtils() {}

    /**
     * @param aDate
     * @return a date object
     */
    public static Date createDateFrom_ddmmyyyy( String aDate ) {
        try {
            return DATE_FORMAT.parse( aDate );
        }
        catch ( ParseException parseException ) {
            throw new IllegalArgumentException( "Failed to parse date [" + aDate
                                                + "] into a Date.  Try using format of ddMMyyyy" );
        }
    }
}
