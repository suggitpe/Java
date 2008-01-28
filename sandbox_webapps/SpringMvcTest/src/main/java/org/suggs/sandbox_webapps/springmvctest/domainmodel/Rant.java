/*
 * Rant.java created on 24 Jan 2008 07:52:03 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.domainmodel;

/**
 * Class to encapsulate the underlying rant.
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2008
 */
public class Rant
{

    private String mRantText_;
    private long mTimeStamp_;
    private Vehicle mVehicle_;

    public Rant()
    {
    }

    public Rant( String aRant )
    {
        mRantText_ = aRant;
        mTimeStamp_ = System.currentTimeMillis();
    }

    public String getRantText()
    {
        return mRantText_;
    }

    public void setRantText( String aRant )
    {
        mRantText_ = aRant;
    }

    public long getTimeStamp()
    {
        return mTimeStamp_;
    }

    public Vehicle getVehicle()
    {
        return mVehicle_;
    }

    public void setVehicle( Vehicle aVehicle )
    {
        mVehicle_ = aVehicle;
    }
}
