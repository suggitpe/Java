/*
 * JaxbXmlConnectionStoreManagerHelper.java created on 9 Oct 2008 18:09:39 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl;

import org.suggs.apps.mercury.connectionstore.ConnectionDataGroup;
import org.suggs.apps.mercury.connectionstore.ConnectionDataItemType;
import org.suggs.apps.mercury.connectionstore.ConnectionSecurityType;
import org.suggs.apps.mercury.connectionstore.ConnectionStoreType;
import org.suggs.apps.mercury.connectionstore.ConnectionType;
import org.suggs.apps.mercury.connectionstore.ObjectFactory;
import org.suggs.apps.mercury.model.connection.ConnectionDataException;
import org.suggs.apps.mercury.model.connection.ConnectionDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<ConnectionDataItemType> cData = connType.getConnectiondataitems().getConnectiondata();
        for ( ConnectionDataItemType t : cData )
        {
            try
            {
                ret.addConnectionDataItem( t.getKey(), t.getValue() );
            }
            catch ( ConnectionDataException cde )
            {
                LOG.warn( "Trying to add duplicate metadata item with key[" + t.getKey()
                          + "] to metadata list" );
            }
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

        ConnectionDataGroup mt = factory.createConnectionDataGroup();
        for ( String key : connDtls.getConnectionData().keySet() )
        {
            ConnectionDataItemType data = factory.createConnectionDataItemType();
            data.setKey( key );
            data.setValue( connDtls.getConnectionData().get( key ) );
            mt.getConnectiondata().add( data );
        }
        ret.setConnectiondataitems( mt );

        return ret;
    }
}
