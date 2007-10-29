/*
 * AbstractBasePostHttpServlet.java created on 18 Oct 2007 07:16:52 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.support;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a base class impl for the doPost method that also
 * imoplements the doGet method to call the doPost method for you
 * 
 * @author suggitpe
 * @version 1.0 18 Oct 2007
 */
public abstract class AbstractBasePostHttpServlet extends HttpServlet
{

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost( HttpServletRequest aRequest, HttpServletResponse aResponse ) throws IOException, ServletException
    {

        aResponse.setContentType( "text/html" );
        PrintWriter out = aResponse.getWriter();

        StringBuffer head = new StringBuffer( "<html>\n<head><title>" ).append( getTitle() )
            .append( "</title>\n" )
            .append( "<link rel=\"stylesheet\" type=\"text/css\" href=\"format.css\" />\n" )
            .append( "</head>\n" )
            .append( "" );
        out.println( head.toString() );

        buildReponse( out, aRequest, aResponse );

        out.println( "</html>" );

    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest aRequest, HttpServletResponse aResponse ) throws IOException, ServletException
    {
        doPost( aRequest, aResponse );
    }

    protected abstract String getTitle();

    protected abstract void buildReponse( PrintWriter aOut, HttpServletRequest aRequest, HttpServletResponse aResponse )
                    throws IOException, ServletException;

}
