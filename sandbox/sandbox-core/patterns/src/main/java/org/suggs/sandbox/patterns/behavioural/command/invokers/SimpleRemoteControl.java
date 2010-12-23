/*
 * SimpleRemoteControl.java created on 29 Aug 2007 17:10:54 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.invokers;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation where only one command is allowed
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class SimpleRemoteControl {

    private static final Logger LOG = LoggerFactory.getLogger( SimpleRemoteControl.class );

    private ICommand slot;

    /**
     * Constructs a new instance.
     */
    public SimpleRemoteControl() {}

    /**
     * Setter for the command
     * 
     * @param aCommand
     *            the command to set
     */
    public void setCommand( ICommand aCommand ) {
        slot = aCommand;
    }

    /**
     * This method is called when the button is pressed
     */
    public void buttonWasPressed() {
        LOG.debug( "Button pressed" );
        slot.execute();
    }

}
