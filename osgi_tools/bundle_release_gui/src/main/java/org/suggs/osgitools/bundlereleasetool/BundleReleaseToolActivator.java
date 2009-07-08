/*
 * BundleReleaseToolActivator.java created on 1 Jul 2009 19:46:35 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

import org.suggs.osgitools.bundlereleasetool.GUI.BundleReleaseToolGui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Activator for the Bundle Release Tool GUI so that we can activate
 * it through the OSGi state change.
 * 
 * @author suggitpe
 * @version 1.0 1 Jul 2009
 */
public class BundleReleaseToolActivator implements BundleActivator
{

    private static final Log LOG = LogFactory.getLog( BundleReleaseToolActivator.class );

    private BundleReleaseToolGui mGui_;

    /**
     * Start the GUI and listen for it being closed by the user.
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( final BundleContext aCtx ) throws Exception
    {
        LOG.debug( "Starting the Bundle Release Tool GUI" );
        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        mGui_ = new BundleReleaseToolGui();
        // this is needed so we can ensure that the JVM is correctly
        // shutdown when the user quits.
        mGui_.buildGui( new WindowAdapter()
        {

            /**
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosing( WindowEvent we )
            {
                try
                {
                    aCtx.getBundle().stop();
                }
                catch ( BundleException be )
                {
                    // nadda
                }
            }
        } );
    }

    /**
     * Stop the GUItart
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( final BundleContext aCtx ) throws Exception
    {
        LOG.debug( "Stopping the Bundle Release Tool GUI" );
    }

}
