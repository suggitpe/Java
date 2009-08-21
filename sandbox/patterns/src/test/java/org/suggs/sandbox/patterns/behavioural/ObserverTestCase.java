/*
 * ObserverTestCase.java created on 28 Aug 2007 06:33:31 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.observer.observer.CurrentConditionsDisplay;
import org.suggs.sandbox.patterns.behavioural.observer.observer.StatisticsDisplay;
import org.suggs.sandbox.patterns.behavioural.observer.subject.WeatherData;

import org.junit.Test;

/**
 * Tests for the observer pattern impl.
 * 
 * @author suggitpe
 * @version 1.0 28 Aug 2007
 */
public class ObserverTestCase extends AbstractPatternTestCase
{

    @Test
    public void testWeatherStation()
    {
        WeatherData data = new WeatherData();

        @SuppressWarnings("unused")
        CurrentConditionsDisplay disp = new CurrentConditionsDisplay( data );
        @SuppressWarnings("unused")
        StatisticsDisplay stat = new StatisticsDisplay( data );

        data.setMeasurements( 80, 65, 30.4f );
        data.setMeasurements( 82, 67, 29.2f );
        data.setMeasurements( 78, 63, 27.8f );
    }
}
