package Server;

import Proxy.Burger;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer
{

//    private BlockingQueue<Burger> blockingQueue;
    //todo queue implementation

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
    public void putBurger(Burger burger) throws RemoteException
    {

    }

    @Override
    public Burger getBurger() throws RemoteException
    {
        return null;//will be changed later
    }

    public static void main(String[] args)
    {
        Registry registry;
        try
        {
            registry = LocateRegistry.createRegistry(1099);
            Server server = new Server();
            registry.bind("Server",server);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        catch (AlreadyBoundException e)
        {
            e.printStackTrace();
        }
    }
}
