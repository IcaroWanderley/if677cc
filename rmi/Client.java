import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        try {
            
            String route = "//localhost:1099/server";
            Metodos metodo = (Metodos)  Naming.lookup(route);
            String retorno = metodo.echo("O período tá acabando");
            System.out.println(retorno + " Comigo!!");
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
