/*
 * RantService.java created on 22 Jan 2008 19:44:24 by suggitpe for project SpringMvcTest
 * 
 */
package org.suggs.sandbox_webapps.springmvctest.service.impl;

import org.suggs.sandbox_webapps.springmvctest.service.IRantService;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Write javadoc for RantService
 * 
 * @author suggitpe
 * @version 1.0 22 Jan 2008
 */
public class RantService implements IRantService
{

    /**
     * @see org.suggs.sandbox_webapps.springmvctest.service.IRantService#getRecentRants()
     */
    public List<String> getRecentRants()
    {
        List<String> ret = new ArrayList<String>();
        ret.add( "This is a dummy rant" );
        return ret;
    }

}
