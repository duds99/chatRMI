/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Cliente;
import Cliente.ClienteIF;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author eduardofigueiredo
 */
public interface ServidorIF extends Remote{
    void registerCliente(ClienteIF cliente) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void logout(Cliente cliente) throws RemoteException;
}
