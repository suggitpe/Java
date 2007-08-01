/*
 * IPersistenceLayer.java created on 27 Jul 2007 06:18:41 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.connection.store;

import org.suggs.apps.mercury.model.connection.IJmsConnectionDetails;
import org.suggs.apps.mercury.model.connection.MercuryConnectionStoreException;

import java.util.Map;

/**
 * This interface provides an abstraction of the underlying
 * persistence layer, enabling us to be more flexible with the way
 * that the persistence layer interacts with the connection store.
 * 
 * @author suggitpe
 * @version 1.0 27 Jul 2007
 */
public interface IPersistenceLayer
{

    /**
     * Verifies that the persistence layer is set up correctly,
     * initialing where necessary
     */
    void verifyPersistenceLayer();

    /**
     * Reads all connection details from the underlying persistence
     * layer and passes back to the caller in the form of a map
     * 
     * @return a map of connection details objects
     * @throws MercuryConnectionStoreException
     *             if there are problems in the reading of the data;
     */
    Map<String, IJmsConnectionDetails> readPersistenceLayer() throws MercuryConnectionStoreException;

    /**
     * Saves a given map of connection details to the underlying
     * persistence layer. This may completely replace the content of
     * the existing layer contents.
     * 
     * @param aMap
     *            the map of connection details to persist
     * @throws MercuryConnectionStoreException
     *             if there are any problems in the persistence of the
     *             data
     */
    void savePersistenceLayer( Map<String, IJmsConnectionDetails> aMap ) throws MercuryConnectionStoreException;

}
