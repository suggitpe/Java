/*
 * Projector.java created on 3 Sep 2007 06:56:34 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate the screen
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class Projector
{

    private static final Log LOG = LogFactory.getLog( Projector.class );

    /**
     * 
     */
    public void wideScreenMode()
    {
        LOG.debug( "Projector in wide screen" );
    }

    /**
     * 
     */
    public void on()
    {
        LOG.debug( "Projector on" );
    }

    /**
     * 
     */
    public void off()
    {
        LOG.debug( "Projector off" );
    }

}
