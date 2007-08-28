/*
 * WeatherData.java created on 28 Aug 2007 06:15:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.observer.subject;

import org.suggs.sandbox.patterns.behavioural.observer.IObserver;
import org.suggs.sandbox.patterns.behavioural.observer.ISubject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is the subject object that will be observed by the collection
 * of observers (of which there may be zero)
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public class WeatherData implements ISubject
{

    private static final Log LOG = LogFactory.getLog( WeatherData.class );

    private List<IObserver> mObservers_ = new ArrayList<IObserver>();

    private float mTemperature_;
    private float mHumidity_;
    private float mPressure_;

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.ISubject#notifyObservers()
     */
    public void notifyObservers()
    {
        for ( IObserver o : mObservers_ )
        {
            o.update( mTemperature_, mHumidity_, mPressure_ );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.ISubject#registerObserver(org.suggs.sandbox.patterns.behavioural.observer.IObserver)
     */
    public void registerObserver( IObserver aObserver )
    {
        LOG.debug( "Adding observer" );
        mObservers_.add( aObserver );
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.observer.ISubject#removeObserver(org.suggs.sandbox.patterns.behavioural.observer.IObserver)
     */
    public void removeObserver( IObserver aObserver )
    {
        LOG.debug( "Removing observer" );
        mObservers_.remove( aObserver );
    }

    /**
     * Native method required as a part of API
     */
    public void measurementsChanged()
    {
        notifyObservers();
    }

    /**
     * Setter for the measurements
     * 
     * @param aTemp
     *            the temperature to set
     * @param aHumidity
     *            the humidity to set
     * @param aPressure
     *            the pressure to set
     */
    public void setMeasurements( float aTemp, float aHumidity, float aPressure )
    {
        mTemperature_ = aTemp;
        mHumidity_ = aHumidity;
        mPressure_ = aPressure;
        measurementsChanged();
    }

}
