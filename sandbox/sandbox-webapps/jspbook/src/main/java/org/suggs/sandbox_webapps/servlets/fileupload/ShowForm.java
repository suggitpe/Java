/*
 * ShowForm.java created on 18 Oct 2007 18:22:55 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.fileupload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is called by a form and will show the contents of the
 * file (as a stream).
 * 
 * @author suggitpe
 * @version 1.0 18 Oct 2007
 */
public class ShowForm extends HttpServlet
{

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
    {
        response.setContentType( "text/plain" );
        PrintWriter out = response.getWriter();

        ServletInputStream stream = request.getInputStream();
        for ( int i = stream.read(); i != -1; i = stream.read() )
        {
            out.print( (char) i );
        }
    }

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
    {
        doGet( request, response );
    }

}
