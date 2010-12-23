/*
 * MessageBuilderTest.java created on 17 Feb 2010 07:15:47 by suggitpe for project sandbox-jaxb-test-client
 * 
 */
package org.suggs.sandbox.jaxb.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Write javadoc for MessageBuilderTest
 * 
 * @author suggitpe
 * @version 1.0 17 Feb 2010
 */
public class MessageBuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger( MessageBuilderTest.class );

    @Test
    public void createBasicXmlMessage() {
        LOG.debug( "Creating test XML file" );
    }

    @Test
    public void validateGoodXmlMessage() {
        LOG.debug( "Validating good XML message" );
    }

    @Test
    public void validateBadXmlMessage() {
        LOG.debug( "Validating bad XML message" );
    }

    @Test
    public void serialiseValidXmlMessage() {
        LOG.debug( "Serialising valid XML message" );
    }

    @Test
    public void serialiseInvalidXmlMessage() {
        LOG.debug( "Serialising invalid XML message" );
    }

    @Test
    public void readGoodXmlFileIntoMemory() {
        LOG.debug( "Reading XML file from disk" );
    }

    @Test
    public void readBadXmlFileIntoMemory() {
        LOG.debug( "Reading XML file from disk" );
    }
}
