package com.ubs.gfit.buildpipeline.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.gfit.buildpipeline.domain.ReleaseVersion;
import com.ubs.gfit.buildpipeline.domain.ReleaseVersionManager;
import com.ubs.gfit.buildpipeline.validtors.ReleaseVersionValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class to handle the release management aspects
 * <p/>
 * User: suggitpe
 * Date: 06/07/11
 * Time: 07:48
 */
@Controller
@RequestMapping("/release-management")
public class ReleaseManagementController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseManagementController.class );

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView handleReleaseVersions() {
        LOG.debug( "getting all of the release versions" );
        ModelAndView mav = new ModelAndView();
        mav.addObject( "releaseVersions", ReleaseVersionManager.instance().getAllReleaseVersions() );
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newReleaseRequest( Model aModel ) {
        ReleaseVersion version = new ReleaseVersion();
        aModel.addAttribute( version );
        return "releaseVersion/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processReleaseRequest( @ModelAttribute ReleaseVersion releaseVersion, BindingResult aResult, SessionStatus aStatus ) {
        new ReleaseVersionValidator().validate( releaseVersion, aResult );
        if ( aResult.hasErrors() ) {
            return "releaseVersion/form";
        }
        else {
            ReleaseVersionManager.instance().createVersion( releaseVersion );
            aStatus.setComplete();
            return "redirect:/release-mangement";
        }
    }


}
