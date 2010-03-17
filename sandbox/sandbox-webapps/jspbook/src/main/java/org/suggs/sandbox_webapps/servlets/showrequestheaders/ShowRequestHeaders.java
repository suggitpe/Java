/*
 * ShowRequestHeaders.java created on 17 Oct 2007 18:11:31 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.showrequestheaders;

import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is a useful debugging servlet as it shows you the
 * headers passed into the servlet in the request
 * 
 * @author suggitpe
 * @version 1.0 17 Oct 2007
 */
public class ShowRequestHeaders extends AbstractBaseGetHttpServlet
{

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#buildReponse(java.io.PrintWriter,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void buildReponse( PrintWriter aOut, HttpServletRequest aRequest, HttpServletResponse aResponse ) throws IOException,
                    ServletException
    {
        aOut.println( "<h3>HTTP request headers sent by the client</h3>" );
        aOut.println( "<p>The following were extracted from the request sent to this URL ...</p>" );
        aOut.println( "<ul>" );

        Enumeration e = aRequest.getHeaderNames();
        while ( e.hasMoreElements() )
        {
            String name = (String) e.nextElement();
            aOut.println( "<li>" );
            aOut.print( name + " = " + aRequest.getHeader( name ) );
            aOut.println( "</li>" );
        }

        aOut.println( "<>" );
        aOut.println( "</ul>" );
    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Show Request Headers";
    }
}
