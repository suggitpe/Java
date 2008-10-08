/*
 * CachedXmlConnectionStoreManagerDecoratorTest.java created on 2 Oct 2008 18:55:29 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.ConnectionStoreException;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.IXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl.CachedXmlConnectionStoreManagerDecorator;

import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test the cached xml connection store decorator. The
 * main things that this test will do is to make sure that the
 * decorator actually uses the cache rather than going to the
 * persistent store.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class CachedXmlConnectionStoreManagerDecoratorUnitTest
{

    private static final Log LOG = LogFactory.getLog( CachedXmlConnectionStoreManagerDecoratorUnitTest.class );

    private CachedXmlConnectionStoreManagerDecorator mDecorator_;
    private IXmlConnectionStoreManager mMock_;
    private Map<String, ConnectionDetails> mMapMock_;

    /**
     * This is called before the execution of each test
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- CachedXmlConnectionStoreManagerDecoratorTest" );
        mDecorator_ = new CachedXmlConnectionStoreManagerDecorator();

        LOG.debug( "Creating mock for IXmlConnectionStoreManager" );
        // create the mocks
        mMock_ = EasyMock.createStrictMock( IXmlConnectionStoreManager.class );
        mDecorator_.setXmlConnectionStoreManager( mMock_ );
        mMapMock_ = EasyMock.createStrictMock( Map.class );
    }

    /**
     * In this test we want to verify that if we call the/ decorator a
     * few times that we only ever call the persistent/ store once (ie
     * it uses the cache).
     * 
     * @throws ConnectionStoreException.
     */
    @Test
    public void testReadConectionData() throws ConnectionStoreException
    {
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDecorator_.readConnectionData();
            mDecorator_.readConnectionData();
            mDecorator_.readConnectionData();
        }
        catch ( ConnectionStoreException ce )
        {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly read the connection data three times" );
    }

    /**
     * This test will read a new map from the store, then save down
     * the same one again to test the cache is being used correctly
     * (ie only reads once)
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testSaveConectionData() throws ConnectionStoreException
    {
        // ------- MOCK PREP
        mMock_.saveConnectionData( mMapMock_ );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        try
        {
            mDecorator_.saveConnectionData( mMapMock_ );
        }
        catch ( ConnectionStoreException ce )
        {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        mDecorator_.readConnectionData();

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly saved and then read the map without going to the underlying store" );
    }

    /**
     * This test will read a new map from the store, then save down
     * the same one again to test the cache is being used correctly
     * (ie only reads once)
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testSaveAfterReadConectionData() throws ConnectionStoreException
    {
        // ------- MOCK PREP
        EasyMock.expect( mMock_.readConnectionData() ).andReturn( mMapMock_ ).times( 1 );
        mMock_.saveConnectionData( mMapMock_ );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mMock_ );
        EasyMock.replay( mMapMock_ );

        // ------- TEST EXEC
        Map<String, ConnectionDetails> map = mDecorator_.readConnectionData();
        try
        {
            mDecorator_.saveConnectionData( map );
        }
        catch ( ConnectionStoreException ce )
        {
            String err = "";
            LOG.error( err, ce );
            Assert.fail( err );
        }

        Map<String, ConnectionDetails> map2 = mDecorator_.readConnectionData();
        Assert.assertEquals( map, map2 );
        Assert.assertSame( map, map2 );

        // ------- MOCK VERIFY
        EasyMock.verify( mMock_ );
        EasyMock.verify( mMapMock_ );

        LOG.debug( "Test has correctly read, saved and then re-read the map" );
    }
}
