/*
 * IObserver.java created on 28 Aug 2007 06:05:42 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.observer;

/**
 * Interface to allow subjects to communicate with observers
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public interface IObserver
{

    /**
     * Interface to allow a subject to advise the observers that a
     * change has happened.
     * 
     * @param aTemp
     *            the temperature
     * @param aHumidity
     *            the humidity
     * @param aPressure
     *            the pressure
     */
    void update( float aTemp, float aHumidity, float aPressure );

}
