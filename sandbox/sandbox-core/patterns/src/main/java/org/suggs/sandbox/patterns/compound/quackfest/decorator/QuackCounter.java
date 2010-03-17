/*
 * QuackCounter.java created on 18 Sep 2007 18:11:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.decorator;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;

/**
 * Decorator for IQuackable objects that will count the number of
 * quacks induced
 * 
 * @author suggitpe
 * @version 1.0 18 Sep 2007
 */
public class QuackCounter implements IQuackable
{

    private IQuackable mQuackable_;
    private static int mNumQuacks_;

    /**
     * Constructs a new instance.
     * 
     * @param aQuackable
     *            the quackable object to decorate
     */
    public QuackCounter( IQuackable aQuackable )
    {
        mQuackable_ = aQuackable;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.IQuackable#quack()
     */
    public void quack()
    {
        mQuackable_.quack();
        ++mNumQuacks_;
    }

    /**
     * Getter for the number of quacks
     * 
     * @return the number of quacks
     */
    public static int getQuacks()
    {
        return mNumQuacks_;
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#notifyObservers()
     */
    public void notifyObservers()
    {
        mQuackable_.notifyObservers();
    }

    /**
     * @see org.suggs.sandbox.patterns.compound.quackfest.observer.IQuackObservable#registerObserver(org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver)
     */
    public void registerObserver( IObserver observer )
    {
        mQuackable_.registerObserver( observer );
    }

}
