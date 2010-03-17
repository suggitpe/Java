/*
 * RantsForVehicleController.java created on 24 Jan 2008 07:46:32 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.controller;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;
import org.suggs.sandbox_webapps.springmvctest.service.impl.RantService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

/**
 * TODO Write javadoc for RantsForVehicleController
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2008
 */
public class RantsForVehicleController extends AbstractCommandController
{

    private static final Log LOG = LogFactory.getLog( RantsForVehicleController.class );
    private IRantService mRantService_;

    /**
     * Constructs a new instance.
     */
    public RantsForVehicleController()
    {
        LOG.debug( "Creating rants for vehicle controller" );
        setCommandClass( Vehicle.class );
        setCommandName( "Vehicle" );
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView handle( HttpServletRequest request, HttpServletResponse response,
                                   Object command, BindException errors ) throws Exception
    {
        Vehicle v = (Vehicle) command;
        List<Rant> l = mRantService_.getRantsForVehicle( v );
        Map model = errors.getModel();
        model.put( "rants", l );
        model.put( "vehicle", v );
        return new ModelAndView( "vehicleRants", model );
    }

    /**
     * Setter for the rant service
     * 
     * @param aSvc
     *            the rant service to set
     */
    public void setRantService( RantService aSvc )
    {
        mRantService_ = aSvc;
    }
}
