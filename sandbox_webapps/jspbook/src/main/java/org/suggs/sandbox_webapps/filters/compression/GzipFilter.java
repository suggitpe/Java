/*
 * GzipFilter.java created on 27 Nov 2007 07:56:38 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.compression;

import org.suggs.sandbox_webapps.logging.logger.SiteLogger;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that will review the headers of a request and if the
 * accept-encoding gzip header is set it will compress the response
 * 
 * @author suggitpe
 * @version 1.0 27 Nov 2007
 */
public class GzipFilter implements Filter
{

    Logger logger_;

    // non static initialiser
    {
        logger_ = SiteLogger.getLogger();
    }

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init( FilterConfig arg0 ) throws ServletException
    {
        // nadda
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter( ServletRequest aRequest, ServletResponse aResponse, FilterChain aChain )
                    throws IOException, ServletException
    {
        if ( aRequest instanceof HttpServletRequest )
        {
            HttpServletRequest req = (HttpServletRequest) aRequest;
            HttpServletResponse res = (HttpServletResponse) aResponse;

            String ae = req.getHeader( "accept-encoding" );
            if ( ae != null && ae.indexOf( "gzip" ) != -1 )
            {
                logger_.config( "GZIP supported: compressing" );
                GzipResponseWrapper wrapper = new GzipResponseWrapper( res );
                aChain.doFilter( aRequest, aResponse );
                wrapper.finishResponse();
                return;
            }
            // we still need to complete the chain
            aChain.doFilter( aRequest, aResponse );
        }
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
        // nadda
    }

}
