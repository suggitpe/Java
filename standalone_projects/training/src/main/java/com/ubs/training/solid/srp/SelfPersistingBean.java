/*
 * PersistingBean.java created on 1 Oct 2009 07:12:00 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.srp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple data bean that has two responsibilities: 1) holding data; 2)
 * for writing itself to the database
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class SelfPersistingBean
{

    private static final Log LOG = LogFactory.getLog( SelfPersistingBean.class );

    private long id_;
    private String name_;
    private String somethingInteresting_;

    public void writeToDatabase()
    {
        LOG.debug( "Writing myself to the database" );
        // write self to the database
    }

    /**
     * Returns the value of id.
     * 
     * @return Returns the id.
     */
    public long getId()
    {
        return id_;
    }

    /**
     * Sets the id field to the specified value.
     * 
     * @param aId
     *            The id to set.
     */
    public void setId( long aId )
    {
        id_ = aId;
    }

    /**
     * Returns the value of name.
     * 
     * @return Returns the name.
     */
    public String getName()
    {
        return name_;
    }

    /**
     * Sets the name field to the specified value.
     * 
     * @param aName
     *            The name to set.
     */
    public void setName( String aName )
    {
        name_ = aName;
    }

    /**
     * Returns the value of somethingInteresting.
     * 
     * @return Returns the somethingInteresting.
     */
    public String getSomethingInteresting()
    {
        return somethingInteresting_;
    }

    /**
     * Sets the somethingInteresting field to the specified value.
     * 
     * @param aSomethingInteresting
     *            The somethingInteresting to set.
     */
    public void setSomethingInteresting( String aSomethingInteresting )
    {
        somethingInteresting_ = aSomethingInteresting;
    }
}
