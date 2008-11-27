/*
 * ImageManager.java created on 20 Oct 2008 18:57:59 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.util.image;

import java.io.InputStream;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Class to load an image from the file system.
 * 
 * @author suggitpe
 * @version 1.0 20 Oct 2008
 */
public class ImageManager
{

    public static final String IMAGE_CONNECTION = "images/connect.png";
    public static final String IMAGE_MERCURY = "images/mercury.png";

    /**
     * This static method will utilise the passed in classloader to
     * retrieve the image from the classpath and create it into an
     * ImageDescriptor to be used.
     * 
     * @param loader
     *            the classloader from which the image can be loaded
     * @param imagename
     *            the name of the image to load
     * @return an Image descriptor object for the passed in image name
     */
    public static final ImageDescriptor getImageDescriptor( ClassLoader loader, String imagename )
    {
        return ImageDescriptor.createFromURL( loader.getResource( imagename ) );
    }

    /**
     * This static method will use the passed in classloader to
     * retrieve the image fromt he classpath and return it as a
     * stream.
     * 
     * @param loader
     *            the classloader from which the image can be loaded
     * @param imagename
     *            the name of the image to load
     * @return an input stream for the image from the classpath
     */
    public static final InputStream getImageStream( ClassLoader loader, String imagename )
    {
        return loader.getResourceAsStream( imagename );
    }

}
