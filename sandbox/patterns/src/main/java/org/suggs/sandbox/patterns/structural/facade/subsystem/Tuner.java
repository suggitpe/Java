/*
 * Tuner.java created on 3 Sep 2007 06:56:08 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate a tuner
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Tuner
{

    private static final Log LOG = LogFactory.getLog( Tuner.class );

    /**
     * 
     */
    public void off()
    {
        LOG.debug( "Tuner off" );

    }

}
