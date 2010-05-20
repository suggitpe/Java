/*
 * Appointment.java created on 3 Aug 2009 06:57:21 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.appointments;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Encapsulation of an Appointment domain model object.
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2009
 */
public class Appointment implements Serializable {

    private static final long serialVersionUID = 4712177304804473343L;
    private String reason;
    private List<Contact> contacts;
    private String location;
    private Date startDate;
    private Date endDate;

    /**
     * Constructs a new instance.
     * 
     * @param aReason
     *            reason
     * @param aContacts
     *            list of contacts
     * @param aLocation
     *            location
     * @param aStartDate
     *            start date
     * @param aEndDate
     *            end date
     */
    public Appointment( String aReason, List<Contact> aContacts, String aLocation, Date aStartDate,
                        Date aEndDate ) {
        reason = aReason;
        contacts = aContacts;
        location = aLocation;
        startDate = aStartDate;
        endDate = aEndDate;
    }

    /**
     * Getter
     * 
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Getter
     * 
     * @return list of contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * Getter
     * 
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter
     * 
     * @return start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter
     * 
     * @return end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter
     * 
     * @param aReason
     *            reason
     */
    public void setReason( String aReason ) {
        reason = aReason;
    }

    /**
     * Setter
     * 
     * @param aContacts
     *            lit of contacts
     */
    public void setContacts( List<Contact> aContacts ) {
        contacts = aContacts;
    }

    /**
     * Setter
     * 
     * @param aLocation
     *            location
     */
    public void setLocation( String aLocation ) {
        location = aLocation;
    }

    /**
     * Setter
     * 
     * @param aStartDate
     *            start date
     */
    public void setStartDate( Date aStartDate ) {
        startDate = aStartDate;
    }

    /**
     * Setter
     * 
     * @param aEndDate
     *            end date
     */
    public void setEndDate( Date aEndDate ) {
        endDate = aEndDate;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer( "Appintment: " );
        ret.append( "Reason=[" ).append( reason ).append( "], " );
        ret.append( "Contacts=[" ).append( contacts ).append( "], " );
        ret.append( "Location=[" ).append( location ).append( "], " );
        ret.append( "StartDate=[" ).append( startDate ).append( "], " );
        ret.append( "EndDate=[" ).append( endDate ).append( "]." );
        return ret.toString();
    }
}
