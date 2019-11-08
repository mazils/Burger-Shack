package Server;

import ChefClient.Chef;
import CustomerClient.Customer;
import CustomerClient.CustomerClient;
import Shared.Burger;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;



public class Server implements RemoteServer
{

    private ArrayList<Remote> remoteArrayList = new ArrayList<Remote>();
    private ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    private ArrayList<Chef> chefArrayList = new ArrayList<Chef>();
    private ListADT listADT;

    public Server() throws RemoteException
    {
        listADT = new Adapter();//todo blockingQueue


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

    }

    @Override
    public Burger getBurger() throws RemoteException
    {   //returns first burger in the list and removes it from the list
        System.out.println("burger taken from the list" + " size ");
        return (Burger) listADT.remove(0);

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

    public void stopChef() throws RemoteException
    {
        for(int i= 0;i< chefArrayList.size();i++)
        {
            chefArrayList.get(i).stopWorking();
        }
    }
    public void stopCustomer() throws RemoteException
    {
        for(int i= 0;i< customerArrayList.size();i++)
        {
            customerArrayList.get(i).stopWorking();
        }
    }


    @Override
    public int size()
    {
        return listADT.size();
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
            System.out.println("Server started");
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
