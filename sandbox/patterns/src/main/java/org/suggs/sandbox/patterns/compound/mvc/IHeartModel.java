/*
 * IHeartModel.java created on 25 Sep 2007 20:34:35 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * Heart interface
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2007
 */
public interface IHeartModel {

    /**
     * Getter for a heart rate
     * 
     * @return a heart rate
     */
    int getHeartRate();

    /**
     * Register an observer with this model
     * 
     * @param observer
     *            the observer to register
     */
    void registerObserver( IBeatObserver observer );

    /**
     * Remove an existing observer
     * 
     * @param observer
     *            the observer to remove
     */
    void removeObserver( IBeatObserver observer );

    /**
     * Register an observer with this model
     * 
     * @param observer
     *            the observer to register
     */
    void registerObserver( IBpmObserver observer );

    /**
     * Remove an existing observer
     * 
     * @param observer
     *            the observer to remove
     */
    void removeObserver( IBpmObserver observer );
}
