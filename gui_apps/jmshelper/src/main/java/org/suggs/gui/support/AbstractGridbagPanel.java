/*
 * AbstractGridbagPanel.java created on 25 Jun 2007 06:14:27 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.support;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Abstract Panel class used by deriving Panels to make the addition
 * of panel components much easier
 * 
 * @author suggitpe
 * @version 1.0 25 Jun 2007
 */
public abstract class AbstractGridbagPanel extends JPanel
{

    private GridBagConstraints mConstraints_;

    private static final int C_HORZ = GridBagConstraints.HORIZONTAL;
    private static final int C_NONE = GridBagConstraints.NONE;
    private static final int C_WEST = GridBagConstraints.WEST;
    private static final int C_WIDTH = 1;
    private static final int C_HEIGHT = 1;

    /**
     * Constructs a new instance.
     */
    public AbstractGridbagPanel()
    {
        this( new Insets( 2, 2, 2, 2 ) );
    }

    /**
     * Constructs a new instance.
     * 
     * @param aInsets
     */
    public AbstractGridbagPanel( Insets aInsets )
    {
        super( new GridBagLayout() );
        mConstraints_ = new GridBagConstraints();
        mConstraints_.anchor = GridBagConstraints.WEST;
        mConstraints_.insets = aInsets;
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn )
    {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, C_WEST, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight )
    {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, C_WEST, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     * @param aAnchor
     *            the anchor for the cell
     */
    public void addAnchoredComponent( JComponent aComp, int aRow, int aColumn, int aAnchor )
    {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, aAnchor, C_NONE );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aAnchor
     *            the anchor for the component
     */
    public void addAnchoredComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight, int aAnchor )
    {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, aAnchor, C_NONE );
    }

    /**
     * Add a horizontally filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn )
    {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, C_WEST, C_HORZ );
    }

    /**
     * Add a filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add it to
     * @param aColumn
     *            the column to add it to
     * @param aFill
     *            the filling to use
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn, int aFill )
    {
        addComponent( aComp, aRow, aColumn, C_WIDTH, C_HEIGHT, C_WEST, aFill );
    }

    /**
     * Add a filled component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aFill
     *            the filling to use
     */
    public void addFilledComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight, int aFill )
    {
        addComponent( aComp, aRow, aColumn, aWidth, aHeight, C_WEST, aFill );
    }

    /**
     * Add a component to the panel
     * 
     * @param aComp
     *            the component to add
     * @param aRow
     *            the row to add the component to
     * @param aColumn
     *            the column to add the component to
     * @param aWidth
     *            number of columns to span
     * @param aHeight
     *            numof rows to span
     * @param aAnchor
     *            the anchor to use in the cell
     * @param aFill
     *            the filling to use
     */
    public void addComponent( JComponent aComp, int aRow, int aColumn, int aWidth, int aHeight, int aAnchor, int aFill )
    {
        mConstraints_.gridx = aColumn;
        mConstraints_.gridy = aRow;
        mConstraints_.gridwidth = aWidth;
        mConstraints_.gridheight = aHeight;
        mConstraints_.anchor = aAnchor;
        double weightx = 0.0;
        double weighty = 0.0;

        if ( aWidth > 1 )
        {
            weightx = 1.0;
        }

        if ( aHeight > 1 )
        {
            weighty = 1.0;
        }

        switch ( aFill )
        {
            case GridBagConstraints.HORIZONTAL:
                mConstraints_.weightx = weightx;
                mConstraints_.weighty = 0.0;
                break;
            case GridBagConstraints.VERTICAL:
                mConstraints_.weightx = 0.0;
                mConstraints_.weighty = weighty;
                break;
            case GridBagConstraints.BOTH:
                mConstraints_.weightx = weightx;
                mConstraints_.weighty = weighty;
                break;
            case GridBagConstraints.NONE:
                mConstraints_.weightx = 0.0;
                mConstraints_.weighty = 0.0;
                break;
            default:
                break;
        }

        mConstraints_.fill = aFill;

        add( aComp, mConstraints_ );
    }
}
