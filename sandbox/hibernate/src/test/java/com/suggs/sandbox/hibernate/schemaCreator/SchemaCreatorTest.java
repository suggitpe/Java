/*
 * SchemaCreatorTest.java created on 24 Apr 2007 18:21:26 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schemaCreator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SchemaCreatorTest extends AbstractDependencyInjectionSpringContextTests
{

    private static final Log LOG = LogFactory.getLog( SchemaCreatorTest.class );

    protected String[] getConfigLocations()
    {
        return new String[] { "xml/ut-schema-creator-test.xml" };
    }

    /**
     * test for the schema creator
     */
    public void testCreateSchema()
    {
        LOG.debug( "Creating dirs" );
        ClassPathResource srcRes = new ClassPathResource( "hbm/manual" );
        ClassPathResource destRes = new ClassPathResource( "sql/auto" );
        File hbmDir = null;
        File sqlDir = null;
        try
        {
            hbmDir = srcRes.getFile();
            sqlDir = destRes.getFile();
        }
        catch ( IOException ioe )
        {
            ioe.printStackTrace();
            fail( ioe.getMessage() );
        }

        LOG.debug( "Creating Schema Creator" );
        SchemaCreator creator = new SchemaCreator( hbmDir );
        LOG.debug( "Creating SQL" );
        creator.createDDL( sqlDir );
        
        LOG.info( "SQL files created correctly" );
    }
}
