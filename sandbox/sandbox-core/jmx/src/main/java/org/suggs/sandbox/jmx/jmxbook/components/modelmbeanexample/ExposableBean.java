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

/**
 * This is a simple bean class that we will expose through a model
 * mbean. This class also contains a static method from whihc you can
 * create the model mbean info object.
 * 
 * @author suggitpe
 * @version 1.0 23 Apr 2008
 */
public class ExposableBean implements Serializable
{

    private String mMyAttribute_;

    /**
     * Constructs a new instance.
     */
    public ExposableBean()
    {
    }

    /**
     * Getter for the internal attribute
     * 
     * @return the internal attribute
     */
    public String getMyAttribute()
    {
        return mMyAttribute_;
    }

    /**
     * Prints the internal attribute to the stdout
     */
    public void printMyAttribute()
    {
        System.out.println( mMyAttribute_ );
    }

    /**
     * Creates a model mbean info object for the exposable bean class.
     * 
     * @return the model mbean info object
     */
    public static ModelMBeanInfo buildModelMBeanInfo()
    {
        ModelMBeanInfoBuilder builder = new ModelMBeanInfoBuilder();

        // ** ATTRIBUTE **
        // create the attribute descriptor
        Descriptor attDesc = builder.buildAttributeDescriptor( "MyAttribute",
                                                               null,
                                                               "always",
                                                               "10",
                                                               null,
                                                               "getMyAttribute",
                                                               null,
                                                               "10" );
        // now add the attribute to the builder
        builder.addModelMBeanAttribute( "MyAttribute",
                                        "java.lang.String",
                                        true,
                                        false,
                                        false,
                                        "",
                                        attDesc );

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
