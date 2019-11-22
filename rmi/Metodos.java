import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Metodos extends Remote {
    public String echo(String str) throws RemoteException;
}
