/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulo
 */
public class Server extends UnicastRemoteObject implements metodos{

    Server() throws RemoteException{
        super();
    }
    
    public static void main(String[] args) {
        try {            
            Server server = new Server();
            String route = "//localhost:1099/server";
            Naming.rebind(route, server);
            System.out.println("Servidor criado");            
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String echo(String str) throws RemoteException {
        System.out.println("nova chamada para echo");
        return (str);
    }
    
}
