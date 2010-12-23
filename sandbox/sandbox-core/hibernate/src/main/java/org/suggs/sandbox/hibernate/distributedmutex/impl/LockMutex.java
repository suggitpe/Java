/*
 * LockMutex.java created on 3 Dec 2010 19:26:30 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is set up as a hibernate pojo only so that the generic schema generation test can build up the schema
 * for this test.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
@Entity
@Table(name = "LOCK_MUTEX")
public class LockMutex {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger( LockMutex.class );

    @Id
    @Column(name = "ID")
    private int id;

    /**
     * Returns the value of id.
     * 
     * @return Returns the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id field to the specified value.
     * 
     * @param aId
     *            The id to set.
     */
    public void setId( int aId ) {
        id = aId;
    }

}
