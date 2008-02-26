/*
 * JiniServiceManager.java created on 25 Feb 2008 08:27:06 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.jiniservicemanager;

import net.jini.core.entry.Entry;
import net.jini.core.lookup.ServiceItem;
import net.jini.core.lookup.ServiceMatches;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;

import java.lang.reflect.Method;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ReflectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to manage JINI services. This is to be used only as an
 * example dynamic bean class rather than a fully working version.
 * 
 * @author suggitpe
 * @version 1.0 25 Feb 2008
 */
public class JiniServiceManager implements DynamicMBean
{

    private static final Log LOG = LogFactory.getLog( JiniServiceManager.class );
    private static final String JINI_INTERFACE_NAME = "org.suggs.sandbox.jmx.jmxbook.components.jiniservicemanager.IManagedJiniService";

    private IManagedJiniService mServiceRef_;
    private Entry mInitialAttribute_;

    /**
     * Constructs a new instance.
     */
    public JiniServiceManager( Entry aInitAtt )
    {
        mServiceRef_ = (IManagedJiniService) lookupService();
        mInitialAttribute_ = aInitAtt;
    }

    /**
     * Getter for a single named attribute.
     * 
     * @see javax.management.DynamicMBean#getAttribute(java.lang.String)
     */
    public Object getAttribute( String attribute ) throws AttributeNotFoundException,
                    MBeanException, ReflectionException
    {
        throw new AttributeNotFoundException( attribute );
    }

    /**
     * Getter for the class' attributes. Hard coded to return an empty
     * list.
     * 
     * @see javax.management.DynamicMBean#getAttributes(java.lang.String[])
     */
    public AttributeList getAttributes( String[] attributes )
    {
        return new AttributeList();
    }

    /**
     * Setter for a single named class attribute. Hard coded to throw
     * exception.
     * 
     * @see javax.management.DynamicMBean#setAttribute(javax.management.Attribute)
     */
    public void setAttribute( Attribute attribute ) throws AttributeNotFoundException,
                    InvalidAttributeValueException, MBeanException, ReflectionException
    {
        throw new AttributeNotFoundException( "No attributes can be set" );
    }

    /**
     * Setter for the class' attributes. hard coded to return an empty
     * list.
     * 
     * @see javax.management.DynamicMBean#setAttributes(javax.management.AttributeList)
     */
    public AttributeList setAttributes( AttributeList attributes )
    {
        return new AttributeList();
    }

    /**
     * This method is used to allow one of the class' methods to be
     * invoked
     * 
     * @see javax.management.DynamicMBean#invoke(java.lang.String,
     *      java.lang.Object[], java.lang.String[])
     */
    public Object invoke( String actionName, Object[] params, String[] signature )
                    throws MBeanException, ReflectionException
    {
        try
        {
            String methName = actionName;
            Class[] types = new Class[signature.length];
            for ( int i = 0; i < types.length; ++i )
            {
                types[i] = Class.forName( signature[i] );
            }

            Method m = mServiceRef_.getClass().getMethod( methName, types );
            Object tmp = m.invoke( mServiceRef_, params );
            return tmp;
        }
        catch ( Exception e )
        {
            throw new MBeanException( e );
        }
    }

    /**
     * This method will manage the advertising of the class' behaviour
     * to the MBean server. As this is a dynamic MBean we need to
     * create an information object that can be used by the MBean
     * server to understand the internal class behaviour that we wish
     * to expose to the MBean server agents.
     * 
     * @see javax.management.DynamicMBean#getMBeanInfo()
     */
    public MBeanInfo getMBeanInfo()
    {
        MBeanConstructorInfo[] cons = new MBeanConstructorInfo[1];
        MBeanNotificationInfo[] nots = null;
        MBeanAttributeInfo[] atts = null;
        MBeanOperationInfo[] ops = new MBeanOperationInfo[2];

        // ############
        // constructors
        try
        {
            Class connArgs[] = { Class.forName( "java.lang.String" ),
                                Class.forName( "net.jini.core.entry.Entry" ) };
            MBeanConstructorInfo cinfo = new MBeanConstructorInfo( "Main constructor",
                                                                   this.getClass()
                                                                       .getConstructor( connArgs ) );
            cons[0] = cinfo;
        }
        catch ( Exception e )
        {
            LOG.error( "Failed to create constructor in Mbean Info", e );
        }

        // ##########
        // operations

        // add entries
        MBeanParameterInfo[] sig0 = new MBeanParameterInfo[1];
        sig0[0] = new MBeanParameterInfo( "entries", "java.util.Vector", "Entries to add" );
        ops[0] = new MBeanOperationInfo( "addEntries",
                                         "Used to add service attributes",
                                         sig0,
                                         "void",
                                         MBeanOperationInfo.ACTION );

        // modify entries
        MBeanParameterInfo[] sig1 = new MBeanParameterInfo[2];
        sig1[0] = new MBeanParameterInfo( "oldEntries", "java.util.Vector", "Old entries to modify" );
        sig1[1] = new MBeanParameterInfo( "newEntries", "java.util.Vector", "New entries to modify" );
        ops[1] = new MBeanOperationInfo( "modifyEntries",
                                         "Used to modify service attributes",
                                         sig1,
                                         "void",
                                         MBeanOperationInfo.ACTION );

        // #################
        // create MBean info
        MBeanInfo ret = new MBeanInfo( this.getClass().getName(),
                                       "Manages service: " + mInitialAttribute_.toString(),
                                       atts,
                                       cons,
                                       ops,
                                       nots );
        return ret;
    }

    /**
     * Method used to lookup a JINI service. This impl will not work
     * and this is where change would be required.
     * 
     * @return
     */
    private Object lookupService()
    {
        try
        {
            Class[] interfaces = { Class.forName( JINI_INTERFACE_NAME ) };

            Entry[] ents = new Entry[1];
            ents[0] = mInitialAttribute_;

            ServiceTemplate template = new ServiceTemplate( null, interfaces, ents );
            ServiceRegistrar reg = null;
            ServiceMatches matches = reg.lookup( template, 10000 );

            ServiceItem item = matches.items[1];
            return item.service;
        }
        catch ( Exception e )
        {
            LOG.error( "Failed to lookup service", e );
        }
        return null;
    }
}
