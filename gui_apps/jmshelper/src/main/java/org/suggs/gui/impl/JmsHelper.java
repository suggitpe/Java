/*
 * JmsHelper.java created on 20 Jun 2007 18:49:45 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui.impl;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.suggs.gui.IJmsHelper;
import org.suggs.gui.JmsHelperException;
import org.suggs.gui.support.JmsHelperGuiBuilder;

/**
 * This class manages the construction of the main GUI itself along
 * with the placement of teh main panels
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class JmsHelper implements IJmsHelper
{

    private static final Log LOG = LogFactory.getLog( JmsHelper.class );

    /**
     * Constructs a new instance.
     */
    public JmsHelper()
    {
        super();
    }

    /**
     * @see org.suggs.gui.IJmsHelper#buildGui()
     */
    public void buildGui() throws JmsHelperException
    {

        LOG.debug( "Building GUI" );
        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e )
        {
            throw new JmsHelperException( "Failed to set UI look and feel", e );
        }

        JFrame.setDefaultLookAndFeelDecorated( true );

        final JFrame frame = new JFrame( "JMS Helper Tool" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        buildGuiPanels( frame.getContentPane() );

        // finally show the GUI frame
        JmsHelperGuiBuilder.displayFrame( frame );

    }

    /**
     * Builds the main GUI panels
     * 
     * @param aCtnr
     *            the container to build upon
     * @throws JmsHelperException
     */
    private static final void buildGuiPanels( Container aCntr ) throws JmsHelperException
    {
        LOG.debug( "Building GUI Panels" );

        GridBagLayout mainLayout = new GridBagLayout();
        aCntr.setLayout( mainLayout );

    }

}
