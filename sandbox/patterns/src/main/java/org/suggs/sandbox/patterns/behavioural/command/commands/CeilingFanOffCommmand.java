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
public class CeilingFanOffCommmand implements ICommand
{

    private CeilingFan mFan_;

    /**
     * Constructs a new instance.
     * 
     * @param aFan
     *            a ceiling fan
     */
    public CeilingFanOffCommmand( CeilingFan aFan )
    {
        mFan_ = aFan;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute()
    {
        mFan_.off();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo()
    {
        mFan_.medium();
    }

}
