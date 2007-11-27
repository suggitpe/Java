/*
 * LinkTrackerFilter.java created on 20 Nov 2007 07:49:12 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.linktracker;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A filter to track links through a web app container.
 * 
 * @author suggitpe
 * @version 1.0 20 Nov 2007
 */
public class LinkTrackerFilter implements Filter
{

    Calendar startDate_ = Calendar.getInstance();
    static int count_ = 0;
    public static Hashtable<String, Link> requests_ = new Hashtable<String, Link>();
    public static Hashtable<String, Link> responses_ = new Hashtable<String, Link>();
    public static Hashtable<String, Link> referers_ = new Hashtable<String, Link>();
    FilterConfig filterConfig_ = null;

    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init( FilterConfig aFilterConfig ) throws ServletException
    {
        filterConfig_ = aFilterConfig;
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter( ServletRequest aRequest, ServletResponse aResponse, FilterChain aChain )
                    throws IOException, ServletException
    {

        HttpServletRequest req = (HttpServletRequest) aRequest;
        HttpServletResponse res = (HttpServletResponse) aResponse;

        String uri = req.getRequestURI();
        String path = req.getContextPath();
        String turi = uri.substring( path.length(), uri.length() );

        // process redirects
        if ( turi.startsWith( "/redirect" ) )
        {
            String url = req.getParameter( "url" );
            // error check
            if ( url == null || url.equals( "" ) )
            {
                res.sendRedirect( path );
                return;
            }

            if ( responses_.get( url ) != null )
            {
                Link l = new Link();
                l = responses_.get( url );
                l.setCount( l.getCount() + 1 );
            }
            else
            {
                Link l = new Link();
                l.setUrl( url );
                l.setCount( 1 );
                l.setLastVisited( Calendar.getInstance().getTimeInMillis() );
                responses_.put( l.getUrl(), l );
            }

            res.sendRedirect( url );
            return;
        }

        // process all images
        if ( uri.endsWith( ".js" ) || uri.endsWith( ".css" ) || uri.endsWith( ".gif" )
             || uri.endsWith( ".png" ) || uri.endsWith( ".jpg" ) || uri.endsWith( ".jpeg" ) )
        {
            aChain.doFilter( aRequest, aResponse );
            return;
        }

        {
            if ( requests_.get( uri ) != null )
            {
                Link l = new Link();
                l = requests_.get( uri );
                l.setCount( l.getCount() + 1 );
            }
            else
            {
                Link l = new Link();
                l.setUrl( uri );
                l.setCount( l.getCount() + 1 );
                l.setLastVisited( Calendar.getInstance().getTimeInMillis() );
                requests_.put( l.getUrl(), l );
            }
        }

        // log the referer
        String ref = req.getHeader( "referer" );
        if ( ref != null && !ref.equals( "" ) )
        {
            if ( referers_.get( ref ) != null )
            {
                Link l = referers_.get( ref );
                l.setCount( l.getCount() + 1 );
            }
            else
            {
                Link l = new Link();
                l.setUrl( ref );
                l.setCount( 1 );
                l.setLastVisited( Calendar.getInstance().getTimeInMillis() );
                referers_.put( l.getUrl(), l );
            }
        }

        // log the hit
        count_++;
        aChain.doFilter( aRequest, aResponse );
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
        filterConfig_ = null;
    }

    /**
     * Getter for the count attribute
     * 
     * @return the count
     */
    public int getCount()
    {
        return count_;
    }

    /**
     * Gets all of the requests
     * 
     * @return the requests as an array
     */
    public static Link[] getRequests()
    {
        return createLinkArray( new Vector<Link>( requests_.values() ) );
    }

    /**
     * Gets all of the responses
     * 
     * @return the responses as an array
     */
    public static Link[] getResponses()
    {
        return createLinkArray( new Vector<Link>( responses_.values() ) );
    }

    /**
     * Gets all of the referers
     * 
     * @return the referers as an array
     */
    public static Link[] getReferers()
    {
        return createLinkArray( new Vector<Link>( referers_.values() ) );
    }

    /**
     * Creats a Link array from a vector of values
     * 
     * @param aVector
     *            the vector to create the link array from
     * @return the link array
     */
    private static Link[] createLinkArray( Vector<Link> aVector )
    {
        Collections.sort( aVector, new LinkComparator() );
        return aVector.toArray( new Link[0] );
    }

    /**
     * Gets the number of days between the initialisation of the
     * filter and now
     * 
     * @return the number of days
     */
    public long getDays()
    {
        Calendar now = Calendar.getInstance();
        long a = startDate_.getTimeInMillis();
        long b = now.getTimeInMillis();
        long between = b - a;

        long days = ( between / ( 1000 * 60 * 60 * 24 ) );
        if ( days < 1 )
        {
            days = 1;
        }
        return days;
    }

}
