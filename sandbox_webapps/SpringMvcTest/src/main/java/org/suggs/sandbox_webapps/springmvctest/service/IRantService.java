/*
 * RantService.java created on 22 Jan 2008 19:31:29 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.service;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Motorist;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * Accessor to the rant persistent store.
 * 
 * @author suggitpe
 * @version 1.0 22 Jan 2008
 */
public interface IRantService
{

    /**
     * Gets a collection of the most recent rants
     * 
     * @return a list of rants
     */
    public List<Rant> getRecentRants();

    /**
     * Gets a list of rants for a given vehicle
     * 
     * @return the list of rants
     */
    public List<Rant> getRantsForVehicle( Vehicle aVehicle );

    /**
     * Gets a list of rants based on a date.
     * 
     * @param aDate
     *            the date to use as context for the date based search
     * @return the list of rants for the given date
     */
    public List<Rant> getRantsForDate( Date aDate );

    /**
     * Adds a rant to the persistent store
     * 
     * @param aRant
     *            the rant to add
     */
    public void addRant( Rant aRant );

    /**
     * Adds a motorist to the persistent store
     * 
     * @param aMotorist
     *            the motorist to add
     */
    public void addMotorist( Motorist aMotorist );

}
