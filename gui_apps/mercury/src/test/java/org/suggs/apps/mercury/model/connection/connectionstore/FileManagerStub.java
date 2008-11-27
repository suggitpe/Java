/*
 * FileManagerStub.java created on 13 Oct 2008 18:28:32 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a stub of a file manager interface that will take the
 * persisted string and return it back again in the receive method. It
 * uses no underlying persistent store.
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2008
 */
public class FileManagerStub implements IFileManager
{

    private static final Log LOG = LogFactory.getLog( FileManagerStub.class );
    private String mData_;

    /**
     * Constructs a new instance.
     */
    public FileManagerStub()
    {
        super();
        LOG.debug( "Creating a new FileManagerStub" );
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#persistClob(java.lang.String,
     *      java.io.File)
     */
    public void persistClobToFile( String clob, File file ) throws IOException
    {
        LOG.debug( "Storing the persisted clob data in memory for file [" + file.getAbsolutePath()
                   + "]" );
        mData_ = clob;
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClob(java.io.File)
     */
    public String retrieveClobFromFile( File file ) throws IOException
    {
        LOG.debug( "Retrieving the clob data from the in memory store" );
        return mData_;
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClobFromResource(java.lang.String)
     */
    public String retrieveClobFromResource( String resource ) throws IOException
    {
        LOG.debug( "Retrieving the clob data from the in memory store" );
        return mData_;
    }

}
