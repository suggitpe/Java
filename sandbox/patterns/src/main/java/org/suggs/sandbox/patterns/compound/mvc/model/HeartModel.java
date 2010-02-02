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
public class HeartModel implements IHeartModel, Runnable
{

    List<IBeatObserver> mBeatObservers_ = new ArrayList<IBeatObserver>();
    List<IBpmObserver> mBpmObservers_ = new ArrayList<IBpmObserver>();
    int mTime_ = 1000;
    int mBpm_ = 90;
    Random mRan_ = new Random( System.currentTimeMillis() );
    Thread mThread_;

    /**
     * Constructs a new instance.
     */
    public HeartModel()
    {
        mThread_ = new Thread( this );
        mThread_.start();
    }

    /**
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        int lastrate = -1;
        for ( ;; )
        {
            int change = mRan_.nextInt( 10 );
            if ( mRan_.nextInt( 2 ) == 0 )
            {
                change = 0 - change;
            }

            int rate = 60000 / ( mTime_ + change );
            if ( rate < 120 && rate > 50 )
            {
                mTime_ += change;
                notifyBeatObservers();
                if ( rate != lastrate )
                {
                    lastrate = rate;
                    notifyBpmObservers();
                }
            }

            try
            {
                Thread.sleep( mTime_ );
            }
            catch ( InterruptedException ie )
            {
                // whatever!!!!
            }
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#getHeartRate()
     */
    public int getHeartRate()
    {
        return 6000 / mTime_;
    }

    /**
     * 
     */
    public void notifyBeatObservers()
    {
        for ( IBeatObserver b : mBeatObservers_ )
        {
            b.updateBeat();
        }
    }

    /**
     * 
     */
    public void notifyBpmObservers()
    {
        for ( IBpmObserver b : mBpmObservers_ )
        {
            b.updateBpm();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void registerObserver( IBeatObserver observer )
    {
        mBeatObservers_.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#registerObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void registerObserver( IBpmObserver observer )
    {
        mBpmObservers_.add( observer );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBeatObserver)
     */
    public void removeObserver( IBeatObserver observer )
    {
        int i = mBeatObservers_.indexOf( observer );
        if ( i >= 0 )
        {
            mBeatObservers_.remove( i );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.mvc.IHeartModel#removeObserver(org.suggs.sandbox.patterns.compound.mvc.IBpmObserver)
     */
    public void removeObserver( IBpmObserver observer )
    {
        int i = mBpmObservers_.indexOf( observer );
        if ( i >= 0 )
        {
            mBpmObservers_.remove( i );
        }
    }
}
