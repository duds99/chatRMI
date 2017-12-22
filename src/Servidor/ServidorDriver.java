/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import static Servidor.Log.jTextArea1;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardofigueiredo
 */
public class ServidorDriver {
    public static int quantidade;
   
    public static void main(String [] args) throws RemoteException, MalformedURLException, IOException{
        quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de usuarios:"));
        
        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("RMIServer", new Servidor());
        
        File arquivo = new File( "nomes.txt" );
        arquivo.delete();
        arquivo.createNewFile();
       
        Log log = new Log();
        log.setVisible(true);
        jTextArea1.setText("Servidor funcionando.");
        jTextArea1.setText(jTextArea1.getText().toString() + "\nArquivo de nomes criado.");
        jTextArea1.setText(jTextArea1.getText().toString() + "\nQuantidade máxima de usuário: " + quantidade);
    }
}
