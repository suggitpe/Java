/*
 * XmlConnectionStoreDaoTest.java created on 24 Sep 2008 07:02:17 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.XmlConnectionStoreDao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class is used to test the XmlConnectionStore DAO objects.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2008
 */
public class XmlConnectionStoreDaoUnitTest
{

    private static final Log LOG = LogFactory.getLog( XmlConnectionStoreDaoUnitTest.class );

    private XmlConnectionStoreDao mDao_;
    private IXmlConnectionStoreManager mMock_;
    private Map<String, ConnectionDetails> mMapMock_;

    /**
     * This is run before each test is run
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- XmlConnectionStoreDaoTest" );
        mDao_ = new XmlConnectionStoreDao();
        LOG.debug( "Creating mock for IXmlConnectionStoreManager" );
        // create the mock
        mMock_ = EasyMock.createStrictMock( IXmlConnectionStoreManager.class );
        mMapMock_ = EasyMock.createStrictMock( Map.class );
        // now set the mock on the class to test
        mDao_.setConnectionStoreManager( mMock_ );
    }

    /**
     * This test will test that the correct list of connection names
     * is returned
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testFullGetListOfKnownConnectionNames() throws ConnectionStoreException
    {
        // prepare data for the mock object
        final String TEST_1 = "test1";
        final String TEST_2 = "test2";
        HashSet<String> ret = new HashSet<String>();
        ret.add( TEST_1 );
        ret.add( TEST_2 );

        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( mMapMock_.keySet() ).andReturn( ret ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        Set<String> set = mDao_.getListOfKnownConnectionNames();
        LOG.debug( "Connection list returned: " + set );

        Assert.assertTrue( set.size() == 2 );
        Assert.assertTrue( set.contains( TEST_1 ) );
        Assert.assertTrue( set.contains( TEST_2 ) );

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly found the connection names" );
    }

    /**
     * This test will test that the correct list of connection names
     * is returned for an empty list of connections
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testEmptyGetListOfKnownConnectionNames() throws ConnectionStoreException
    {
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );

        EasyMock.expect( mMapMock_.keySet() ).andReturn( new HashSet<String>() ).times( 1 );
        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        Set<String> set = mDao_.getListOfKnownConnectionNames();
        LOG.debug( "Connection list returned: " + set );

        Assert.assertTrue( set.size() == 0 );

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly found no connection names" );
    }

    /**
     * This test should delete the named connection that we specify
     * here. Here we want to check that if we call delete then we end
     * calling the get list of names and then the save them down again
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testDeleteNamedConnection() throws ConnectionStoreException
    {
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );

        EasyMock.expect( new Boolean( mMapMock_.containsKey( TO_DELETE ) ) )
            .andReturn( new Boolean( true ) )
            .times( 1 );

        mMapMock_.remove( TO_DELETE );
        EasyMock.expectLastCall().andReturn( mMapMock_ ).times( 1 );

        mMock_.saveConnectionData( mMapMock_ );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDao_.deleteNamedConnection( TO_DELETE );

        }
        catch ( ConnectionStoreException e )
        {
            LOG.error( "Failed to delete connection with name [" + TO_DELETE + "]", e );
            Assert.fail( "Failed to delete connection with the name [" + TO_DELETE + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly called remove and save on the underlying containers" );
    }

    /**
     * This test should delete the named connection that we specify
     * here. Here we want to check that if we call delete then we end
     * up with an exception being thrown.
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testDeleteUnknownConnection() throws ConnectionStoreException
    {
        final String TO_DELETE = "to_delete";

        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );

        EasyMock.expect( new Boolean( mMapMock_.containsKey( TO_DELETE ) ) )
            .andReturn( new Boolean( false ) )
            .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDao_.deleteNamedConnection( TO_DELETE );
            LOG.error( "Suceeded in deleting connection with name [" + TO_DELETE
                       + "] .. this is bad as the connection does not exist" );
            Assert.fail( "Succeeded in deleting connection with the name [" + TO_DELETE + "]" );
        }
        catch ( ConnectionStoreException e )
        {
            LOG.info( "Correctly thrown exception for the missing connection" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly found no connection to delete" );
    }

    /**
     * here we simply want to find out of the connection exists or not
     * in the persistent store
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testDoesValidConnectionExist() throws ConnectionStoreException
    {
        final String FIND_ME = "find_me";
        final Set<String> set = new HashSet<String>();
        set.add( FIND_ME );
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( mMapMock_.keySet() ).andReturn( set ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        boolean exists = mDao_.doesConnectionExist( FIND_ME );
        LOG.debug( "Connection exists test for connection [" + FIND_ME + "] returns [" + exists
                   + "]" );
        if ( !exists )
        {
            Assert.fail( "The connection [" + FIND_ME + "] should exist" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly found that the connection exits" );
    }

    /**
     * here we simply want to find out of the connection exists or not
     * in the persistent store
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testDoesInvalidConnectionExist() throws ConnectionStoreException
    {
        final String DONT_FIND_ME = "dont_find_me";
        final Set<String> set = new HashSet<String>();
        set.add( "hahhahahahaha" );
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( mMapMock_.keySet() ).andReturn( set ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        boolean exists = mDao_.doesConnectionExist( DONT_FIND_ME );
        LOG.debug( "Connection exists test for connection [" + DONT_FIND_ME + "] returns ["
                   + exists + "]" );
        if ( exists )
        {
            Assert.fail( "The connection [" + DONT_FIND_ME + "] should not exist" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly found that the connection doesn not exist" );
    }

    /**
     * in this test we want to check that when we try and load a valid
     * connection details that it is retrieved correctly
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testLoadConnectionParameters() throws ConnectionStoreException
    {
        final String LOAD_ME = "load_me";
        ConnectionDetails testConn = new ConnectionDetails( LOAD_ME );
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( new Boolean( mMapMock_.containsKey( LOAD_ME ) ) )
            .andReturn( new Boolean( true ) )
            .times( 1 );
        EasyMock.expect( mMapMock_.get( LOAD_ME ) ).andReturn( testConn ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        ConnectionDetails got = null;
        try
        {
            got = mDao_.loadConnectionParameters( LOAD_ME );
        }
        catch ( ConnectionStoreException ce )
        {
            String err = "Failed to load connection store details for [" + LOAD_ME + "]";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        Assert.assertNotNull( got );
        Assert.assertEquals( testConn, got );

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has successfully loaded connection with name of [" + LOAD_ME + "]" );
    }

    /**
     * here we want to test that if we try and load a connection
     * parameters with no valid connections defined that we throw an
     * exception
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testFailToLoadConnectionParameters() throws ConnectionStoreException
    {
        final String LOAD_ME = "load_me";
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( new Boolean( mMapMock_.containsKey( LOAD_ME ) ) )
            .andReturn( new Boolean( false ) )
            .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        ConnectionDetails got = null;
        try
        {
            got = mDao_.loadConnectionParameters( LOAD_ME );

            String err = "Failed to throw connection store exception when loading connection ["
                         + LOAD_ME + "]";
            LOG.error( err );
            Assert.fail( err );
        }
        catch ( ConnectionStoreException ce )
        {
            LOG.debug( "Correctly caught exception: " + ce.getMessage() );
        }

        Assert.assertNull( got );

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has successfully failed to retrieve a connection called [" + LOAD_ME + "]" );
    }

    /**
     * In this test we ensure that we can save the data down to the
     * persistent store correctly
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testSaveConnectionParameters() throws ConnectionStoreException
    {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails( NEW_CONN );
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( new Boolean( mMapMock_.containsKey( NEW_CONN ) ) )
            .andReturn( new Boolean( false ) )
            .times( 1 );
        EasyMock.expect( mMapMock_.put( NEW_CONN, conn ) ).andReturn( conn ).times( 1 );
        mMock_.saveConnectionData( mMapMock_ );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDao_.saveConnectionParameters( NEW_CONN, conn );
        }
        catch ( ConnectionStoreException ce )
        {
            String err = "Failed to save connection store details for [" + NEW_CONN + "]";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has successfully added a new connection to the underlying store" );
    }

    /**
     * In this test we ensure that if we try and resave over the top
     * of an existing connection that we throw the correct exception
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testFailToSaveConnectionParameters() throws ConnectionStoreException
    {
        final String NEW_CONN = "new_connection";
        final ConnectionDetails conn = new ConnectionDetails( NEW_CONN );
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        EasyMock.expect( new Boolean( mMapMock_.containsKey( NEW_CONN ) ) )
            .andReturn( new Boolean( true ) )
            .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDao_.saveConnectionParameters( NEW_CONN, conn );
            String err = "Failed to throw exception when saving down new connection [" + NEW_CONN
                         + "]";
            LOG.error( err );
            Assert.fail( err );
        }
        catch ( ConnectionStoreException ce )
        {
            LOG.debug( "Exception correctly thrown when trying to persist connection [" + NEW_CONN
                       + "]" );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has successfully failed to add a new connection to the underlying store" );
    }

}
