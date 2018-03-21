/*
 * GumballMachineRemote.java created on 12 Sep 2007 07:08:13 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote gumball machine remote
 */
public interface IGumballMachineRemote extends Remote {

    int getCount() throws RemoteException;

    String getLocation() throws RemoteException;

    IState getState() throws RemoteException;

}
