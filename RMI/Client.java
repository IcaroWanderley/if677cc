/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class Client {
    public static void main(String[] args) {
        try {
            
            String route = "//localhost:1099/server";
            metodos metodo = (metodos)  Naming.lookup(route);
            String retorno = metodo.echo("O período tá acabando");
            System.out.println(retorno + " Comigo!!");
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
