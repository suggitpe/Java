/*
 * JaxbXmlConnectionStoreManagerHelper.java created on 9 Oct 2008 18:09:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.connectionstore.ConnectionFactoryGroup;
import org.suggs.apps.mercury.connectionstore.ConnectionFactoryType;
import org.suggs.apps.mercury.connectionstore.ConnectionStoreType;
import org.suggs.apps.mercury.connectionstore.ConnectionType;
import org.suggs.apps.mercury.connectionstore.DestinationGroup;
import org.suggs.apps.mercury.connectionstore.DestinationType;
import org.suggs.apps.mercury.connectionstore.JmsType;
import org.suggs.apps.mercury.connectionstore.ObjectFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a helper class used solely as a home for the builder
 * methods used by the connection store manager
 * 
 * @author suggitpe
 * @version 1.0 9 Oct 2008
 */
public class JaxbXmlConnectionStoreManagerHelper
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManagerHelper.class );

    /**
     * Builder method used to create the correct domain objects from
     * the jaxb objects returned from the underlying data.
     * 
     * @param aConnStr
     *            the jaxb object from which to create our own data
     * @return the map of connection details
     */
    static Map<String, ConnectionDetails> createDetailsFromConnection( ConnectionStoreType aConnStr )
    {
        LOG.debug( "Building [" + aConnStr.getConnection().size()
                   + "] connection details from JAXB connection" );
        Map<String, ConnectionDetails> ret = new HashMap<String, ConnectionDetails>();
        for ( ConnectionType c : aConnStr.getConnection() )
        {
            ret.put( c.getName(), buildConnectionDetails( c ) );
        }
        return ret;
    }

    /**
     * Builder method to create the connection details object
     * 
     * @param connType
     * @return
     */
    private static ConnectionDetails buildConnectionDetails( ConnectionType connType )
    {
        ConnectionDetails ret = new ConnectionDetails( connType.getName(),
                                                       connType.getType(),
                                                       connType.getHostname(),
                                                       connType.getPort() );

        // now we need to build a collection of conn facts based on
        // their type
        Map<String, Set<String>> connMap = ret.getConnectionFactories();
        ConnectionFactoryGroup cGrp = connType.getConnectionFactories();
        for ( ConnectionFactoryType f : cGrp.getConnectionFactory() )
        {
            if ( !connMap.containsKey( f.getFactType().value() ) )
            {
                connMap.put( f.getFactType().value(), new HashSet<String>() );
            }
            Set<String> s = connMap.get( f.getFactType().value() );
            s.add( f.getName() );
        }

        // now we need to build a collection of destinations based on
        // their type
        Map<String, Set<String>> destMap = ret.getDestinations();
        DestinationGroup dGrp = connType.getDestinations();
        for ( DestinationType d : dGrp.getDestination() )
        {
            if ( !destMap.containsKey( d.getDestType().value() ) )
            {
                destMap.put( d.getDestType().value(), new HashSet<String>() );
            }
            Set<String> s = destMap.get( d.getDestType().value() );
            s.add( d.getName() );
        }

        ret.setConnectionFactories( connMap );
        ret.setDestinations( destMap );

        return ret;
    }

    /**
     * This is a builder method that allows
     * 
     * @param factory
     * @param connDtls
     * @return
     */
    static ConnectionType createConnectionFromDetails( ObjectFactory factory,
                                                       ConnectionDetails connDtls )
    {

        ConnectionType ret = factory.createConnectionType();

        ret.setName( connDtls.getName() );
        ret.setHostname( connDtls.getHostname() );
        ret.setPort( connDtls.getPort() );
        ret.setType( connDtls.getType() );

        ConnectionFactoryGroup cGrp = factory.createConnectionFactoryGroup();
        for ( String type : connDtls.getConnectionFactories().keySet() )
        {
            Set<String> names = connDtls.getConnectionFactories().get( type );
            for ( String name : names )
            {
                ConnectionFactoryType ft = factory.createConnectionFactoryType();
                ft.setName( name );
                ft.setFactType( JmsType.fromValue( type ) );
                cGrp.getConnectionFactory().add( ft );
            }
        }
        ret.setConnectionFactories( cGrp );

        DestinationGroup dGrp = factory.createDestinationGroup();
        for ( String type : connDtls.getDestinations().keySet() )
        {
            Set<String> names = connDtls.getDestinations().get( type );
            for ( String name : names )
            {
                DestinationType dt = factory.createDestinationType();
                dt.setName( name );
                dt.setDestType( JmsType.fromValue( type ) );
                dGrp.getDestination().add( dt );
            }
        }
        ret.setDestinations( dGrp );

        return ret;
    }
}
