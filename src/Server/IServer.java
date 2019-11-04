package Server;

import Server.domain.Burger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
    void putBurger(Burger burger) throws RemoteException;
    Burger getBurger() throws RemoteException;

}
