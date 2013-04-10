/*
 * FacadeTestCase.java created on 31 Aug 2007 06:38:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural;

import org.suggs.sandbox.patterns.AbstractPatternTest;
import org.suggs.sandbox.patterns.structural.facade.HomeTheatreFacade;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Amplifier;
import org.suggs.sandbox.patterns.structural.facade.subsystem.CdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.DvdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.PopcornMachine;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Projector;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Screen;
import org.suggs.sandbox.patterns.structural.facade.subsystem.TheatreLights;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Tuner;

import org.junit.Test;

/**
 * Test case for the facade pattern
 */
public class FacadeTest extends AbstractPatternTest {

    @Test
    public void testWatchMovie() {
        HomeTheatreFacade facade = createFacade();
        facade.watchMovie( "Rambo" );
    }

    @Test
    public void testCloseDown() {
        HomeTheatreFacade facade = createFacade();
        facade.shutDownTheatre();
    }

    private HomeTheatreFacade createFacade() {
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
