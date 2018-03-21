/*
 * IBeatModel.java created on 19 Sep 2007 18:14:42 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * High level interface to the beat model.
 */
public interface IBeatModel {

    void initialise();

    void on();

    void off();

    void setBPM( int bpm );


    int getBpm();

    void registerObserver( IBeatObserver observer );

    void removeObserver( IBeatObserver observer );

    void registerObserver( IBpmObserver observer );

    void removeObserver( IBpmObserver observer );

}
