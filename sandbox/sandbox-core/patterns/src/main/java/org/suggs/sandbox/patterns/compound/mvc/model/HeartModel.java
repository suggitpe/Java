/*
 * HeartModel.java created on 25 Sep 2007 20:47:17 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.model;

import org.suggs.sandbox.patterns.compound.mvc.IBeatObserver;
import org.suggs.sandbox.patterns.compound.mvc.IBpmObserver;
import org.suggs.sandbox.patterns.compound.mvc.IHeartModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Heart model implementation
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2007
 */
public class HeartModel implements IHeartModel, Runnable {

    List<IBeatObserver> beatObservers = new ArrayList<IBeatObserver>();
    List<IBpmObserver> bpmObservers = new ArrayList<IBpmObserver>();
    int time = 1000;
    int bpm = 90;
    Random random = new Random( System.currentTimeMillis() );
    Thread thread;

    /**
     * Constructs a new instance.
     */
    public HeartModel() {
        thread = new Thread( this );
        thread.start();
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run() {
        int lastrate = -1;
        for ( ;; ) {
            int change = random.nextInt( 10 );
            if ( random.nextInt( 2 ) == 0 ) {
                change = 0 - change;
            }

            int rate = 60000 / ( time + change );
            if ( rate < 120 && rate > 50 ) {
                time += change;
                notifyBeatObservers();
                if ( rate != lastrate ) {
                    lastrate = rate;
                    notifyBpmObservers();
                }
            }

            try {
                Thread.sleep( time );
            }
            catch ( InterruptedException ie ) {
                // whatever!!!!
            }
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#getHeartRate()
     */
    public int getHeartRate() {
        return 6000 / time;
    }

    /**
     * 
     */
    public void notifyBeatObservers() {
        for ( IBeatObserver b : beatObservers ) {
            b.updateBeat();
        }
    }

    /**
     * 
     */
    public void notifyBpmObservers() {
        for ( IBpmObserver b : bpmObservers ) {
            b.updateBpm();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void registerObserver( IBeatObserver observer ) {
        beatObservers.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void registerObserver( IBpmObserver observer ) {
        bpmObservers.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void removeObserver( IBeatObserver observer ) {
        int i = beatObservers.indexOf( observer );
        if ( i >= 0 ) {
            beatObservers.remove( i );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void removeObserver( IBpmObserver observer ) {
        int i = bpmObservers.indexOf( observer );
        if ( i >= 0 ) {
            bpmObservers.remove( i );
        }
    }
}
