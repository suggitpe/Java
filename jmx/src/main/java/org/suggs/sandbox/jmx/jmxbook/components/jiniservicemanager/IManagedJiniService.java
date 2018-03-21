/*
 * ManagedJiniService.java created on 25 Feb 2008 19:35:38 by suggitpe for project SandBox - JMX
 * 
 */
package org.suggs.sandbox.jmx.jmxbook.components.jiniservicemanager;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IManagedJiniService extends Remote {

    public void addEntries(List<?> aEntry) throws RemoteException;

    public void modifyEntries(List<?> aOldEntries, List<?> aNewEntries) throws RemoteException;

}
