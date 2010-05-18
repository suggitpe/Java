/*
 * HeartController.java created on 26 Sep 2007 07:07:26 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.controller;

import org.suggs.sandbox.patterns.compound.mvc.IController;
import org.suggs.sandbox.patterns.compound.mvc.IHeartModel;
import org.suggs.sandbox.patterns.compound.mvc.model.HeartAdapter;
import org.suggs.sandbox.patterns.compound.mvc.view.DJView;

/**
 * TODO Write javadoc for HeartController
 * 
 * @author suggitpe
 * @version 1.0 26 Sep 2007
 */
public class HeartController implements IController {

    IHeartModel model;
    DJView view;

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     */
    public HeartController( IHeartModel aModel ) {
        model = aModel;
        view = new DJView( this, new HeartAdapter( model ) );

        view.createView();
        view.createControls();
        view.disableStopMenuItem();
        view.disableStartMenuItem();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#decreaseBpm()
     */
    public void decreaseBpm() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#increaseBpm()
     */
    public void increaseBpm() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#setBpm(int)
     */
    public void setBpm( int bpm ) {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#start()
     */
    public void start() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IController#stop()
     */
    public void stop() {}
}
