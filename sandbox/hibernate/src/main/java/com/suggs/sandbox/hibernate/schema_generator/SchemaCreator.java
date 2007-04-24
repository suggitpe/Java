/*
 * ScemaCreator.java created on 23 Apr 2007 18:35:39 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schema_generator;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class SchemaCreator
{

    private static final Log LOG = LogFactory.getLog( SchemaCreator.class );

    /**
     * Main method that will manage the passing of the files to the
     * hibernate impl
     * 
     * @param aSrcDir
     *            a directory that contains the hbm files
     * @param aDestDir
     *            a directory to drop all of the resulting sql files
     */
    public void createSqlFiles( String aSrcDir, String aDestDir )
    {
        // search for all opf the hbm files from a passed in dir
        Assert.notNull( aSrcDir, "invalid src dir for hbm files" );
        Assert.notNull( aDestDir, "invalid dest dir for sql files" );

        File src = new File( aSrcDir );
        File dest = new File( aDestDir );

        Assert.isTrue( ( src.exists() && src.isDirectory() ), "The src directory is invalid" );
        Assert.isTrue( ( dest.exists() && dest.isDirectory() ), "The dest directory is invalid" );

        // first we go through all of the
        File[] hbms = src.listFiles( new FilenameFilter()
        {

            public boolean accept( File dir, String filename )
            {
                return filename.endsWith( "hbm.xml" );
            }
        } );

        Configuration cfg = createCfg( hbms );

        for ( int i = 0; i < hbms.length; ++i )
        {
            LOG.debug( "converting hbm file [" + hbms[i] + "]" );
            createSql( hbms[i], dest, cfg );
            LOG.debug( "converted file [" + hbms[i] + "]" );
        }
    }

    /**
     * simple method to create and add the resources to the
     * configuration object
     * 
     * @param aListOfHbms
     *            the hbm files to add
     * @return a new configuration object
     */
    private Configuration createCfg( File[] aListOfHbms )
    {
        Configuration cfg = new Configuration();
        for ( int i = 0; i < aListOfHbms.length; ++i )
        {
            cfg.addResource( aListOfHbms[i].getPath() );
        }

        return cfg;
    }

    /**
     * Method to interact to the hibernate interface to manage the
     * underlying sql file creation
     * 
     * @param aHbmFile
     *            an hbm file to forward generate
     * @param aDestDir
     *            a destination dir for the sql file
     * @param aCfg
     *            Configuartion obejct used by the schemaexport
     *            process
     */
    private void createSql( File aHbmFile, File aDestDir, Configuration aCfg )
    {

        SchemaExport export = new SchemaExport( aCfg );

    }
}
