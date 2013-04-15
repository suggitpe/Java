/*
 * AbstractDynamicMBeanSupport.java created on 26 Feb 2008 08:23:12 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

/**
 * This is a utility base class for creating dynamic MBeans.
 */
public abstract class AbstractDynamicMBeanSupport implements DynamicMBean {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDynamicMBeanSupport.class);

    private Map<String, MBeanAttributeInfo> attributes = new Hashtable<String, MBeanAttributeInfo>();
    private Map<Constructor<?>, MBeanConstructorInfo> constructors = new Hashtable<Constructor<?>, MBeanConstructorInfo>();
    private Map<String, MBeanOperationInfo> operations = new Hashtable<String, MBeanOperationInfo>();

    public AbstractDynamicMBeanSupport() {
        super();

        // add the desc attribute from this class
        addMBeanAttribute("description", "java.lang.String", true, true, false, "Description of the MBean");

        // add the def ctor
        addMBeanConstructor(this.getClass().getConstructors()[0], "Default Constructor");
    }

    @Override
    public Object getAttribute(String attribute) {
        try {
            Method m = this.getClass().getDeclaredMethod("get" + attribute, (Class[]) null);
            return m.invoke(this, (Object) null);
        } catch (Exception e) {
            LOG.error("Failed to get attribute [" + attribute + "]", e);
        }
        return null;
    }

    @Override
    public AttributeList getAttributes(String[] aAttributes) {
        AttributeList list = new AttributeList();
        for (String s : aAttributes) {
            try {
                list.add(new Attribute(s, getAttribute(s)));
            } catch (Exception e) {
                LOG.error("Failed to get all attributes", e);
            }
        }
        return list;
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException {
        String attName = attribute.getName();
        Object attValue = attribute.getValue();
        try {
            String type = getType(attName, false, true);
            if (type == null) {
                throw new AttributeNotFoundException(attName);
            }

            Class<?>[] types = {Class.forName(type)};
            Method m = this.getClass().getDeclaredMethod("set" + attName, types);

            Object[] args = {attValue};
            m.invoke(this, args);
        } catch (AttributeNotFoundException anfe) {
            throw anfe;
        } catch (Exception e) {
            LOG.error("Unable to set attribute [" + attName + "]", e);
        }
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        Attribute[] atts = attributes.toArray(new Attribute[attributes.size()]);
        for (Attribute a : atts) {
            try {
                this.setAttribute(a);
            } catch (Exception e) {
                LOG.error("Failed to set attribute [" + a.getName() + "]", e);
            }
        }
        return attributes;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) {
        try {
            Class<?> c = this.getClass();
            Class<?> sig[] = null;
            if (signature != null) {
                sig = new Class[signature.length];
                for (int i = 0; i < signature.length; ++i) {
                    sig[i] = Class.forName(signature[i]);
                }
            }

            Method m = c.getDeclaredMethod(actionName, sig);
            return m.invoke(this, params);
        } catch (Exception e) {
            LOG.error("Failed to invoke method [" + actionName + "]", e);
        }
        return null;
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        try {
            return buildDynamicMBeanInfo();
        } catch (Exception e) {
            LOG.error("Failed to construct a new MBeanInfo object", e);
        }
        return null;
    }

    protected void addMBeanOperation(String name, String[] paramTypes, String[] paramNames,
                                     String[] paramDescs, String aDescription, String rtype, int type) {
        MBeanParameterInfo[] params = null;
        if (paramTypes != null) {
            params = new MBeanParameterInfo[paramTypes.length];
            for (int i = 0; i < paramTypes.length; ++i) {
                params[i] = new MBeanParameterInfo(paramNames[i], paramTypes[i], paramDescs[i]);
            }
        }
        operations.put(name, new MBeanOperationInfo(name, aDescription, params, rtype, type));
    }

    protected final void addMBeanAttribute(String name, String type, boolean read, boolean write,
                                           boolean is, String aDescription) {
        attributes.put(name, new MBeanAttributeInfo(name, type, aDescription, read, write, is));

    }

    protected final void addMBeanConstructor(Constructor<?> cons, String aDescription) {
        constructors.put(cons, new MBeanConstructorInfo(aDescription, cons));
    }

    private MBeanInfo buildDynamicMBeanInfo() {
        // operations
        MBeanOperationInfo[] ops = operations.values().toArray(new MBeanOperationInfo[operations.size()]);

        // attributes
        MBeanAttributeInfo[] atts = attributes.values().toArray(new MBeanAttributeInfo[attributes.size()]);

        // constructors
        MBeanConstructorInfo[] cons = constructors.values()
                .toArray(new MBeanConstructorInfo[constructors.size()]);

        String description = "Description of the MBean";
        return new MBeanInfo(this.getClass().getName(), description, atts, cons, ops, null);
    }

    private String getType(String attName, boolean read, boolean write) {
        boolean allowed = true;

        if (attributes.containsKey(attName)) {
            MBeanAttributeInfo attInfo = attributes.get(attName);

            if (read) {
                if (!attInfo.isReadable()) {
                    allowed = false;
                }
            }

            if (write) {
                if (!attInfo.isWritable()) {
                    allowed = false;
                }
            }

            if (!allowed) {
                return null;
            }
            return attInfo.getType();
        }
        return null;
    }

}
