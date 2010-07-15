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
public class GetJarInformationDialog extends Dialog {

    public static final String XML = "XML";
    public static final String HTML = "HTML";
    public static final String TEXT = "TEXT";

    private String fromJarName;
    private String toJarName;
    private String diffOutput;

    private Text fromJarTextField;
    private Text toJarTextField;

    private Button htmlRadioButton;
    private Button xmlRadioButton;
    private Button textRadioButton;

    /**
     * Constructs a new instance.
     * 
     * @param aParentShell
     */
    public GetJarInformationDialog( Shell aParentShell ) {
        super( aParentShell );
    }

    /**
     * Updates the shell with a decnt title.
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell( Shell newShell ) {
        super.configureShell( newShell );
        newShell.setText( "Jardiff Plugin" );
    }

    /**
     * Creates the JarDiff dialog screen itself.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea( Composite parent ) {
        Composite ret = (Composite) super.createDialogArea( parent );
        createJarSelection( ret );
        createOutputSelectionRowInGui( ret );
        ret.pack();
        return ret;
    }

    /**
     * Creates the part of the dialog that contains the jar file selection.
     * 
     * @param aComposite
     *            the composite to add the section to.
     */
    private void createJarSelection( Composite aComposite ) {
        Group group = new Group( aComposite, SWT.None );
        group.setText( "Jar file selection" );

        group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group.setLayout( new GridLayout( 3, false ) );

        buildFromJarRowInGui( group );
        buildToJarRowInGui( group );
        buildFlipJarsRowInGui( group );

    }

    private void buildFromJarRowInGui( Composite aComposite ) {
        // first row
        final Label fromJarLabel = new Label( aComposite, SWT.NONE );
        fromJarLabel.setText( "From Jar:" );

        fromJarTextField = new Text( aComposite, SWT.BORDER );
        GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
        gridData.widthHint = 400;
        fromJarTextField.setLayoutData( gridData );

        final Button fromButton = new Button( aComposite, SWT.NONE );
        fromButton.setText( "..." );
        fromButton.addSelectionListener( buildSelectionListenerFileDialogForTextField( fromJarTextField ) );
    }

    private void buildToJarRowInGui( Composite aComposite ) {
        // second row
        final Label toJarLabel = new Label( aComposite, SWT.NONE );
        toJarLabel.setText( "To Jar:" );

        toJarTextField = new Text( aComposite, SWT.BORDER );
        GridData gridData = new GridData( GridData.FILL_HORIZONTAL );
        gridData.widthHint = 400;
        toJarTextField.setLayoutData( gridData );

        final Button toButton = new Button( aComposite, SWT.NONE );
        toButton.setText( "..." );
        toButton.addSelectionListener( buildSelectionListenerFileDialogForTextField( toJarTextField ) );
    }

    private void buildFlipJarsRowInGui( Composite aComposite ) {
        final Button flipToFromButton = new Button( aComposite, SWT.NONE );
        flipToFromButton.setText( "Flip to and from jars" );
        flipToFromButton.addSelectionListener( buildJarFlipSelectionListener() );
    }

    private SelectionListener buildSelectionListenerFileDialogForTextField( final Text aTextField ) {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected( SelectionEvent e ) {
                FileDialog fileDialog = buildArchiveFileDialog();
                String fileLocation = fileDialog.open();
                if ( fileLocation != null && !fileLocation.equals( "" ) ) {
                    aTextField.setText( fileLocation );
                    aTextField.setToolTipText( fileLocation );
                }
            }
        };
    }

    private final FileDialog buildArchiveFileDialog() {
        FileDialog fileDialog = new FileDialog( Display.getCurrent().getActiveShell(), SWT.READ_ONLY );
        fileDialog.setFilterNames( new String[] { "Java Archives", "Web Archives",
                                                 "Enterprise Application Archives", "Resource Archives" } );
        fileDialog.setFilterExtensions( new String[] { "*.jar", "*.war", "*.ear", "*.rar" } );
        return fileDialog;
    }

    private SelectionListener buildJarFlipSelectionListener() {
        return new SelectionAdapter() {

            @Override
            public void widgetSelected( SelectionEvent e ) {
                String fromText = fromJarTextField.getText();
                fromJarTextField.setText( toJarTextField.getText() );
                fromJarTextField.setToolTipText( toJarTextField.getText() );
                toJarTextField.setText( fromText );
                toJarTextField.setToolTipText( fromText );
            }
        };
    }

    private void createOutputSelectionRowInGui( Composite aComposite ) {

        Group group = new Group( aComposite, SWT.None );
        group.setText( "Output method selection" );

        group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group.setLayout( new GridLayout( 3, false ) );

        htmlRadioButton = new Button( group, SWT.RADIO );
        htmlRadioButton.setSelection( true );
        htmlRadioButton.setText( "HTML" );

        textRadioButton = new Button( group, SWT.RADIO );
        textRadioButton.setText( "Text" );

        xmlRadioButton = new Button( group, SWT.RADIO );
        xmlRadioButton.setText( "XML" );
    }

    /**
     * This is called when the dialog closes but before the widgets are recycled. This impl will extract all
     * the relevant data from the dialog and pop them into the actual object so they can be retrievd later on.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#close()
     */
    @Override
    public boolean close() {
        fromJarName = fromJarTextField.getText();
        toJarName = toJarTextField.getText();
        if ( xmlRadioButton.getSelection() ) {
            diffOutput = XML;
        }
        else if ( htmlRadioButton.getSelection() ) {
            diffOutput = HTML;
        }
        else if ( textRadioButton.getSelection() ) {
            diffOutput = TEXT;
        }
        else {
            System.out.println( "Unable to determine the output mechanism" );
        }
        return super.close();
    }

    /**
     * Called when the OK button is pressed. In this impl we verify that the user has actually entered some
     * data.
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    @Override
    protected void okPressed() {
        // now we do the validation
        String fromName = fromJarTextField.getText();
        String toName = toJarTextField.getText();
        if ( fromName == null || fromName.equals( "" ) || toName == null || toName.equals( "" ) ) {
            MessageDialog.openError( Display.getCurrent().getActiveShell(),
                                     "Incomplete input",
                                     "Both to and from jars need to be selected" );
            return;
        }

        File fromDest = new File( fromName );
        File toDest = new File( toName );
        if ( !fromDest.exists() || !toDest.exists() ) {
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
    public String getFromJarName() {
        return fromJarName;
    }

    /**
     * Getter for name of the To Jar
     * 
     * @return the to jar name
     */
    public String getToJarName() {
        return toJarName;
    }

    /**
     * Getter for the diff output option.
     * 
     * @return the diff output type
     */
    public String getDiffOutput() {
        return diffOutput;
    }

}
