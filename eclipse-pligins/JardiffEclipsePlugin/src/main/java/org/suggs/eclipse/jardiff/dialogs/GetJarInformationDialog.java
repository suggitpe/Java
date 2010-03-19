/*
 * GetJarInformationDialog.java created on 16 Mar 2010 07:11:50 by suggitpe for project JardiffPlugin
 * 
 */
package org.suggs.eclipse.jardiff.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog to get the jar information for diffing.
 * 
 * @author suggitpe
 * @version 1.0 16 Mar 2010
 */
public class GetJarInformationDialog extends Dialog
{

    private String fromJarName;
    private Text fromJarTextField;
    private String toJarName;
    private Text toJarTextField;

    /**
     * Constructs a new instance.
     * 
     * @param aParentShell
     */
    public GetJarInformationDialog( Shell aParentShell )
    {
        super( aParentShell );
    }

    /**
     * Updates the shell with a decnt title.
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell newShell )
    {
        super.configureShell( newShell );
        newShell.setText( "Jardiff Plugin" );
    }

    /**
     * Creates the JarDiff dialog screen itself.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent )
    {
        Composite ret = (Composite) super.createDialogArea( parent );
        createJarSelection( ret );
        createOutputSelection( ret );
        ret.pack();
        return ret;
    }

    /**
     * Creates the part of the dialog that contains the jar file
     * selection.
     * 
     * @param aComposite
     *            the composite to add the section to.
     */
    private void createJarSelection( Composite aComposite )
    {
        Group group = new Group( aComposite, SWT.None );
        group.setText( "Jar file selection" );

        group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group.setLayout( new GridLayout( 3, false ) );

        // first row
        final Label fromJarLabel = new Label( group, SWT.NONE );
        fromJarLabel.setText( "From Jar:" );

        fromJarTextField = new Text( group, SWT.BORDER );
        fromJarTextField.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        fromJarTextField.setEnabled( false );

        final Button fromButton = new Button( group, SWT.NONE );
        fromButton.setText( "..." );
        fromButton.addSelectionListener( buildSelectionListenerFileDialogForTextField( fromJarTextField ) );

        // second row
        final Label toJarLabel = new Label( group, SWT.NONE );
        toJarLabel.setText( "To Jar:" );

        toJarTextField = new Text( group, SWT.BORDER );
        toJarTextField.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        toJarTextField.setEnabled( false );

        final Button toButton = new Button( group, SWT.NONE );
        toButton.setText( "..." );
        toButton.addSelectionListener( buildSelectionListenerFileDialogForTextField( toJarTextField ) );

        final Button flipToFromButton = new Button( group, SWT.NONE );
        flipToFromButton.setText( "Flip to and from jars" );
        flipToFromButton.addSelectionListener( buildJarFlipSelectionListener() );
    }

    private SelectionListener buildSelectionListenerFileDialogForTextField( final Text aTextField )
    {
        return new SelectionAdapter()
        {

            @Override
            public void widgetSelected( SelectionEvent e )
            {
                FileDialog fileDialog = buildArchiveFileDialog();
                String fileLocation = fileDialog.open();
                if ( fileLocation != null && !fileLocation.equals( "" ) )
                {
                    aTextField.setText( fileLocation );
                }
            }
        };
    }

    private final FileDialog buildArchiveFileDialog()
    {
        FileDialog fileDialog = new FileDialog( Display.getCurrent().getActiveShell(),
                                                SWT.READ_ONLY );
        fileDialog.setFilterNames( new String[] { "Java Archives", "Web Archives",
                                                 "Enterprise Application Archives",
                                                 "Resource Archives" } );
        fileDialog.setFilterExtensions( new String[] { "*.jar", "*.war", "*.ear", "*.rar" } );
        return fileDialog;
    }

    private SelectionListener buildJarFlipSelectionListener()
    {
        return new SelectionAdapter()
        {

            @Override
            public void widgetSelected( SelectionEvent e )
            {
                String fromText = fromJarTextField.getText();
                fromJarTextField.setText( toJarTextField.getText() );
                toJarTextField.setText( fromText );
            }
        };
    }

    /**
     * Creates the part of the dialog that deals with the output
     * selection.
     * 
     * @param aComposite
     *            the composite to add the section to
     */
    private void createOutputSelection( Composite aComposite )
    {

        Group group = new Group( aComposite, SWT.None );
        group.setText( "Output method selection" );

        group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group.setLayout( new GridLayout( 3, false ) );

        Button htmlRadio = new Button( group, SWT.RADIO );
        htmlRadio.setSelection( true );
        htmlRadio.setText( "HTML" );

        Button textRadio = new Button( group, SWT.RADIO );
        textRadio.setText( "Text" );

        Button xmlRadio = new Button( group, SWT.RADIO );
        xmlRadio.setText( "XML" );
    }

    /**
     * This is called when the dialog closes but before the widgets
     * are recycled. This impl will extract all the relevant data from
     * the dialog and pop them into the actual object so they can be
     * retrievd later on.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close()
    {
        fromJarName = fromJarTextField.getText();
        toJarName = toJarTextField.getText();
        return super.close();
    }

    /**
     * Called when the OK button is pressed. In this impl we verify
     * that the user has actually entered some data.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed()
    {
        // now we do the validation
        String fromName = fromJarTextField.getText();
        String toName = toJarTextField.getText();
        if ( fromName == null || fromName.equals( "" ) || toName == null || toName.equals( "" ) )
        {
            MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                     "Incomplete input",
                                     "Both to and from jars need to be selected" );
            return;
        }

        File fromDest = new File( fromName );
        File toDest = new File( toName );
        if ( !fromDest.exists() || !toDest.exists() )
        {
            MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                     "Bad data",
                                     "One of the filenames entered does not actually exist." );
            return;
        }

        super.okPressed();
    }

    /**
     * Getter for the name of the From jar
     * 
     * @return the from jar name
     */
    public String getFromJarName()
    {
        return fromJarName;
    }

    /**
     * Getter for name of the To Jar
     * 
     * @return the to jar name
     */
    public String getToJarName()
    {
        return toJarName;
    }

}
