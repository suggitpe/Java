/*
 * ImageUtil.java created on 22 Dec 2008 19:35:23 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.fileexplorer;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Display;

/**
 * This is used to manage images in the app.
 * 
 * @author suggitpe
 * @version 1.0 22 Dec 2008
 */
public class ImageUtil {

    private static ImageRegistry IMAGES;
    private static Clipboard CLIPBOARD;

    /**
     * Singleton method for managing an image registry for
     * 
     * @return the image registry with all of the image items included
     */
    public static ImageRegistry getImageRegistry() {
        if ( IMAGES == null ) {
            IMAGES = new ImageRegistry();
            IMAGES.put( "folder", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "folder.gif" ) ) );
            IMAGES.put( "file", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "file.gif" ) ) );
            IMAGES.put( "exit", ImageDescriptor.createFromURL( ImageUtil.class.getClassLoader()
                .getResource( "exit.gif" ) ) );

        }
        return IMAGES;
    }

    /**
     * Accessor for the singleton ckipboard.
     * 
     * @return the clipboard
     */
    public static Clipboard getClipboard() {
        if ( CLIPBOARD == null ) {
            CLIPBOARD = new Clipboard( Display.getCurrent() );
        }
        return CLIPBOARD;
    }
}
