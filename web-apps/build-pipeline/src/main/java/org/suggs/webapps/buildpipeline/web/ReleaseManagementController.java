package org.suggs.webapps.buildpipeline.web;

import org.suggs.webapps.buildpipeline.domain.component.ComponentVersionService;
import org.suggs.webapps.buildpipeline.domain.releaseversion.ReleaseVersion;
import org.suggs.webapps.buildpipeline.domain.releaseversion.ReleaseVersionManager;
import org.suggs.webapps.buildpipeline.validators.ReleaseVersionValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class to handle the release management aspects
 * <p/>
 * User: suggitpe Date: 06/07/11 Time: 07:48
 */

@Controller
@RequestMapping("/release-management")
@SessionAttributes(types = ReleaseVersion.class)
public final class ReleaseManagementController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( ReleaseManagementController.class );

    @Autowired
    private ComponentVersionService componentVersionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView handleReleaseVersions() {
        LOG.debug( "Getting all of the release versions" );
        ModelAndView mav = new ModelAndView();
        mav.addObject( "releaseVersions", ReleaseVersionManager.instance().getAllReleaseVersions() );
        return mav;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newReleaseRequest( Model aModel ) {
        LOG.debug( "Getting a new release version form" );
        ReleaseVersion version = new ReleaseVersion();
        aModel.addAttribute( version );
        aModel.addAttribute( "componentVersionsBean", componentVersionService.getComponentVersions() );
        return "releaseVersion/form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processReleaseRequest( Model aModel, @ModelAttribute ReleaseVersion releaseVersion, BindingResult aResult, SessionStatus aStatus ) {
        LOG.debug( "Posting a new release version form" );
        new ReleaseVersionValidator().validate( releaseVersion, aResult );
        if ( aResult.hasErrors() ) {
            aModel.addAttribute( "componentVersionsBean", componentVersionService.getComponentVersions() );
            return "releaseVersion/form";
        }
        else {
            ReleaseVersionManager.instance().addVersion( releaseVersion );
            aStatus.setComplete();
            return "redirect:/release-management/" + releaseVersion.getVersion();
        }
    }

    @RequestMapping(value = "/{releaseVersion}", method = RequestMethod.GET)
    public ModelAndView showReleaseVersion( @PathVariable("releaseVersion") String aReleaseVersion ) {
        LOG.debug( "Getting a single release version for [" + aReleaseVersion + "]" );
        ModelAndView mav = new ModelAndView( "releaseVersion/show" );
        mav.addObject( ReleaseVersionManager.instance().getVersion( aReleaseVersion ) );
        return mav;
    }

    @RequestMapping(value = "/{releaseVersion}/edit", method = RequestMethod.GET)
    public String setupEditForm( @PathVariable("releaseVersion") String aReleaseVersion, Model aModel ) {
        LOG.debug( "Getting a edit release version form for [" + aReleaseVersion + "]" );
        ReleaseVersion version = ReleaseVersionManager.instance().getVersion( aReleaseVersion );
        aModel.addAttribute( version );
        aModel.addAttribute( "componentVersionsBean", componentVersionService.getComponentVersions() );
        return "releaseVersion/form";
    }

    @RequestMapping(value = "/{releaseVersion}/edit", method = RequestMethod.POST)
    public String processSubmitEdit( Model aModel, @ModelAttribute ReleaseVersion releaseVersion, BindingResult aBindingResult, SessionStatus aStatus ) {
        LOG.debug( "Posting a edit release version form for [" + releaseVersion + "]" );
        new ReleaseVersionValidator().validate( releaseVersion, aBindingResult );
        if ( aBindingResult.hasErrors() ) {
            aModel.addAttribute( "componentVersionsBean", componentVersionService.getComponentVersions() );
            return "releaseVersion/form";
        }
        else {
            ReleaseVersionManager.instance().addVersion( releaseVersion );
            aStatus.setComplete();
            return "redirect:/release-management/" + releaseVersion.getVersion();
        }
    }

    @RequestMapping(value = "/{releaseVersion}/edit", method = RequestMethod.DELETE)
    public String processDeleteReleaseVersion( @PathVariable("releaseVersion") String aReleaseVersion ) {
        ReleaseVersionManager.instance().remove( aReleaseVersion );
        return "redirect:/release-management";
    }

}
