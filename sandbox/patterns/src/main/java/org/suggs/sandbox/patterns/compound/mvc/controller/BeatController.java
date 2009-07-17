/*
 * BeatController.java created on 25 Sep 2007 20:25:29 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.controller;

import org.suggs.sandbox.patterns.compound.mvc.IBeatModel;
import org.suggs.sandbox.patterns.compound.mvc.IController;
import org.suggs.sandbox.patterns.compound.mvc.view.DJView;

/**
 * Beat controller
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2007
 */
public class BeatController implements IController
{

    IBeatModel mModel_;
    DJView mView_;

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     */
    public BeatController( IBeatModel aModel )
    {
        mModel_ = aModel;
        mView_ = new DJView( this, mModel_ );
        mView_.createView();
        mView_.createControls();
        mView_.enableStartMenuItem();
        mView_.disableStopMenuItem();
        mModel_.initialise();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#start()
     */
    public void start()
    {
        mModel_.on();
        mView_.disableStartMenuItem();
        mView_.enableStopMenuItem();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#stop()
     */
    public void stop()
    {
        mModel_.off();
        mView_.enableStartMenuItem();
        mView_.disableStopMenuItem();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#increaseBpm()
     */
    public void increaseBpm()
    {
        mModel_.setBPM( mModel_.getBpm() + 1 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#decreaseBpm()
     */
    public void decreaseBpm()
    {
        mModel_.setBPM( mModel_.getBpm() + 1 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#setBpm(int)
     */
    public void setBpm( int bpm )
    {
        mModel_.setBPM( bpm );
    }

}
