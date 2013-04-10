/*
 * IHeartModel.java created on 25 Sep 2007 20:34:35 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * Heart interface
 */
public interface IHeartModel {

    int getHeartRate();

    void registerObserver( IBeatObserver observer );

    void removeObserver( IBeatObserver observer );

    void registerObserver( IBpmObserver observer );

    void removeObserver( IBpmObserver observer );
}
