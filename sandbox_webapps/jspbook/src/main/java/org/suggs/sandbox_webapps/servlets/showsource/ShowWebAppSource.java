/*
 * ShowWebAppSource.java created on 24 Oct 2007 06:35:40 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.showsource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Show the full content of the web application
 * 
 * @author suggitpe
 * @version 1.0 24 Oct 2007
 */
public class ShowWebAppSource extends HttpServlet
{

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
    {
        ServletContext ctx = getServletContext();

        PrintWriter out = response.getWriter();

        String resource = request.getParameter( "resource" );
        if ( resource != null && !resource.equals( "" ) )
        {
            // use getResourceAsStream to properly set the file
            InputStream is = ctx.getResourceAsStream( resource );
            if ( is != null )
            {
                response.setContentType( "text/plain" );
                StringWriter writer = new StringWriter();
                for ( int i = is.read(); i != -1; i = is.read() )
                {
                    writer.write( i );
                }
                out.println( writer.toString() );
            }
        }
        else
        {
            response.setContentType( "text/html" );
            StringBuffer buff = new StringBuffer( "<html>\n<head>\n" ).append( "<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\" />\n" )
                .append( "<title>Source Code Servlet</title>\n</head>\n" )
                .append( "<body>\n<h1>Web application source code viewer</h1>\n" )
                .append( "<form>Choose a resource to see the source<br/><select name=\"resource\">" );
            out.println( buff.toString() );

            // list all of the files in the web app
            listFiles( ctx, out, "/" );

            buff = new StringBuffer( "</select><br/>\n<input type=\"submit\" value=\"Show Source\"/>\n" ).append( "</forrm></body></html>" );
            out.println( buff.toString() );
        }

    }

    /**
     * Method to find all files in the web app and output them to the
     * print writer
     * 
     * @param aCtx
     *            the servlet context from which to get the files in
     *            the web app
     * @param aOut
     *            the print writer to pass to the response
     * @param aBase
     *            the base directory for the search within the web app
     */
    private void listFiles( ServletContext aCtx, PrintWriter aOut, String aBase )
    {
        Set paths = aCtx.getResourcePaths( aBase );
        for ( Iterator iter = paths.iterator(); iter.hasNext(); )
        {
            String s = (String) iter.next();
            if ( s.endsWith( "/" ) )
            {
                listFiles( aCtx, aOut, s );
            }
            else
            {
                aOut.println( "<option value=\"" + s + "\">" + s + "</option>" );
            }
        }
    }
}
