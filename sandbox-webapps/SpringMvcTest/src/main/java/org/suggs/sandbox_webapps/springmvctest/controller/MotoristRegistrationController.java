/*
 * MotoristRegistrationController.java created on 25 Jan 2008 08:21:49 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.controller;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Motorist;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

/**
 * Wizard form based approach to getting the details for a user
 * registration
 * 
 * @author suggitpe
 * @version 1.0 25 Jan 2008
 */
public class MotoristRegistrationController extends AbstractWizardFormController
{

    private IRantService mRantService_;

    /**
     * Constructs a new instance.
     */
    public MotoristRegistrationController()
    {
        setCommandClass( Motorist.class );
        setCommandName( "motorist" );
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Object formBackingObject( HttpServletRequest aRequest ) throws Exception
    {
        Motorist formMotorist = new Motorist();
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add( new Vehicle() );
        formMotorist.setVehicles( vehicles );
        return formMotorist;
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#referenceData(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.Errors,
     *      int)
     */
    @Override
    protected Map referenceData( HttpServletRequest aRequest, Object aCommand, Errors aErr,
                                 int aPage ) throws Exception
    {
        Motorist mtr = (Motorist) aCommand;
        Map<String, Integer> refData = new HashMap<String, Integer>();

        if ( aPage == 1 && aRequest.getParameter( "_target1" ) != null )
        {
            refData.put( "nextVehicle", new Integer( mtr.getVehicles().size() - 1 ) );
        }
        return refData;
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#postProcessPage(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.Errors,
     *      int)
     */
    @Override
    protected void postProcessPage( HttpServletRequest aRequest, Object aCommand, Errors aErr,
                                    int page ) throws Exception
    {
        Motorist mtr = (Motorist) aCommand;
        if ( page == 1 && aRequest.getParameter( "_target1" ) != null )
        {
            mtr.getVehicles().add( new Vehicle() );
        }
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView processFinish( HttpServletRequest request, HttpServletResponse response,
                                          Object command, BindException errors ) throws Exception
    {
        Motorist mtr = (Motorist) command;

        // the last of the vehicles is always blank so we can safely
        // remove it now
        mtr.getVehicles().remove( mtr.getVehicles().size() - 1 );
        mRantService_.addMotorist( mtr );

        return new ModelAndView( getSuccessView(), "motorist", mtr );
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processCancel(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView processCancel( HttpServletRequest request, HttpServletResponse response,
                                          Object command, BindException errors ) throws Exception
    {
        return new ModelAndView( getSuccessView() );
    }

    /**
     * Gets the last of the pages in the view to show the success page
     * 
     * @return the name of the success view page
     */
    private String getSuccessView()
    {
        return getPages()[getPages().length - 1];
    }

    /**
     * Setter for the rant service.
     * 
     * @param aRantSvc
     *            the rant service to set
     */
    public void setRantService( IRantService aRantSvc )
    {
        mRantService_ = aRantSvc;
    }
}
