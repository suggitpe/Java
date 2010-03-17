/*
 * CacheFilter.java created on 4 Dec 2007 07:52:51 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.cache;

import org.suggs.sandbox_webapps.logging.logger.SiteLogger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A cache filter to enable page caching in the webapp
 * 
 * @author suggitpe
 * @version 1.0 4 Dec 2007
 */
public class CacheFilter implements Filter
{

    private ServletContext ctx_;
    private FilterConfig cfg_;
    private long cacheTimeout_ = Long.MAX_VALUE;

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init( FilterConfig aCfg ) throws ServletException
    {
        cfg_ = aCfg;
        String ct = cfg_.getInitParameter( "cacheTimeout" );
        if ( ct != null && !ct.equals( "" ) )
        {
            try
            {
                cacheTimeout_ = 60 * 1000 * Long.parseLong( ct );
            }
            catch ( NumberFormatException nfe )
            {
                // nadda .. just stick with default value
            }
        }
        ctx_ = cfg_.getServletContext();
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter( ServletRequest aRequest, ServletResponse aResponse, FilterChain aChain )
                    throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) aRequest;
        HttpServletResponse response = (HttpServletResponse) aResponse;

        // check if is is a resource the should not be cached
        String r = ctx_.getRealPath( "" );
        String path = cfg_.getInitParameter( request.getRequestURI() );
        if ( path != null && path.equals( "nocache" ) )
        {
            aChain.doFilter( request, response );
            return;
        }
        path = r + path;

        // customise to match parameters
        String id = request.getRequestURI() + request.getQueryString();

        // optionally append i18n sensitivity to the id
        String localSens = cfg_.getInitParameter( "locale-sensitive" );
        if ( localSens != null )
        {
            StringWriter lData = new StringWriter();
            Enumeration locales = request.getLocales();
            while ( locales.hasMoreElements() )
            {
                Locale loc = (Locale) locales.nextElement();
                lData.write( loc.getISO3Language() );
            }
            id = id + lData.toString();
        }

        File tmpDir = (File) ctx_.getAttribute( "javax.servlet.context.tempdir" );

        // get possible cache
        String tmp = tmpDir.getAbsolutePath();
        File file = new File( tmp + id );
        SiteLogger.getLogger().config( "Using " + file.getAbsolutePath() + " as a cache file/dir" );

        // get the current resource
        if ( path == null )
        {
            path = ctx_.getRealPath( request.getRequestURI() );
        }
        File current = new File( path );

        try
        {
            long now = Calendar.getInstance().getTimeInMillis();
            // set timestamp check
            if ( !file.exists() || ( file.exists() && current.lastModified() > file.lastModified() )
                 || cacheTimeout_ < now - file.lastModified() )
            {
                String name = file.getAbsolutePath();
                name = name.substring( 0, name.lastIndexOf( "\\" ) );
                new File( name ).mkdirs();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                SiteLogger.getLogger().config( "Size before = " + baos.size() );
                CacheResponseWrapper wrappedResponse = new CacheResponseWrapper( response, baos );
                aChain.doFilter( request, wrappedResponse );
                SiteLogger.getLogger().config( "Size after = " + baos.size() );

                // write out the data content to the cache file
                FileOutputStream fos = new FileOutputStream( file );
                fos.write( baos.toByteArray() );
                fos.flush();
                fos.close();
            }
            SiteLogger.getLogger().config( "Cache writing complete" );
        }
        catch ( ServletException se )
        {
            if ( !file.exists() )
            {
                throw new ServletException( se );
            }
        }
        /*
         * catch ( IOException ioe ) { if ( !file.exists() ) { throw
         * new IOException( ioe ); } }
         */

        FileInputStream fis = new FileInputStream( file );
        String mt = ctx_.getMimeType( request.getRequestURI() );
        response.setContentType( mt );
        ServletOutputStream sos = response.getOutputStream();
        for ( int i = fis.read(); i != -1; i = fis.read() )
        {
            sos.write( (byte) i );
        }
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
        ctx_ = null;
        cfg_ = null;
    }

}
