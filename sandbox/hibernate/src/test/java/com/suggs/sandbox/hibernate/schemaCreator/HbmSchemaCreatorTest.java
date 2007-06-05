/*
 * HbmSchemaCreatorTest.java created on 5 Jun 2007 06:20:59 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schemaCreator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import org.hibernate.cfg.Configuration;

public class HbmSchemaCreatorTest extends AbstractSchemaCreatorTest
{

    @Override
    protected Configuration createCfg()
    {
        ClassPathResource srcRes = new ClassPathResource( "hbm/manual" );
        File hbmDir = null;
        try
        {
            hbmDir = srcRes.getFile();
        }
        catch ( IOException ioe )
        {
            throw new IllegalStateException( "Failed to create dir object for hbm files" );
        }

        // first we go through all of the
        File[] hbms = hbmDir.listFiles( new FilenameFilter()
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
