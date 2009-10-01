/*
 * DatabaseAccessorBean.java created on 1 Oct 2009 07:17:17 by suggitpe for project Graduate Training
 * 
 */
package com.ubs.training.solid.lsp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class who's sole job is to write beans to the database.
 * 
 * @author suggitpe
 * @version 1.0 1 Oct 2009
 */
public class DatabaseAccessorBean implements IBeanDao
{

    private static final Log LOG = LogFactory.getLog( DatabaseAccessorBean.class );

    @Override
    public void writeBean( PersistableBean aBean )
    {
        LOG.debug( "Writing bean to the database" );
        // do db stuff
    }

    @Override
    public PersistableBean readBean()
    {
        LOG.debug( "Reading bean from database" );
        // do db stuff
        return new PersistableBean();
    }
}
