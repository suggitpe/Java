/*
 * CeilingFanOnCommmand.java created on 31 Aug 2007 06:00:43 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.receivers.CeilingFan;

/**
 * Class to represent a ceiling fan on command
 * 
 * @author suggitpe
 * @version 1.0 31 Aug 2007
 */
public class CeilingFanOnCommmand implements ICommand
{

    private CeilingFan mFan_;

    /**
     * Constructs a new instance.
     * 
     * @param aFan
     *            a ceiling fan
     */
    public CeilingFanOnCommmand( CeilingFan aFan )
    {
        mFan_ = aFan;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute()
    {
        mFan_.medium();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo()
    {
        mFan_.off();
    }

}
