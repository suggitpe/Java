/*
 * PropertyManagerMBean.java created on 20 Feb 2008 19:18:14 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.propertymanager;

import java.util.Enumeration;

/**
 * MBean interface for setting properties
 */
public interface PropertyManagerMBean {

    public String getProperty(String aName);

    public void setProperty(String aName, String aValue);

    public Enumeration<String> keys();

    public void setSource(String aPath);

}
