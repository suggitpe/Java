/*
 * IController.java created on 25 Sep 2007 20:21:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * Controller interface.
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2007
 */
public interface IController {

    /**
     * Start beats
     */
    void start();

    /**
     * Stop beats
     */
    void stop();

    /**
     * Increase the beats per minute
     */
    void increaseBpm();

    /**
     * Decrease the beats per minute
     */
    void decreaseBpm();

    /**
     * Setter for the beats per minute
     * 
     * @param aBpm
     *            the beats per minute to set
     */
    void setBpm( int aBpm );

}
