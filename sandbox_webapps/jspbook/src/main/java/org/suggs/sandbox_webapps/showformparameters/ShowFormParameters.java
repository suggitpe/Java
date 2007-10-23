/*
 * ShowFormParameters.java created on 18 Oct 2007 06:59:03 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.showformparameters;

import org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO Write javadoc for ShowFormParameters
 * 
 * @author suggitpe
 * @version 1.0 18 Oct 2007
 */
public class ShowFormParameters extends AbstractBaseGetHttpServlet
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

        out.println( "<h3>HTTP form parameters sent by the client after the form</h3>" );
        out.println( "<p>The following parameters were extracted from the request sent to this URL ...</p>" );
        out.println( "<ul>" );
        Enumeration e = request.getParameterNames();
        while ( e.hasMoreElements() )
        {
            String name = (String) e.nextElement();
            String[] values = request.getParameterValues( name );
            out.print( "<b>" + name + "</b>:" );
            for ( String s : values )
            {
                out.print( s );
            }
            out.println( "<br/>" );
        }
        out.println( "</ul>" );
    }

    /**
     * @see org.suggs.sandbox_webapps.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Show Form Parameters";
    }

}
