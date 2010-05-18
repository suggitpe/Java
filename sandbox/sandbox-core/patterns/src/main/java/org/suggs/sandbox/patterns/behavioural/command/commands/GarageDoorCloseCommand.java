/*
 * GarageDoorCloseCommand.java created on 30 Aug 2007 06:16:35 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.receivers.GarageDoor;

/**
 * Command for closing the garage door
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class GarageDoorCloseCommand implements ICommand {

    private GarageDoor door;

    /**
     * Constructs a new instance.
     * 
     * @param aDoor
     *            the door to set
     */
    public GarageDoorCloseCommand( GarageDoor aDoor ) {
        door = aDoor;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute() {
        door.lightOff();
        door.down();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo() {
        door.up();
        door.lightOn();
    }

}
