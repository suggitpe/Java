/*
 * DatabaseAccessorBean.java created on 1 Oct 2009 07:17:17 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.ocp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class who's sole job is to write beans to the database.
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class DatabaseAccessorBean
{

    private static final Log LOG = LogFactory.getLog( DatabaseAccessorBean.class );

    public void writeBeanToDatabase( PersistableBean aBean )
    {
        LOG.debug( "Writing bean to the database" );
        // do db stuff
    }

    public PersistableBean readBeanFromDatabase()
    {
        LOG.debug( "Reading bean from database" );
        // do db stuff
        return new PersistableBean();
    }
}
