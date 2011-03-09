package org.suggs.sandbox_webapps.springmvcpersistenttest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Used as the initial and defaul controller for the webapp.  This allows for some simple text like operations and binds
 * to teh welcome page.
 * <p/>
 * <p/>
 * User: suggitpe Date: 11/02/11 Time: 07:39
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( DefaultController.class );

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String pingTest() {
        return "ping";
    }
}
