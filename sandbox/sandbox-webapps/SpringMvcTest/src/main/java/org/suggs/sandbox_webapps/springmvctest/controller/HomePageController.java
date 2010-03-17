/*
 * HomePageController.java created on 22 Jan 2008 19:23:41 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.controller;

import org.suggs.sandbox_webapps.springmvctest.domainmodel.Rant;
import org.suggs.sandbox_webapps.springmvctest.service.IRantService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Controller for the home page
 * 
 * @author suggitpe
 * @version 1.0 22 Jan 2008
 */
public class HomePageController extends AbstractController
{

    private IRantService mRantService_;

    /**
     * Constructs a new instance.
     */
    public HomePageController()
    {
    }

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ModelAndView handleRequestInternal( HttpServletRequest arg0, HttpServletResponse arg1 )
                    throws Exception
    {
        List<Rant> recentRants = mRantService_.getRecentRants();
        return new ModelAndView( "home", "rants", recentRants );
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
