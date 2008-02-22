/*
 * PropertyManagerSetup.java created on 20 Feb 2008 19:47:17 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.configmanager;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;
import org.suggs.sandbox.jmx.jmxbook.client.RmiClientFactory;
import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class will interact with the MBean server to register a new
 * mbean for the property manager.
 * 
 * @author suggitpe
 * @version 1.0 20 Feb 2008
 */
public class MBeanDeploymentSetup
{

    private static final Log LOG = LogFactory.getLog( MBeanDeploymentSetup.class );

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
            conn.createMBean( "org.suggs.sandbox.jmx.configmanager.propertymanager.PropertyManager",
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
            conn.createMBean( "org.suggs.sandbox.jmx.configmanager.logmanager.Logger", loggerName );
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
     * @param args
     *            runtime arguments
     */
    public static void main( String[] args )
    {
        LOG.debug( "Deploying propertyManager ..." );
        MBeanDeploymentSetup.deployPropertyManager();

        LOG.debug( "Deploying Logger ..." );
        MBeanDeploymentSetup.deployLogger();
    }
}
