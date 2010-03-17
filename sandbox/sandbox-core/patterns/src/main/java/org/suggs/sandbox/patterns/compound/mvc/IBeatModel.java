/*
 * IBeatModel.java created on 19 Sep 2007 18:14:42 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * High level interface to the beat model.
 * 
 * @author suggitpe
 * @version 1.0 19 Sep 2007
 */
public interface IBeatModel
{

    /**
     * Initialise the model
     */
    void initialise();

    /**
     * Turn the beat generation on
     */
    void on();

    /**
     * Turn the beat generation off
     */
    void off();

    /**
     * Setter for the beats per minute
     * 
     * @param bpm
     *            the beats per minute
     */
    void setBPM( int bpm );

    /**
     * Getter for the beats per minute
     * 
     * @return the beats per minute
     */
    int getBpm();

    /**
     * Add an obsrever
     * 
     * @param observer
     *            the observer to add
     */
    void registerObserver( IBeatObserver observer );

    /**
     * Remove an observer
     * 
     * @param observer
     *            the observer to remove
     */
    void removeObserver( IBeatObserver observer );

    /**
     * Add an observer
     * 
     * @param observer
     *            the observer to add
     */
    void registerObserver( IBpmObserver observer );

    /**
     * Remove an observer
     * 
     * @param observer
     *            the observer to remove
     */
    void removeObserver( IBpmObserver observer );

}
