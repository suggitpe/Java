/*
 * MacroCommand.java created on 31 Aug 2007 06:28:23 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;

import java.util.Arrays;

/**
 * A command that will execute a colection of commands
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class MacroCommand implements ICommand {

    private ICommand[] commands;

    /**
     * Constructs a new instance.
     * 
     * @param aCommands
     *            an array of commands to execute
     */
    public MacroCommand( ICommand[] aCommands ) {
        if ( aCommands != null ) {
            commands = Arrays.copyOf( aCommands, aCommands.length );
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute() {
        for ( ICommand cmd : commands ) {
            cmd.execute();
        }
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo() {
        for ( ICommand cmd : commands ) {
            cmd.undo();
        }
    }

}
