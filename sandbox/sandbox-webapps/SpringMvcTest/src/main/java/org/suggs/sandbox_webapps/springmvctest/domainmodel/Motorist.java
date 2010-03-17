/*
 * Motorist.java created on 25 Jan 2008 18:53:31 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.domainmodel;

import java.util.List;

/**
 * Motorist encapsultion
 * 
 * @author suggitpe
 * @version 1.0 25 Jan 2008
 */
public class Motorist
{

    private List<Vehicle> mVehicles_;

    /**
     * Constructs a new instance.
     */
    public Motorist()
    {
    }

    /**
     * Getter for the list of vehicles
     * 
     * @return the list of vehicles
     */
    public List<Vehicle> getVehicles()
    {
        return mVehicles_;
    }

    /**
     * Setter for the list of vehicles
     * 
     * @param aList
     *            the list of vehicles ot set
     */
    public void setVehicles( List<Vehicle> aList )
    {
        mVehicles_ = aList;
    }

}
