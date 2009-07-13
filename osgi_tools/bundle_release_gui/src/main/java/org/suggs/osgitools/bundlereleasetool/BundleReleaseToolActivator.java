/*
 * BundleReleaseToolActivator.java created on 1 Jul 2009 19:46:35 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

import org.suggs.osgitools.bundlereleasetool.GUI.BundleReleaseToolGui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.Bundle;
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

    private BundleContext mCtx_;

    // this is where we encapsulate all of the bundle related
    // activity
    private IBundleReleaseToolContextCallback mCallback = new IBundleReleaseToolContextCallback()
    {

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#buildWindowListener()
         */
        @Override
        public WindowListener buildWindowListener()
        {
            return new WindowAdapter()
            {

                /**
                 * Stop the underlying bundle as the window closes.
                 * 
                 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
                 */
                @Override
                public void windowClosing( WindowEvent we )
                {
                    try
                    {
                        LOG.debug( "Stopping bundle" );
                        mCtx_.getBundle().stop();
                    }
                    catch ( BundleException be )
                    {
                        // nadda
                    }
                }
            };
        }

        /**
         * Installs the bundle with the given URI
         * 
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#installBundle(java.lang.String)
         */
        @Override
        public boolean installBundle( String uri )
        {
            try
            {
                mCtx_.installBundle( uri );
                LOG.debug( "Installing bundle from [" + uri + "]" );
            }
            catch ( BundleException be )
            {
                LOG.error( "Failed to release bundle", be );
                return false;
            }
            return true;
        }

        /**
         * Interrogates the bundle context and retrieves the bundles
         * that are of use to us
         * 
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#getBundleData()
         */
        @Override
        public Vector<BundleData> getBundleData()
        {
            Vector<BundleData> ret = new Vector<BundleData>();

            for ( Bundle bundle : mCtx_.getBundles() )
            {
                ret.add( new BundleData( bundle.getBundleId(),
                                         getBundleState( bundle.getState() ),
                                         bundle.getLocation(),
                                         bundle.getSymbolicName() ) );
            }

            return ret;
        }

        private String getBundleState( int aState )
        {
            switch ( aState )
            {
                case Bundle.UNINSTALLED:
                    return "Uninstalled";
                case Bundle.INSTALLED:
                    return "Installed";
                case Bundle.RESOLVED:
                    return "Resolved";
                case Bundle.STARTING:
                    return "Starting";
                case Bundle.STOPPING:
                    return "Stopping";
                case Bundle.ACTIVE:
                    return "Active";
                default:
                    return "Unknown";
            }
        }
    };

    /**
     * Start the GUI and listen for it being closed by the user.
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( final BundleContext aCtx ) throws Exception
    {
        LOG.debug( "Starting the Bundle Release Tool GUI" );
        mCtx_ = aCtx;
        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        // bit confusing here as we are using the callback to pass in
        // all of the bundle related functionality
        new BundleReleaseToolGui( mCallback );
    }

    /**
     * Stop the GUI
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( final BundleContext aCtx ) throws Exception
    {
        LOG.debug( "Stopping the Bundle Release Tool GUI" );
        mCtx_ = null;
    }

}
