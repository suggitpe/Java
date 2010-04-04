/*
 * SchemaCreatorTest.java created on 24 Apr 2007 18:21:26 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.caveatEmptor;

import org.suggs.sandbox.hibernate.basicentity.Message;
import org.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;
import org.suggs.sandbox.hibernate.support.SchemaCreator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Test object
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:xml/ut-schema-creator-test.xml" })
public class SchemaCreatorTest {

    private static final Log LOG = LogFactory.getLog( SchemaCreatorTest.class );

    /**
     * test for the schema creator
     */
    @Test
    public void testCreateSchema() {
        LOG.debug( "Creating dirs" );
        ClassPathResource destRes = new ClassPathResource( "sql" );
        File sqlDir = null;
        try {
            sqlDir = destRes.getFile();
            LOG.debug( "Created directory [" + sqlDir.getAbsolutePath() + "]" );
        }
        catch ( IOException ioe ) {
            ioe.printStackTrace();
            Assert.fail( ioe.getMessage() );
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
    protected Configuration createCfg() {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.configure( "hibernate.cfg.xml" );
        cfg.addAnnotatedClass( AbstractPersistentBaseClass.class );
        cfg.addAnnotatedClass( Address.class );
        cfg.addAnnotatedClass( BankAccount.class );
        cfg.addAnnotatedClass( Bid.class );
        cfg.addAnnotatedClass( BillingDetails.class );
        cfg.addAnnotatedClass( Category.class );
        cfg.addAnnotatedClass( Comment.class );
        cfg.addAnnotatedClass( CreditCard.class );
        cfg.addAnnotatedClass( Item.class );
        cfg.addAnnotatedClass( User.class );
        cfg.addAnnotatedClass( Message.class );

        return cfg;
    }
}
