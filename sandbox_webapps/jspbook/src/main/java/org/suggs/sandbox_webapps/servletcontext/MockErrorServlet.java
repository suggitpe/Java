/*
 * MockError.java created on 23 Oct 2007 18:13:59 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servletcontext;

import org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a mock error reporting servlet so that we can show how
 * 
 * @author suggitpe
 * @version 1.0 23 Oct 2007
 */
public class MockErrorServlet extends AbstractBaseGetHttpServlet
{

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request, HttpServletResponse response ) throws IOException,
                    ServletException
    {
        ServletContext ctx = getServletConfig().getServletContext();
        String adminEmail = ctx.getInitParameter( "AdminEmail" );
        StringBuffer buff = new StringBuffer( "<h1>Mock Error Page</h1>\n" ).append( "<p>Sorry!  But an unexpected error has occured.</p>\n" )
            .append( "Please send an error report to " )
            .append( adminEmail )
            .append( "." );
        out.println( buff.toString() );
    }

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "An error has occurred!";
    }

}
