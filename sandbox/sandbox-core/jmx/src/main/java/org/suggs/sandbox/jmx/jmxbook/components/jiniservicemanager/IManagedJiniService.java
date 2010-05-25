/*
 * ManagedJiniService.java created on 25 Feb 2008 19:35:38 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.jiniservicemanager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * TODO Write javadoc for ManagedJiniService
 * 
 * @author suggitpe
 * @version 1.0 25 Feb 2008
 */
public interface IManagedJiniService extends Remote {

    /**
     * @param aEntry
     * @throws RemoteException
     */
    @SuppressWarnings("unchecked")
    public void addEntries( List aEntry ) throws RemoteException;

    /**
     * MOdifies an existing set of entries
     * 
     * @param aOldEntries
     * @param aNewEntries
     * @throws RemoteException
     */
    @SuppressWarnings("unchecked")
    public void modifyEntries( List aOldEntries, List aNewEntries ) throws RemoteException;

}
