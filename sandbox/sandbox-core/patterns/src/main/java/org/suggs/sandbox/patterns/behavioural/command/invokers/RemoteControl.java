/*
 * RemoteControl.java created on 30 Aug 2007 16:51:30 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.invokers;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Sophisticated command invoker that contains collections of commands
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class RemoteControl {

    private static final Log LOG = LogFactory.getLog( RemoteControl.class );

    private static final int NUM_CMDS = 7;

    private ICommand[] onCommands = new ICommand[NUM_CMDS];
    private ICommand[] offCommands = new ICommand[NUM_CMDS];
    private ICommand undoCommand;

    /**
     * Constructs a new instance.
     */
    public RemoteControl() {
        ICommand noCommand = new ICommand() {

            public void execute() {
                LOG.debug( "Command not implemented" );
            }

            public void undo() {
                LOG.debug( "Command not implemented" );
            }

        };

        for ( int i = 0; i < NUM_CMDS; ++i ) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }

        undoCommand = noCommand;
    }

    /**
     * Setter for the commands
     * 
     * @param aSlot
     *            the slot to add the command to
     * @param aOnCommand
     *            the on command
     * @param aOffCommand
     *            the off command
     */
    public void setCommand( int aSlot, ICommand aOnCommand, ICommand aOffCommand ) {
        onCommands[aSlot] = aOnCommand;
        offCommands[aSlot] = aOffCommand;
    }

    /**
     * Initiator for the button that was pushed
     * 
     * @param aSlot
     *            the slot that the button lives in
     */
    public void onButtonWasPushed( int aSlot ) {
        onCommands[aSlot].execute();
        undoCommand = onCommands[aSlot];
    }

    /**
     * Initiator for the button that was pushed
     * 
     * @param aSlot
     *            the slot that the button belongs to
     */
    public void offButtonWasPushed( int aSlot ) {
        offCommands[aSlot].execute();
        undoCommand = offCommands[aSlot];
    }

    /**
     * Initiator for the undo button being pressed
     */
    public void undoButtonWasPressed() {
        undoCommand.undo();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();

        buff.append( "\n-------- Remote Control --------\n" );
        for ( int i = 0; i < NUM_CMDS; ++i ) {
            buff.append( "[slot " + i + "] " + onCommands[i].getClass().getSimpleName() + " "
                         + offCommands[i].getClass().getSimpleName() + "\n" );
        }
        return buff.toString();
    }
}
