/*
 * ModelMBeanInfoBuilder.java created on 22 Apr 2008 06:30:35 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.support;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Map;

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
 */
public class ModelMBeanInfoBuilder {

    private Map<String, ModelMBeanAttributeInfo> attributes = new Hashtable<String, ModelMBeanAttributeInfo>();
    private Map<String, ModelMBeanNotificationInfo> notifications = new Hashtable<String, ModelMBeanNotificationInfo>();
    private Map<Constructor<?>, ModelMBeanConstructorInfo> constructors = new Hashtable<Constructor<?>, ModelMBeanConstructorInfo>();
    private Map<String, ModelMBeanOperationInfo> operations = new Hashtable<String, ModelMBeanOperationInfo>();

    public ModelMBeanInfoBuilder() {
    }

    public void addModelMBeanMethod(String aName, String[] aParamTypes, String[] aParamNames,
                                    String[] aParamDescs, String aDescription, String aRetType, int aType,
                                    Descriptor aDescriptor) {
        MBeanParameterInfo[] params = null;
        if (aParamTypes != null) {
            params = new MBeanParameterInfo[aParamTypes.length];
            for (int i = 0; i < aParamTypes.length; ++i) {
                params[i] = new MBeanParameterInfo(aParamNames[i], aParamTypes[i], aParamDescs[i]);
            }
        }
        operations.put(aName, new ModelMBeanOperationInfo(aName,
                aDescription,
                params,
                aRetType,
                aType,
                aDescriptor));
    }

    public void addModelMBeanNotification(String[] aType, String aClassName, String aDescription,
                                          Descriptor aDescriptor) {
        notifications.put(aClassName, new ModelMBeanNotificationInfo(aType,
                aClassName,
                aDescription,
                aDescriptor));
    }

    public void addModelMBeanAttribute(String aName, String aType, boolean aRead, boolean aWrite,
                                       boolean aIs, String aDescription, Descriptor aDescriptor) {
        attributes.put(aName, new ModelMBeanAttributeInfo(aName,
                aType,
                aDescription,
                aRead,
                aWrite,
                aIs,
                aDescriptor));
    }

    public void addModelMBeanConstructor(Constructor<?> aCtor, String aDescription, Descriptor aDescriptor) {
        constructors.put(aCtor, new ModelMBeanConstructorInfo(aDescription, aCtor, aDescriptor));
    }

    public ModelMBeanInfo buildModelMBeanInfo(Descriptor aDescriptor) {
        ModelMBeanOperationInfo[] ops = operations.values()
                .toArray(new ModelMBeanOperationInfo[operations.size()]);

        ModelMBeanAttributeInfo[] atts = attributes.values()
                .toArray(new ModelMBeanAttributeInfo[attributes.size()]);

        ModelMBeanConstructorInfo[] cons = constructors.values()
                .toArray(new ModelMBeanConstructorInfo[constructors.size()]);

        ModelMBeanNotificationInfo[] notifs = notifications.values()
                .toArray(new ModelMBeanNotificationInfo[notifications.size()]);

        return new ModelMBeanInfoSupport("javax.management.modelmbean.ModelMBeanInfo",
                "description",
                atts,
                cons,
                ops,
                notifs,
                aDescriptor);
    }

    public Descriptor buildAttributeDescriptor(String aName, String aDisplayName, String aPersistPolicy,
                                               String aPersistPeriod, Object aDefaultValue, String aGetter,
                                               String aSetter, String aCurrency) {
        Descriptor ret = new DescriptorSupport();
        if (aName != null) {
            ret.setField("name", aName);
        }
        ret.setField("descriptorType", "attribute");
        if (aDisplayName != null) {
            ret.setField("displayName", aDisplayName);
        }
        if (aGetter != null) {
            ret.setField("getMethod", aGetter);
        }
        if (aSetter != null) {
            ret.setField("setMethod", aSetter);
        }
        if (aCurrency != null) {
            ret.setField("currencyTimeLimit", aCurrency);
        }
        if (aPersistPolicy != null) {
            ret.setField("persistPolicy", aPersistPolicy);
        }
        if (aPersistPeriod != null) {
            ret.setField("persistPeriod", aPersistPeriod);
        }
        if (aDefaultValue != null) {
            ret.setField("defaultValue", aDefaultValue);
        }
        return ret;
    }

    public Descriptor buildOperationDescriptor(String name, String displayName, String role,
                                               Object targetObject, Object targetType, String ownerClass,
                                               String currency) {
        Descriptor desc = new DescriptorSupport();
        if (name != null) {
            desc.setField("name", name);
        }
        desc.setField("descriptorType", "operation");
        if (displayName != null) {
            desc.setField("displayName", displayName);
        }
        if (role != null) {
            desc.setField("role", role);
        }
        if (targetObject != null) {
            desc.setField("targetObject", targetObject);
        }
        if (targetType != null) {
            desc.setField("targetType", targetType);
        }
        if (ownerClass != null) {
            desc.setField("class", ownerClass);
        }
        if (currency != null) {
            desc.setField("currencyTimeLimit", currency);
        }
        return desc;
    }

    public Descriptor buildMBeanDescriptor(String name, String displayName, String persistPolicy,
                                           String persistPeriod, String persistLocation, String persistName,
                                           String log, String logFile) {
        Descriptor desc = new DescriptorSupport();
        if (name != null) {
            desc.setField("name", name);
        }
        desc.setField("descriptorType", "mbean");
        if (displayName != null) {
            desc.setField("displayName", displayName);
        }
        if (persistLocation != null) {
            desc.setField("persistLocation", persistLocation);
        }
        if (persistName != null) {
            desc.setField("persistName", persistName);
        }
        if (log != null) {
            desc.setField("log", log);
        }
        if (persistPolicy != null) {
            desc.setField("persistPolicy", persistPolicy);
        }
        if (persistPeriod != null) {
            desc.setField("persistPeriod", persistPeriod);
        }
        if (logFile != null) {
            desc.setField("logFile", logFile);
        }
        return desc;
    }

}
