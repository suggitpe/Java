/*
 * ControlFilter.java created on 19 Dec 2007 07:30:10 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.jspbook_site.filter;

import org.suggs.sandbox_webapps.jspbook_site.control.IControl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Intercepts all requests and executes implifcit Java calls to the
 * Model 2 logic
 * 
 * @author suggitpe
 * @version 1.0 19 Dec 2007
 */
public class ControlFilter implements Filter
{

    private static final String CLASS_PREFIX = "org.suggs.sandbox_webapps.jspbook_site.control.";

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init( FilterConfig aCfg ) throws ServletException
    {
        // nadda ... may later need to store the config object
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter( ServletRequest aRequest, ServletResponse aResponse, FilterChain aChain )
                    throws IOException, ServletException
    {
        System.out.println( "In doFilter of the control filter" );
        if ( !( aRequest instanceof HttpServletRequest ) )
        {
            throw new ServletException( "ControlFilter only accepts HTTP Servlet Requests" );
        }

        // determine the name of the implicit control component
        HttpServletRequest request = (HttpServletRequest) aRequest;
        HttpServletResponse response = (HttpServletResponse) aResponse;

        String uri = request.getRequestURI();
        int start = uri.lastIndexOf( "/" ) + 1;
        int end = uri.lastIndexOf( "." );
        String name = "default";
        if ( start < end )
        {
            name = uri.substring( start, end );
        }
        boolean doFilter = true;

        // try to load and run an implicit MVC control component
        try
        {
            Object o = Class.forName( CLASS_PREFIX + name ).newInstance();
            if ( !( o instanceof IControl ) )
            {
                throw new ServletException( "Class " + o.getClass().getName()
                                            + " does not implement IControl" );
            }

            IControl ctrl = (IControl) o;
            doFilter = ctrl.doLogic( request, response );
        }
        catch ( IllegalAccessException iae )
        {
            throw new ServletException( iae );
        }
        catch ( ClassNotFoundException nfe )
        {
            // nadda
        }
        catch ( InstantiationException ie )
        {
            throw new ServletException( ie );
        }

        // do whatever is next in the chain
        if ( doFilter )
        {
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
