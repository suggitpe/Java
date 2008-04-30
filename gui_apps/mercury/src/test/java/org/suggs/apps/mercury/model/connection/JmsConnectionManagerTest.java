/*
 * JmsConnectionTest.java created on 2 Aug 2007 06:03:25 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection;

import org.suggs.apps.mercury.model.support.AbstractMercuryModelTest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A a test for the connection manager classes
 * 
 * @author suggitpe
 * @version 1.0 2 Aug 2007
 */
public class JmsConnectionManagerTest extends AbstractMercuryModelTest
{

    private static final Log LOG = LogFactory.getLog( JmsConnectionManagerTest.class );

    protected String mPortNum_;
    protected String mServer_;
    protected String mType_;
    protected IConnectionManager mConnMgr_;

    /**
     * Constructs a new instance.
     */
    public JmsConnectionManagerTest()
    {
        setName( "JmsConectionManagerTest" );
        setPopulateProtectedVariables( true );
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
     */
    @Override
    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-model-connection-JmsConnMgrTest.xml" };
    }

    /**
     * 
     *
     */
    public void testBasicConnection()
    {
        runMercuryTest( new IMercuryTestCallBack()
        {

            public String getName()
            {
                return "testBasicConnection";
            }

            public void runTest()
            {
                // IConnectionDetails dtls = new ConnectionDetails(
                // "TestConnection",
                // EConnectionType.valueOf( "EMS" ),
                // mServer_,
                // mPortNum_ );

                LOG.debug( "Initial state is [" + mConnMgr_.getConnectionState().name() + "]" );
                /*
                 * try { mConnMgr_.connect( dtls ); } catch (
                 * MercuryConnectionException me ) { fail( "Exception
                 * thrown when trying to connect: " + me.getMessage() ); }
                 */

                LOG.debug( "Connected state is [" + mConnMgr_.getConnectionState().name() + "]" );
                if ( mConnMgr_.getConnectionState() != EConnectionState.CONNECTED )
                {
                    // fail( "Test should be in a connecvted state
                    // now" );
                }

                try
                {
                    mConnMgr_.disconnect();
                }
                catch ( MercuryConnectionException me )
                {
                    fail( "Exception thrown when trying to connect: " + me.getMessage() );
                }

                LOG.debug( "Disconnected state is [" + mConnMgr_.getConnectionState().name() + "]" );
                if ( mConnMgr_.getConnectionState() != EConnectionState.DISCONNECTED )
                {
                    fail( "Test should now be in a disconnected state" );
                }
            }
        } );

    }
}
