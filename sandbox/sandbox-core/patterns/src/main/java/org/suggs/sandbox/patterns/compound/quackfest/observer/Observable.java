/*
 * Observable.java created on 19 Sep 2007 06:56:45 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to encapsulate the observable nature of the quackable
 * objects. This manages the logic for all of the behaviour that is
 * required for being observabnle.
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public class Observable implements IQuackObservable
{

    List<IObserver> mObservers_ = new ArrayList<IObserver>();
    IQuackObservable mDuck_;

    /**
     * Constructs a new instance.
     * 
     * @param aDuck
     *            the observable duck
     */
    public Observable( IQuackObservable aDuck )
    {
        mDuck_ = aDuck;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    public void notifyObservers()
    {
        for ( IObserver o : mObservers_ )
        {
            o.update( mDuck_ );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    public void registerObserver( IObserver observer )
    {
        mObservers_.add( observer );
    }
}
