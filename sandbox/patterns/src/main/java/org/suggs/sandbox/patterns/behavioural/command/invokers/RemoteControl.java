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
public class RemoteControl
{

    private static final Log LOG = LogFactory.getLog( RemoteControl.class );

    private static final int NUM_CMDS = 7;

    private ICommand[] mOnCommands_ = new ICommand[NUM_CMDS];
    private ICommand[] mOffCommands_ = new ICommand[NUM_CMDS];

    private ICommand mUndoCommand_;

    /**
     * Constructs a new instance.
     */
    public RemoteControl()
    {
        ICommand noCommand = new ICommand()
        {

            public void execute()
            {
                LOG.debug( "Command not implemented" );
            }

            public void undo()
            {
                LOG.debug( "Command not implemented" );
            }

        };

        for ( int i = 0; i < NUM_CMDS; ++i )
        {
            mOnCommands_[i] = noCommand;
            mOffCommands_[i] = noCommand;
        }

        mUndoCommand_ = noCommand;
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
    public void setCommand( int aSlot, ICommand aOnCommand, ICommand aOffCommand )
    {
        mOnCommands_[aSlot] = aOnCommand;
        mOffCommands_[aSlot] = aOffCommand;
    }

    /**
     * Initiator for the button that was pushed
     * 
     * @param aSlot
     *            the slot that the button lives in
     */
    public void onButtonWasPushed( int aSlot )
    {
        mOnCommands_[aSlot].execute();
        mUndoCommand_ = mOnCommands_[aSlot];
    }

    /**
     * Initiator for the button that was pushed
     * 
     * @param aSlot
     *            the slot that the button belongs to
     */
    public void offButtonWasPushed( int aSlot )
    {
        mOffCommands_[aSlot].execute();
        mUndoCommand_ = mOffCommands_[aSlot];
    }

    /**
     * Initiator for the undo button being pressed
     */
    public void undoButtonWasPressed()
    {
        mUndoCommand_.undo();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buff = new StringBuffer();

        buff.append( "\n-------- Remote Control --------\n" );
        for ( int i = 0; i < NUM_CMDS; ++i )
        {
            buff.append( "[slot " + i + "] " + mOnCommands_[i].getClass().getSimpleName() + " "
                         + mOffCommands_[i].getClass().getSimpleName() + "\n" );
        }
        return buff.toString();
    }
}
