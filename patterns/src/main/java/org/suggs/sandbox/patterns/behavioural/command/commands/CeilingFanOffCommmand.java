/*
 * CeilingFanOffCommmand.java created on 31 Aug 2007 06:00:43 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.receivers.CeilingFan;

/**
 * Class to represent a ceiling fan off command
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class CeilingFanOffCommmand implements ICommand {

    private CeilingFan fan;

    /**
     * Constructs a new instance.
     * 
     * @param aFan
     *            a ceiling fan
     */
    public CeilingFanOffCommmand( CeilingFan aFan ) {
        fan = aFan;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    @Override
    public void execute() {
        fan.off();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    @Override
    public void undo() {
        fan.medium();
    }

}
