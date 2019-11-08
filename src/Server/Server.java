package Server;

import ChefClient.Chef;
import CustomerClient.Customer;
import Server.Adapter.ArrayList;
import Server.Adapter.BlockingQueue;
import Server.Adapter.IBlockingQueue;
import Shared.Burger;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer
{

    private ArrayList<Remote> remoteArrayList = new ArrayList<Remote>();
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private ArrayList<Chef> chefArrayList = new ArrayList<Chef>();
    private IBlockingQueue blockingQueue;

    public Server() throws RemoteException
    {

        blockingQueue = new BlockingQueue();
        try
        {
            UnicastRemoteObject.exportObject(this, 0);
            System.out.println("Server started");
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
        blockingQueue.addBurger(burger);

    }

    @Override
    public Burger getBurger() throws RemoteException
    {   //returns first burger in the list and removes it from the list
        System.out.println("burger taken from the list" + " size ");
        return blockingQueue.removeBurger();

    }

    //todo shutdown method it will shutdown chefs and costumers // callback is needed to use
    @Override
    public void stopWorking() throws RemoteException
    {
        System.out.println("Everyone stops working");
        stopChef();
        stopCustomer();



        //todo stop chef

    }

    public void stopChef()
    {
        for(int i= 0;i< chefArrayList.size();i++)
        {
            try
            {
                chefArrayList.get(i).stopWorking();
            } catch (RemoteException e)
            {
                e.getMessage();
            }
        }
    }
    public void stopCustomer()
    {
        for(int i= 0;i< customerArrayList.size();i++)
        {
            try
            {
                customerArrayList.get(i).stopWorking();
            }
            catch (RemoteException e)
            {
                e.getMessage();
            }
        }
    }


    @Override
    public int size()
    {
        return blockingQueue.size();
    }

    @Override
    public void addClient(Remote remote) throws RemoteException
    {
        remoteArrayList.add(remote);
        System.out.println("costumer added");

        if(remote instanceof Customer)
        {
            customerArrayList.add((Customer) remote);
        }
        else if(remote instanceof Chef)
        {
            chefArrayList.add((Chef) remote);
        }
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
