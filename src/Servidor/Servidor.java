/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Cliente;
import Cliente.ClienteIF;
import static Servidor.Log.jTextArea1;
import static Servidor.ServidorDriver.quantidade;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author eduardofigueiredo
 */
public class Servidor extends UnicastRemoteObject implements ServidorIF{
    private static final long serialVersionUID = 1L;
    private ArrayList<ClienteIF> cliente;
   
    protected Servidor() throws RemoteException{
        cliente = new ArrayList<ClienteIF>();
    }

    public synchronized void registerCliente(ClienteIF cliente) throws RemoteException {
        int valor = this.cliente.size();
        if(valor < quantidade){
           this.cliente.add(cliente);
        }else{
            jTextArea1.setText(jTextArea1.getText().toString() + "\nNovo usuário tentou conectar.");
        }
    }

    public synchronized void broadcastMessage(String message) throws RemoteException {
        int i = 0;
        jTextArea1.setText(jTextArea1.getText().toString() + "\n"+ message);
        while(i < cliente.size()){  
            cliente.get(i++).retrieveMessage(message);
        }
    }
    
    public synchronized void logout(Cliente cliente){
        this.cliente.remove(cliente);
        jTextArea1.setText(jTextArea1.getText().toString() + "\nRemoveu.");
    }
}
