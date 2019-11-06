package Server;

import ChefClient.Chef;
import CustomerClient.Customer;
import Shared.Burger;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer
{

    private ListADT listADT;
    private Customer customer;

    public Server() throws RemoteException
    {
        listADT = new Adapter();

        try
        {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void putBurger(Burger burger) throws RemoteException
    {
        //add burger to end of list
        System.out.println("burger added to the list");
        listADT.add(burger);
        customer.notifyAll(); // todo customer is null probably


    }

    @Override
    public Burger getBurger() throws RemoteException
    {   //returns first burger in the list and removes it from the list
        System.out.println("burger taken from the list");
//        chef.notifyAll(); todo should be this i guess
        return (Burger) listADT.remove(0);

    }

    //todo shutdown method it will shutdown chefs and costumers // callback is needed to use
    @Override
    public void stopWorking(Customer customer, Chef chef) throws RemoteException
    {
        //callback
        this.customer = customer;
        System.out.println("Everyone stops working");
        chef.stopWorking();
        customer.stopWorking();
    }

    @Override
    public int size()
    {
        return listADT.size();
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
