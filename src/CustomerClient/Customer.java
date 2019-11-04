package CustomerClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Customer extends Remote {
    // manager will be calling this method trough the server/ - in order  to close the bar
    public void stopWorking() throws RemoteException;
}
