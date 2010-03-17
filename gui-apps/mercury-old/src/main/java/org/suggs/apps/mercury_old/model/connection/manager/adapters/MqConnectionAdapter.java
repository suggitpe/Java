/*
 * MqConnectionAdapter.java created on 3 Aug 2007 06:13:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection.manager.adapters;

import org.suggs.apps.mercury_old.model.connection.IConnectionDetails;
import org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter;

import java.util.Map;

import javax.naming.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO this class needs to be implemented. This is the connection
 * adaopter for the MQ connections.
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public class MqConnectionAdapter implements IConnectionAdapter
{

    private static final Log LOG = LogFactory.getLog( MqConnectionAdapter.class );

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#createJmsContext(org.suggs.apps.mercury_old.model.connection.IConnectionDetails)
     */
    public Context createJmsContext( IConnectionDetails aConnDetails )
    {
        LOG.debug( "createJmsContext has not been implemented yet for the mq adapter" );
        throw new IllegalStateException( "Method not implemented ..." );
    }

    /**
     * @see org.suggs.apps.mercury_old.model.connection.manager.IConnectionAdapter#findAllBrokerObjects()
     */
    public Map<String, String> findAllBrokerObjects()
    {
        return null;
    }

}
