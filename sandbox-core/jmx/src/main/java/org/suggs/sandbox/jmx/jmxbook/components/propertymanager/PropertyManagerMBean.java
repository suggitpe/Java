/*
 * PropertyManagerMBean.java created on 20 Feb 2008 19:18:14 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.propertymanager;

import java.util.Enumeration;

/**
 * MBean interface for setting properties
 * 
 * @author suggitpe
 * @version 1.0 20 Feb 2008
 */
public interface PropertyManagerMBean {

    /**
     * Getter for a string property
     * 
     * @param aName
     *            the name of the property
     * @return the string property with the name aName
     */
    public String getProperty( String aName );

    /**
     * Setter for a property
     * 
     * @param aName
     * @param aValue
     */
    public void setProperty( String aName, String aValue );

    /**
     * Getter for the keys
     * 
     * @return an Enumeration of keys
     */
    public Enumeration<String> keys();

    /**
     * Setter for the source
     * 
     * @param aPath
     *            the path of the source File
     */
    public void setSource( String aPath );

}
