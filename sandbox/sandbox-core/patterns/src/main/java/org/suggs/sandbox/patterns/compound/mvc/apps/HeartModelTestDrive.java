/*
 * HeartModelTestDrive.java created on 26 Sep 2007 07:11:12 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.compound.mvc.apps;

import org.suggs.sandbox.patterns.compound.mvc.IController;
import org.suggs.sandbox.patterns.compound.mvc.IHeartModel;
import org.suggs.sandbox.patterns.compound.mvc.controller.HeartController;
import org.suggs.sandbox.patterns.compound.mvc.model.HeartModel;

/**
 * TODO Write javadoc for HeartModelTestDrive
 * 
 * @author suggitpe
 * @version 1.0 26 Sep 2007
 */
public class HeartModelTestDrive
{

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        IHeartModel model = new HeartModel();

        @SuppressWarnings("unused")
        IController ctrl = new HeartController( model );
    }

}
