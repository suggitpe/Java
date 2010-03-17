/*
 * IState.java created on 23 Jul 2009 19:01:01 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.appointments;

/**
 * High level interface for all of the states
 * 
 * @author suggitpe
 * @version 1.0 23 Jul 2009
 */
public interface IState
{

    /**
     * Save an appointment
     */
    void save();

    /**
     * Edit an appointment
     */
    void edit();

}
