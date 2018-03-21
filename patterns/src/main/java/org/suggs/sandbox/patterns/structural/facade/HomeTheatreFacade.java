/*
 * HomeTheatreFacade.java created on 3 Sep 2007 06:55:35 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suggs.sandbox.patterns.structural.facade.subsystem.*;

/**
 * This is the facade class that will simplify the interface to the home theatre subsystem.
 */
public class HomeTheatreFacade {

    private static final Logger LOG = LoggerFactory.getLogger(HomeTheatreFacade.class);

    private Amplifier amplifier;
    private CdPlayer cdPlayer;
    private DvdPlayer dvdPlayer;
    private PopcornMachine popcornMachine;
    private Projector projector;
    private Screen screen;
    private TheatreLights theatreLights;
    private Tuner tuner;

    public HomeTheatreFacade(Amplifier aAmplifier, CdPlayer aCdPlayer, DvdPlayer aDvdPlayer,
                             PopcornMachine aPopcornMachine, Projector aProjector, Screen aScreen,
                             TheatreLights aTheatreLights, Tuner aTuner) {
        amplifier = aAmplifier;
        cdPlayer = aCdPlayer;
        dvdPlayer = aDvdPlayer;
        popcornMachine = aPopcornMachine;
        projector = aProjector;
        screen = aScreen;
        theatreLights = aTheatreLights;
        tuner = aTuner;
    }

    public void watchMovie(String aMovie) {
        LOG.debug("Get ready to watch a movie ...");
        popcornMachine.on();
        popcornMachine.pop();
        theatreLights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amplifier.on();
        amplifier.setDvd(aMovie);
        amplifier.setSurroundSound();
        amplifier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(aMovie);
    }

    public void shutDownTheatre() {
        LOG.debug("Shutting down move theatre");
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
