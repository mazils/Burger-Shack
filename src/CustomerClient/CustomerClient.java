package CustomerClient;

import Server.RemoteServer;


import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class CustomerClient implements Customer, Runnable, Serializable
{
    private RemoteServer bar;
    private boolean working;

    public CustomerClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        bar = (RemoteServer) registry.lookup("Server");
        System.out.println("Connected");
        working = true;
        bar.addClient(this);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1Created");
    }

    @Override
    public void stopWorking() {
        System.out.println("customer stops working");
         working = false;
        System.out.println("working" + working);
    }

    @Override
    public void run() {
        Random random = new Random();

        while(working){
            try
            {
                    System.out.println("state of working in customer " + working);
                    // it does take when the bar doesnt have burger

                    System.out.println("Clients eats a burger" + "\n" + "there are ammount of burgers in list: " + bar.size());
                    bar.getBurger();
                    System.out.println("Client is eating burger");
                    //todo notify chef


            }
            catch (RemoteException  e)
            {
                e.printStackTrace();
            }




            int p = random.nextInt(8000-4000)+4000;
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Customer customer = new CustomerClient();
            Thread customerThread = new Thread((Runnable) customer);
            customerThread.start();
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
