/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import static Cliente.Tela.jTextArea1;
import static Cliente.Tela.jTextArea2;
import Servidor.ServidorIF;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author eduardofigueiredo
 */
public class ClienteDriver {
            
    public static void main(String []args) throws RemoteException, NotBoundException, FileNotFoundException, IOException{
        JFrame frame = new JFrame();
        String nome = JOptionPane.showInputDialog("Digite seu apelido:");
        Registry reg = LocateRegistry.getRegistry("127.0.0.1",1099);
        System.out.println("Connect to server");
        ServidorIF servidor = (ServidorIF) reg.lookup("RMIServer");
        Cliente cliente = new Cliente(nome,servidor);
        new Thread(cliente).start();
        
        Tela tela = new Tela();
        tela.insere(cliente);
        tela.setVisible(true);
        
        jTextArea1.setText("Você entrou no chat");
        jTextArea2.setText(nome);
               
        FileReader fr = new FileReader("nomes.txt");
        BufferedReader br = new BufferedReader(fr);
        
        while(br.ready()){
            String linha  = br.readLine();
            jTextArea2.setText(jTextArea2.getText().toString() + "\n" + linha);
        }
        br.close();
        fr.close();
        
        FileWriter fw = new FileWriter("nomes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nome + "\n");
        bw.close();
        fw.close();    
    }
}
