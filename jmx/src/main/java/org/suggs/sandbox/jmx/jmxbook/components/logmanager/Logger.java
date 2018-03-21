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

import org.slf4j.LoggerFactory;

/**
 * Implementation of a logging mechanism.
 */
public class Logger implements LoggerMBean, MBeanRegistration {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Logger.class);
    static final int ALL = 3;
    static final int ERRORS = 2;
    static final int NONE = 1;

    private PrintWriter output;
    private int logLevel = Logger.ALL;

    public Logger() {
        try {
            output = new PrintWriter(new FileOutputStream("record.log"));
        } catch (FileNotFoundException fnf) {
            ExceptionUtil.printException(fnf);
        }
    }

    @Override
    public int getLogLevel() {
        return logLevel;
    }

    @Override
    public String retrieveLog(int linesBack) {
        // must pop an impl in here at some point
        return null;
    }

    @Override
    public void setLogLevel(int level) {
        logLevel = level;
    }

    @Override
    public void writeLog(String message, int type) {
        if (type <= logLevel) {
            output.println(message);
        }
    }

    @Override
    public void postDeregister() {
    }

    @Override
    public void postRegister(Boolean registrationDone) {
    }

    @Override
    public void preDeregister() {
    }

    @Override
    public ObjectName preRegister(MBeanServer server, ObjectName name) {
        LOG.debug("Running the preRegister Logger impl");
        try {
            String svrName = JmxBookConfig.getInstance().getCfgProperty(JmxBookConfig.MBEAN_SERVERNAME);
            ObjectName name1 = new ObjectName(svrName + ":name=propertyManager");
            Object[] params = new Object[]{"loglevel"};
            String[] sig = new String[]{"java.lang.String"};

            String value = (String) server.invoke(name1, "getProperty", params, sig);

            logLevel = Integer.parseInt(value);
        } catch (Exception e) {
            ExceptionUtil.printException(e);
            logLevel = 0;
        }
        return name;
    }
}
