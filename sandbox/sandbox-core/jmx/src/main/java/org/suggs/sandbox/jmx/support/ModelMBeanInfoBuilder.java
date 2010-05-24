/*
 * ModelMBeanInfoBuilder.java created on 22 Apr 2008 06:30:35 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.support;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.management.Descriptor;
import javax.management.MBeanParameterInfo;
import javax.management.modelmbean.DescriptorSupport;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanConstructorInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.ModelMBeanInfoSupport;
import javax.management.modelmbean.ModelMBeanNotificationInfo;
import javax.management.modelmbean.ModelMBeanOperationInfo;

/**
 * Support class that allows for the construction of Model MBean Info classes. This class should be used to
 * take the pain out of constructing Model MBeans.
 * 
 * @author suggitpe
 * @version 1.0 22 Apr 2008
 */
public class ModelMBeanInfoBuilder {

    private Map<String, ModelMBeanAttributeInfo> attributes = new Hashtable<String, ModelMBeanAttributeInfo>();
    private Map<String, ModelMBeanNotificationInfo> notifications = new Hashtable<String, ModelMBeanNotificationInfo>();
    @SuppressWarnings("unchecked")
    private Map<Constructor, ModelMBeanConstructorInfo> constructors = new Hashtable<Constructor, ModelMBeanConstructorInfo>();
    private Map<String, ModelMBeanOperationInfo> operations = new Hashtable<String, ModelMBeanOperationInfo>();

    /**
     * Constructs a new instance.
     */
    public ModelMBeanInfoBuilder() {}

    /**
     * Adds a new method to the model MBean operations list. Initially this is simply loaded up into an
     * internal Hashtable but will later be loaded up into the ModelMbeanInfo object.
     * 
     * @param aName
     *            the name of the method
     * @param aParamTypes
     *            the array of parameter types
     * @param aParamNames
     *            the array of parameter names
     * @param aParamDescs
     *            the array of parameter descriptions
     * @param aDescription
     *            description of the method
     * @param aRetType
     *            the return type of the method
     * @param aType
     *            the type of method
     * @param aDescriptor
     *            the method descriptor
     */
    public void addModelMBeanMethod( String aName, String[] aParamTypes, String[] aParamNames,
                                     String[] aParamDescs, String aDescription, String aRetType, int aType,
                                     Descriptor aDescriptor ) {
        MBeanParameterInfo[] params = null;
        if ( aParamTypes != null ) {
            params = new MBeanParameterInfo[aParamTypes.length];
            for ( int i = 0; i < aParamTypes.length; ++i ) {
                params[i] = new MBeanParameterInfo( aParamNames[i], aParamTypes[i], aParamDescs[i] );
            }
        }
        operations.put( aName, new ModelMBeanOperationInfo( aName,
                                                            aDescription,
                                                            params,
                                                            aRetType,
                                                            aType,
                                                            aDescriptor ) );
    }

    /**
     * Adds a new notification type to the internal notifications Hashtable.
     * 
     * @param aType
     *            the type array for the notification
     * @param aClassName
     *            the class name of the notification object
     * @param aDescription
     *            the description of the notification
     * @param aDescriptor
     *            the descriptor of the notification
     */
    public void addModelMBeanNotification( String[] aType, String aClassName, String aDescription,
                                           Descriptor aDescriptor ) {
        notifications.put( aClassName, new ModelMBeanNotificationInfo( aType,
                                                                       aClassName,
                                                                       aDescription,
                                                                       aDescriptor ) );
    }

    /**
     * Adds a new attribute to the internal attributes hash table.
     * 
     * @param aName
     *            the name of the attribute
     * @param aType
     *            the type of the attribute
     * @param aRead
     *            is readable
     * @param aWrite
     *            is writable
     * @param aIs
     *            is a boolean getter
     * @param aDescription
     *            description of the attribute
     * @param aDescriptor
     *            the attribute descriptor
     */
    public void addModelMBeanAttribute( String aName, String aType, boolean aRead, boolean aWrite,
                                        boolean aIs, String aDescription, Descriptor aDescriptor ) {
        attributes.put( aName, new ModelMBeanAttributeInfo( aName,
                                                            aType,
                                                            aDescription,
                                                            aRead,
                                                            aWrite,
                                                            aIs,
                                                            aDescriptor ) );
    }

    /**
     * Adds a new constructor to the internal constructors hash table.
     * 
     * @param aCtor
     *            the constructor to add
     * @param aDescription
     *            the description of the constructor
     * @param aDescriptor
     *            the descriptor of the constructor
     */
    @SuppressWarnings("unchecked")
    public void addModelMBeanConstructor( Constructor aCtor, String aDescription, Descriptor aDescriptor ) {
        constructors.put( aCtor, new ModelMBeanConstructorInfo( aDescription, aCtor, aDescriptor ) );
    }

    /**
     * Constructs a model mbean info object. This method relies on the fact that you have pre-loaded this
     * class with the relevant constructors, operations, attributes and notifications.
     * 
     * @param aDescriptor
     *            the descriptor of the mbean infop object
     * @return the model mbean info object
     */
    public ModelMBeanInfo buildModelMBeanInfo( Descriptor aDescriptor ) {
        ModelMBeanOperationInfo[] ops = new ModelMBeanOperationInfo[operations.size()];
        Vector<ModelMBeanOperationInfo> vo = new Vector<ModelMBeanOperationInfo>( operations.values() );
        vo.copyInto( ops );

        ModelMBeanAttributeInfo[] atts = new ModelMBeanAttributeInfo[attributes.size()];
        Vector<ModelMBeanAttributeInfo> va = new Vector<ModelMBeanAttributeInfo>( attributes.values() );
        va.copyInto( atts );

        ModelMBeanConstructorInfo[] cons = new ModelMBeanConstructorInfo[constructors.size()];
        Vector<ModelMBeanConstructorInfo> vc = new Vector<ModelMBeanConstructorInfo>( constructors.values() );
        vc.copyInto( cons );

        ModelMBeanNotificationInfo[] notifs = new ModelMBeanNotificationInfo[notifications.size()];
        Vector<ModelMBeanNotificationInfo> vn = new Vector<ModelMBeanNotificationInfo>( notifications.values() );
        vn.copyInto( notifs );

        return new ModelMBeanInfoSupport( "javax.management.modelmbean.ModelMBeanInfo",
                                          "description",
                                          atts,
                                          cons,
                                          ops,
                                          notifs,
                                          aDescriptor );
    }

    /**
     * Method to build an attribute descriptor
     * 
     * @param aName
     *            the name of the attribute
     * @param aDisplayName
     *            the display name of the attribute
     * @param aPersistPolicy
     *            the persist policy of the attribute
     * @param aPersistPeriod
     *            the persist period of the attribute
     * @param aDefaultValue
     *            the default value for the attribute
     * @param aGetter
     *            the getter method for the attribute
     * @param aSetter
     *            the setter method for the attribute
     * @param aCurrency
     *            the currency time limit for the attribute
     * @return the attribute descriptor
     */
    public Descriptor buildAttributeDescriptor( String aName, String aDisplayName, String aPersistPolicy,
                                                String aPersistPeriod, Object aDefaultValue, String aGetter,
                                                String aSetter, String aCurrency ) {
        Descriptor ret = new DescriptorSupport();
        if ( aName != null ) {
            ret.setField( "name", aName );
        }
        ret.setField( "descriptorType", "attribute" );
        if ( aDisplayName != null ) {
            ret.setField( "displayName", aDisplayName );
        }
        if ( aGetter != null ) {
            ret.setField( "getMethod", aGetter );
        }
        if ( aSetter != null ) {
            ret.setField( "setMethod", aSetter );
        }
        if ( aCurrency != null ) {
            ret.setField( "currencyTimeLimit", aCurrency );
        }
        if ( aPersistPolicy != null ) {
            ret.setField( "persistPolicy", aPersistPolicy );
        }
        if ( aPersistPeriod != null ) {
            ret.setField( "persistPeriod", aPersistPeriod );
        }
        if ( aDefaultValue != null ) {
            ret.setField( "defaultValue", aDefaultValue );
        }
        return ret;
    }

    /**
     * @param name
     * @param displayName
     * @param role
     * @param targetObject
     * @param targetType
     * @param ownerClass
     * @param currency
     * @return a Descriptor
     */
    public Descriptor buildOperationDescriptor( String name, String displayName, String role,
                                                Object targetObject, Object targetType, String ownerClass,
                                                String currency ) {
        Descriptor desc = new DescriptorSupport();
        if ( name != null ) {
            desc.setField( "name", name );
        }
        desc.setField( "descriptorType", "operation" );
        if ( displayName != null ) {
            desc.setField( "displayName", displayName );
        }
        if ( role != null ) {
            desc.setField( "role", role );
        }
        if ( targetObject != null ) {
            desc.setField( "targetObject", targetObject );
        }
        if ( targetType != null ) {
            desc.setField( "targetType", targetType );
        }
        if ( ownerClass != null ) {
            desc.setField( "class", ownerClass );
        }
        if ( currency != null ) {
            desc.setField( "currencyTimeLimit", currency );
        }
        return desc;
    }

    /**
     * @param name
     * @param displayName
     * @param persistPolicy
     * @param persistPeriod
     * @param persistLocation
     * @param persistName
     * @param log
     * @param logFile
     * @return a Descriptor
     */
    public Descriptor buildMBeanDescriptor( String name, String displayName, String persistPolicy,
                                            String persistPeriod, String persistLocation, String persistName,
                                            String log, String logFile ) {
        Descriptor desc = new DescriptorSupport();
        if ( name != null ) {
            desc.setField( "name", name );
        }
        desc.setField( "descriptorType", "mbean" );
        if ( displayName != null ) {
            desc.setField( "displayName", displayName );
        }
        if ( persistLocation != null ) {
            desc.setField( "persistLocation", persistLocation );
        }
        if ( persistName != null ) {
            desc.setField( "persistName", persistName );
        }
        if ( log != null ) {
            desc.setField( "log", log );
        }
        if ( persistPolicy != null ) {
            desc.setField( "persistPolicy", persistPolicy );
        }
        if ( persistPeriod != null ) {
            desc.setField( "persistPeriod", persistPeriod );
        }
        if ( logFile != null ) {
            desc.setField( "logFile", logFile );
        }
        return desc;
    }

}
