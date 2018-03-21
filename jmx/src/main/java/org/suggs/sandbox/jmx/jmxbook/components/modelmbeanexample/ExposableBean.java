/*
 * ExposableBen.java created on 23 Apr 2008 06:29:58 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.modelmbeanexample;

import org.suggs.sandbox.jmx.support.ModelMBeanInfoBuilder;

import java.io.Serializable;

import javax.management.Descriptor;
import javax.management.MBeanOperationInfo;
import javax.management.modelmbean.ModelMBeanInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple bean class that we will expose through a model mbean. This class also contains a static
 * method from whihc you can create the model mbean info object.
 */
public class ExposableBean implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger( ExposableBean.class );
    private static final long serialVersionUID = 4094088490297464163L;
    private String myAttribute;

    public ExposableBean() {}

    public String getMyAttribute() {
        return myAttribute;
    }

    public void printMyAttribute() {
        LOG.debug( myAttribute );
    }

    public static ModelMBeanInfo buildModelMBeanInfo() {
        ModelMBeanInfoBuilder builder = new ModelMBeanInfoBuilder();

        Descriptor attDesc = builder.buildAttributeDescriptor( "MyAttribute",
                                                               null,
                                                               "always",
                                                               "10",
                                                               null,
                                                               "getMyAttribute",
                                                               null,
                                                               "10" );
        // now add the attribute to the builder
        builder.addModelMBeanAttribute( "MyAttribute", "java.lang.String", true, false, false, "", attDesc );

        // ** ATTRIBUTE GETTER **
        // create the operation descriptor for the getter
        Descriptor opGetDesc = builder.buildOperationDescriptor( "getMyAttribute",
                                                                 null,
                                                                 "getter",
                                                                 null,
                                                                 null,
                                                                 "org.suggs.sandbox.jmx.jmxbook.components.modelmbeanexample.ExposableBean",
                                                                 "10" );
        // now we add the operation to the builder
        builder.addModelMBeanMethod( "getMyAttribute",
                                     null,
                                     null,
                                     null,
                                     "",
                                     "java.lang.String",
                                     MBeanOperationInfo.INFO,
                                     opGetDesc );

        // ** PRINT METHOD **
        // create the operation descriptor for the print method
        Descriptor opDesc = builder.buildOperationDescriptor( "printMyAttribute",
                                                              null,
                                                              "operation",
                                                              null,
                                                              null,
                                                              "org.suggs.sandbox.jmx.jmxbook.components.modelmbeanexample.ExposableBean",
                                                              "10" );
        // now we add the operation to the builder
        builder.addModelMBeanMethod( "printMyAttribute",
                                     null,
                                     null,
                                     null,
                                     "",
                                     "void",
                                     MBeanOperationInfo.ACTION,
                                     opDesc );

        // ** MBEAN **
        // create the mbean descriptor for the bean itself
        Descriptor mbeanDescriptor = builder.buildMBeanDescriptor( "exposableBean",
                                                                   "",
                                                                   "always",
                                                                   "10",
                                                                   ".",
                                                                   "ExposableBean",
                                                                   null,
                                                                   null );
        // now build the modelMbeanINfo object
        return builder.buildModelMBeanInfo( mbeanDescriptor );
    }

}
