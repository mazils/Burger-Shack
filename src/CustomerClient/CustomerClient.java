package CustomerClient;

import Server.RemoteServer;


import java.rmi.RemoteException;

public class CustomerClient implements Customer, Runnable{
    private RemoteServer bar;
    private boolean working;

    public CustomerClient() throws RemoteException {
        //Registry registry = LocateRegistry.getRegistry("localhost",);
        //bar = (RemoteServer) registry.lookup();
        System.out.println("Connected");
        working = true;
    }

    @Override
    public void stopWorking() {
        working = false;
    }

    @Override
    public void run() {
        while(working){
            // method to enqueue sth like -- bar.eatBurger();
            System.out.println("Client is eating burger");
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
