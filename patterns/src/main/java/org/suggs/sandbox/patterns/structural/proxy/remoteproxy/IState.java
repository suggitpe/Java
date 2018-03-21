/*
 * IState.java created on 10 Sep 2007 18:08:25 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.io.Serializable;

/**
 * High level state object for the gumball machine.
 */
public interface IState extends Serializable {

    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();

}
