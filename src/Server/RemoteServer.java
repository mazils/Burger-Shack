package Server;

import ChefClient.Chef;
import CustomerClient.Customer;
import Shared.Burger;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote
{
    void putBurger(Burger burger) throws RemoteException;
    Burger getBurger() throws RemoteException;
    void stopWorking(Customer customer, Chef chef) throws RemoteException;

}
