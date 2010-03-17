/*
 * ScemaCreator.java created on 23 Apr 2007 18:35:39 by suggitpe for project SandBox - Hibernate
 * 
 */
package org.suggs.sandbox.hibernate.schemaCreator;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.Assert;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Schema creation class uses the hibernate configuration to drive the
 * creation of the schema
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2007
 */
public class SchemaCreator
{

    private static final Log LOG = LogFactory.getLog( SchemaCreator.class );

    private final static String FILE = "suggsSchema";

    /**
     * Constructs a new instance.
     */
    public SchemaCreator()
    {
        super();
    }

    /**
     * Main method that will manage the passing of the files to the
     * hibernate impl
     * 
     * @param aDestDir
     *            a directory to drop all of the resulting sql files
     * @param aCfg
     *            the configuration
     */
    public void createDDL( File aDestDir, Configuration aCfg )
    {
        // search for all opf the hbm files from a passed in dir
        Assert.notNull( aDestDir, "invalid dest dir for sql files" );

        Assert.isTrue( ( aDestDir.exists() && aDestDir.isDirectory() ), "The dest directory is invalid" );

        createSql( createNewSqlFile( aDestDir ), aCfg );
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
        File dest = new File( aDestDir.getAbsolutePath() + "/" + FILE + ".sql" );
        if ( dest.exists() )
        {
            LOG.error( "Overwriting previous sql file with [" + dest.getAbsolutePath() + "]" );
        }
        return dest.getAbsolutePath();
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
        // export.setHaltOnError( true );
        export.setDelimiter( ";" );
        export.setOutputFile( aDestFilename );
        export.create( false, false );
    }
}
