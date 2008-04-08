/*
 * CreateXmlFileAction.java created on 10 Mar 2008 19:24:19 by suggitpe for project Sandbox - TestEclipsePlugin
 * 
 */
package org.suggs.sandbox_eclipse.testplugin.actions;

import org.suggs.sandbox_eclipse.testplugin.dialogs.DisplaySpringXmlDialog;
import org.suggs.sandbox_eclipse.testplugin.dialogs.GetSpringXmlOptionsDialog;
import org.suggs.sandbox_eclipse.testplugin.fsmgeneration.FsmXmlGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

/**
 * Action to create an Spring XML representation from a selection in
 * the eclipse framework.
 * 
 * @author suggitpe
 * @version 1.0 10 Mar 2008
 */
public class CreateXmlFileAction extends Action
{

    // this is the selction in the eclipse framework
    IFile mModel_;

    /**
     * Constructs a new instance.
     */
    public CreateXmlFileAction( IFile aModel )
    {
        super();
        mModel_ = aModel;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run()
    {
        GetSpringXmlOptionsDialog d = new GetSpringXmlOptionsDialog( Display.getDefault()
            .getActiveShell() );

        d.open();

        if ( d.getReturnCode() == Window.CANCEL )
        {
            return;
        }

        try
        {
            FsmXmlGenerator gen = new FsmXmlGenerator( mModel_ );
            String xml = gen.generateXml( d.getFsmKeySelection() );
            if ( d.getDestination() == GetSpringXmlOptionsDialog.DEST_POPUP )
            {
                dispatchXmlToPopup( xml );
            }
            else if ( d.getDestination() == GetSpringXmlOptionsDialog.DEST_FILE )
            {
                dispatchXmlToFile( xml, d.getFileName() );
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Drop the contents of the
     * 
     * @param aXml
     */
    private void dispatchXmlToPopup( String aXml )
    {
        DisplaySpringXmlDialog d = new DisplaySpringXmlDialog( Display.getDefault()
            .getActiveShell(), aXml );
        d.open();
    }

    private void dispatchXmlToFile( String aXml, String aFilename ) throws IOException
    {
        FileOutputStream fos = new FileOutputStream( aFilename );
        FileChannel chan = fos.getChannel();
        try
        {
            // load up the buffer and pop the marker to the start
            int strSize = aXml.getBytes().length;
            ByteBuffer buff = ByteBuffer.allocate( strSize );
            buff.put( aXml.getBytes() );
            buff.flip();

            chan.write( buff );
        }
        finally
        {
            chan.close();
            fos.close();
        }
        MessageDialog.openInformation( Display.getDefault().getActiveShell(),
                                       "File Written",
                                       "Have written " + aXml.getBytes().length + " bytes to "
                                                       + aFilename + " correctly" );
    }
}
