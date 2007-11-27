/*
 * Link.java created on 22 Nov 2007 07:49:35 by suggitpe for project SandBoxWebApps - JSP Book
 * 
 */
package org.suggs.sandbox_webapps.filters.linktracker;

/**
 * Encapsulation of as link for the link tracker
 * 
 * @author suggitpe
 * @version 1.0 22 Nov 2007
 */
public class Link
{

    private String url_;
    private int count_;
    private long lastVisited_;

    /**
     * Returns the value of url.
     * 
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url_;
    }

    /**
     * Sets the url field to the specified value.
     * 
     * @param uri
     *            The url to set.
     */
    public void setUrl( String url )
    {
        url_ = url;
    }

    /**
     * Returns the value of count.
     * 
     * @return Returns the count.
     */
    public int getCount()
    {
        return count_;
    }

    /**
     * Sets the count field to the specified value.
     * 
     * @param count
     *            The count to set.
     */
    public void setCount( int count )
    {
        count_ = count;
    }

    /**
     * Returns the value of lastVisited.
     * 
     * @return Returns the lastVisited.
     */
    public long getLastVisited()
    {
        return lastVisited_;
    }

    /**
     * Sets the lastVisited field to the specified value.
     * 
     * @param lastVisited
     *            The lastVisited_ to set.
     */
    public void setLastVisited( long lastVisited )
    {
        lastVisited_ = lastVisited;
    }
}
