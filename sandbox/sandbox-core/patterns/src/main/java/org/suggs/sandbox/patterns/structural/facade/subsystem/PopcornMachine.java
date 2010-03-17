/*
 * PopcornMachine.java created on 3 Sep 2007 06:56:58 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade.subsystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to encapsulate the popcorn machine
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class PopcornMachine
{

    private static final Log LOG = LogFactory.getLog( PopcornMachine.class );

    /**
     * 
     */
    public void pop()
    {
        LOG.debug( "popping ...." );
    }

    /**
     * 
     */
    public void on()
    {
        LOG.debug( "Popcorn machine on" );
    }

    /**
     * 
     */
    public void off()
    {
        LOG.debug( "Popcorn machine off" );
    }

}
