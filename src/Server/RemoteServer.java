package Server;

import Domain.Burger;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote
{
    void putBurger(Burger burger) throws RemoteException;
    Burger getBurger() throws RemoteException;

}
