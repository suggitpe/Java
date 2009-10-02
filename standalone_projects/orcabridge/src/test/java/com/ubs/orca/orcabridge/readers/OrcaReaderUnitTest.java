/*
 * OrcaReaderTest.java created on 24 Sep 2009 06:54:48 by suggitpe for project Orca Bridge
 * 
 */
package com.ubs.orca.orcabridge.readers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ubs.orca.client.api.IOrcaClient;
import com.ubs.orca.client.api.OrcaException;
import com.ubs.orca.client.api.OrcaIdentity;
import com.ubs.orca.orcabridge.IMessageProcessor;
import com.ubs.orca.orcabridge.OrcaBridgeException;

/**
 * Test suite for the orca reader.
 * 
 * @author suggitpe
 * @version 1.0 24 Sep 2009
 */
public class OrcaReaderUnitTest
{

    private static final Log LOG = LogFactory.getLog( OrcaReaderUnitTest.class );
    private OrcaSingleMessageReader mOrcaReader_;
    private IOrcaClient mMockOrcaClient_;
    private IMessageProcessor mMockMessageProcessor_;

    private static String ORCA_TOKEN = "OrcaBridgeTestToken:1";
    private static String ORCA_URL = "tcp://localhost:7222";

    /**
     * Test setup
     * 
     * @throws Exception
     */
    @Before
    public void doBefore() throws Exception
    {
        LOG.debug( "-----------------" );
        mMockOrcaClient_ = EasyMock.createMock( IOrcaClient.class );
        mMockMessageProcessor_ = EasyMock.createMock( IMessageProcessor.class );
        mOrcaReader_ = new OrcaSingleMessageReader( new OrcaIdentity( ORCA_TOKEN ),
                                                    ORCA_URL,
                                                    mMockMessageProcessor_ );
        mOrcaReader_.setOrcaClient( mMockOrcaClient_ );

    }

    /**
     * Tests that when a client starts correctly, that no exceptions
     * are thrown etc
     * 
     * @throws Exception
     */
    @Test
    public void testCleanStart() throws Exception
    {
        mMockOrcaClient_.connect();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.start();
        EasyMock.expectLastCall().once();

        EasyMock.replay( mMockOrcaClient_ );

        mOrcaReader_.afterPropertiesSet();
        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mOrcaReader_.startReader();
        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_RUNNING );

        EasyMock.verify( mMockOrcaClient_ );
    }

    /**
     * Tests that when the client starts badly, that the exceptions
     * are handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStart() throws Exception
    {
        mMockOrcaClient_.connect();
        EasyMock.expectLastCall()
            .andThrow( new OrcaException( "This is an expected exception thrown from the OrcaBridge" ) );

        EasyMock.replay( mMockOrcaClient_ );

        mOrcaReader_.afterPropertiesSet();
        Assert.assertSame( "OrcaReader state is not correct:",
                           mOrcaReader_.getState(),
                           AbstractMessageReader.STATE_UNINITIALISED );
        mOrcaReader_.startReader();
        Assert.fail( "Test should not have reached this part of the test" );

        EasyMock.verify( mMockOrcaClient_ );
    }

    /**
     * Tests that when the client stops cleanly that the application
     * is deemed to be in a good running state
     * 
     * @throws Exception
     */
    @Test
    public void testCleanStop() throws Exception
    {
        mMockOrcaClient_.stop();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.disconnect();
        EasyMock.expectLastCall().once();

        EasyMock.replay( mMockOrcaClient_ );

        mOrcaReader_.afterPropertiesSet();
        mOrcaReader_.stopReader();

        EasyMock.verify( mMockOrcaClient_ );
    }

    /**
     * Tests that when the client stops badly, that all exceptions are
     * handled correctly.
     * 
     * @throws Exception
     */
    @Test(expected = OrcaBridgeException.class)
    public void testBadStop() throws Exception
    {
        mMockOrcaClient_.stop();
        EasyMock.expectLastCall().once();
        mMockOrcaClient_.disconnect();
        EasyMock.expectLastCall()
            .andThrow( new OrcaException( "This is an expected exception to be thrown" ) );

        EasyMock.replay( mMockOrcaClient_ );

        mOrcaReader_.afterPropertiesSet();
        mOrcaReader_.stopReader();

        Assert.fail( "The test should not have reached this part of the code" );

        EasyMock.verify( mMockOrcaClient_ );
    }

}
