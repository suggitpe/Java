/*
 * GooseAdapter.java created on 18 Sep 2007 18:00:41 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.adapter;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.birdlife.Goose;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;
import org.suggs.sandbox.patterns.compound.quackfest.observer.Observable;

/**
 * Adapter to enable the Goose objec to fit the IQuackable interface
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class GooseAdapter implements IQuackable
{

    private Goose mGoose_;
    private Observable mObservable_;

    /**
     * Constructs a new instance.
     * 
     * @param aGoose
     */
    public GooseAdapter( Goose aGoose )
    {
        mGoose_ = aGoose;
        mObservable_ = new Observable( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    public void quack()
    {
        mGoose_.honk();
        notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    public void notifyObservers()
    {
        mObservable_.notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    public void registerObserver( IObserver observer )
    {
        mObservable_.registerObserver( observer );
    }

}
