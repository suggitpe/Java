/*
 * Ch7Images.java created on 2 Sep 2008 18:46:26 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.widgetwindow.ch7;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * Simple app that will create an animated GIF file to use by the
 * widget window composite
 * 
 * @author suggitpe
 * @version 1.0 2 Sep 2008
 */
public class Ch7Images
{

    public static final int ROWS = 6;
    public static final int COLS = 11;
    public static final int PIX = 20;

    public static void main( String[] args )
    {
        // initialise the colour palette to use
        PaletteData pd = new PaletteData( new RGB[] { new RGB( 0x00, 0x00, 0x00 ),
                                                     new RGB( 0x80, 0x80, 0x80 ),
                                                     new RGB( 0xFF, 0xFF, 0xFF ) } );

        // create three images to allow for the animation (these are
        // the image frames in the animation)
        ImageData[] flagArray = new ImageData[3];
        for ( int f = 0; f < flagArray.length; ++f )
        {
            flagArray[f] = new ImageData( PIX * COLS, PIX * ROWS, 4, pd );
            flagArray[f].delayTime = 10;
            for ( int x = 0; x < PIX * COLS; ++x )
            {
                for ( int y = 0; y < PIX * ROWS; ++y )
                {
                    // determine the id in the palette to use for the
                    // colour
                    int val = ( ( ( x / PIX ) % 3 ) + ( 3 - ( ( y / PIX ) % 3 ) ) + f ) % 3;
                    // set the actual pixel colour
                    flagArray[f].setPixel( x, y, val );
                }
            }
        }

        // load the image loader with 3 ImageData frames
        ImageLoader loader = new ImageLoader();
        ByteArrayOutputStream flagByte[] = new ByteArrayOutputStream[3];
        byte[][] gifArray = new byte[3][];
        loader.data = flagArray;

        // convert images to bytes
        for ( int i = 0; i < 3; ++i )
        {
            flagByte[i] = new ByteArrayOutputStream();
            flagArray[0] = flagArray[i];
            loader.save( flagByte[i], SWT.IMAGE_GIF );
            gifArray[i] = flagByte[i].toByteArray();
        }

        // prepare output gif stream
        byte[] gif = new byte[4628];
        System.arraycopy( gifArray[0], 0, gif, 0, 61 );
        System.arraycopy( new byte[] { 33, (byte) 255, 11 }, 0, gif, 64, 11 );
        System.arraycopy( new String( "NETSCAPE2.0" ).getBytes(), 0, gif, 64, 11 );
        System.arraycopy( new byte[] { 3, 1, -24, 3, 0, 33, -7, 4, -24 }, 0, gif, 75, 9 );

        // add images to gif stream
        System.arraycopy( gifArray[0], 65, gif, 84, 1512 );
        for ( int i = 1; i < 3; ++i )
        {
            System.arraycopy( gifArray[i], 61, gif, 1516 * i + 80, 3 );
            gif[1516 * i + 83] = (byte) -24;
            System.arraycopy( gifArray[i], 65, gif, 1516 * i + 84, 1512 );
        }

        // create the gif
        try
        {
            DataOutputStream in = new DataOutputStream( new BufferedOutputStream( new FileOutputStream( new File( "FlagGIF.gif" ) ) ) );
            in.write( gif, 0, gif.length );
            System.out.println( "writing file" );
        }
        catch ( FileNotFoundException fnf )
        {
            fnf.printStackTrace();
        }
        catch ( IOException ioe )
        {
            ioe.printStackTrace();
        }

    }
}
