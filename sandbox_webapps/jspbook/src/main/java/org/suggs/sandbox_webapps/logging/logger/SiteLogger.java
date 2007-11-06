/*
 * SiteLogger.java created on 1 Nov 2007 18:39:07 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.logging.logger;

import org.suggs.sandbox_webapps.logging.formatter.BasicFormatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Logger for the whole site
 * 
 * @author suggitpe
 * @version 1.0 1 Nov 2007
 */
public class SiteLogger implements ServletContextListener
{

    private static Logger mLogger_ = Logger.getLogger( "global" );;

    /**
     * Getter for the global logger
     * 
     * @return the logger
     */
    public static synchronized Logger getLogger()
    {
        return mLogger_;
    }

    /**
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized( ServletContextEvent e )
    {
        System.out.println( "Starting SiteLogger" );
        ServletContext ctx = e.getServletContext();
        mLogger_.setLevel( Level.INFO );

        try
        {
            String root = ctx.getRealPath( "/" );
            FileHandler h = new FileHandler( root + "/log.txt" );
            h.setFormatter( new BasicFormatter() );
            mLogger_.addHandler( h );
        }
        catch ( IOException ioe )
        {
            System.err.println( "Can't load the logger:\n" + ioe.getMessage() );
        }

        ctx.setAttribute( "org.suggs.sandbox_webapps.logging.logger.SiteLogger", mLogger_ );
    }

    /**
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed( ServletContextEvent e )
    {
        ServletContext ctx = e.getServletContext();
        ctx.removeAttribute( "org.suggs.sandbox_webapps.logging.logger.SiteLogger" );
        mLogger_ = null;
    }
}
