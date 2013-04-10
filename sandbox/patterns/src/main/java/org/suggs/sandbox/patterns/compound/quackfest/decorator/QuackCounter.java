/*
 * QuackCounter.java created on 18 Sep 2007 18:11:03 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.quackfest.decorator;

import org.suggs.sandbox.patterns.compound.quackfest.IQuackable;
import org.suggs.sandbox.patterns.compound.quackfest.observer.IObserver;

/**
 * Decorator for IQuackable objects that will count the number of quacks induced
 */
public class QuackCounter implements IQuackable {

    private IQuackable quackable;
    private static int numQuacks;

    public QuackCounter( IQuackable aQuackable ) {
        quackable = aQuackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        ++numQuacks;
    }

    public static int getQuacks() {
        return numQuacks;
    }

    @Override
    public void notifyObservers() {
        quackable.notifyObservers();
    }

    @Override
    public void registerObserver( IObserver observer ) {
        quackable.registerObserver( observer );
    }

}
