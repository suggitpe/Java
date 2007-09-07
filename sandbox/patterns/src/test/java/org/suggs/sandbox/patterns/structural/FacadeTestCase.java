/*
 * FacadeTestCase.java created on 31 Aug 2007 06:38:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.structural.facade.HomeTheatreFacade;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Amplifier;
import org.suggs.sandbox.patterns.structural.facade.subsystem.CdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.DvdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.PopcornMachine;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Projector;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Screen;
import org.suggs.sandbox.patterns.structural.facade.subsystem.TheatreLights;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Tuner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test case for the facade pattern
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class FacadeTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( FacadeTestCase.class );

    /**
     * @see org.suggs.sandbox.patterns.AbstractPatternTestCase#testName()
     */
    @Override
    public void testName()
    {
        LOG.info( "=================================" );
        LOG.debug( "FACADE PATTERN" );
    }

    /**
     * Test the watching of a movie
     */
    public void testWatchMovie()
    {
        HomeTheatreFacade facade = createFacade();
        facade.watchMovie( "Rambo" );
    }

    /**
     * Test the closing down of the theatre
     */
    public void testCloseDown()
    {
        HomeTheatreFacade facade = createFacade();
        facade.shutDownTheatre();
    }

    /**
     * Builds a new facade object
     * 
     * @return
     */
    private HomeTheatreFacade createFacade()
    {
        return new HomeTheatreFacade( new Amplifier(),
                                      new CdPlayer(),
                                      new DvdPlayer(),
                                      new PopcornMachine(),
                                      new Projector(),
                                      new Screen(),
                                      new TheatreLights(),
                                      new Tuner() );
    }
}
