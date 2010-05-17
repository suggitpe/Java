/*
 * IState.java created on 10 Sep 2007 18:08:25 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.io.Serializable;

/**
 * High level state object for the gumball machine.
 * 
 * @author suggitpe
 * @version 1.0 10 Sep 2007
 */
public interface IState extends Serializable {

    /**
     * Insert a quarter into the gumball machine
     */
    void insertQuarter();

    /**
     * Eject a quarter that is already in the machine
     */
    void ejectQuarter();

    /**
     * Turn the crank on the machine
     */
    void turnCrank();

    /**
     * Dispense a gumball
     */
    void dispense();

}
