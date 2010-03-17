/*
 * RantsForDayController.java created on 28 Jan 2008 07:47:14 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.controller;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;
import org.suggs.sandbox_webapps.springmvctest.service.impl.RantService;

import java.util.Date;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.throwaway.ThrowawayController;

/**
 * Controller for a date based rant search
 * 
 * @author suggitpe
 * @version 1.0 28 Jan 2008
 */
public class RantsForDateController implements ThrowawayController
{

    private Date mDate;
    private IRantService mRantService_;

    /**
     * @see org.springframework.web.servlet.mvc.throwaway.ThrowawayController#execute()
     */
    public ModelAndView execute() throws Exception
    {
        List<Rant> dayRants = mRantService_.getRantsForDate( mDate );
        return new ModelAndView( "dateRants", "rants", dayRants );
    }

    /**
     * Setter for the date attribute
     * 
     * @param aDate
     *            the date to set
     */
    public void setDate( Date aDate )
    {
        mDate = aDate;
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
