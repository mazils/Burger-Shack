package Server;

import Shared.Burger;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer
{

    private ListADT listADT;

    public Server() throws RemoteException
    {
        listADT = new Adapter();

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
        //add burger to end of list
        System.out.println("burger added to the list");
        listADT.add(burger);
    }

    @Override
    public Burger getBurger() throws RemoteException
    {   //returns last burger in the list and removes it from the list
        System.out.println("burger taken from the list");
        return (Burger) listADT.remove(listADT.size() -1);

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
