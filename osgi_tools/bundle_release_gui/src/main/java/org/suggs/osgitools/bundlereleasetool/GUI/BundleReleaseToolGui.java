/*
 * BundleReleaseToolFrame.java created on 2 Jul 2009 07:04:13 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool.GUI;

import org.suggs.osgitools.bundlereleasetool.BundleData;
import org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Main frame for the release tool GUI.
 * 
 * @author suggitpe
 * @version 1.0 2 Jul 2009
 */
public class BundleReleaseToolGui
{

    // static logger
    private static final Log LOG = LogFactory.getLog( BundleReleaseToolGui.class );

    private boolean mExitOnClose_;
    private final JTextField mBundleName_ = new JTextField();
    private IBundleReleaseToolContextCallback mCallback_;
    private BundleDataModel mModel_ = new BundleDataModel();
    private JTable mTable_ = new JTable();

    /**
     * Constructs a new instance (builds the frame and inserts a new
     * pane).
     * 
     * @param aCallback
     *            used for any bundle related activity
     */
    public BundleReleaseToolGui( IBundleReleaseToolContextCallback aCallback )
    {
        this( aCallback, false );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aExitOnClose
     *            specify whether the GUI should exit on close of GUI
     *            window
     * @param aCallback
     *            callback used to encapsulate any bundle related
     *            activity
     */
    public BundleReleaseToolGui( IBundleReleaseToolContextCallback aCallback, boolean aExitOnClose )
    {
        super();
        LOG.debug( "Building Bundle Release Tool GUI" );
        mExitOnClose_ = aExitOnClose;
        mCallback_ = aCallback;

        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e )
        {
            // ah well!!
            LOG.warn( "Unable to set look and feel ... do not consider this something to worry about" );
        }

        final JFrame desktop = new JFrame( "Bundle release tool" );
        if ( mExitOnClose_ )
        {
            desktop.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        }
        desktop.addWindowListener( mCallback_.buildWindowListener() );

        // delegate the construction of the panel
        buildDesktop( desktop.getContentPane() );

        displayFrame( desktop );
    }

    /**
     * Builds up the pane.
     */
    private void buildDesktop( final Container aDesktop )
    {
        // set up for using the gridbag layout
        aDesktop.setLayout( new GridBagLayout() );

        aDesktop.setLayout( new GridBagLayout() );
        GridBagConstraints dc = new GridBagConstraints();
        dc.insets = new Insets( 2, 2, 2, 2 );

        int row = 0;

        dc.anchor = GridBagConstraints.CENTER;
        dc.gridx = 0;
        dc.gridy = row++;
        aDesktop.add( buildBundlePanel(), dc );

        dc.anchor = GridBagConstraints.EAST;
        dc.gridx = 0;
        dc.gridy = row++;
        aDesktop.add( buildInstallPanel(), dc );

        dc.anchor = GridBagConstraints.EAST;
        dc.gridx = 0;
        dc.gridy = row++;
        aDesktop.add( buildButtonsPanel(), dc );
    }

    /**
     * Delegation to create the Bundle view panel
     * 
     * @return the JPanel that is responsible for showing the bundle
     *         in a tble format
     */
    private JComponent buildBundlePanel()
    {
        // build the table
        mTable_.setColumnSelectionAllowed( true );
        mTable_.setCellSelectionEnabled( true );
        mTable_.setAutoCreateColumnsFromModel( true );
        mTable_.getTableHeader().setUpdateTableInRealTime( false );
        mTable_.setModel( mModel_ );

        mModel_.setBundleData( mCallback_.getBundleData() );
        mTable_.tableChanged( new TableModelEvent( mModel_ ) );
        JScrollPane ret = new JScrollPane();
        ret.doLayout();
        ret.getViewport().setBackground( mTable_.getBackground() );
        ret.getViewport().add( mTable_ );

        return ret;
    }

    /**
     * Delegation to create the main panel
     * 
     * @return
     */
    private JComponent buildInstallPanel()
    {
        JPanel ret = new JPanel();
        ret.setLayout( new GridBagLayout() );

        // set up the defaults to use throughout main
        EmptyBorder border = new EmptyBorder( new Insets( 0, 0, 0, 10 ) );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets( 2, 2, 2, 2 );
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        JLabel l = new JLabel( "Please select a bundle jar to release" );
        l.setBorder( border );
        ret.add( l, c );

        // add in the text box
        c.gridx = 0;
        c.gridy = 1;
        mBundleName_.setPreferredSize( new Dimension( 440, 20 ) );
        mBundleName_.setEditable( false );
        ret.add( mBundleName_, c );

        // add a button to select the file
        c.gridx = 1;
        c.gridy = 1;
        JButton button = new JButton( "..." );
        button.addActionListener( new ActionListener()
        {

            /**
             * Open up a file chooser dialog so that we can get a file
             * name.
             * 
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            @Override
            public void actionPerformed( ActionEvent event )
            {
                ChooseBundleJarAction action = new ChooseBundleJarAction();
                action.run();
                if ( action.getBundleJarName() != null && action.getBundleJarName().length() > 0 )
                {
                    mBundleName_.setText( action.getBundleJarName() );
                }
            }
        } );
        ret.add( button, c );

        return ret;
    }

    /**
     * Delegation to create the buttons panel
     * 
     * @return a new panel for the buttons
     */
    private JComponent buildButtonsPanel()
    {
        final JPanel ret = new JPanel();
        // set up the layout for the panel
        ret.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets( 2, 2, 2, 2 );
        c.anchor = GridBagConstraints.EAST;

        // create the OK button
        c.gridx = 0;
        c.gridy = 0;
        JButton load = new JButton( "Load" );
        load.addActionListener( new ActionListener()
        {

            /**
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            @Override
            public void actionPerformed( ActionEvent event )
            {
                if ( mBundleName_.getText() == null || mBundleName_.getText().length() == 0 )
                {
                    JOptionPane.showMessageDialog( ret,
                                                   "No Bundle name selected",
                                                   "User error!",
                                                   JOptionPane.ERROR_MESSAGE );
                    return;
                }

                mCallback_.installBundle( mBundleName_.getText() );

                mModel_.setBundleData( mCallback_.getBundleData() );
                mTable_.tableChanged( new TableModelEvent( mModel_ ) );
            }
        } );
        ret.add( load );

        return ret;
    }

    /**
     * Method to pop the GUI in the middle of the screen
     */
    private void displayFrame( JFrame aFrame )
    {
        // first center the frame
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point c = e.getCenterPoint();
        Rectangle bounds = e.getMaximumWindowBounds();

        int w = Math.max( bounds.width / 2, Math.min( aFrame.getWidth(), bounds.width ) );
        int h = Math.max( bounds.height / 2, Math.min( aFrame.getHeight(), bounds.height ) );
        int x = c.x - w / 2;
        int y = c.y - h / 2;
        aFrame.setBounds( x, y, w, h );
        if ( w == bounds.width && h == bounds.height )
        {
            aFrame.setExtendedState( Frame.MAXIMIZED_BOTH );
        }

        aFrame.validate();

        // then show it
        aFrame.pack();
        aFrame.setVisible( true );
        aFrame.setResizable( true );
        aFrame.toFront();
    }

    /**
     * Table data model
     * 
     * @author suggitpe
     * @version 1.0 10 Jul 2009
     */
    class BundleDataModel extends AbstractTableModel
    {

        private final ColumnData cols[] = { new ColumnData( "ID", 10, Label.LEFT ),
                                           new ColumnData( "State", 10, Label.LEFT ),
                                           new ColumnData( "Location", 10, Label.LEFT ),
                                           new ColumnData( "Bundle Name", 100, Label.LEFT ) };
        protected Vector<BundleData> mVector_ = new Vector<BundleData>();

        /**
         * Setter for the bundle data
         * 
         * @param aVector
         */
        public void setBundleData( Vector<BundleData> aVector )
        {
            mVector_.removeAllElements();
            for ( BundleData d : aVector )
            {
                mVector_.add( d );
            }
        }

        /**
         * @see javax.swing.table.TableModel#getColumnCount()
         */
        @Override
        public int getColumnCount()
        {
            return cols.length;
        }

        /**
         * @see javax.swing.table.TableModel#getRowCount()
         */
        @Override
        public int getRowCount()
        {
            return mVector_ == null ? 0 : mVector_.size();
        }

        /**
         * @see javax.swing.table.TableModel#getValueAt(int, int)
         */
        @Override
        public Object getValueAt( int row, int col )
        {
            if ( row < 0 || row >= getRowCount() )
            {
                return "";
            }

            BundleData dataRow = mVector_.get( row );
            switch ( col )
            {
                case 0:
                    return dataRow.id;
                case 1:
                    return dataRow.state;
                case 2:
                    return dataRow.location;
                case 3:
                    return dataRow.bundleName;
            }
            return "";
        }

        /**
         * @see javax.swing.table.AbstractTableModel#getColumnName(int)
         */
        @Override
        public String getColumnName( int col )
        {
            return cols[col].title;
        }

    }

    /**
     * Simple class to encapsulate the column data for the table
     * 
     * @author suggitpe
     * @version 1.0 10 Jul 2009
     */
    class ColumnData
    {

        public String title;
        public int width;
        public int align;

        public ColumnData( String aTitle, int aWidth, int aAlign )
        {
            title = aTitle;
            width = aWidth;
            align = aAlign;
        }
    }

}
