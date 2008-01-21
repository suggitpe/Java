/*
 * addNews.java created on 18 Jan 2008 08:04:11 by suggitpe for project JspbookSite
 * 
 */
package org.suggs.sandbox_webapps.jspbook_site.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Class that allows us to add or remove news items from the
 * presentation layer.
 * 
 * @author suggitpe
 * @version 1.0 18 Jan 2008
 */
public class addNews implements IControl
{

    /**
     * @see org.suggs.sandbox_webapps.jspbook_site.control.IControl#doLogic(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public boolean doLogic( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException
    {
        System.out.println( "In the addNews control" );

        String title = request.getParameter( "title" );
        String link = request.getParameter( "link" );
        String text = request.getParameter( "text" );

        // check all is OK to proceed
        if ( title == null || title.equals( "" ) || link == null || link.equals( "" )
             || text == null || text.equals( "" ) )
        {
            return true;
        }

        try
        {
            InitialContext initCtx = new InitialContext();
            Context ctx = (Context) initCtx.lookup( "java:comp/env" );
            DataSource ds = (DataSource) ctx.lookup( "jdbc/localdb" );

            Connection conn = ds.getConnection();

            try
            {
                Statement s = conn.createStatement();

                ServletContext sc = request.getSession().getServletContext();
                String dir = sc.getRealPath( "/WEB-INF/news" );
                new File( dir ).mkdirs();
                long lg = Calendar.getInstance().getTimeInMillis();

                // write out a news file
                FileWriter fw = new FileWriter( dir + "/" + lg + ".jsp" );
                fw.write( "<p class=\"title\"><a href=\"" + link + "\">" + title + "</a></p>" );
                fw.write( "<p class=\"content\">text</p>" );
                fw.flush();
                fw.close();

                // add the entry into the db
                s.executeQuery( "insert into news values (" + lg + ", 1)" );
                response.sendRedirect( "index.jsp" );
            }
            catch ( Exception e )
            {
                throw new ServletException( e );
            }
            finally
            {
                conn.close();
            }

        }
        catch ( SQLException se )
        {
            throw new ServletException( se );
        }
        catch ( NamingException ne )
        {
            throw new ServletException( ne );
        }
        return false;
    }
}
