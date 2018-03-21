/*
 * PropertyManager.java created on 20 Feb 2008 19:20:37 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.propertymanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * MBean managed property manager
 */
public class PropertyManager implements PropertyManagerMBean {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyManager.class);
    private Properties properties;

    // non static initialiser
    {
        properties = new Properties();
    }

    public PropertyManager(String aPath) {
        LOG.debug("Loading property file [" + aPath + "]");
        try {
            InputStream is = PropertyManager.class.getClassLoader().getResourceAsStream(aPath);
            properties.load(is);
            is.close();
        } catch (Exception e) {
            LOG.error("Failed to load properties into propertymanager", e);
        }
    }

    @Override
    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    @Override
    public void setProperty(String name, String value) {
        properties.setProperty(name, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Enumeration keys() {
        return properties.keys();
    }

    @Override
    public void setSource(String path) {
        LOG.debug("Reloading properies from file [" + path + "]");
        FileInputStream fis = null;
        try {
            properties = new Properties();
            fis = new FileInputStream(path);
            properties.load(fis);
        } catch (Exception e) {
            LOG.error("Failed to reload properties into manager", e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ioe) {
                LOG.error("Failed to close file input stream:", ioe);
            }
        }
    }

}
