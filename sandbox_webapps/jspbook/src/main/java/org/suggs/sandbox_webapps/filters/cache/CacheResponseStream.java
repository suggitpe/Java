/*
 * CacheResponseStream.java created on 4 Dec 2007 20:30:08 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.cache;

import org.suggs.sandbox_webapps.logging.logger.SiteLogger;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Cache response stream class used in conjunction with the cache
 * response wrapper.
 * 
 * @author suggitpe
 * @version 1.0 4 Dec 2007
 */
public class CacheResponseStream extends ServletOutputStream
{

    private boolean closed_ = false;
    private HttpServletResponse response_;
    private ServletOutputStream out_;
    private OutputStream cache_;

    /**
     * Constructs a new instance.
     * 
     * @param aResponse
     * @param aCache
     * @throws IOException
     */
    public CacheResponseStream( HttpServletResponse aResponse, OutputStream aCache )
        throws IOException
    {
        super();
        SiteLogger.getLogger().config( "creating a new cache reponse stream" );
        response_ = aResponse;
        cache_ = aCache;
    }

    /**
     * @see java.io.OutputStream#close()
     */
    @Override
    public void close() throws IOException
    {
        SiteLogger.getLogger().config( "closoing cache response stream" );
        if ( closed_ )
        {
            throw new IOException( "This output stream has already been closed" );
        }
        cache_.close();
        closed_ = true;
    }

    /**
     * @see java.io.OutputStream#flush()
     */
    @Override
    public void flush() throws IOException
    {
        SiteLogger.getLogger().config( "flushing cache response stream" );
        if ( closed_ )
        {
            throw new IOException( "This stream has already been closed (unable to flush)" );
        }
        cache_.flush();
    }

    /**
     * @see java.io.OutputStream#write(int)
     */
    @Override
    public void write( int aByte ) throws IOException
    {
        SiteLogger.getLogger().config( "writing a byte" );
        if ( closed_ )
        {
            throw new IOException( "Cannot write to a closed output stream" );
        }
        SiteLogger.getLogger().config( "writing [1] byte to output cache_" );
        cache_.write( (byte) aByte );
    }

    /**
     * @see java.io.OutputStream#write(byte[])
     */
    @Override
    public void write( byte b[] ) throws IOException
    {
        write( b, 0, b.length );
    }

    /**
     * @see java.io.OutputStream#write(byte[], int, int)
     */
    @Override
    public void write( byte b[], int off, int len ) throws IOException
    {
        SiteLogger.getLogger().config( "writing many bytes" );
        if ( closed_ )
        {
            throw new IOException( "Cannot writed to a closed output stream" );
        }
        SiteLogger.getLogger().config( "writing [" + len + "] bytes to output cache_" );
        cache_.write( b, off, len );
    }

    /**
     * getter for the closed var
     * 
     * @return
     */
    public boolean closed()
    {
        return closed_;
    }

    /**
     */
    public void reset()
    {
        // nadda
    }
}
