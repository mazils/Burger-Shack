package ChefClient;

import Proxy.Burger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chef extends Remote {
    void stopWorking() throws RemoteException;
    void addBurgers(Burger burger)throws RemoteException;
}