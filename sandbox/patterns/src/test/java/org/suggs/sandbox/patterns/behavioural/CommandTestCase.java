/*
 * CommandTestCase.java created on 29 Aug 2007 06:33:36 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural;

import org.suggs.sandbox.patterns.AbstractPatternTestCase;
import org.suggs.sandbox.patterns.behavioural.command.ICommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.CeilingFanOffCommmand;
import org.suggs.sandbox.patterns.behavioural.command.commands.CeilingFanOnCommmand;
import org.suggs.sandbox.patterns.behavioural.command.commands.GarageDoorCloseCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.GarageDoorOpenCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.LightOffCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.LightOnCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.MacroCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.StereoOffCommand;
import org.suggs.sandbox.patterns.behavioural.command.commands.StereoOnWithCdCommand;
import org.suggs.sandbox.patterns.behavioural.command.invokers.RemoteControl;
import org.suggs.sandbox.patterns.behavioural.command.invokers.SimpleRemoteControl;
import org.suggs.sandbox.patterns.behavioural.command.receivers.CeilingFan;
import org.suggs.sandbox.patterns.behavioural.command.receivers.GarageDoor;
import org.suggs.sandbox.patterns.behavioural.command.receivers.Light;
import org.suggs.sandbox.patterns.behavioural.command.receivers.Stereo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Test case for the command pattern
 * 
 * @author suggitpe
 * @version 1.0 29 Aug 2007
 */
public class CommandTestCase extends AbstractPatternTestCase
{

    private static final Log LOG = LogFactory.getLog( CommandTestCase.class );

    @Test
    public void testSimpleRemoteControl()
    {
        SimpleRemoteControl ctrl = new SimpleRemoteControl();

        Light l = new Light( "Kitchen" );
        LightOnCommand lCmd = new LightOnCommand( l );

        GarageDoor d = new GarageDoor( "Garage" );
        GarageDoorOpenCommand gCmd = new GarageDoorOpenCommand( d );

        LOG.debug( "Pressing button to turn on light" );
        ctrl.setCommand( lCmd );
        ctrl.buttonWasPressed();

        LOG.debug( "Pressing button to open garage door" );
        ctrl.setCommand( gCmd );
        ctrl.buttonWasPressed();
    }

    @Test
    public void testRemoteControl()
    {
        RemoteControl ctrl = buildRemoteControl();
        LOG.debug( ctrl );
    }

    @Test
    public void testRemoteControlOnOff()
    {
        RemoteControl ctrl = buildRemoteControl();

        ctrl.onButtonWasPushed( 0 );
        ctrl.offButtonWasPushed( 0 );
        ctrl.onButtonWasPushed( 1 );
        ctrl.offButtonWasPushed( 1 );
        ctrl.onButtonWasPushed( 2 );
        ctrl.offButtonWasPushed( 2 );
        ctrl.onButtonWasPushed( 3 );
        ctrl.offButtonWasPushed( 3 );
        ctrl.onButtonWasPushed( 4 );
        ctrl.offButtonWasPushed( 4 );
    }

    @Test
    public void testRemoteControlUndo()
    {
        RemoteControl ctrl = buildRemoteControl();

        ctrl.undoButtonWasPressed();

        ctrl.onButtonWasPushed( 0 );
        ctrl.undoButtonWasPressed();

        ctrl.onButtonWasPushed( 3 );
        ctrl.offButtonWasPushed( 3 );
        ctrl.undoButtonWasPressed();

    }

    @Test
    public void testMacro()
    {
        RemoteControl ctrl = buildRemoteControl();

        ctrl.onButtonWasPushed( 6 );
        LOG.debug( "-------------------" );
        ctrl.offButtonWasPushed( 6 );
    }

    /**
     * Builds an instance of the remote control
     * 
     * @return a new Remote Control
     */
    private RemoteControl buildRemoteControl()
    {
        RemoteControl ctrl = new RemoteControl();

        // build receivers
        Light livingRoomLight = new Light( "Living room" );
        Light kitchenLight = new Light( "Kitchen" );
        CeilingFan cFan = new CeilingFan( "Living room" );
        GarageDoor gDoor = new GarageDoor( "Garage" );
        Stereo stereo = new Stereo( "Living room" );

        // build commands
        LightOnCommand livLightOn = new LightOnCommand( livingRoomLight );
        LightOffCommand livLightOff = new LightOffCommand( livingRoomLight );

        LightOnCommand kitLightOn = new LightOnCommand( kitchenLight );
        LightOffCommand kitLightOff = new LightOffCommand( kitchenLight );

        CeilingFanOnCommmand fanOn = new CeilingFanOnCommmand( cFan );
        CeilingFanOffCommmand fanOff = new CeilingFanOffCommmand( cFan );

        GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand( gDoor );
        GarageDoorCloseCommand garageClose = new GarageDoorCloseCommand( gDoor );

        StereoOnWithCdCommand stereoOn = new StereoOnWithCdCommand( stereo );
        StereoOffCommand stereoOff = new StereoOffCommand( stereo );

        MacroCommand macroOn = new MacroCommand( new ICommand[] { livLightOn, kitLightOn, fanOn,
                                                                 stereoOn } );
        MacroCommand macroOff = new MacroCommand( new ICommand[] { livLightOff, kitLightOff,
                                                                  fanOff, stereoOff } );

        ctrl.setCommand( 0, livLightOn, livLightOff );
        ctrl.setCommand( 1, kitLightOn, kitLightOff );
        ctrl.setCommand( 2, fanOn, fanOff );
        ctrl.setCommand( 3, garageOpen, garageClose );
        ctrl.setCommand( 4, stereoOn, stereoOff );

        ctrl.setCommand( 6, macroOn, macroOff );

        return ctrl;
    }
}
