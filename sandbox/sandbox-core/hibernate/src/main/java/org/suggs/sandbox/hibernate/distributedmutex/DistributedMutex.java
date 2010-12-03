/*
 * DistributedMutex.java created on 3 Dec 2010 17:34:15 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.hibernate.distributedmutex;

/**
 * Class that will allow for a distributed logical mutex to be applied based on a given ID.
 * 
 * @author suggitpe
 * @version 1.0 3 Dec 2010
 */
public interface DistributedMutex {

    void synchronise( final int aId );

}
