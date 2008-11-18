/*
 * JaxbXmlConnectionStoreManagerHelper.java created on 9 Oct 2008 18:09:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.connectionstore.ConnectionFactoryGroup;
import org.suggs.apps.mercury.connectionstore.ConnectionFactoryType;
import org.suggs.apps.mercury.connectionstore.ConnectionSecurityType;
import org.suggs.apps.mercury.connectionstore.ConnectionStoreType;
import org.suggs.apps.mercury.connectionstore.ConnectionType;
import org.suggs.apps.mercury.connectionstore.DestinationGroup;
import org.suggs.apps.mercury.connectionstore.DestinationType;
import org.suggs.apps.mercury.connectionstore.JmsType;
import org.suggs.apps.mercury.connectionstore.MetadataGroup;
import org.suggs.apps.mercury.connectionstore.MetadataType;
import org.suggs.apps.mercury.connectionstore.ObjectFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDataException;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a helper class used solely as a home for the builder
 * methods used by the connection store manager. In class you will
 * find all of the builder methods to create the XML content etc.
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
        if ( connType.isSetSecurity() )
        {
            ret.setSecurityDetails( connType.getSecurity().getUsername(), connType.getSecurity()
                .getPassword() );
        }

        List<MetadataType> metadata = connType.getMetadata().getMetadata();
        for ( MetadataType t : metadata )
        {
            try
            {
                ret.addMetaDataItem( t.getKey(), t.getValue() );
            }
            catch ( ConnectionDataException cde )
            {
                LOG.warn( "Trying to add duplicate metadata item with key[" + t.getKey()
                          + "] to metadata list" );
            }
        }

        // now we need to build a collection of conn facts based on
        // their type
        ConnectionFactoryGroup cGrp = connType.getConnectionFactories();
        for ( ConnectionFactoryType f : cGrp.getConnectionFactory() )
        {
            ret.addConnectionFactory( f.getFactType().value(), f.getName() );
        }

        // now we need to build a collection of destinations based on
        // their type
        DestinationGroup dGrp = connType.getDestinations();
        for ( DestinationType d : dGrp.getDestination() )
        {
            ret.addDestination( d.getDestType().value(), d.getName() );
        }

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

        if ( connDtls.isSecurityEnabled() )
        {
            ConnectionSecurityType t = factory.createConnectionSecurityType();
            t.setUsername( connDtls.getUsername() );
            t.setPassword( connDtls.getPassword() );
            ret.setSecurity( t );
        }

        MetadataGroup mt = factory.createMetadataGroup();
        for ( String key : connDtls.getMetaData().keySet() )
        {
            MetadataType data = factory.createMetadataType();
            data.setKey( key );
            data.setValue( connDtls.getMetaData().get( key ) );
            mt.getMetadata().add( data );
        }
        ret.setMetadata( mt );

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
