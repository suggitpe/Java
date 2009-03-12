/*
 * BundleCounterActivator.java created on 10 Mar 2009 07:32:57 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */
package org.suggs.sandbox_osgi.tutorial.basics.bundlecounter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * Bundle that will count the number of active bundles in the OSGI
 * framework
 * 
 * @author suggitpe
 * @version 1.0 10 Mar 2009
 */
public class BundleCounterActivator implements BundleActivator, BundleListener
{

    private BundleContext mContext_;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        mContext_ = ctx;
        mContext_.addBundleListener( this );
        printBundleCount();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        mContext_.removeBundleListener( this );
        mContext_ = null;
    }

    /**
     * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
     */
    public void bundleChanged( BundleEvent evt )
    {
        switch ( evt.getType() )
        {
            case BundleEvent.INSTALLED:
                System.out.println( "Bundle installed" );
                printBundleCount();
                break;
            case BundleEvent.UNINSTALLED:
                System.out.println( "Bundle uninstalled" );
                printBundleCount();
                break;

        }
    }

    /**
     * Simple method that will print out the bundle count in the OSGI
     * framework
     */
    private void printBundleCount()
    {
        System.out.println( "There are currently " + mContext_.getBundles().length + " bundles" );
    }

}
