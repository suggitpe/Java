/*
 * DatabaseDistributedMutex.java created on 3 Dec 2010 17:36:38 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex.impl;


import org.suggs.sandbox.hibernate.distributedmutex.DistributedMutex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Distributed Mutex implementation that utliised a database entry to form the mutex context.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
public class DatabaseDistributedMutex implements DistributedMutex {

    @SuppressWarnings("unused")
    private static final Log LOG = LogFactory.getLog( DatabaseDistributedMutex.class );

    /**
     * @see org.suggs.sandbox.hibernate.distributedmutex.DistributedMutex#synchronise(int)
     */
    @Override
    public void synchronise( int aId ) {}
}
