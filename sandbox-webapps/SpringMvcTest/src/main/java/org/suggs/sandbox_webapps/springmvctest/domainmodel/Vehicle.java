/*
 * Veh.java created on 24 Jan 2008 08:02:29 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.domainmodel;

/**
 * Bean to encapsulate a Vehicle.
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2008
 */
public class Vehicle
{

    private String mState_;
    private String mPlateNumber_;

    public Vehicle()
    {
    }

    public Vehicle( String aState, String aPlateNumber )
    {
        mState_ = aState;
        mPlateNumber_ = aPlateNumber;
    }

    public String getState()
    {
        return mState_;
    }

    public void setState( String aState )
    {
        mState_ = aState;
    }

    public String getPlateNumber()
    {
        return mPlateNumber_;
    }

    public void setPlateNumber( String aPlateNumber )
    {
        mPlateNumber_ = aPlateNumber;
    }

}
