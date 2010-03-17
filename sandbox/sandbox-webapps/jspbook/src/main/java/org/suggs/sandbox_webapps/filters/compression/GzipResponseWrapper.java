/*
 * GzipResponseWrapper.java created on 28 Nov 2007 07:05:15 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.compression;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * The main purpose of this class is to overload the
 * createOutputStream so that it creates a Gzip reposnse stream rather
 * than a servlet output stream.
 * 
 * @author suggitpe
 * @version 1.0 28 Nov 2007
 */
public class GzipResponseWrapper extends HttpServletResponseWrapper
{

    private HttpServletResponse origResponse_;
    private ServletOutputStream outStrm_;
    private PrintWriter writer_;

    /**
     * Constructs a new instance.
     * 
     * @param aResponse
     */
    public GzipResponseWrapper( HttpServletResponse aResponse )
    {
        super( aResponse );
        origResponse_ = aResponse;
    }

    /**
     * @return
     * @throws IOException
     */
    private ServletOutputStream createOutputStream() throws IOException
    {
        return ( new GzipResponseStream( origResponse_ ) );
    }

    /**
     * Close the internal i/o
     */
    public void finishResponse()
    {
        try
        {
            if ( writer_ != null )
            {
                writer_.close();
            }
            else
            {
                if ( outStrm_ != null )
                {
                    outStrm_.close();
                }
            }
        }
        catch ( IOException ioe )
        {
            // nadda
        }
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

    /**
     * 
     */
    public void setContentLength()
    {
    }

}
