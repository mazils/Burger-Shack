package Server;

import Proxy.Burger;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
    void putBurger(Burger burger) throws RemoteException;
    Burger getBurger() throws RemoteException;

}
