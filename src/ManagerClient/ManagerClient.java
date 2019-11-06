package ManagerClient;

import ChefClient.Chef;
import CustomerClient.Customer;
import Server.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ManagerClient implements Manager,  Runnable {
    private static final String STOP= "Close down burger bar";
    private RemoteServer server;

    private Customer customer;
    private Chef chef;

    public ManagerClient(Customer customer,Chef chef) throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server= (RemoteServer) reg.lookup("Burgers");
        System.out.println("connected to Server");

        this.chef = chef;
        this.customer = customer;
    }

    @Override
    public synchronized void allStopWork() throws RemoteException {
        System.out.println(STOP);
        server.stopWorking(customer,chef);
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);

        while(true)
        {
           String a;
           if((a = scan.nextLine()) != null)
            {
                if(a.equals(STOP))
                {
                    try {
                        allStopWork();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
