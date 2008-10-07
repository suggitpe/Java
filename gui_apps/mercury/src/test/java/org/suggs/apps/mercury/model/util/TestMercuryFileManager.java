/*
 * TestMercuryFileManager.java created on 6 Oct 2008 06:42:58 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util;

import org.suggs.apps.mercury.model.util.impl.MercuryFileManager;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This integration test tests that the file manager will work against
 * a given file system. That it correctly finds files that are
 * incorrectly created for use with Mercury.
 * 
 * @author suggitpe
 * @version 1.0 6 Oct 2008
 */
public class TestMercuryFileManager
{

    private static final Log LOG = LogFactory.getLog( TestMercuryFileManager.class );
    private IMercuryFileManager mFileManager_;
    private static final String TEST_ROOT = "c:";
    private static final String TEST_DIR = TEST_ROOT + "/test";
    private static final String TEST_FILE = "dummyFile.txt";
    private static final String DUMMY_CLOB = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * This is run before all of the tests so that they are correctly
     * set up
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception
    {
        LOG.debug( "------------------- TestMercuryFileManager" );
        mFileManager_ = new MercuryFileManager();
    }

    /**
     * This is run after each test so that we do not polute the local
     * drives with our crap.
     * 
     * @throws Exception
     */
    @After
    public void teardown() throws Exception
    {
        LOG.debug( "Deleting file [" + TEST_DIR + "/" + TEST_FILE + "]" );
        File file = new File( TEST_DIR + "/" + TEST_FILE );
        file.delete();

        LOG.debug( "Deleting directory [" + TEST_DIR + "]" );
        File dir = new File( TEST_DIR );
        if ( dir.isDirectory() )
        {
            dir.delete();
        }
        else
        {
            dir.deleteOnExit();
        }
        LOG.debug( "------------------- end of TearDown" );
    }

    /**
     * Test that we can correctly create a file in a directory and
     * that we can then later read it
     * 
     * @throws IOException
     *             if there is any issue in the persistence of the
     *             file.
     */
    @Test
    public void testCorrectlyWriteFile() throws IOException
    {
        mFileManager_.persistClob( DUMMY_CLOB, new File( TEST_DIR + "/" + TEST_FILE ) );
        File file = new File( TEST_DIR + "/" + TEST_FILE );
        Assert.assertTrue( file.exists() );
    }

    /**
     * Test that we cannot create a file too close to the underlying
     * file system
     * 
     * @throws IOException
     *             if there is any issue in the persistence of the
     *             file.
     */
    @Test
    public void testFileTooCloseToRoot()
    {
        String err = "";
        try
        {
            mFileManager_.persistClob( DUMMY_CLOB, new File( TEST_ROOT + "/" + TEST_FILE ) );
            LOG.error( err );
            Assert.fail( err );
        }
        catch ( IOException ioe )
        {
            LOG.debug( "Correctly caught the IOEXception [" + ioe.getMessage() + "]" );
        }
    }

    /**
     * Test that if the file already exits and the file is not
     * writable then an exception is thrown. Note this test is not
     * allowed to throw an IOException.
     */
    @Test
    public void testNotWritableFile()
    {
        LOG.debug( "Creating read-only directly to test with" );
        File file = new File( TEST_DIR + "/" + TEST_FILE );

        String err0 = "Failed to create the read only file and dir for the test";
        try
        {
            file.getParentFile().mkdirs();
            if ( !file.createNewFile() )
            {
                Assert.fail( err0 );
            }
        }
        catch ( IOException ioe )
        {
            Assert.fail( err0 );

        }

        if ( !file.setReadOnly() )
        {
            Assert.fail( "Failed to set the file to read only" );
        }

        try
        {
            mFileManager_.persistClob( DUMMY_CLOB, new File( TEST_DIR + "/" + TEST_FILE ) );
            String err1 = "No IOException was thrown from persistClob call";
            LOG.error( err1 );
            Assert.fail( err1 );
        }
        catch ( IOException ioe )
        {
            LOG.debug( "Correctly caught IOException [" + ioe.getMessage() + "]" );
        }

    }
}
