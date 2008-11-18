/*
 * JaxbXmlConnectionStoreManagerTest.java created on 2 Oct 2008 18:44:56 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl.JaxbXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.IFileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This test tests the Jaxb Xml Connection Store Manager using a Mock
 * for the File Manager.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class JaxbXmlConnectionStoreManagerUnitTest
{

    private static final Log LOG = LogFactory.getLog( JaxbXmlConnectionStoreManagerUnitTest.class );
    private JaxbXmlConnectionStoreManager mManager_;
    private IFileManager mFileManagerMock_;

    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- JaxbXmlConnectionStoreManagerTest" );
        mManager_ = new JaxbXmlConnectionStoreManager();
        mFileManagerMock_ = EasyMock.createStrictMock( IFileManager.class );
        mManager_.setFileManager( mFileManagerMock_ );
    }

    /**
     * This test will ensure that the xml connection store manager
     * will read data from the persistent store, transform it from the
     * String clob and then return it in a form that is understood
     */
    @Test
    public void testReadConnectionData() throws ConnectionStoreException, IOException
    {
        // ------- MOCK PREP
        EasyMock.expect( mFileManagerMock_.retrieveClob( (File) EasyMock.anyObject() ) )
            .andReturn( createTestXML() )
            .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mFileManagerMock_ );

        // ------- TEST EXEC
        Map<String, ConnectionDetails> map = mManager_.readConnectionData();
        Assert.assertTrue( map.size() == 2 );
        Assert.assertTrue( map.containsKey( "CONN_1" ) && map.containsKey( "CONN_2" ) );

        // ------- MOCK VERIFY
        EasyMock.verify( mFileManagerMock_ );

    }

    /**
     * Test that when we pass in a valid map of data into the manager
     * that the correct amount of xml is generated.
     * 
     * @throws ConnectionStoreException
     * @throws IOException
     */
    @Test
    public void testSaveConnectionDataWithValidData() throws ConnectionStoreException, IOException
    {
        Map<String, ConnectionDetails> in = new HashMap<String, ConnectionDetails>();
        populateMapWithDetails( in );

        // ------- MOCK PREP
        mFileManagerMock_.persistClob( (String) EasyMock.anyObject(), (File) EasyMock.anyObject() );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mFileManagerMock_ );

        // ------- TEST EXEC
        LOG.debug( "Testing that we can save marshall an map of empty connection details objects" );
        mManager_.saveConnectionData( in );

        // ------- MOCK VERIFY
        EasyMock.verify( mFileManagerMock_ );

    }

    /**
     * This test will ensure that when we pass in a map object that
     * this is then serialised back down into the correct form into
     * the clob when passed only partial information (this is an empty
     * map). Here we ensure that even though the data is empty that we
     * can ensure that not exceptions are thrown.
     * 
     * @throws ConnectionStoreException
     * @throws IOException
     */
    @Test
    public void testSaveConnectionDataWithEmptyData() throws ConnectionStoreException, IOException
    {
        Map<String, ConnectionDetails> in = new HashMap<String, ConnectionDetails>();

        // ------- MOCK PREP
        mFileManagerMock_.persistClob( (String) EasyMock.anyObject(), (File) EasyMock.anyObject() );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mFileManagerMock_ );

        // ------- TEST EXEC
        LOG.debug( "Testing that we can save marshall an map of empty connection details objects" );
        mManager_.saveConnectionData( in );

        // ------- MOCK VERIFY
        EasyMock.verify( mFileManagerMock_ );
    }

    /**
     * This test will ensure that if we pass in a null map of data
     * then an exception is thrown.
     */
    @Test(expected = ConnectionStoreException.class)
    public void testSaveConnectionDataWithNullData() throws ConnectionStoreException
    {
        // ------- MOCK PREP

        // ------- MOCK LOAD
        EasyMock.replay( mFileManagerMock_ );

        // ------- TEST EXEC
        LOG.debug( "Testing that we throw an error if the map is null" );
        mManager_.saveConnectionData( null );

        // ------- MOCK VERIFY
        EasyMock.verify( mFileManagerMock_ );
    }

    /**
     * This is the be all and end all of tests for the Jaxb
     * implementation
     */
    @Test
    public void testReadAndWriteConnectionData() throws ConnectionStoreException
    {
        Map<String, ConnectionDetails> origMap = new HashMap<String, ConnectionDetails>();
        populateMapWithDetails( origMap );

        // ------- TEST EXEC
        mManager_.setFileManager( new FileManagerStub() );
        mManager_.saveConnectionData( origMap );
        Map<String, ConnectionDetails> newMap = mManager_.readConnectionData();

        // ------- VALIDATE
        Assert.assertTrue( origMap.size() == newMap.size() );

        for ( String s : origMap.keySet() )
        {
            LOG.debug( "........." );
            LOG.debug( "Original: " + origMap.get( s ).toString() );
            LOG.debug( "New map : " + newMap.get( s ).toString() );
        }

        for ( String s : origMap.keySet() )
        {
            ConnectionDetails orig = origMap.get( s );
            ConnectionDetails nw = newMap.get( s );
            Assert.assertEquals( orig, nw );
        }
    }

    // ##########################################
    // /////////////// HELPERS //////////////////
    // ##########################################
    /**
     * This little helper will centralise the construction of a set of
     * connections that can be used as needed.
     */
    private void populateMapWithDetails( Map<String, ConnectionDetails> map )
    {
        ConnectionDetails d1 = new ConnectionDetails( "CONN_1", "EMS" );
        d1.setPort( 123 );
        d1.setHostname( "pgdsx01.org.suggs.uk" );

        d1.setSecurityDetails( "username", "password" );

        d1.addConnectionFactory( "Topic", "TopicConnFact1" );
        d1.addConnectionFactory( "Topic", "TopicConnFact2" );
        d1.addConnectionFactory( "Queue", "QueueConnFact1" );
        d1.addConnectionFactory( "Generic", "GenericConnFact1" );

        d1.addDestination( "Topic", "TopicDest1" );
        d1.addDestination( "Topic", "TopicDest2" );
        d1.addDestination( "Topic", "TopicDest3" );
        d1.addDestination( "Generic", "GenericDest1" );

        ConnectionDetails d2 = new ConnectionDetails( "CONN_2" );
        d2.setPort( 456 );
        d2.setHostname( "pgdsx02.org.suggs.uk" );
        d2.setType( "MQ" );
        map.put( d2.getName(), d2 );

        ConnectionDetails d3 = new ConnectionDetails( "CONN_3", "TEST_TYPE" );
        d3.setPort( 789 );
        d3.setHostname( "pgdsx03.org.suggs.uk" );
        map.put( d3.getName(), d3 );
    }

    /**
     * This method is used by the file manager mock to create the
     * serialised XML that can then be read
     * 
     * @return some test serialised xml
     */
    private String createTestXML()
    {
        StringBuffer ret = new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" );
        ret.append( "<connectionStore xmlns=\"http://www.suggs.org.uk/ConnectionStore\">\n" );
        ret.append( "<connection type=\"TEST_TYPE\" name=\"CONN_1\">\n" )
            .append( " <hostname>pgdsx01.org.suggs.uk</hostname>" )
            .append( "<port>123</port>\n" )
            .append( "<connectionFactories/>\n" )
            .append( "<destinations/>\n" )
            .append( "<metadata/>\n" )
            .append( "</connection>\n" );
        ret.append( "<connection type=\"TEST_TYPE\" name=\"CONN_2\">\n" )
            .append( " <hostname>pgdsx02.org.suggs.uk</hostname>" )
            .append( "<port>456</port>\n" )
            .append( "<connectionFactories/>\n" )
            .append( "<destinations/>\n" )
            .append( "<metadata/>\n" )
            .append( "</connection>\n" );
        ret.append( "</connectionStore>" );
        return ret.toString();
    }

}
