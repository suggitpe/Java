/*
 * GumballMachineRemote.java created on 12 Sep 2007 07:08:13 by suggitpe for project SandBox - Patterns
 * 
 */
package org.suggs.sandbox.patterns.structural.proxy.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote gumball machine remote
 * 
 * @author suggitpe
 * @version 1.0 12 Sep 2007
 */
public interface IGumballMachineRemote extends Remote
{

    /**
     * Getter for the gumball count
     * 
     * @return the number of gumballs in the machine
     * @throws RemoteException
     *             if there is an issue with the remote connection
     */
    int getCount() throws RemoteException;

    /**
     * Getter for the gumball machine location
     * 
     * @return the location of the gumball machine
     * @throws RemoteException
     *             if there is an issue with the remote connection
     */
    String getLocation() throws RemoteException;

    /**
     * Getter for the state of the gumball machine
     * 
     * @return the state of the gumball machine
     * @throws RemoteException
     *             if there is an issue with the remote connection
     */
    IState getState() throws RemoteException;

}
