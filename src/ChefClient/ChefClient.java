package ChefClient;

import Proxy.Burger;
import Proxy.Recipe;
import Proxy.RecipeProvider;
import Server.IServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChefClient implements Chef,  Runnable {
    private boolean Working = true;
    private IServer server;
    private RecipeProvider recipe;

    public ChefClient(RecipeProvider rec) throws RemoteException, NotBoundException {
        recipe= rec;
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server= (IServer) reg.lookup("Burgers");
        System.out.println("connected to Server");
    }

    @Override
    public void stopWorking() throws RemoteException {
        Working =false;
    }

    @Override
    public void addBurgers(Burger burger) throws RemoteException {
        server.putBurger(burger);

    }

    @Override
    public void run() {
        while(Working)
        {
            //Queue.enqueue(burger)
            try {
                Recipe rec=recipe.getRecipeByName("");
                addBurgers(rec.createBurger());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
