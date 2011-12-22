package org.suggs.webapps.buildpipeline.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Used as the initial default controller for the webapp.  This allows for some simple text like operations and binds to
 * the welcome page.
 * <p/>
 * User: suggitpe Date: 06/07/11 Time: 07:39
 */

@Controller
@RequestMapping("/")
public final class DefaultController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DefaultController.class );

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome() {
        return "home";
    }

}
