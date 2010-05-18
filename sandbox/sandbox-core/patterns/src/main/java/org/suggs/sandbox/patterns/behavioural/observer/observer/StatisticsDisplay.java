/*
 * StatisticsDisplay.java created on 28 Aug 2007 16:47:08 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.observer.observer;

import org.suggs.sandbox.patterns.behavioural.observer.IDisplayElement;
import org.suggs.sandbox.patterns.behavioural.observer.IObserver;
import org.suggs.sandbox.patterns.behavioural.observer.ISubject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Statistics display to show another observer for the pattern.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public class StatisticsDisplay implements IObserver, IDisplayElement {

    private static final Log LOG = LogFactory.getLog( StatisticsDisplay.class );

    private ISubject observable;

    private float oldTemp = 0.0f;
    private float newTemp = 0.0f;
    private float oldHumidity = 0.0f;
    private float newHumidity = 0.0f;
    private float oldPressure = 0.0f;
    private float newPressure = 0.0f;

    /**
     * Constructs a new instance.
     * 
     * @param data
     *            the weather data to observe
     */
    public StatisticsDisplay( ISubject data ) {
        observable = data;
        observable.registerObserver( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IObserver#update(float, float, float)
     */
    public void update( float temp, float humidity, float pressure ) {
        oldTemp = newTemp;
        newTemp = temp;

        oldHumidity = newHumidity;
        newHumidity = humidity;

        oldPressure = newPressure;
        newPressure = pressure;

        display();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IDisplayElement#display()
     */
    public void display() {

        LOG.debug( "STATS: temp [" + calcChange( oldTemp, newTemp ) + "%] change, humidity ["
                   + calcChange( oldHumidity, newHumidity ) + "%] change, pressure ["
                   + calcChange( oldPressure, newPressure ) + "%] change" );
    }

    /**
     * Works out what the % change is for a given difference in floats
     * 
     * @param aOld
     *            the old calculation
     * @param aNew
     *            the new calculation
     * @return the percentage change
     */
    private float calcChange( float aOld, float aNew ) {
        if ( aOld == 0.0f ) {
            return 0.0f;
        }
        return aNew / aOld * 100 - 100;
    }
}
