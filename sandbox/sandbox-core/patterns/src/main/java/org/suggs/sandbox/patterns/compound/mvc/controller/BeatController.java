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
public class BeatController implements IController {

    private IBeatModel model;
    private DJView view;

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     */
    public BeatController( IBeatModel aModel ) {
        model = aModel;
        view = new DJView( this, model );
        view.createView();
        view.createControls();
        view.enableStartMenuItem();
        view.disableStopMenuItem();
        model.initialise();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#start()
     */
    @Override
    public void start() {
        model.on();
        view.disableStartMenuItem();
        view.enableStopMenuItem();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#stop()
     */
    @Override
    public void stop() {
        model.off();
        view.enableStartMenuItem();
        view.disableStopMenuItem();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#increaseBpm()
     */
    @Override
    public void increaseBpm() {
        model.setBPM( model.getBpm() + 1 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#decreaseBpm()
     */
    @Override
    public void decreaseBpm() {
        model.setBPM( model.getBpm() + 1 );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#setBpm(int)
     */
    @Override
    public void setBpm( int bpm ) {
        model.setBPM( bpm );
    }

}
