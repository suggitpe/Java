/*
 * RmiMbeanDeployer.java created on 15 Feb 2008 07:26:24 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.client;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class that will allow you to connect to an MBean server and deploy
 * an MBean
 * 
 * @author suggitpe
 * @version 1.0 15 Feb 2008
 */
public class RmiMbeanDeployer
{

    private static final Log LOG = LogFactory.getLog( RmiMbeanDeployer.class );

    /**
     * deploys the hello wworld mbean to the mbean server
     */
    public static void deployHelloWorldBean()
    {
        LOG.debug( "Deploying hello world mbean" );
        try
        {
            MBeanServerConnection client = RmiClientFactory.getClient();
            ObjectName name = new ObjectName( "JmxBookAgent:name=helloWorld" );

            client.createMBean( "org.suggs.sandbox.jmx.helloworld.HelloWorld", name );
            client.invoke( name, "printGreeting", null, null );
        }
        catch ( MalformedObjectNameException e )
        {
            LOG.error( "Badly formed object name:" + e.getMessage() );
            ExceptionUtil.printException( e );
        }
        catch ( IOException ioe )
        {
            LOG.error( "Problem creating RMI Mbean server client:" + ioe.getMessage() );
            ExceptionUtil.printException( ioe );
        }
        catch ( Exception e )
        {
            LOG.error( "Problem with JMX RMI: " + e.getMessage() );
            ExceptionUtil.printException( e );
        }
    }

    /**
     * Main method
     * 
     * @param aArgs
     *            runtime args
     */
    public static void main( String[] aArgs )
    {
        RmiMbeanDeployer.deployHelloWorldBean();
    }

}
