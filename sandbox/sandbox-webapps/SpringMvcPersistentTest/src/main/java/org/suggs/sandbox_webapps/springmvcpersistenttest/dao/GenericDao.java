package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * @author suggitpe
 * @version 1.0 3 Feb 2011
 */
public interface GenericDao<T, PK extends Serializable> {

    /**
     * Returns you a list of all objects within the data store.
     * 
     * @return List of all objects from the table
     */
    List<T> getAll();

    /**
     * Getter by ID
     * 
     * @param id
     *            the id of teh object to get
     * @return an object or null
     */
    T get( PK id );

    /**
     * Checks to see if a particular object exists in the database.
     * 
     * @param id
     *            the id of the object to look for
     * @return true if object exists, else false
     */
    boolean exists( PK id );

    /**
     * saves or updates an object in teh data store
     * 
     * @param object
     *            the object to persist
     * @return th eobject that has beenperissted.
     */
    T saveOrUpdate( T object );

    /**
     * Persists an object to the database.
     * 
     * @param object
     *            the object to save to the database
     */
    void save( T object );

    /**
     * Removes an object from the database.
     * 
     * @param object
     *            the object to remove
     */
    void remove( T object );
}
