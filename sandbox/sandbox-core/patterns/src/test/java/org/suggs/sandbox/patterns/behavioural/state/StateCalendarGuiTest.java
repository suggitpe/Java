/*
 * StateCalendarGuiTest.java created on 5 Aug 2009 07:10:10 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state;

import org.suggs.sandbox.patterns.behavioural.state.appointments.CalendarEditor;
import org.suggs.sandbox.patterns.behavioural.state.appointments.StateGui;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for StateCalendarGuiTest
 * 
 * @author suggitpe
 * @version 1.0 5 Aug 2009
 */
public class StateCalendarGuiTest {

    // static logger
    private static final Logger LOG = LoggerFactory.getLogger( StateCalendarGuiTest.class );

    /**
     * Main method
     * 
     * @param args
     *            cmd line arguments
     */
    public static void main( String[] args ) {
        LOG.debug( "Example of the state pattern" );

        if ( !( new File( "appointments.ser" ).exists() ) ) {
            StateCalendarDataCreator.serialize( "appointments.ser" );
        }

        LOG.debug( "Creating CalendarEditor" );
        CalendarEditor book = new CalendarEditor();

        LOG.debug( "Calendar created. Appointments=[" + book.getAppointments() + "]" );

        LOG.debug( "Creating GUI" );
        StateGui gui = new StateGui( book );
        gui.createGui();

    }
}
