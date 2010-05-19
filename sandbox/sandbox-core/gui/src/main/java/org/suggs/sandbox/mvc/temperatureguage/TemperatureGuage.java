/*
 * TemperatureGuage.java created on 9 Jul 2007 08:42:35 by suggitpe for project SandBox - GUI
 * 
 */
package org.suggs.sandbox.mvc.temperatureguage;

import org.suggs.sandbox.mvc.temperatureguage.model.TemperatureModel;
import org.suggs.sandbox.mvc.temperatureguage.view.CelsiusGUI;
import org.suggs.sandbox.mvc.temperatureguage.view.FarenheightGUI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Temperature guage impl for a MVC demo
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public final class TemperatureGuage {

    private static final Log LOG = LogFactory.getLog( TemperatureGuage.class );

    private TemperatureGuage() {}

    /**
     * @param args
     */
    public static void main( String[] args ) {
        LOG.debug( "Starting GUI" );
        TemperatureModel model = new TemperatureModel();
        new FarenheightGUI( model, 100, 100 );
        new CelsiusGUI( model, 100, 250 );
    }

}
