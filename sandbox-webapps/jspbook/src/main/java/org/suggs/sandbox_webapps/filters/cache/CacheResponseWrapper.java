/*
 * CacheResponseWrapper.java created on 4 Dec 2007 20:15:23 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.cache;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Response wrapper for the cache filter
 * 
 * @author suggitpe
 * @version 1.0 4 Dec 2007
 */
public class CacheResponseWrapper extends HttpServletResponseWrapper
{

    private HttpServletResponse origResponse_;
    private ServletOutputStream outStrm_;
    private PrintWriter writer_;
    private OutputStream cache_;

    /**
     * Constructs a new instance.
     * 
     * @param aResponse
     * @param aStream
     */
    public CacheResponseWrapper( HttpServletResponse aResponse, OutputStream aStream )
    {
        super( aResponse );
        origResponse_ = aResponse;
        cache_ = aStream;
    }

    /**
     * creates a new cache response stream
     * 
     * @return
     * @throws IOException
     */
    public ServletOutputStream createOutputStream()
    {
        return new CacheResponseStream( origResponse_, cache_ );
    }

    /**
     * @see javax.servlet.ServletResponseWrapper#flushBuffer()
     */
    @Override
    public void flushBuffer() throws IOException
    {
        outStrm_.flush();
    }

    /**
     * @see javax.servlet.ServletResponseWrapper#getOutputStream()
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        if ( writer_ != null )
        {
            throw new IllegalStateException( "getWriter() has already been called" );
        }

        if ( outStrm_ == null )
        {
            outStrm_ = createOutputStream();
        }
        return outStrm_;
    }

    /**
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    @Override
    public PrintWriter getWriter() throws IOException
    {
        if ( writer_ != null )
        {
            return writer_;
        }

        if ( outStrm_ != null )
        {
            throw new IllegalStateException( "getOutputStream() has already been called" );
        }

        outStrm_ = createOutputStream();
        writer_ = new PrintWriter( new OutputStreamWriter( outStrm_, "UTF-8" ) );
        return writer_;
    }

}
