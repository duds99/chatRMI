/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import static Cliente.Tela.jTextArea1;
import static Cliente.Tela.jTextArea2;
import Servidor.ServidorIF;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardofigueiredo
 */
public class Cliente extends UnicastRemoteObject implements ClienteIF, Runnable{
    private static final long serialVersion = 1L;
    public static ServidorIF servidor;
    private String nome = null;
    
    public Cliente(String nome, ServidorIF servidor) throws RemoteException{ 
        this.nome = nome;
        this.servidor = servidor;
        servidor.broadcastMessage(nome + " entrou no chat.");
        servidor.registerCliente(this);
    }
    
    public void retrieveMessage(String message) throws RemoteException {
        System.out.println(message);
        if(message.length() >=16){
            String verifica = message.substring(message.length()-16,message.length());
            if(verifica.equals(" entrou no chat.")){
                String aux = message.substring(0,message.length()-16);
                jTextArea2.setText(jTextArea2.getText().toString() + "\n" + aux);
            }
        }
        jTextArea1.setText(jTextArea1.getText().toString() + "\n" + message);  
    }
    
    public void sair() throws RemoteException{
        System.out.println("Cliente.Cliente.sair()x");
        servidor.logout(this);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message; 
        
        while(true){
            message = scanner.nextLine();
            try {
                servidor.broadcastMessage(nome + ": " + message);
            } catch (RemoteException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
