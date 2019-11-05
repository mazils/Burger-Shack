package ManagerClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Manager extends Remote {
    void allStopWork() throws RemoteException;
}
