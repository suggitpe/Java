/*
 * SchemaCreatorTest.java created on 24 Apr 2007 18:21:26 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schemaCreator;

import com.suggs.sandbox.hibernate.caveatEmptor.Address;
import com.suggs.sandbox.hibernate.caveatEmptor.BankAccount;
import com.suggs.sandbox.hibernate.caveatEmptor.Bid;
import com.suggs.sandbox.hibernate.caveatEmptor.BillingDetails;
import com.suggs.sandbox.hibernate.caveatEmptor.Category;
import com.suggs.sandbox.hibernate.caveatEmptor.Comment;
import com.suggs.sandbox.hibernate.caveatEmptor.CreditCard;
import com.suggs.sandbox.hibernate.caveatEmptor.Item;
import com.suggs.sandbox.hibernate.caveatEmptor.User;
import com.suggs.sandbox.hibernate.caveatEmptor.support.AbstractPersistentBaseClass;
import com.suggs.sandbox.hibernate.chapter2.Message;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class AbstractSchemaCreatorTest extends AbstractDependencyInjectionSpringContextTests
{

    private static final Log LOG = LogFactory.getLog( AbstractSchemaCreatorTest.class );

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
        creator.createDDL( sqlDir, createAnnotationCfg() );

        LOG.info( "SQL files created correctly" );
    }

    private Configuration createAnnotationCfg()
    {
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

    /**
     * Create a configuration object
     * 
     * @param aSrcDir
     *            the dir containing the hbm files
     * @return a new cfg object
     */
    protected Configuration creatCfg( File aSrcDir )
    {
        // first we go through all of the
        File[] hbms = aSrcDir.listFiles( new FilenameFilter()
        {

            public boolean accept( File dir, String filename )
            {
                return filename.endsWith( "hbm.xml" );
            }
        } );

        Configuration cfg = new Configuration().configure( "hibernate.cfg.xml" );
        for ( File f : hbms )
        {
            cfg.addResource( "hbm/manual/" + f.getName() );
        }

        return cfg;
    }
}
