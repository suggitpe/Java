/*
 * HelloWorldActivator.java created on 17 Feb 2009 08:07:03 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */
package org.suggs.sandbox_osgi.tutorial.basics.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator for the Hello World stuff
 * 
 * @author suggitpe
 * @version 1.0 17 Feb 2009
 */
public class HelloWorldActivator implements BundleActivator
{

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext aArg0 ) throws Exception
    {
        System.out.println( "Hello world" );
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext aArg0 ) throws Exception
    {
        System.out.println( "Good-bye world" );
    }

}
