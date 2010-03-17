/*
 * DynamicImage.java created on 17 Oct 2007 06:59:29 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.servlets.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * This servlet will create an image dynamically snd show to the
 * calling client
 * 
 * @author suggitpe
 * @version 1.0 17 Oct 2007
 */
public class DynamicImage extends HttpServlet
{

    private static final String IMAGE_STRING = "Test for Pete";

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest aRequest, HttpServletResponse aResponse ) throws IOException, ServletException
    {
        aResponse.setContentType( "image/jpeg" );

        // create the image
        int width = 200;
        int height = 30;
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );

        // get drawing context
        Graphics2D g = (Graphics2D) image.getGraphics();

        // fill back ground
        g.setColor( Color.gray );
        g.fillRect( 0, 0, width, height );

        // draw a string
        g.setColor( Color.white );
        g.setFont( new Font( "Dialog", Font.PLAIN, 14 ) );
        g.drawString( IMAGE_STRING, 10, height / 2 + 4 );

        // draw a border
        g.setColor( Color.black );
        g.drawRect( 0, 0, width - 1, height - 1 );

        g.dispose();

        // send back the image
        ServletOutputStream strm = aResponse.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( strm );
        encoder.encode( image );

    }
}
