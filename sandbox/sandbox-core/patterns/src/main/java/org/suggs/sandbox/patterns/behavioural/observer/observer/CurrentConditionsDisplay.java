/*
 * CurrentConditionsDisplay.java created on 28 Aug 2007 06:27:01 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.observer.observer;

import org.suggs.sandbox.patterns.behavioural.observer.IDisplayElement;
import org.suggs.sandbox.patterns.behavioural.observer.IObserver;
import org.suggs.sandbox.patterns.behavioural.observer.ISubject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A display object that will show the current measurements taken by the weather data object
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public class CurrentConditionsDisplay implements IObserver, IDisplayElement {

    private static final Log LOG = LogFactory.getLog( CurrentConditionsDisplay.class );

    private float temperature;
    private float humidity;
    private ISubject observable;

    /**
     * Constructs a new instance.
     * 
     * @param aData
     */
    public CurrentConditionsDisplay( ISubject aData ) {
        observable = aData;
        observable.registerObserver( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IObserver#update(float, float, float)
     */
    @Override
    public void update( float aTemperature, float aHumidity, float pressure ) {
        temperature = aTemperature;
        humidity = aHumidity;

        display();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IDisplayElement#display()
     */
    @Override
    public void display() {
        LOG.info( "Current conditions: " + temperature + "C and " + humidity + "% humidity" );
    }

}
