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
public class Appointment implements Serializable
{

    private String mReason_;
    private List<Contact> mContacts_;
    private String mLocation_;
    private Date mStartDate_;
    private Date mEndDate_;

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
                        Date aEndDate )
    {
        mReason_ = aReason;
        mContacts_ = aContacts;
        mLocation_ = aLocation;
        mStartDate_ = aStartDate;
        mEndDate_ = aEndDate;
    }

    /**
     * Getter
     * 
     * @return reason
     */
    public String getReason()
    {
        return mReason_;
    }

    /**
     * Getter
     * 
     * @return list of contacts
     */
    public List<Contact> getContacts()
    {
        return mContacts_;
    }

    /**
     * Getter
     * 
     * @return location
     */
    public String getLocation()
    {
        return mLocation_;
    }

    /**
     * Getter
     * 
     * @return start date
     */
    public Date getStartDate()
    {
        return mStartDate_;
    }

    /**
     * Getter
     * 
     * @return end date
     */
    public Date getEndDate()
    {
        return mEndDate_;
    }

    /**
     * Setter
     * 
     * @param aReason
     *            reason
     */
    public void setReason( String aReason )
    {
        mReason_ = aReason;
    }

    /**
     * Setter
     * 
     * @param aContacts
     *            lit of contacts
     */
    public void setContacts( List<Contact> aContacts )
    {
        mContacts_ = aContacts;
    }

    /**
     * Setter
     * 
     * @param aLocation
     *            location
     */
    public void setLocation( String aLocation )
    {
        mLocation_ = aLocation;
    }

    /**
     * Setter
     * 
     * @param aStartDate
     *            start date
     */
    public void setStartDate( Date aStartDate )
    {
        mStartDate_ = aStartDate;
    }

    /**
     * Setter
     * 
     * @param aEndDate
     *            end date
     */
    public void setEndDate( Date aEndDate )
    {
        mEndDate_ = aEndDate;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer ret = new StringBuffer( "Appintment: " );
        ret.append( "Reason=[" ).append( mReason_ ).append( "], " );
        ret.append( "Contacts=[" ).append( mContacts_ ).append( "], " );
        ret.append( "Location=[" ).append( mLocation_ ).append( "], " );
        ret.append( "StartDate=[" ).append( mStartDate_ ).append( "], " );
        ret.append( "EndDate=[" ).append( mEndDate_ ).append( "]." );
        return ret.toString();
    }
}
