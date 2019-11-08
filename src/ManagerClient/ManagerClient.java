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
    private final String STOP= "Close down burger bar"; //do we need to have it static?
    private RemoteServer server;



    public ManagerClient() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server = (RemoteServer) reg.lookup("Server");
        System.out.println("connected to Server");


    }

    @Override
    public synchronized void allStopWork() throws RemoteException {
        System.out.println(STOP);
        server.stopWorking();
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

    public static void main(String[] args)
    {
        try
        {
            Manager manager = new ManagerClient();
            Thread managerThread = new Thread((Runnable) manager);
            managerThread.start();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}
