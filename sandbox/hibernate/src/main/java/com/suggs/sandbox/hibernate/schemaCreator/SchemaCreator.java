/*
 * ScemaCreator.java created on 23 Apr 2007 18:35:39 by suggitpe for project SandBox - Hibernate
 * 
 */
package com.suggs.sandbox.hibernate.schemaCreator;

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

    private final static String FILE = "suggsSchema";

    private File mSrcDir_;

    private SchemaCreator()
    {
        // hidden
    }

    public SchemaCreator( final File aSrcDir )
    {
        Assert.notNull( aSrcDir, "Invalid src dir for hbm files" );
        Assert.isTrue( ( aSrcDir.exists() && aSrcDir.isDirectory() ), "The src directory is invalid" );
        mSrcDir_ = aSrcDir;
    }

    /**
     * Main method that will manage the passing of the files to the
     * hibernate impl
     * 
     * @param aSrcDir
     *            a directory that contains the hbm files
     * @param aDestDir
     *            a directory to drop all of the resulting sql files
     */
    public void createDDL( File aDestDir )
    {
        // search for all opf the hbm files from a passed in dir
        Assert.notNull( aDestDir, "invalid dest dir for sql files" );

        Assert.isTrue( ( aDestDir.exists() && aDestDir.isDirectory() ), "The dest directory is invalid" );

        // first we go through all of the
        File[] hbms = mSrcDir_.listFiles( new FilenameFilter()
        {

            public boolean accept( File dir, String filename )
            {
                return filename.endsWith( "hbm.xml" );
            }
        } );

        Configuration cfg = createCfg( hbms );

        createSql( createNewSqlFile( aDestDir ), cfg );
    }

    /**
     * This method will create a new file in the destination directory
     * after archiving off the exiting files so that no data is lost
     * 
     * @param aDestDir
     *            the directory for the new file
     * @return the name of the new file
     */
    private String createNewSqlFile( File aDestDir )
    {
        File dest = new File( aDestDir.getAbsolutePath()  + "/" + FILE + ".sql" );
        if ( dest.exists() )
        {
            LOG.error( "Overwriting previous sql file with [" + dest.getAbsolutePath() + "]" );
        }
        return dest.getAbsolutePath();
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
            cfg.addResource( "hbm/manual/" + aListOfHbms[i].getName() );
        }

        return cfg;
    }

    /**
     * Method to interact to the hibernate interface to manage the
     * underlying sql file creation
     * 
     * @param aDestFilename
     *            a destination filename for the sql file
     * @param aCfg
     *            Configuartion obejct used by the schemaexport
     *            process
     */
    private void createSql( String aDestFilename, Configuration aCfg )
    {
        SchemaExport export = new SchemaExport( aCfg );
        //export.setHaltOnError( true );
        export.setDelimiter( ";" );
        export.setOutputFile( aDestFilename );
        export.create( false, false );
    }
}
