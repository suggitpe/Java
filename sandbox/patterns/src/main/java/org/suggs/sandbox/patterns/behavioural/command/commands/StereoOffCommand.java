/*
 * StereoOffCommand.java created on 30 Aug 2007 17:11:56 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.command.commands;

import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.receivers.Stereo;

/**
 * Class to represent the command to turn off the stereo
 * 
 * @author suggitpe
 * @version 1.0 30 Aug 2007
 */
public class StereoOffCommand implements ICommand
{

    private Stereo mStereo_;

    /**
     * Constructs a new instance.
     */
    public StereoOffCommand( Stereo aStereo )
    {
        mStereo_ = aStereo;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    public void execute()
    {
        mStereo_.off();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    public void undo()
    {
        mStereo_.on();
        mStereo_.setCd();
        mStereo_.setVolume( 5 );
    }

}
