package ChefClient;

import Burger;
import ChefClient.Domain.Recipe;
import ChefClient.Proxy.RecipeProvider;
import Server.RemoteServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

public class ChefClient implements Chef,  Runnable {
    private boolean Working = true;
    private RemoteServer server;
    private RecipeProvider recipe;
    private ArrayList<Recipe> recipeList;

    public ChefClient(RecipeProvider rec) throws Exception {
        recipe= rec;
        recipeList= new ArrayList<Recipe>();
        makeSomeRecipes();
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server= (RemoteServer) reg.lookup("Burgers");
        System.out.println("connected to Server");
    }

    @Override
    public void stopWorking() throws RemoteException {
        Working =false;
    }

    @Override
    public synchronized void addBurgers(Burger burger) throws RemoteException {
        server.putBurger(burger);

    }

    public void makeSomeRecipes() throws Exception {
        String[] ingre= {"a","b","c"};
        Recipe a= new Recipe("1","CheeseBurger",ingre);
        recipe.updateRecipe(a);
        System.out.println("Added recipe to RecipeList");
        Recipe b= new Recipe("2","HamBurger",ingre);
        recipe.updateRecipe(b);
        System.out.println("Added recipe to RecipeList");
        Recipe c= new Recipe("3","BeefBurger",ingre);
        recipe.updateRecipe(c);
        System.out.println("Added recipe to RecipeList");
    }

    public synchronized void createBurgers() throws Exception {

        Random rand= new Random();
        int ran= rand.nextInt((3-1)+1) + 1;
        String random= Integer.toString(ran);
        Recipe rec=recipe.getRecipeById(random);
        Burger burg=rec.createBurger();
        addBurgers(burg);
        System.out.println("Added a new Burger to the Queue");
    }

    @Override
    public void run() {
        while(Working)
        {
            try {
                createBurgers();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
