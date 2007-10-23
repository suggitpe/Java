/*
 * CalledServlet.java created on 22 Oct 2007 07:05:42 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.linkedservlets;

import org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Write javadoc for CalledServlet
 * 
 * @author suggitpe
 * @version 1.0 22 Oct 2007
 */
public class CalledServlet extends AbstractBaseGetHttpServlet
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
        out.println( "<h1>Called Servlet</h1>" );
        String val = (String) request.getAttribute( "value" );
        if ( val != null && !val.equals( "" ) )
        {
            out.println( "Calling Servlet passed a string object via request scope.  The value of that string is [<b>" + val + "</b>]" );
        }
        else
        {
            out.println( "No values passed in!" );
        }
    }

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Called Servlet";
    }
}
