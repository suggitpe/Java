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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the facade class that will simplify the interface to the
 * home theatre subsystem.
 * 
 * @author suggitpe
 * @version 1.0 3 Sep 2007
 */
public class HomeTheatreFacade
{

    private static final Log LOG = LogFactory.getLog( HomeTheatreFacade.class );

    private Amplifier mAmplifier_;
    private CdPlayer mCdPlayer_;
    private DvdPlayer mDvdPlayer_;
    private PopcornMachine mPopcornMachine_;
    private Projector mProjector_;
    private Screen mScreen_;
    private TheatreLights mTheatreLights_;
    private Tuner mTuner_;

    public HomeTheatreFacade( Amplifier aAmplifier, CdPlayer aCdPlayer, DvdPlayer aDvdPlayer, PopcornMachine aPopcornMachine,
                              Projector aProjector, Screen aScreen, TheatreLights aTheatreLights, Tuner aTuner )
    {
        mAmplifier_ = aAmplifier;
        mCdPlayer_ = aCdPlayer;
        mDvdPlayer_ = aDvdPlayer;
        mPopcornMachine_ = aPopcornMachine;
        mProjector_ = aProjector;
        mScreen_ = aScreen;
        mTheatreLights_ = aTheatreLights;
        mTuner_ = aTuner;
    }

    /**
     * Run through the entire subsystem setting everything up to watch
     * a movie.
     * 
     * @param aMovie
     *            the movie to watch
     */
    public void watchMovie( String aMovie )
    {
        LOG.debug( "Get ready to watch a movie ..." );
        mPopcornMachine_.on();
        mPopcornMachine_.pop();
        mTheatreLights_.dim( 10 );
        mScreen_.down();
        mProjector_.on();
        mProjector_.wideScreenMode();
        mAmplifier_.on();
        mAmplifier_.setDvd( aMovie );
        mAmplifier_.setSurroundSound();
        mAmplifier_.setVolume( 5 );
        mDvdPlayer_.on();
        mDvdPlayer_.play( aMovie );
    }

    /**
     * Run through all of the steps needed to turn off the entire
     * subsystem
     */
    public void shutDownTheatre()
    {
        LOG.debug( "Shutting down move theatre" );
        mPopcornMachine_.off();
        mTheatreLights_.off();
        mScreen_.up();
        mProjector_.off();
        mAmplifier_.off();
        mDvdPlayer_.stop();
        mDvdPlayer_.eject();
        mDvdPlayer_.off();
        mTuner_.off();
        mCdPlayer_.stop();
        mCdPlayer_.eject();
        mCdPlayer_.off();
    }

}
