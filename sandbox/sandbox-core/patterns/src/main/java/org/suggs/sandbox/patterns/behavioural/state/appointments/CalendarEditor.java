/*
 * CalendarEditor.java created on 23 Jul 2009 19:02:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.appointments;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for CalendarEditor
 * 
 * @author suggitpe
 * @version 1.0 23 Jul 2009
 */
public class CalendarEditor {

    // static logger
    private static final Log LOG = LogFactory.getLog( CalendarEditor.class );

    private IState currentState;
    private File appointmentsFile;
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private static final String DEFAULT_APPOINTMENT_FILE = "appointments.ser";

    /**
     * Constructs a new instance using the default file name for the appointments file.
     */
    public CalendarEditor() {
        this( DEFAULT_APPOINTMENT_FILE );
    }

    /**
     * Constructs a new instance.
     * 
     * @param fileName
     *            the name of the file to use for the appointments.
     */
    @SuppressWarnings("unchecked")
    public CalendarEditor( String fileName ) {
        appointmentsFile = new File( fileName );
        try {
            appointments = (ArrayList<Appointment>) FileLoader.loadData( appointmentsFile );
        }
        catch ( ClassCastException cce ) {
            String err = new StringBuffer( "Unable to load information. The file [" ).append( fileName )
                .append( "] does not contain a list of appointments." )
                .toString();
            LOG.error( err, cce );
        }
        LOG.debug( "Have loaded [" + appointments.size() + "] appointments loaded from file." );
        currentState = new CleanState();
        LOG.debug( "Loaded appointments from file [" + appointmentsFile + "]" );
    }

    /**
     * attempt to transition the state to save
     */
    public void save() {
        currentState.save();
    }

    /**
     * attempt to transition the state to edit
     */
    public void edit() {
        currentState.edit();
    }

    /**
     * Getter for the appointments list
     * 
     * @return a list of appointments
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Setter method to add an additional appointment to the existing List
     * 
     * @param aAppointment
     *            the appointment to add
     */
    public void addAppointment( Appointment aAppointment ) {
        if ( !appointments.contains( aAppointment ) ) {
            appointments.add( aAppointment );
        }
    }

    /**
     * Accessor method to allow us to remove a single appointment from the list
     * 
     * @param aAppointment
     *            the appointment to remove
     */
    public void removeAppointment( Appointment aAppointment ) {
        appointments.remove( aAppointment );
    }

    /**
     * State to represent that the appointments is in a dirty state to its persistent layer
     * 
     * @author suggitpe
     * @version 1.0 23 Jul 2009
     */
    private class DirtyState implements IState {

        private IState mNextState;

        /**
         * Constructs a new instance.
         * 
         * @param aNextState
         *            the next state that this will transition to
         */
        public DirtyState( IState aNextState ) {
            mNextState = aNextState;
        }

        /**
         * @see org.suggs.sandbox.patterns.behavioural.state.appointments.IState#edit()
         */
        @Override
        public void edit() {
            // nadda
            LOG.debug( "Edit called on DirtyState" );
        }

        /**
         * @see org.suggs.sandbox.patterns.behavioural.state.appointments.IState#save()
         */
        @Override
        public void save() {
            LOG.debug( "Save called on DirtyState" );
            FileLoader.storeData( appointmentsFile, appointments );
            currentState = mNextState;
        }
    }

    /**
     * Class to represent the state where the appointments are in a clean state with the persistence layer
     * 
     * @author suggitpe
     * @version 1.0 23 Jul 2009
     */
    private class CleanState implements IState {

        IState mNextState_ = new DirtyState( this );

        /**
         * @see org.suggs.sandbox.patterns.behavioural.state.appointments.IState#edit()
         */
        @Override
        public void edit() {
            LOG.debug( "Edit called on CleanState" );
            currentState = mNextState_;
        }

        /**
         * @see org.suggs.sandbox.patterns.behavioural.state.appointments.IState#save()
         */
        @Override
        public void save() {
            // nadda
            LOG.debug( "Save called on CleanState" );
        }
    }
}
