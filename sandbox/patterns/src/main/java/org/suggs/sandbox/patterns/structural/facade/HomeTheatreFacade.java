/*
 * HomeTheatreFacade.java created on 3 Sep 2007 06:55:35 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade;

import org.suggs.sandbox.patterns.structural.facade.subsystem.Amplifier;
import org.suggs.sandbox.patterns.structural.facade.subsystem.CdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.DvdPlayer;
import org.suggs.sandbox.patterns.structural.facade.subsystem.PopcornMachine;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Projector;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Screen;
import org.suggs.sandbox.patterns.structural.facade.subsystem.TheatreLights;
import org.suggs.sandbox.patterns.structural.facade.subsystem.Tuner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the facade class that will simplify the interface to the home theatre subsystem.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class HomeTheatreFacade {

    private static final Logger LOG = LoggerFactory.getLogger( HomeTheatreFacade.class );

    private Amplifier amplifier;
    private CdPlayer cdPlayer;
    private DvdPlayer dvdPlayer;
    private PopcornMachine popcornMachine;
    private Projector projector;
    private Screen screen;
    private TheatreLights theatreLights;
    private Tuner tuner;

    /**
     * Constructs a new instance.
     * 
     * @param aAmplifier
     * @param aCdPlayer
     * @param aDvdPlayer
     * @param aPopcornMachine
     * @param aProjector
     * @param aScreen
     * @param aTheatreLights
     * @param aTuner
     */
    public HomeTheatreFacade( Amplifier aAmplifier, CdPlayer aCdPlayer, DvdPlayer aDvdPlayer,
                              PopcornMachine aPopcornMachine, Projector aProjector, Screen aScreen,
                              TheatreLights aTheatreLights, Tuner aTuner ) {
        amplifier = aAmplifier;
        cdPlayer = aCdPlayer;
        dvdPlayer = aDvdPlayer;
        popcornMachine = aPopcornMachine;
        projector = aProjector;
        screen = aScreen;
        theatreLights = aTheatreLights;
        tuner = aTuner;
    }

    /**
     * Run through the entire subsystem setting everything up to watch a movie.
     * 
     * @param aMovie
     *            the movie to watch
     */
    public void watchMovie( String aMovie ) {
        LOG.debug( "Get ready to watch a movie ..." );
        popcornMachine.on();
        popcornMachine.pop();
        theatreLights.dim( 10 );
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setDvd( aMovie );
        amplifier.setSurroundSound();
        amplifier.setVolume( 5 );
        dvdPlayer.on();
        dvdPlayer.play( aMovie );
    }

    /**
     * Run through all of the steps needed to turn off the entire subsystem
     */
    public void shutDownTheatre() {
        LOG.debug( "Shutting down move theatre" );
        popcornMachine.off();
        theatreLights.off();
        screen.up();
        projector.off();
        amplifier.off();
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
        tuner.off();
        cdPlayer.stop();
        cdPlayer.eject();
        cdPlayer.off();
    }

}
