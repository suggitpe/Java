/*
 * Goose.java created on 18 Sep 2007 17:59:16 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.birdlife;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Impl for a goose (not IQuackable)
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class Goose
{

    private static final Log LOG = LogFactory.getLog( Goose.class );

    /** */
    public void honk()
    {
        LOG.debug( "Honk" );
    }

}
