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
public class StereoOffCommand implements ICommand {

    private Stereo stereo;

    /**
     * Constructs a new instance.
     * 
     * @param aStereo
     */
    public StereoOffCommand( Stereo aStereo ) {
        stereo = aStereo;
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#execute()
     */
    @Override
    public void execute() {
        stereo.off();
    }

    /**
     * @see org.suggs.sandbox.patterns.behavioural.command.ICommand#undo()
     */
    @Override
    public void undo() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume( 5 );
    }

}
