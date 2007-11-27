/*
 * HelloWorldFilter.java created on 20 Nov 2007 07:25:52 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * A very simple hello world filter.
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2007
 */
public class HelloWorldFilter implements Filter
{

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init( FilterConfig arg0 ) throws ServletException
    {
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
    {
        HttpServletResponse res = (HttpServletResponse) response;

        res.setContentType( "text/html" );
        PrintWriter out = response.getWriter();

        StringBuffer buff = new StringBuffer( "<html>\n<head>" ).append( "<link rel=\"stylesheet\" type=\"text/css\" href=\"/format.css\" />" )
            .append( "<title>Hello World Filter</title></head>\n" )
            .append( "<body><h3>Hello world filter!!!!!</h3></body>\n" )
            .append( "</html>" );
        out.print( buff.toString() );
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
    }
}
