/*
 * DistributedMutex.java created on 3 Dec 2010 17:34:15 by suggitpe for project sandbox-hibernate
 * 
 */
package org.suggs.sandbox.spring.distributedmutex;

/**
 * Class that will allow for a distributed logical mutex to be applied based on a given ID.
 */
public interface DistributedMutex {

    void synchronise( final int aId );

}
