package org.suggs.sandbox_webapps.springmvcpersistenttest.dao;

import org.suggs.sandbox_webapps.springmvcpersistenttest.domain.support.AbstractEntityBase;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * @author suggitpe
 * @version 1.0 3 Feb 2011
 */
public interface GenericDao<PK extends Serializable, T extends AbstractEntityBase> {

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
     * Gets all objects of this type. <strong>Be bloomin careful with this impl as it will fetch everything from teh
     * database</strong>
     *
     * @return a list of all objects.
     */
    List<T> getAll();

    /**
     * Persists an object to the database.
     *
     * @param object the object to save to the database
     */
    void save( T object );

    /**
     * Merges object with a persistent object
     *
     * @param object the object to merge from
     * @return returns the resulting object from the merge operation.
     */
    T merge( T object );

    /**
     * Removes an object from the database.
     *
     * @param id the id of the object to remove
     */
    void remove( PK id );
}
