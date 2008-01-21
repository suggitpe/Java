/*
 * AbstractControl.java created on 17 Jan 2008 19:24:57 by suggitpe for project JspbookSite
 * 
 */
package org.suggs.sandbox_webapps.jspbook_site.control;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Abstract control class that manages mopst of the impl of the
 * control access to the database
 * 
 * @author suggitpe
 * @version 1.0 17 Jan 2008
 */
public abstract class AbstractControl implements IControl
{

    /**
     * Template pattern method for getting the table name from the
     * deriving classes.
     * 
     * @return the name for the table that this class works with.
     */
    protected abstract String getTable();

    /**
     * @see org.suggs.sandbox_webapps.jspbook_site.control.IControl#doLogic(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public final boolean doLogic( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException
    {
        System.out.println( "In the abstract control" );
        String table = getTable();

        LinkedList<String> content = new LinkedList<String>();
        request.setAttribute( table, content );

        try
        {
            InitialContext initCtx = new InitialContext();
            Context ctx = (Context) initCtx.lookup( "java:comp/env" );
            DataSource ds = (DataSource) ctx.lookup( "jdbc/localdb" );

            Connection conn = ds.getConnection();

            try
            {
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery( "select * from " + table + " where visible=1" );
                // ResultSetMetaData rsmd = rs.getMetaData();

                while ( rs.next() )
                {
                    long lg = rs.getLong( 1 );
                    // Timestamp ts = new Timestamp( lg );
                    SimpleDateFormat fmt = new SimpleDateFormat( "EEE, MMM d, yyyy, HH:mm:ss z" );
                    Date d = new Date( lg );

                    String con = "<p class=\"date\">" + fmt.format( d ) + "</p>";
                    String u = "/WEB-INF/" + table + "/" + new Long( lg ).toString() + ".jsp";

                    // fix this
                    StringWriter sw = new StringWriter();
                    String file = request.getSession().getServletContext().getRealPath( u );
                    FileInputStream fis = new FileInputStream( file );
                    int i = 0;
                    while ( ( i = fis.read() ) != -1 )
                    {
                        sw.write( i );
                    }

                    con += new String( sw.toString() );
                    content.add( con );
                }
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
        return true;
    }
}
