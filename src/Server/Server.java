package Server;

import Server.domain.Burger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements IServer
{
    public Server() throws RemoteException
    {
        try
        {
            UnicastRemoteObject.exportObject(this,0);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Burger burger) throws RemoteException
    {

    }

    @Override
    public Burger get() throws RemoteException
    {
        return null;
    }
}
