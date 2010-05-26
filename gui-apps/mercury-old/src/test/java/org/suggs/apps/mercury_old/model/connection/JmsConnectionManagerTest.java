/*
 * JmsConnectionTest.java created on 2 Aug 2007 06:03:25 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury_old.model.connection;

import org.suggs.apps.mercury_old.model.support.AbstractMercuryModelTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A a test for the connection manager classes
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
public class JmsConnectionManagerTest extends AbstractMercuryModelTest {

    private static final Log LOG = LogFactory.getLog( JmsConnectionManagerTest.class );

    protected String portNum;
    protected String server;
    protected String type;
    protected IConnectionManager connMgr;

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerTest() {
        setName( "JmsConectionManagerTest" );
        setPopulateProtectedVariables( true );
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations() {
        return new String[] { "xml/ut-model-connection-JmsConnMgrTest.xml" };
    }

    /**
     * 
     *
     */
    public void testBasicConnection() {
        runMercuryTest( new IMercuryTestCallBack() {

            public String getName() {
                return "testBasicConnection";
            }

            public void runTest() {
                // IConnectionDetails dtls = new ConnectionDetails(
                // "TestConnection",
                // EConnectionType.valueOf( "EMS" ),
                // mServer_,
                // mPortNum_ );

                LOG.debug( "Initial state is [" + connMgr.getConnectionState().name() + "]" );
                /*
                 * try { mConnMgr_.connect( dtls ); } catch ( MercuryConnectionException me ) { fail(
                 * "Exception thrown when trying to connect: " + me.getMessage() ); }
                 */

                LOG.debug( "Connected state is [" + connMgr.getConnectionState().name() + "]" );
                if ( connMgr.getConnectionState() != EConnectionState.CONNECTED ) {
                    // fail( "Test should be in a connecvted state
                    // now" );
                }

                try {
                    connMgr.disconnect();
                }
                catch ( MercuryConnectionException me ) {
                    fail( "Exception thrown when trying to connect: " + me.getMessage() );
                }

                LOG.debug( "Disconnected state is [" + connMgr.getConnectionState().name() + "]" );
                if ( connMgr.getConnectionState() != EConnectionState.DISCONNECTED ) {
                    fail( "Test should now be in a disconnected state" );
                }
            }
        } );

    }
}
