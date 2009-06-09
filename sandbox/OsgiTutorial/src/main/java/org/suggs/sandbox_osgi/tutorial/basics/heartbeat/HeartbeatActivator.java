package org.suggs.sandbox_osgi.tutorial.basics.heartbeat;

/*
 * HeartbeatActivator.java created on 10 Mar 2009 07:46:13 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator that will spawn a new thread to pump out a heartbeat (ie
 * logging)
 * 
 * @author suggitpe
 * @version 1.0 10 Mar 2009
 */
public class HeartbeatActivator implements BundleActivator
{

    private static final long SLEEP_TIME = 5000;
    private Thread mThread_;

    /**
     * Start the thread
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        mThread_ = new Thread( new Heartbeat() );
        mThread_.start();
    }

    /**
     * Stop the thread
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        mThread_.interrupt();
    }

    /**
     * This inner class is purely here to manage the thread contents
     * 
     * @author suggitpe
     * @version 1.0 10 Mar 2009
     */
    private class Heartbeat implements Runnable
    {

        public void run()
        {
            try
            {
                while ( !Thread.interrupted() )
                {
                    Thread.sleep( SLEEP_TIME );
                    System.out.println( "... thread still running" );
                }
            }
            catch ( InterruptedException ie )
            {
                System.out.println( "Interrupt received .. stopping" );
            }

        }
    }

}
