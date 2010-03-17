/*
 * CallingServerlet.java created on 22 Oct 2007 07:01:38 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.linkedservlets;

import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example servlet that will call another servlet (CalledServlet)
 * passing a string through
 * 
 * @author suggitpe
 * @version 1.0 22 Oct 2007
 */
public class CallingServlet extends AbstractBaseGetHttpServlet
{

    /**
     * The important thing to see here is that the sevlet actually
     * calls itself. If the data has been set then it calls the called
     * servlet else it will just rebuild the servlet.
     * 
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request, HttpServletResponse response ) throws IOException,
                    ServletException
    {
        String param = request.getParameter( "value" );
        if ( param != null && !param.equals( "" ) )
        {
            request.setAttribute( "value", param );
            RequestDispatcher d = request.getRequestDispatcher( "/CalledServlet" );
            d.forward( request, response );
            return;
        }
        out.println( "<h1>Calling Servlet form</h1>\n<form>" );
        out.println( "Enter a value to sernd to the CalledServlet:" );
        out.println( "<input name=\"value\"/><br/>" );
        out.println( "<input type=\"Submit\" value=\"Send to CalledServlet\" />" );
        out.println( "</form>" );
    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Calling Servlet";
    }

}
