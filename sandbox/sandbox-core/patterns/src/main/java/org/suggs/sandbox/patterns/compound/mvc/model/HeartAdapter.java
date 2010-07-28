/*
 * HeartAdapter.java created on 26 Sep 2007 07:03:52 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.model;

import org.suggs.sandbox.patterns.compound.mvc.IBeatModel;
import org.suggs.sandbox.patterns.compound.mvc.IBeatObserver;
import org.suggs.sandbox.patterns.compound.mvc.IBpmObserver;
import org.suggs.sandbox.patterns.compound.mvc.IHeartModel;

/**
 * TODO Write javadoc for HeartAdapter
 * 
 * @author suggitpe
 * @version 1.0 26 Sep 2007
 */
public class HeartAdapter implements IBeatModel {

    private IHeartModel model;

    /**
     * Constructs a new instance.
     * 
     * @param aModel
     */
    public HeartAdapter( IHeartModel aModel ) {
        model = aModel;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#getBpm()
     */
    @Override
    public int getBpm() {
        return model.getHeartRate();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#initialise()
     */
    @Override
    public void initialise() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#on()
     */
    @Override
    public void on() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#off()
     */
    @Override
    public void off() {}

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    @Override
    public void registerObserver( IBeatObserver observer ) {
        model.registerObserver( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    @Override
    public void registerObserver( IBpmObserver observer ) {
        model.registerObserver( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    @Override
    public void removeObserver( IBeatObserver observer ) {
        model.removeObserver( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    @Override
    public void removeObserver( IBpmObserver observer ) {
        model.removeObserver( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IBeatModel#setBPM(int)
     */
    @Override
    public void setBPM( int bpm ) {}

}
