package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import java.io.Serializable;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * @author suggitpe
 * @version 1.0 3 Feb 2011
 */
public interface GenericDao<PK extends Serializable, T> {

    /**
     * Getter by ID
     *
     * @param id the id of teh object to get
     * @return an object or null
     */
    T get( PK id );

    /**
     * Checks to see if a particular object exists in the database.
     *
     * @param id the id of the object to look for
     * @return true if object exists, else false
     */
    boolean exists( PK id );

    /**
     * Persists an object to the database.
     *
     * @param object the object to save to the database
     */
    void save( T object );

    /**
     * Removes an object from the database.
     *
     * @param object the object to remove
     */
    void remove( T object );
}
