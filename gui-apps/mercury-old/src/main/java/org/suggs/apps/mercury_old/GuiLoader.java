/*
 * GuiLoader.java created on 20 Jun 2007 18:50:53 by suggitpe for project GUI - JmsHelper
 * 
 */
package org.suggs.apps.mercury_old;

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
    private static final String DEFAULT_CFG_FILE = "xml/gui-loader.xml";

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

        String cfgFile = null;

        if ( aArgs.length == 0 )
        {
            LOG.debug( "Using defaul spring configuration from [" + DEFAULT_CFG_FILE + "]" );
            cfgFile = DEFAULT_CFG_FILE;
        }
        else if ( aArgs.length == 1 )
        {
            LOG.debug( "Using defaul spring configuration from [" + aArgs[0] + "]" );
            cfgFile = aArgs[0];
        }
        else
        {
            String err = "Invalid parameters passed into gui loader";
            LOG.error( err );
            throw new IllegalArgumentException( err );
        }

        BeanFactory fact = new XmlBeanFactory( new ClassPathResource( cfgFile ) );
        final IMercuryApp helper = (IMercuryApp) fact.getBean( "mercuryApplication" );

        javax.swing.SwingUtilities.invokeLater( new Runnable()
        {

            public void run()
            {
                LOG.debug( "Executing Run to build GUI" );
                try
                {
                    helper.buildGui();
                }
                catch ( MercuryException jhe )
                {
                    LOG.error( "Exception thrown when trying to build GUI app" );
                    jhe.printStackTrace();
                }
                LOG.debug( "GUI Run execution complete" );
            }

        } );

    }
}
