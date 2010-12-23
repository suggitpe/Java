/*
 * JaxbXmlConnectionStoreManagerTest.java created on 2 Oct 2008 18:44:56 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.connection.ConnectionDetails;
import org.suggs.apps.mercury.model.connection.connectionstore.xmldao.impl.JaxbXmlConnectionStoreManager;
import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * This test tests the Jaxb Xml ConnectionContext Store Manager using a Mock for the File Manager.
 * 
 * @author suggitpe
 * @version 1.0 2 Oct 2008
 */
public class JaxbXmlConnectionStoreManagerTest {

    private static final Logger LOG = LoggerFactory.getLogger( JaxbXmlConnectionStoreManagerTest.class );
    private JaxbXmlConnectionStoreManager connectionStoreManager;
    private IFileManager mockFileManager;

    @Before
    public void setUp() throws Exception {
        LOG.debug( "------------------- JaxbXmlConnectionStoreManagerTest" );
        mockFileManager = EasyMock.createStrictMock( IFileManager.class );
        connectionStoreManager = new JaxbXmlConnectionStoreManager( mockFileManager, "no care" );
    }

    /**
     * This test will ensure that the xml connection store manager will read data from the persistent store,
     * transform it from the String clob and then return it in a form that is understood
     * 
     * @throws ConnectionStoreException
     * @throws IOException
     */
    @Test
    public void testReadConnectionData() throws ConnectionStoreException, IOException {
        // ------- MOCK PREP
        EasyMock.expect( mockFileManager.retrieveClobFromFile( (File) EasyMock.anyObject() ) )
            .andReturn( createTestXML() )
            .times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockFileManager );

        // ------- TEST EXEC
        Map<String, ConnectionDetails> map = connectionStoreManager.readConnectionData();
        Assert.assertTrue( map.size() == 2 );
        Assert.assertTrue( map.containsKey( "CONN_1" ) && map.containsKey( "CONN_2" ) );

        // ------- MOCK VERIFY
        EasyMock.verify( mockFileManager );

    }

    /**
     * Test that when we pass in a valid map of data into the manager that the correct amount of xml is
     * generated.
     * 
     * @throws ConnectionStoreException
     * @throws IOException
     */
    @Test
    public void testSaveConnectionDataWithValidData() throws ConnectionStoreException, IOException {
        Map<String, ConnectionDetails> in = new HashMap<String, ConnectionDetails>();
        populateMapWithDetails( in );

        // ------- MOCK PREP
        mockFileManager.persistClobToFile( (String) EasyMock.anyObject(), (File) EasyMock.anyObject() );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockFileManager );

        // ------- TEST EXEC
        LOG.debug( "Testing that we can save marshall an map of empty connection details objects" );
        connectionStoreManager.saveConnectionData( in );

        // ------- MOCK VERIFY
        EasyMock.verify( mockFileManager );

    }

    /**
     * This test will ensure that when we pass in a map object that this is then serialised back down into the
     * correct form into the clob when passed only partial information (this is an empty map). Here we ensure
     * that even though the data is empty that we can ensure that not exceptions are thrown.
     * 
     * @throws ConnectionStoreException
     * @throws IOException
     */
    @Test
    public void testSaveConnectionDataWithEmptyData() throws ConnectionStoreException, IOException {
        Map<String, ConnectionDetails> in = new HashMap<String, ConnectionDetails>();

        // ------- MOCK PREP
        mockFileManager.persistClobToFile( (String) EasyMock.anyObject(), (File) EasyMock.anyObject() );
        EasyMock.expectLastCall().times( 1 );

        // ------- MOCK LOAD
        EasyMock.replay( mockFileManager );

        // ------- TEST EXEC
        LOG.debug( "Testing that we can save marshall an map of empty connection details objects" );
        connectionStoreManager.saveConnectionData( in );

        // ------- MOCK VERIFY
        EasyMock.verify( mockFileManager );
    }

    /**
     * This test will ensure that if we pass in a null map of data then an exception is thrown.
     * 
     * @throws ConnectionStoreException
     */
    @Test(expected = ConnectionStoreException.class)
    public void testSaveConnectionDataWithNullData() throws ConnectionStoreException {
        // ------- MOCK PREP

        // ------- MOCK LOAD
        EasyMock.replay( mockFileManager );

        // ------- TEST EXEC
        LOG.debug( "Testing that we throw an error if the map is null" );
        connectionStoreManager.saveConnectionData( null );

        // ------- MOCK VERIFY
        EasyMock.verify( mockFileManager );
    }

    /**
     * This is the be all and end all of tests for the Jaxb implementation
     * 
     * @throws ConnectionStoreException
     */
    @Test
    public void testReadAndWriteConnectionData() throws ConnectionStoreException {
        Map<String, ConnectionDetails> origMap = new HashMap<String, ConnectionDetails>();
        populateMapWithDetails( origMap );

        // ------- TEST EXEC
        connectionStoreManager.setFileManager( new FileManagerStub() );
        connectionStoreManager.saveConnectionData( origMap );
        Map<String, ConnectionDetails> newMap = connectionStoreManager.readConnectionData();

        // ------- VALIDATE
        Assert.assertTrue( origMap.size() == newMap.size() );

        for ( String s : origMap.keySet() ) {
            LOG.debug( "........." );
            LOG.debug( "Original: " + origMap.get( s ).toString() );
            LOG.debug( "New map : " + newMap.get( s ).toString() );
        }

        for ( String s : origMap.keySet() ) {
            ConnectionDetails orig = origMap.get( s );
            ConnectionDetails nw = newMap.get( s );
            Assert.assertEquals( orig, nw );
        }
    }

    // ##########################################
    // /////////////// HELPERS //////////////////
    // ##########################################
    /**
     * This little helper will centralise the construction of a set of connections that can be used as needed.
     */
    private void populateMapWithDetails( Map<String, ConnectionDetails> map ) {
        ConnectionDetails d1 = new ConnectionDetails( "CONN_1", "EMS" );
        d1.setPort( 123 );
        d1.setHostname( "pgdsx01.org.suggs.uk" );

        d1.setSecurityDetails( "username", "password" );

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
     * This method is used by the file manager mock to create the serialised XML that can then be read
     * 
     * @return some test serialised xml
     */
    private String createTestXML() {
        StringBuffer ret = new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" );
        ret.append( "<connectionStore xmlns=\"http://www.suggs.org.uk/ConnectionStore\">\n" );
        ret.append( "<connection type=\"TEST_TYPE\" name=\"CONN_1\">\n" )
            .append( "<parameters>" )
            .append( "<hostname>pgdsx01.org.suggs.uk</hostname>" )
            .append( "<port>123</port>\n" )
            .append( "<connectiondataitems/>\n" )
            .append( "</parameters>" )
            .append( "</connection>\n" );
        ret.append( "<connection type=\"TEST_TYPE\" name=\"CONN_2\">\n" )
            .append( "<parameters>" )
            .append( "<hostname>pgdsx02.org.suggs.uk</hostname>" )
            .append( "<port>456</port>\n" )
            .append( "<connectiondataitems/>\n" )
            .append( "</parameters>" )
            .append( "</connection>\n" );
        ret.append( "</connectionStore>" );
        return ret.toString();
    }

}
