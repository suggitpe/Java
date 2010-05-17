/*
 * DJTestDrive.java created on 25 Sep 2007 20:40:44 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.apps;

import org.suggs.sandbox.patterns.compound.mvc.IBeatModel;
import org.suggs.sandbox.patterns.compound.mvc.controller.BeatController;
import org.suggs.sandbox.patterns.compound.mvc.model.BeatModel;

/**
 * Starter application
 * 
 * @author suggitpe
 * @version 1.0 25 Sep 2007
 */
public class DJTestDrive {

    /**
     * @param args
     */
    public static void main( String[] args ) {
        IBeatModel model = new BeatModel();

        new BeatController( model );
    }

}
