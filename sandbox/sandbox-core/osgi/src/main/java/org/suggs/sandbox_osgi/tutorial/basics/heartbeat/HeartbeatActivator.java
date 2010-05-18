package org.suggs.sandbox_osgi.tutorial.basics.heartbeat;

/*
 * HeartbeatActivator.java created on 10 Mar 2009 07:46:13 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator that will spawn a new thread to pump out a heartbeat (ie logging)
 * 
 * @author suggitpe
 * @version 1.0 10 Mar 2009
 */
public class HeartbeatActivator implements BundleActivator {

    private static final Log LOG = LogFactory.getLog( HeartbeatActivator.class );
    private static final long SLEEP_TIME = 5000;
    private Thread thread;

    /**
     * Start the thread
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception {
        thread = new Thread( new Heartbeat() );
        thread.start();
    }

    /**
     * Stop the thread
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception {
        thread.interrupt();
    }

    /**
     * This inner class is purely here to manage the thread contents
     * 
     * @author suggitpe
     * @version 1.0 10 Mar 2009
     */
    private class Heartbeat implements Runnable {

        public void run() {
            try {
                while ( !Thread.interrupted() ) {
                    Thread.sleep( SLEEP_TIME );
                    LOG.debug( "... thread still running" );
                }
            }
            catch ( InterruptedException ie ) {
                LOG.debug( "Interrupt received .. stopping" );
            }

        }
    }

}
