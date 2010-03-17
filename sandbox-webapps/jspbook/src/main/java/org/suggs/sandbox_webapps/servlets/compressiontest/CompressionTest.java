/*
 * CompressionTest.java created on 30 Nov 2007 19:23:57 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.compressiontest;

import org.suggs.sandbox_webapps.logging.logger.SiteLogger;
import org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple servlet to test the difference between compressed and
 * non-compressed servlet responses.
 * 
 * @author suggitpe
 * @version 1.0 30 Nov 2007
 */
public class CompressionTest extends AbstractBaseGetHttpServlet
{

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
        StringBuffer buff = new StringBuffer( "<h3>Compression Test</h3>\n<form>\n" );
        String url = request.getParameter( "url" );
        if ( url != null && !url.equals( "" ) )
        {
            buff.append( "<input size=\"50\" name=\"url\" value=\"" )
                .append( url )
                .append( "\"/>\n" );
        }
        else
        {
            buff.append( "<input size=\"50\" name=\"url\"/>\n" );
        }

        buff.append( "<input type=\"submit\" value=\"Check\"/>\n</form>\n" );

        buff.append( "URL: " ).append( url ).append( "\n" );
        if ( url != null && !url.equals( "" ) )
        {

            SiteLogger.getLogger().config( "Retrieving test data" );
            // open up a non compressed connection
            URL noCompress = new URL( url );
            HttpURLConnection huc = (HttpURLConnection) noCompress.openConnection();
            huc.setRequestProperty( "accept-encoding", "nozipping" );
            huc.setRequestProperty( "user-agent", "Mozilla(MSIE)" );
            huc.connect();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            InputStream is = huc.getInputStream();
            while ( is.read() != -1 )
            {
                baos.write( (byte) is.read() );
            }
            byte[] b1 = baos.toByteArray();
            SiteLogger.getLogger().config( "Have retrieved uncompressed [" + b1.length + "] bytes" );

            // open up a compressed connection
            URL compress = new URL( url );
            HttpURLConnection hucCompress = (HttpURLConnection) compress.openConnection();
            hucCompress.setRequestProperty( "accept-encoding", "gzip" );
            hucCompress.setRequestProperty( "user-agent", "Mozilla(MSIE)" );
            hucCompress.connect();

            ByteArrayOutputStream baosCompress = new ByteArrayOutputStream();

            InputStream isCompress = hucCompress.getInputStream();
            while ( isCompress.read() != -1 )
            {
                baosCompress.write( (byte) isCompress.read() );
            }
            byte[] b2 = baosCompress.toByteArray();
            SiteLogger.getLogger().config( "Have retrieved compressed [" + b2.length + "] bytes" );

            // now we can output how well they performed
            buff.append( "<pre>" );
            buff.append( "Uncompressed: " )
                .append( b1.length )
                .append( "\nCompressed: " )
                .append( b2.length )
                .append( "\n" );

            buff.append( "Space saved: " )
                .append( b1.length - b2.length )
                .append( ", or " )
                .append( ( ( b1.length - b2.length ) * 100 ) / b1.length )
                .append( "%\n" );

            buff.append( "\nDownstream(2kbps)\n" )
                .append( "No GZIP: " )
                .append( (float) b1.length / 2000 )
                .append( " seconds\n" )
                .append( "GZIP: " )
                .append( (float) b2.length / 2000 )
                .append( " seconds\n" );

            buff.append( "\nDownstream(5kbps)\n" )
                .append( "No GZIP: " )
                .append( (float) b1.length / 5000 )
                .append( " seconds\n" )
                .append( "GZIP: " )
                .append( (float) b2.length / 5000 )
                .append( " seconds\n" );

            buff.append( "\nDownstream(10kbps)\n" )
                .append( "No GZIP: " )
                .append( (float) b1.length / 10000 )
                .append( " seconds\n" )
                .append( "GZIP: " )
                .append( (float) b2.length / 10000 )
                .append( " seconds\n" );

            buff.append( "</pre>" );

        }

        out.println( buff.toString() );
    }

    /**
     * @see org.suggs.sandbox_webapps.servlets.support.AbstractBaseGetHttpServlet#getTitle()
     */
    @Override
    protected String getTitle()
    {
        return "Response compression test";
    }

}
