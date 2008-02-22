/*
 * RmiMbeanDeployer.java created on 15 Feb 2008 07:26:24 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.client;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;
import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

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
            String svrName = JmxBookConfig.getInstance()
                .getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            ObjectName name = new ObjectName( svrName + ":name=helloWorld" );

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
     * Constructs a new instance.
     */
    public static void deployPropertyManager()
    {
        LOG.debug( "Running the property manager setup application" );
        try
        {
            // get a connection to the MBean server
            MBeanServerConnection conn = RmiClientFactory.getClient();

            // create the object name
            String svrName = JmxBookConfig.getInstance()
                .getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            ObjectName propertyName = new ObjectName( svrName + ":name=propertyManager" );

            // now create the MBean uing the correct parameters for
            // the ctor
            conn.createMBean( "org.suggs.sandbox.jmx.jmxbook.components.propertymanager.PropertyManager",
                              propertyName,
                              new Object[] { new String( "propertyManager.properties" ) },
                              new String[] { "java.lang.String" } );
        }
        catch ( IOException ioe )
        {
            LOG.error( "Failed to connect to the MBean server" );
            ExceptionUtil.printException( ioe );
        }
        catch ( MalformedObjectNameException mon )
        {
            LOG.error( "Badly named object name for MBean server" );
            ExceptionUtil.printException( mon );
        }
        catch ( Exception e )
        {
            LOG.error( "Failed to create MBean on the remote server" );
            ExceptionUtil.printException( e );
        }
    }

    public static void deployLogger()
    {
        LOG.debug( "Running the logger setup application" );
        try
        {
            // get a connection to the MBean server
            MBeanServerConnection conn = RmiClientFactory.getClient();

            // create the object name
            String svrName = JmxBookConfig.getInstance()
                .getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            ObjectName loggerName = new ObjectName( svrName + ":name=logger" );

            // now create the MBean using the correct parameters for
            // the ctor
            conn.createMBean( "org.suggs.sandbox.jmx.jmxbook.components.logmanager.Logger",
                              loggerName );
        }
        catch ( IOException ioe )
        {
            LOG.error( "Failed to connect to the MBean server" );
            ExceptionUtil.printException( ioe );
        }
        catch ( MalformedObjectNameException mon )
        {
            LOG.error( "Badly named object name for MBean server" );
            ExceptionUtil.printException( mon );
        }
        catch ( Exception e )
        {
            LOG.error( "Failed to create MBean on the remote server" );
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
        LOG.debug( "Deploying propertyManager ..." );
        RmiMbeanDeployer.deployPropertyManager();
        LOG.debug( "Deploying Logger ..." );
        RmiMbeanDeployer.deployLogger();
    }

}
