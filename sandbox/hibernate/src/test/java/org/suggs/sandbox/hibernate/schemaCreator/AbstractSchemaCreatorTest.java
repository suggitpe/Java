/*
 * SchemaCreatorTest.java created on 24 Apr 2007 18:21:26 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.schemaCreator;

import org.suggs.sandbox.hibernate.schemaCreator.SchemaCreator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import org.hibernate.cfg.Configuration;

/**
 * 
 * TODO Write javadoc for AbstractSchemaCreatorTest
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public abstract class AbstractSchemaCreatorTest extends AbstractDependencyInjectionSpringContextTests
{

    private static final Log LOG = LogFactory.getLog( AbstractSchemaCreatorTest.class );

    @Override
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
        ClassPathResource destRes = new ClassPathResource( "sql/auto" );
        File sqlDir = null;
        try
        {
            sqlDir = destRes.getFile();
        }
        catch ( IOException ioe )
        {
            ioe.printStackTrace();
            fail( ioe.getMessage() );
        }

        LOG.debug( "Creating Schema Creator" );
        SchemaCreator creator = new SchemaCreator();
        LOG.debug( "Creating the configuration object" );
        Configuration cfg = createCfg();
        LOG.debug( "Creating SQL" );
        creator.createDDL( sqlDir, cfg );

        LOG.info( "SQL files created correctly" );
    }

    /**
     * Allows delegation classes to define the manner in which they
     * construction the hibernate configuratikon object
     * 
     * @return a Configuration object
     */
    protected abstract Configuration createCfg();
}
