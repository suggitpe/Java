/*
 * GzipResponseStream.java created on 30 Nov 2007 07:26:54 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.compression;

import org.suggs.sandbox_webapps.logging.logger.SiteLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * This class captures all of the client data and compresses it
 * sending back to teh client (after setting the correct headers)
 * 
 * @author suggitpe
 * @version 1.0 30 Nov 2007
 */
public class GzipResponseStream extends ServletOutputStream
{

    private ByteArrayOutputStream byteOut_ = new ByteArrayOutputStream();
    private GZIPOutputStream gzipOut_;
    private boolean closed_ = false;
    private HttpServletResponse response_;
    private ServletOutputStream out_;

    /**
     * Constructs a new instance.
     * 
     * @param aResponse
     * @throws IOException
     */
    public GzipResponseStream( HttpServletResponse aResponse ) throws IOException
    {
        super();
        response_ = aResponse;
        out_ = response_.getOutputStream();
        gzipOut_ = new GZIPOutputStream( byteOut_ );
    }

    /**
     * @see java.io.OutputStream#close()
     */
    @Override
    public void close() throws IOException
    {
        if ( closed_ )
        {
            throw new IOException( "This output stream has already been closed" );
        }

        gzipOut_.finish();

        byte[] bytes = byteOut_.toByteArray();

        response_.addHeader( "Content-Length", Integer.toString( bytes.length ) );
        response_.addHeader( "Content-Encoding", "gzip" );
        out_.write( bytes );
        out_.flush();

        out_.close();
        closed_ = true;
    }

    /**
     * @see java.io.OutputStream#flush()
     */
    @Override
    public void flush() throws IOException
    {
        if ( closed_ )
        {
            throw new IOException( "Cannot flush a closed output stream" );
        }
        gzipOut_.flush();
    }

    /**
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write( int aByte ) throws IOException
    {
        if ( closed_ )
        {
            throw new IOException( "Cannot write to a closed output stream" );
        }
        gzipOut_.write( (byte) aByte );
    }

    /**
     * @see java.io.OutputStream#write(byte[])
     */
    @Override
    public void write( byte aBytes[] ) throws IOException
    {
        if ( closed_ )
        {
            throw new IOException( "Cannot write to a closed output stream" );
        }
        gzipOut_.write( aBytes, 0, aBytes.length );
    }

    /**
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    @Override
    public void write( byte aBytes[], int aOffset, int aLength ) throws IOException
    {
        SiteLogger.getLogger().info( "writing ..." );
        if ( closed_ )
        {
            throw new IOException( "Cannot write to a closed output stream" );
        }
        gzipOut_.write( aBytes, aOffset, aLength );
    }

    /**
     * Getter for the closed member
     * 
     * @return true if closed, else false
     */
    public boolean closed()
    {
        return closed_;
    }

    /**
     * 
     */
    public void reset()
    {
        // nadda
    }

}
