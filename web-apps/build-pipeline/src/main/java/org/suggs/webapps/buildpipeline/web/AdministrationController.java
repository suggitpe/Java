package org.suggs.webapps.buildpipeline.web;

import org.suggs.webapps.buildpipeline.domain.releaseversion.ReleaseVersionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class for the administration space within the application.
 * <p/>
 * User: suggitpe Date: 19/07/11 Time: 19:41
 */

@Controller
@RequestMapping("/administration")
public class AdministrationController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( AdministrationController.class );

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String handleAdministration() {
        return "administration";
    }

    @RequestMapping(value = "deleteAllVersions", method = RequestMethod.GET)
    public String deleteAllReleaseVersions() {
        ReleaseVersionManager.instance().deleteAllReleaseVersions();
        return "redirect:/administration";
    }
}
