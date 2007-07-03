/*
 * GuiLoader.java created on 20 Jun 2007 18:50:53 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.gui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * This class encapsulates the main method for the initialisation of
 * the BeanFactory and spins off a thread to run the GUI.
 * 
 * @author suggitpe
 * @version 1.0 21 Jun 2007
 */
public class GuiLoader
{

    private static final Log LOG = LogFactory.getLog( GuiLoader.class );

    /**
     * Main method. Entry point into the application
     * 
     * @param aArgs
     *            args passed into the main app.
     */
    public static void main( String[] aArgs )
    {

        if ( LOG.isDebugEnabled() )
        {
            LOG.debug( "*************************************" );
            LOG.debug( "Classpath: " + System.getProperty( "java.class.path" ) );
            LOG.debug( "Java version: " + System.getProperty( "java.version" ) );
            LOG.debug( "*************************************" );
        }

        if ( aArgs.length != 1 )
        {
            String err = "No spring configuration file name set on cmd line ";
            LOG.error( err );
            throw new IllegalArgumentException( err );
        }

        final String cfgFile = aArgs[0];

        BeanFactory fact = new XmlBeanFactory( new ClassPathResource( cfgFile ) );
        final IJmsHelper helper = (IJmsHelper) fact.getBean( "jmsHelper" );

        javax.swing.SwingUtilities.invokeLater( new Runnable()
        {

            public void run()
            {
                LOG.debug( "Executing Run to build GUI" );
                try
                {
                    helper.buildGui();
                }
                catch ( JmsHelperException jhe )
                {
                    LOG.error( "Exception thrown when trying to build GUI app" );
                    jhe.printStackTrace();
                }
                LOG.debug( "GUI Run execution complete" );
            }

        } );

    }
}
