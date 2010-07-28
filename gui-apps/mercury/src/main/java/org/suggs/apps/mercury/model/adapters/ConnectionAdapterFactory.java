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
public final class ConnectionAdapterFactory {

    private static final Log LOG = LogFactory.getLog( ConnectionAdapterFactory.class );

    /**
     * Constructs a new instance.
     */
    private ConnectionAdapterFactory() {}

    /**
     * factory method that will create the correct type of adapter for what is needed.
     * 
     * @param aType
     *            the type of adapter to create.
     * @return a connection adapter
     */
    public static IConnectionAdapter createAdapter( IConnectionAdapter.CONNECTION_TYPE aType ) {
        LOG.info( "Creating adapter of type [STUB]" );
        return new IConnectionAdapter() {

            @Override
            public String getFriendlyName() {
                return null;
            }

            @Override
            public String getType() {
                return null;
            }
        };
    }
}
