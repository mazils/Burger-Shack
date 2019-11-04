package Server;

import Server.domain.Burger;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote
{
    void send(Burger burger) throws RemoteException;
    Burger get() throws RemoteException;

}
