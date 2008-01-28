/*
 * AddRantFormController.java created on 24 Jan 2008 19:31:41 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.controller;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.domainmodel.Vehicle;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Add a Rant controller
 * 
 * @author suggitpe
 * @version 1.0 24 Jan 2008
 */
public class AddRantFormController extends SimpleFormController
{

    private IRantService mRantService_;

    private static final String[] ALL_STATES = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
                                                "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
                                                "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN",
                                                "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
                                                "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
                                                "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA",
                                                "WV", "WI", "WY" };

    /**
     * Constructs a new instance.
     */
    public AddRantFormController()
    {
        setCommandClass( Rant.class );
        setCommandName( "rant" );
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Object formBackingObject( HttpServletRequest aRequest ) throws Exception
    {
        Rant rantForm = (Rant) super.formBackingObject( aRequest );
        rantForm.setVehicle( new Vehicle() );
        return rantForm;
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected Map referenceData( HttpServletRequest aRequest ) throws Exception
    {
        Map<String, String[]> refData = new HashMap<String, String[]>();
        refData.put( "states", ALL_STATES );
        return refData;
    }

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit( Object aCommand, BindException aException ) throws Exception
    {
        Rant rant = (Rant) aCommand;
        mRantService_.addRant( rant );
        return new ModelAndView( getSuccessView() );
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
