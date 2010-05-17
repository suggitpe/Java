/*
 * AbstractDynamicMBeanSupport.java created on 26 Feb 2008 08:23:12 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a utility base class for creating dynamic MBeans.
 * 
 * @author suggitpe
 * @version 1.0 26 Feb 2008
 */
public abstract class AbstractDynamicMBeanSupport implements DynamicMBean {

    private static final Log LOG = LogFactory.getLog( AbstractDynamicMBeanSupport.class );

    private Hashtable<String, MBeanAttributeInfo> attributes = new Hashtable<String, MBeanAttributeInfo>();
    @SuppressWarnings("unchecked")
    private Hashtable<Constructor, MBeanConstructorInfo> constructors = new Hashtable<Constructor, MBeanConstructorInfo>();
    private Hashtable<String, MBeanOperationInfo> operations = new Hashtable<String, MBeanOperationInfo>();

    private String description = "Description of the MBean";

    /**
     * Constructs a new instance.
     */
    public AbstractDynamicMBeanSupport() {
        super();

        // add the desc attribute from this class
        addMBeanAttribute( "description", "java.lang.String", true, true, false, "Description of the MBean" );

        // add the def ctor
        addMBeanConstructor( this.getClass().getConstructors()[0], "Default Constructor" );
    }

    /**
     * @see javax.management.DynamicMBean#getAttribute(java.lang.String)
     */
    public Object getAttribute( String attribute ) throws AttributeNotFoundException, MBeanException,
                    ReflectionException {
        try {
            Method m = this.getClass().getDeclaredMethod( "get" + attribute, (Class[]) null );
            return m.invoke( this, (Object) null );
        }
        catch ( Exception e ) {
            LOG.error( "Failed to get attribute [" + attribute + "]", e );
        }
        return null;
    }

    /**
     * @see javax.management.DynamicMBean#getAttributes(java.lang.String[])
     */
    public AttributeList getAttributes( String[] aAttributes ) {
        AttributeList list = new AttributeList();
        for ( String s : aAttributes ) {
            try {
                list.add( new Attribute( s, getAttribute( s ) ) );
            }
            catch ( Exception e ) {
                LOG.error( "Failed to get all attributes", e );
            }
        }
        return list;
    }

    /**
     * @see javax.management.DynamicMBean#setAttribute(javax.management.Attribute)
     */
    @SuppressWarnings("unchecked")
    public void setAttribute( Attribute attribute ) throws AttributeNotFoundException,
                    InvalidAttributeValueException, MBeanException, ReflectionException {
        String attName = attribute.getName();
        Object attValue = attribute.getValue();
        try {
            String type = getType( attName, false, true );
            if ( type == null ) {
                throw new AttributeNotFoundException( attName );
            }

            Class[] types = { Class.forName( type ) };
            Method m = this.getClass().getDeclaredMethod( "set" + attName, types );

            Object[] args = { attValue };
            m.invoke( this, args );
        }
        catch ( AttributeNotFoundException anfe ) {
            throw anfe;
        }
        catch ( Exception e ) {
            LOG.error( "Unable to set attribute [" + attName + "]", e );
        }
    }

    /**
     * @see javax.management.DynamicMBean#setAttributes(javax.management.AttributeList)
     */
    public AttributeList setAttributes( AttributeList attributes ) {
        Attribute[] atts = attributes.toArray( new Attribute[attributes.size()] );
        for ( int i = 0; i < atts.length; ++i ) {
            Attribute a = atts[i];
            try {
                this.setAttribute( a );
            }
            catch ( Exception e ) {
                LOG.error( "Failed to set attribute [" + a.getName() + "]", e );
            }
        }
        return attributes;
    }

    /**
     * Generic invocation method that will look for the correct method signature on this class and invoke it.
     * 
     * @see javax.management.DynamicMBean#invoke(java.lang.String, java.lang.Object[], java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    public Object invoke( String actionName, Object[] params, String[] signature ) throws MBeanException,
                    ReflectionException {
        try {
            Class c = this.getClass();
            Class sig[] = null;
            if ( signature != null ) {
                sig = new Class[signature.length];
                for ( int i = 0; i < signature.length; ++i ) {
                    sig[i] = Class.forName( signature[i] );
                }
            }

            Method m = c.getDeclaredMethod( actionName, sig );
            return m.invoke( this, params );
        }
        catch ( Exception e ) {
            LOG.error( "Failed to invoke method [" + actionName + "]", e );
        }
        return null;
    }

    /**
     * @see javax.management.DynamicMBean#getMBeanInfo()
     */
    public MBeanInfo getMBeanInfo() {
        try {
            return buildDynamicMBeanInfo();
        }
        catch ( Exception e ) {
            LOG.error( "Failed to construct a new MBeanInfo object", e );
        }
        return null;
    }

    /**
     * Accessor to add an operation to the exposed list of operations of the class
     * 
     * @param aName
     *            the name of the operation
     * @param aParamTypes
     *            the types array of the parameters of the operation
     * @param aParamNames
     *            the names array of the parameters of the operation
     * @param aParamDescs
     *            the descriptions array of the parameters of the operation
     * @param aDescription
     *            the description of the operation
     * @param rtype
     *            the return type of the operation
     * @param type
     *            the type of operation
     */
    protected void addMBeanOperation( String name, String[] paramTypes, String[] paramNames,
                                      String[] paramDescs, String aDescription, String rtype, int type ) {
        MBeanParameterInfo[] params = null;
        if ( paramTypes != null ) {
            params = new MBeanParameterInfo[paramTypes.length];
            for ( int i = 0; i < paramTypes.length; ++i ) {
                params[i] = new MBeanParameterInfo( paramNames[i], paramTypes[i], paramDescs[i] );
            }
        }
        operations.put( name, new MBeanOperationInfo( name, aDescription, params, rtype, type ) );
    }

    /**
     * Accessor to add an attribute to the exposed list of attributes of the class.
     * 
     * @param name
     *            the name of the attribute
     * @param type
     *            the type of the attribute
     * @param read
     *            whether the attribute is readable
     * @param write
     *            whether the attribute is writeable
     * @param is
     *            whether the attribute uses an is rather than a get
     * @param description
     *            the description of the attribute
     */
    protected void addMBeanAttribute( String name, String type, boolean read, boolean write, boolean is,
                                      String aDescription ) {
        attributes.put( name, new MBeanAttributeInfo( name, type, aDescription, read, write, is ) );

    }

    /**
     * Accessor to add a constructor to the exposed list of constructors for the class.
     * 
     * @param cons
     *            the constructor
     * @param aDescription
     *            a description of the constructor
     */
    @SuppressWarnings("unchecked")
    protected void addMBeanConstructor( Constructor cons, String aDescription ) {
        constructors.put( cons, new MBeanConstructorInfo( aDescription, cons ) );
    }

    /**
     * Private method used to construct the mbean info object
     * 
     * @return a newly created mbean info object
     */
    private MBeanInfo buildDynamicMBeanInfo() {
        // operations
        MBeanOperationInfo[] ops = new MBeanOperationInfo[operations.size()];
        Vector<MBeanOperationInfo> vo = new Vector<MBeanOperationInfo>( operations.values() );
        vo.copyInto( ops );

        // attributes
        MBeanAttributeInfo[] atts = new MBeanAttributeInfo[attributes.size()];
        Vector<MBeanAttributeInfo> va = new Vector<MBeanAttributeInfo>( attributes.values() );
        va.copyInto( atts );

        // constructors
        MBeanConstructorInfo[] cons = new MBeanConstructorInfo[constructors.size()];
        Vector<MBeanConstructorInfo> vc = new Vector<MBeanConstructorInfo>( constructors.values() );
        vc.copyInto( cons );

        return new MBeanInfo( this.getClass().getName(), description, atts, cons, ops, null );
    }

    /**
     * Accessor method for an attribute, to get the correct type information about the attribute
     * 
     * @param attName
     *            the name of the attribute
     * @param read
     *            states whether we have read access
     * @param write
     *            states whether we have write access
     * @return the type of the attribute
     */
    private String getType( String attName, boolean read, boolean write ) {
        boolean allowed = true;

        if ( attributes.containsKey( attName ) ) {
            MBeanAttributeInfo attInfo = attributes.get( attName );

            if ( read ) {
                if ( !attInfo.isReadable() ) {
                    allowed = false;
                }
            }

            if ( write ) {
                if ( !attInfo.isWritable() ) {
                    allowed = false;
                }
            }

            if ( !allowed ) {
                return null;
            }
            return attInfo.getType();
        }
        return null;
    }

}
