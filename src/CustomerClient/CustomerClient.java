package CustomerClient;

import Server.RemoteServer;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class CustomerClient implements Customer, Runnable
{
    private RemoteServer bar;
    private boolean working;

    public CustomerClient() throws RemoteException, NotBoundException
    {
        UnicastRemoteObject.exportObject(this, 0);//if  server needs to call methods on client you need this
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        bar = (RemoteServer) registry.lookup("Server");
        System.out.println("Connected");
        working = true;
        bar.addClient(this);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1Created");
    }

    @Override
    public void stopWorking()
    {
        working = false;

    }



    @Override
    public void run()
    {
        Random random = new Random();

        while (working)
        {
            try
            {
                System.out.println("state of working in customer ---" + working);
                // it does take when the bar doesnt have burger

                System.out.println("Clients eats a burger" + "\n" + "there are ammount of burgers in list: " + bar.size());
                bar.getBurger();
                Thread.sleep(1000);

            } catch (RemoteException | InterruptedException e)
            {
                e.printStackTrace();
            }

        }
        System.out.println("customer stops working");
        System.exit(0);
    }

    public static void main(String[] args)
    {
        try
        {
            Customer customer = new CustomerClient();
            Thread customerThread = new Thread((Runnable) customer);
            customerThread.start();
        } catch (RemoteException e)
        {
            e.printStackTrace();
        } catch (NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}
