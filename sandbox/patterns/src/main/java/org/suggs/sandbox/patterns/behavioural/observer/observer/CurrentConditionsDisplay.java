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
 * A display object that will show the current measurements taken by
 * the weather data object
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public class CurrentConditionsDisplay implements IObserver, IDisplayElement
{

    private static final Log LOG = LogFactory.getLog( CurrentConditionsDisplay.class );

    private float mTemperature_;
    private float mHumidity_;
    private ISubject mObservable_;

    /**
     * Constructs a new instance.
     */
    public CurrentConditionsDisplay( ISubject aData )
    {
        mObservable_ = aData;
        mObservable_.registerObserver( this );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IObserver#update(float,
     *      float, float)
     */
    public void update( float temp, float humidity, float pressure )
    {
        mTemperature_ = temp;
        mHumidity_ = humidity;

        display();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.IDisplayElement#display()
     */
    public void display()
    {
        LOG.info( "Current conditions: " + mTemperature_ + "C and " + mHumidity_ + "% humidity" );
    }

}
