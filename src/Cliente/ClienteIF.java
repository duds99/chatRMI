/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author eduardofigueiredo
 */
public interface ClienteIF extends Remote {
    void retrieveMessage(String message) throws RemoteException;
}
