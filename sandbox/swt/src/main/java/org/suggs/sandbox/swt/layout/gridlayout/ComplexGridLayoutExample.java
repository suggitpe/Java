/*
 * ComplexGridLayoutExample.java created on 9 Apr 2008 07:06:14 by suggitpe for project SandBox - SWT
 * 
 */
package org.suggs.sandbox.swt.layout.gridlayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class aims to show a varying range of layouts and SWT widgets
 * that can be aranged on a GUI canvas
 * 
 * @author suggitpe
 * @version 1.0 9 Apr 2008
 */
public class ComplexGridLayoutExample
{

    private static final Log LOG = LogFactory.getLog( ComplexGridLayoutExample.class );

    private static Display mDisplay_;
    private static Shell mShell_;
    private static Text mDogName_;
    private static Combo mDogBreed_;
    private static Canvas mDogPhoto_;
    private static Image mDogImage_;
    private static List mCategories_;
    private static Text mOwnerName_;
    private static Text mOwnerPhone_;

    public static void main( String[] args )
    {

        LOG.debug( "Creating GUI" );
        // set up the shell
        mDisplay_ = new Display();
        mShell_ = new Shell( mDisplay_ );
        mShell_.setText( "Dog Show Entry" );

        // now set up the high level layout
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        mShell_.setLayout( layout );

        // ------------------------------------
        // dog name
        new Label( mShell_, SWT.NULL ).setText( "Dog's name:" );
        mDogName_ = new Text( mShell_, SWT.SINGLE | SWT.BORDER );
        GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalSpan = 2;
        mDogName_.setLayoutData( data );

        // breed combo box
        new Label( mShell_, SWT.NULL ).setText( "Dog's breed:" );
        mDogBreed_ = new Combo( mShell_, SWT.NULL );
        mDogBreed_.setItems( new String[] { "Collie", "Pitbull", "Poodle", "Scottie" } );
        mDogBreed_.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL ) );

        // categories label
        Label l = new Label( mShell_, SWT.NULL );
        l.setText( "Categories" );
        l.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL ) );

        // Photos
        new Label( mShell_, SWT.NULL ).setText( "Photo:" );
        mDogPhoto_ = new Canvas( mShell_, SWT.BORDER );
        data = new GridData( GridData.FILL_BOTH );
        data.widthHint = 80;
        data.heightHint = 80;
        data.verticalSpan = 3;
        mDogPhoto_.setLayoutData( data );
        mDogPhoto_.addPaintListener( new PaintListener()
        {

            public void paintControl( PaintEvent aEvent )
            {
                if ( mDogImage_ != null )
                {
                    aEvent.gc.drawImage( mDogImage_, 0, 0 );
                }
            }
        } );

        // categories
        mCategories_ = new List( mShell_, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL );
        mCategories_.setItems( new String[] { "Best of breed", "Prettiest female",
                                             "Handsomest male", "Best dressed", "Fluffiest ears",
                                             "Most colours", "Best performer", "Loudest bark",
                                             "Best behaved", "Prettiest eyes", "Most hair",
                                             "Longest tail", "Cutest trick", "Most stupig grin" } );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL );
        data.verticalSpan = 4;
        int listHeight = mCategories_.getItemHeight() * 12;
        Rectangle trim = mCategories_.computeTrim( 0, 0, 0, listHeight );
        data.heightHint = trim.height;
        mCategories_.setLayoutData( data );

        // add a button to load the doggie picture
        Button browse = new Button( mShell_, SWT.PUSH );
        browse.setText( "Browse..." );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalIndent = 5;
        browse.setLayoutData( data );
        browse.addSelectionListener( new SelectionAdapter()
        {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event )
            {
                String filename = new FileDialog( mShell_ ).open();
                if ( filename != null )
                {
                    mDogImage_ = new Image( mDisplay_, filename );
                }
            }

        } );

        // add button to delete the doggie picture
        Button del = new Button( mShell_, SWT.PUSH );
        del.setText( "Delete" );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING );
        data.horizontalIndent = 5;
        del.setLayoutData( data );
        del.addSelectionListener( new SelectionAdapter()
        {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event )
            {
                if ( mDogImage_ != null )
                {
                    mDogImage_.dispose();
                    mDogImage_ = null;
                    mDogPhoto_.redraw();
                }
            }
        } );

        // create a group for the owner layout
        Group ownerInfo = new Group( mShell_, SWT.NULL );
        ownerInfo.setText( "Owner Info" );
        layout = new GridLayout();
        layout.numColumns = 2;
        ownerInfo.setLayout( layout );
        data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalSpan = 2;
        ownerInfo.setLayoutData( data );

        // set up owner name
        new Label( ownerInfo, SWT.NULL ).setText( "Name:" );
        mOwnerName_ = new Text( ownerInfo, SWT.SINGLE | SWT.BORDER );
        mOwnerName_.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // set up the owner phone
        new Label( ownerInfo, SWT.NULL ).setText( "Phone:" );
        mOwnerPhone_ = new Text( ownerInfo, SWT.SINGLE | SWT.BORDER );
        mOwnerPhone_.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

        // complete with an enter button
        Button enter = new Button( mShell_, SWT.PUSH );
        enter.setText( "Enter" );
        data = new GridData( GridData.HORIZONTAL_ALIGN_END );
        data.horizontalSpan = 3;
        enter.setLayoutData( data );
        enter.addSelectionListener( new SelectionAdapter()
        {

            /**
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected( SelectionEvent event )
            {
                StringBuffer buff = new StringBuffer();
                buff.append( "\nDog Name:" ).append( mDogName_.getText() ).append( "\n" );
                buff.append( "Dog Breed:" ).append( mDogBreed_.getText() ).append( "\n" );
                buff.append( "Owner name:" ).append( mOwnerName_.getText() ).append( "\n" );
                buff.append( "Owner phone:" ).append( mOwnerPhone_.getText() ).append( "\n" );
                buff.append( "Categories:\n" );
                for ( String s : mCategories_.getSelection() )
                {
                    buff.append( "\t" ).append( s ).append( "\n" );
                }

                LOG.info( buff );
            }
        } );

        // ------------------------------------
        LOG.debug( "Opening GUI" );
        // now get the dialog up and running
        mShell_.pack();
        mShell_.open();
        while ( !mShell_.isDisposed() )
        {
            if ( !mDisplay_.readAndDispatch() )
            {
                mDisplay_.sleep();
            }
        }
        LOG.debug( "All done" );
    }
}
