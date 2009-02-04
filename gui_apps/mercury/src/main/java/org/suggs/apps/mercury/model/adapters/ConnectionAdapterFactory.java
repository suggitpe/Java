/*
 * ConnectionAdapterFactory.java created on 4 Feb 2009 20:26:59 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.adapters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This factory will provide the mechanism for creating the correct
 * 
 * @author suggitpe
 * @version 1.0 4 Feb 2009
 */
public class ConnectionAdapterFactory
{

    private static final Log LOG = LogFactory.getLog( ConnectionAdapterFactory.class );

    public static IConnectionAdapter createAdapter( IConnectionAdapter.CONNECTION_TYPE aType )
    {
        LOG.info( "Creating adapter of type [STUB]" );
        return new IConnectionAdapter()
        {

            public String getFriendlyName()
            {
                return null;
            }

            public String getType()
            {
                return null;
            }
        };
    }
}
