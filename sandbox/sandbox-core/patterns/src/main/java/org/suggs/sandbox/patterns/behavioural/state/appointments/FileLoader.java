/*
 * FileLoader.java created on 4 Aug 2009 07:12:19 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.behavioural.state.appointments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * File persistence helper class.
 * 
 * @author suggitpe
 * @version 1.0 4 Aug 2009
 */
public class FileLoader {

    // static logger
    private static final Log LOG = LogFactory.getLog( FileLoader.class );

    private FileLoader() {}

    /**
     * Loads a file from a given place and returns it as an object.
     * 
     * @param aInputFile
     *            the file from which to read the underlying data
     * @return an object representation of the contents of the file
     */
    public static Object loadData( File aInputFile ) {
        Object ret = null;
        try {
            if ( aInputFile.exists() ) {
                if ( aInputFile.isFile() ) {
                    ObjectInputStream readIn = new ObjectInputStream( new FileInputStream( aInputFile ) );
                    try {
                        ret = readIn.readObject();
                    }
                    finally {
                        readIn.close();
                    }
                }
                else {
                    LOG.error( "Input file [" + aInputFile + "] is a directory" );
                }
            }
            else {
                LOG.error( "Input file [" + aInputFile + "] does not exist" );
            }
        }
        catch ( ClassNotFoundException cnfe ) {
            LOG.error( "Error converting file contents from [" + aInputFile + "] into an object", cnfe );
        }
        catch ( IOException ioe ) {
            LOG.error( "Error reading in data from file [" + aInputFile + "]", ioe );
        }
        return ret;
    }

    /**
     * Persist an object into a file
     * 
     * @param aOutputFile
     *            the file to write to
     * @param aData
     *            the data to write into the file
     */
    public static void storeData( File aOutputFile, Serializable aData ) {
        try {
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( aOutputFile ) );
            try {
                out.writeObject( aData );
            }
            finally {
                out.close();
            }
        }
        catch ( IOException ioe ) {
            LOG.error( "Failed to write data to the file [" + aOutputFile.getAbsolutePath() + "]", ioe );
        }
    }
}
