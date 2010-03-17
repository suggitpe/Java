/*
 * AuditFilter.java created on 27 Nov 2007 07:24:11 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.wrapper;

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

/**
 * Filter to add in the request wrapper class
 * 
 * @author suggitpe
 * @version 1.0 27 Nov 2007
 */
public class AuditFilter implements Filter
{

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
        Logger logger = SiteLogger.getLogger();
        AuditRequestWrapper wrappedRequest = new AuditRequestWrapper( logger,
                                                                      (HttpServletRequest) aRequest );
        aChain.doFilter( wrappedRequest, aResponse );
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
        // nadda
    }

}
