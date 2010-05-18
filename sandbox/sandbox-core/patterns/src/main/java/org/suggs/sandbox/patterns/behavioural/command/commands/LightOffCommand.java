/*
 * LightOffCommand.java created on 29 Aug 2007 17:02:27 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.receivers.Light;

/**
 * Command for turning off a light.
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class LightOffCommand implements ICommand {

    private Light light;

    /**
     * Constructs a new instance.
     * 
     * @param aLight
     *            the command receiver
     */
    public LightOffCommand( Light aLight ) {
        light = aLight;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute() {
        light.off();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo() {
        light.on();
    }

}
