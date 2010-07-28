/*
 * FileManagerStub.java created on 13 Oct 2008 18:28:32 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.connectionstore;

import org.suggs.apps.mercury.model.util.file.IFileManager;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a stub of a file manager interface that will take the persisted string and return it back again in
 * the receive method. It uses no underlying persistent store.
 * 
 * @author suggitpe
 * @version 1.0 13 Oct 2008
 */
public class FileManagerStub implements IFileManager {

    private static final Log LOG = LogFactory.getLog( FileManagerStub.class );
    private String data;

    /**
     * Constructs a new instance.
     */
    public FileManagerStub() {
        super();
        LOG.debug( "Creating a new FileManagerStub" );
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#persistClobToFile(java.lang.String,
     *      java.io.File)
     */
    @Override
    public void persistClobToFile( String clob, File file ) {
        LOG.debug( "Storing the persisted clob data in memory for file [" + file.getAbsolutePath() + "]" );
        data = clob;
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClobFromFile(java.io.File)
     */
    @Override
    public String retrieveClobFromFile( File file ) {
        LOG.debug( "Retrieving the clob data from the in memory store" );
        return data;
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveClobFromResource(java.lang.String)
     */
    @Override
    public String retrieveClobFromResource( String resource ) {
        LOG.debug( "Retrieving the clob data from the in memory store" );
        return data;
    }

    /**
     * @see org.suggs.apps.mercury.model.util.file.IFileManager#retrieveBytesFromFile(java.io.File)
     */
    @Override
    public byte[] retrieveBytesFromFile( File file ) {
        return data.getBytes();
    }

}
