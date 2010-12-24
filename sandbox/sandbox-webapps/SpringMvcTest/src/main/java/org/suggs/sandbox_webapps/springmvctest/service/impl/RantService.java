/*
 * RantService.java created on 22 Jan 2008 19:44:24 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.service.impl;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Motorist;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to get access to the rant persistent store.
 * 
 * @author suggitpe
 * @version 1.0 22 Jan 2008
 */
public class RantService implements IRantService {

    private static final Logger LOG = LoggerFactory.getLogger( RantService.class );

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#getRecentRants()
     */
    public List<Rant> getRecentRants() {
        return createRants();
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#getRantsForVehicle(org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle)
     */
    public List<Rant> getRantsForVehicle( Vehicle vehicle ) {
        return createRants();
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#getRantsForDate(java.util.Date)
     */
    public List<Rant> getRantsForDate( Date date ) {
        return createRants();
    }

    private List<Rant> createRants() {
        List<Rant> ret = new ArrayList<Rant>();
        ret.add( new Rant( "This is a dummy rant" ) );
        ret.add( new Rant( "This is a dummy rant too" ) );
        return ret;
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#addRant(org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant)
     */
    public void addRant( Rant rant ) {
        LOG.debug( "Adding a new rant to the persistent store" );
    }

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#addMotorist(org.suggs.sandbox_webapps.springmvctest.domainmodel.Motorist)
     */
    public void addMotorist( Motorist motorist ) {
        LOG.debug( "Adding a motorist through the rant service" );
    }

}
