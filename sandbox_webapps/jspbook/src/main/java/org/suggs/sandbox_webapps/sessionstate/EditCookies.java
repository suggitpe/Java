/*
 * EditCookies.java created on 10 Dec 2007 08:07:51 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.sessionstate;

import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Write javadoc for EditCookies
 * 
 * @author suggitpe
 * @version 1.0 10 Dec 2007
 */
public class EditCookies extends AbstractBaseGetHttpServlet
{

    private static final String REDIRECT_URL = "/jspbook/EditCookies";

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter out, HttpServletRequest request,
                                 HttpServletResponse response ) throws IOException,
                    ServletException
    {
        // get params to add/edit a cookie
        String cookieName = request.getParameter( "name" );
        String cookieValue = request.getParameter( "value" );
        if ( cookieName != null && !cookieName.equals( "" ) && cookieValue != null
             && !cookieValue.equals( "" ) )
        {
            Cookie c = new Cookie( cookieName, cookieValue );
            response.addCookie( c );
            response.sendRedirect( REDIRECT_URL );
        }

        // set up collection of cookies
        Cookie[] cookies = request.getCookies();

        // delete a cookie
        String cookieToDelete = request.getParameter( "deleteCookie" );
        if ( cookieToDelete != null && !cookieToDelete.equals( "" ) )
        {
            for ( int i = 0; i < cookies.length; ++i )
            {
                Cookie c = cookies[i];
                if ( c.getName().equals( cookieToDelete ) )
                {
                    c.setMaxAge( 0 );
                    response.addCookie( c );
                    response.sendRedirect( REDIRECT_URL );
                }
            }
        }

        // look at the existing cookies and allow us to update their
        // content
        StringBuffer outBuff = new StringBuffer( "<h3>Existing Cookies</h3>" );
        if ( cookies == null )
        {
            outBuff.append( "No cookies were sent!!!" );
        }
        else
        {
            for ( int i = 0; i < cookies.length; ++i )
            {
                Cookie c = cookies[i];
                outBuff.append( "<p>Cookie #" )
                    .append( i )
                    .append( ": <form method=\"post\">\n" )
                    .append( "<b>Name</b>: <input name=\"name\" value=\"" )
                    .append( c.getName() )
                    .append( "\"/>\n" )
                    .append( "<b>Value</b>: <input name=\"value\" value=\"" )
                    .append( c.getValue() )
                    .append( "\"/>\n" )
                    .append( "<input type=\"submit\" value=\"Update Cookie\"/>\n</form></p>\n\n" );
            }
        }

        // a form for creating a new cookie
        outBuff.append( "<hr/><h3>Create a new cookie</h3>\n<form method=\"post\"/>\n" )
            .append( "<b>Name</b>: <input name=\"name\"/>\n" )
            .append( "<b>Value</b>: <input name=\"value\"/>\n" )
            .append( "<input type=\"submit\" value=\"Add cookie\"/>\n</form>" );

        // a form for deleting an existing cookie
        if ( cookies != null )
        {
            outBuff.append( "<hr/><h3>Delete a cookie</h3>\n<form method=\"post\">\n" )
                .append( "<select name=\"deleteCookie\">" );
            for ( int i = 0; i < cookies.length; ++i )
            {
                Cookie c = cookies[i];
                outBuff.append( "<option value=\"" )
                    .append( c.getName() )
                    .append( "\">" )
                    .append( c.getName() )
                    .append( "</option><br/>" );
            }
            outBuff.append( "</select><input type=\"submit\" value=\"Delete Cookie\"/>\n</form> " );
        }

        out.println( outBuff.toString() );

    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Edit Cookies";
    }

}
