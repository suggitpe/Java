/*
 * MqConnectionAdapter.java created on 3 Aug 2007 06:13:55 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.manager.adapters;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.manager.IConnectionAdapter;

import javax.naming.Context;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO Write javadoc for MqConnectionAdapter
 * 
 * @author suggitpe
 * @version 1.0 3 Aug 2007
 */
public class MqConnectionAdapter implements IConnectionAdapter
{

    private static final Log LOG = LogFactory.getLog( MqConnectionAdapter.class );

    /**
     * @see org.suggs.apps.mercury.model.connection.manager.IConnectionAdapter#createJmsContext(org.suggs.apps.mercury.model.connection.IJmsConnectionDetails)
     */
    public Context createJmsContext( IJmsConnectionDetails aConnDetails )
    {
        LOG.debug( "createJmsContext has not been implemented yet for the mq adapter" );
        throw new NotImplementedException();
    }

}
