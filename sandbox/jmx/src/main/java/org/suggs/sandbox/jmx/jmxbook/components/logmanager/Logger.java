/*
 * Logger.java created on 21 Feb 2008 08:17:49 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.logmanager;

import org.suggs.sandbox.jmx.jmxbook.ExceptionUtil;
import org.suggs.sandbox.jmx.jmxbook.config.JmxBookConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation of a logging mechanism.
 * 
 * @author suggitpe
 * @version 1.0 21 Feb 2008
 */
public class Logger implements LoggerMBean, MBeanRegistration
{

    private static final Log LOG = LogFactory.getLog( Logger.class );
    static final int ALL = 3;
    static final int ERRORS = 2;
    static final int NONE = 1;

    private PrintWriter mOut_;
    private int mLogLevel = Logger.ALL;

    /**
     * Constructs a new instance.
     */
    public Logger()
    {
        try
        {
            mOut_ = new PrintWriter( new FileOutputStream( "record.log" ) );
        }
        catch ( FileNotFoundException fnf )
        {
            ExceptionUtil.printException( fnf );
        }
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.logmanager.LoggerMBean#getLogLevel()
     */
    public int getLogLevel()
    {
        return mLogLevel;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.logmanager.LoggerMBean#retrieveLog(int)
     */
    public String retrieveLog( int linesBack )
    {
        // must pop an impl in here at some point
        return null;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.logmanager.LoggerMBean#setLogLevel(int)
     */
    public void setLogLevel( int level )
    {
        mLogLevel = level;
    }

    /**
     * @see org.suggs.sandbox.jmx.jmxbook.components.logmanager.LoggerMBean#writeLog(java.lang.String,
     *      int)
     */
    public void writeLog( String message, int type )
    {
        if ( type <= mLogLevel )
        {
            mOut_.println( message );
        }
    }

    /**
     * @see javax.management.MBeanRegistration#postDeregister()
     */
    public void postDeregister()
    {}

    /**
     * @see javax.management.MBeanRegistration#postRegister(java.lang.Boolean)
     */
    public void postRegister( Boolean registrationDone )
    {}

    /**
     * @see javax.management.MBeanRegistration#preDeregister()
     */
    public void preDeregister() throws Exception
    {}

    /**
     * @see javax.management.MBeanRegistration#preRegister(javax.management.MBeanServer,
     *      javax.management.ObjectName)
     */
    public ObjectName preRegister( MBeanServer server, ObjectName name ) throws Exception
    {
        LOG.debug( "Running the preRegister Logger impl" );
        try
        {
            String svrName = JmxBookConfig.getInstance()
                .getCfgProperty( JmxBookConfig.MBEAN_SERVERNAME );
            ObjectName name1 = new ObjectName( svrName + ":name=propertyManager" );
            Object[] params = new Object[] { "loglevel" };
            String[] sig = new String[] { "java.lang.String" };

            String value = (String) server.invoke( name1, "getProperty", params, sig );

            mLogLevel = Integer.parseInt( value );
        }
        catch ( Exception e )
        {
            ExceptionUtil.printException( e );
            mLogLevel = 0;
        }
        return name;
    }
}
