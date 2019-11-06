package CustomerClient;

import Server.RemoteServer;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class CustomerClient implements Customer, Runnable
{
    private RemoteServer bar;
    private boolean working;

    public CustomerClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        bar = (RemoteServer) registry.lookup("Server");
        System.out.println("Connected");
        working = true;
    }

    @Override
    public void stopWorking() {
        working = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while(working){
            try {
                // it does take when the bar doesnt have burger
                bar.getBurger();
            }catch(RemoteException e){
                e.printStackTrace();
            }
            System.out.println("Client is eating burger");
            int p = random.nextInt(8000-4000)+4000;
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
