/*
 * ErrorPage.java created on 31 Oct 2007 07:27:40 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.errorpage;

import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is an error page servlet that details the exception content.
 * 
 * @author suggitpe
 * @version 1.0 31 Oct 2007
 */
public class ErrorPage extends AbstractBaseGetHttpServlet
{

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request, HttpServletResponse response ) throws IOException,
                    ServletException
    {
        ServletContext sc = getServletConfig().getServletContext();
        String email = sc.getInitParameter( "AdminEmail" );

        Exception e = (Exception) request.getAttribute( "javax.servlet.jsp.jspException" );

        StringBuffer buff = new StringBuffer( "<h3>An error has occured</h3><hr/>\n" ).append( "Sorry, but this site is currently unavailable to render the service you requested. \n" )
            .append( "<p>A bug in the system has caused this error to occur. Please send adescription of the problem to " )
            .append( email )
            .append( ". Thanks.</p>" )
            .append( "<p>The cause of the exception was '<code>" )
            .append( e.getMessage() )
            .append( "</code>'.</p><hr/>" )
            .append( "<p><i>By the way I am a Servlet</i></p>" );

        out.println( buff.toString() );
    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "An error has occurred";
    }

}
