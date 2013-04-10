/*
 * IController.java created on 25 Sep 2007 20:21:48 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc;

/**
 * Controller interface.
 */
public interface IController {

    void start();

    void stop();

    void increaseBpm();

    void decreaseBpm();

    void setBpm( int aBpm );

}
