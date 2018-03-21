/*
 * StateCalendarDataCreator.java created on 4 Aug 2009 19:54:36 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state;

import org.suggs.sandbox.patterns.behavioural.state.appointments.Appointment;
import org.suggs.sandbox.patterns.behavioural.state.appointments.Contact;
import org.suggs.sandbox.patterns.behavioural.state.appointments.FileLoader;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class to create a collection of sample data to be used by a different test.
 */
public class StateCalendarDataCreator {
    private static final Logger LOG = LoggerFactory.getLogger( StateCalendarDataCreator.class );

    private static final String DEFAULT_FILE = "data.ser";
    private static final Calendar dateCreator = Calendar.getInstance();

    public static void main( String[] args ) {
        String fileName;
        if ( args.length == 1 ) {
            fileName = args[0];
        }
        else {
            fileName = DEFAULT_FILE;
        }

        serialize( fileName );
    }

    public static void serialize( String aFileName ) {
        FileLoader.storeData( new File( aFileName ), createData() );
    }

    private static Serializable createData() {
        LOG.debug( "Creating test data" );
        ArrayList<Appointment> ret = new ArrayList<Appointment>();
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        contacts.add( new Contact( "Test", "Subject", "Volunteer", "United Patterns Consortium" ) );

        ret.add( new Appointment( "Slowpokes annoymous",
                                  contacts,
                                  "London",
                                  createDate( 2001, 1, 1, 12, 01 ),
                                  createDate( 2001, 1, 1, 12, 31 ) ) );
        ret.add( new Appointment( "Java focus group",
                                  contacts,
                                  "New York",
                                  createDate( 2001, 1, 1, 12, 2 ),
                                  createDate( 2001, 1, 1, 12, 32 ) ) );
        ret.add( new Appointment( "Something else",
                                  contacts,
                                  "Nowhere",
                                  createDate( 2001, 1, 1, 12, 3 ),
                                  createDate( 2001, 1, 1, 12, 33 ) ) );
        ret.add( new Appointment( "Inca Stinker",
                                  contacts,
                                  "In his basket",
                                  createDate( 2001, 1, 1, 12, 4 ),
                                  createDate( 2001, 1, 1, 12, 34 ) ) );
        ret.add( new Appointment( "Squigy face",
                                  contacts,
                                  "At home",
                                  createDate( 2001, 1, 1, 12, 5 ),
                                  createDate( 2001, 1, 1, 12, 35 ) ) );

        LOG.debug( "Test data created" );
        return ret;
    }

    private static Date createDate( int year, int month, int day, int hour, int minute ) {
        dateCreator.set( year, month, day, hour, minute );
        return dateCreator.getTime();
    }
}
