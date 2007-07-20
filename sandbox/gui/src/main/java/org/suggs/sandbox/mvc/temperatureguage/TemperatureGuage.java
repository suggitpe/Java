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
 * TODO Write javadoc for TemperatureGuage
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2007
 */
public class TemperatureGuage
{

    private static final Log LOG = LogFactory.getLog( TemperatureGuage.class );

    /**
     * @param args
     */
    public static void main( String[] args )
    {
        LOG.debug( "Starting GUI" );
        TemperatureModel model = new TemperatureModel();
        @SuppressWarnings("unused")
        final FarenheightGUI f = new FarenheightGUI( model, 100, 100 );
        @SuppressWarnings("unused")
        final CelsiusGUI c = new CelsiusGUI( model, 100, 250 );
    }

}
