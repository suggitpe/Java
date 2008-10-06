/*
 * MercuryFilePersister.java created on 3 Oct 2008 18:49:23 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.impl;

import org.suggs.apps.mercury.model.util.IMercuryFileManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This implementation is the basic implementation that uses the NIO
 * channels for persistence and retrieval.
 * 
 * @author suggitpe
 * @version 1.0 3 Oct 2008
 */
public class MercuryFileManager implements IMercuryFileManager
{

    private static final Log LOG = LogFactory.getLog( MercuryFileManager.class );

    /**
     * Persist CLOB data to the underlying persistence layer
     * 
     * @param aClob
     *            the clob data to persist
     * @param aFile
     *            the file to persist the data
     * @throws IOException
     *             if there are any issues in the persistence
     */
    public final void persistClob( String aClob, File aFile ) throws IOException
    {
        // first ensure that the persistence layer is OK to use
        verifyPersistenceLayer( aFile );

        // now we persist the new values to the persistent file
        FileOutputStream out = null;
        FileChannel chan = null;
        try
        {
            LOG.debug( "Overwriting existing xml persistence layer with new file at ["
                       + aFile.getCanonicalPath() + "]" );
            out = new FileOutputStream( aFile );
            chan = out.getChannel();
            int xmlSize = aClob.getBytes().length;

            ByteBuffer buff = ByteBuffer.allocate( xmlSize );
            buff.put( aClob.getBytes() );
            // flip will move the buffer limit to where the buffer
            // pointer now sits
            buff.flip();

            chan.write( buff );
        }
        catch ( IOException ioe )
        {
            LOG.warn( "Exception caught when persisting file [" + aFile.getAbsolutePath() + "]: "
                      + ioe.getMessage() + "\n.. rethrowing it" );
            throw ioe;
        }
        finally
        {
            try
            {
                chan.close();
                out.close();
            }
            catch ( IOException ioe )
            {
                LOG.warn( "Caught IOException when closing file channel [" + ioe.getMessage() + "]" );
            }
        }
    }

    /**
     * Verifies that the persistence layer is read for the new file to
     * be persisted.
     * 
     * @throws IOException
     *             if there are any issues in the persistence layer
     */
    private static void verifyPersistenceLayer( File aFile ) throws IOException
    {
        // check that the persistence dir exists
        File dir = new File( aFile.getAbsolutePath() );

        if ( !( dir.exists() ) )
        {
            LOG.warn( "Mercury home dir does not exist or is not writable ... assuming firt application execution" );
            LOG.info( "Creating MERCURY xml persistence store dir [" + dir.getAbsolutePath() + "]" );
            if ( !( dir.mkdir() ) )
            {
                throw new IOException( "Failed to create persistence dir [" + MERCURY_HOME_DIR
                                       + "]" );
            }
        }
        else
        {
            if ( !( dir.isDirectory() ) || !( dir.canWrite() ) )
            {
                String err = "Cannot write to the mercury persistence directory ["
                             + MERCURY_HOME_DIR + "]";
                LOG.error( err );
                throw new IOException( err );
            }
        }

        // now check that the actual file exists
        if ( !( MERCURY_FILE.exists() ) )
        {
            createPersistenceFile();
        }
        else
        {
            if ( !( MERCURY_FILE.isFile() ) || !( MERCURY_FILE.canWrite() ) )
            {
                String err = "Cannot write to mercury persistence layer ["
                             + MERCURY_FILE.getAbsolutePath() + "]";
                LOG.error( err );
                throw new IOException( err );
            }
        }

        LOG.debug( "XML connection store manager persistence layer correctly set up for persistence" );
    }

    /**
     * Creates the underlying file that we will use.
     * 
     * @throws IOException
     *             if there are issues in the creation of the file
     */
    private static final void createPersistenceFile() throws IOException
    {
        LOG.info( "Creating MERCURY xml persistence store [" + MERCURY_FILE.getAbsolutePath() + "]" );
        try
        {
            if ( !( MERCURY_FILE.createNewFile() ) )
            {
                throw new IOException( "Failed to create the persistence xml file ["
                                       + MERCURY_FILE.getAbsolutePath() + "]" );
            }
        }
        catch ( IOException ioe )
        {
            LOG.error( "Failed to create persistence file [" + ioe.getMessage() + "]" );
            throw ioe;
        }
    }

}
