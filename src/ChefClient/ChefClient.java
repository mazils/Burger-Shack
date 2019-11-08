package ChefClient;

import ChefClient.Domain.Recipe;
import ChefClient.Proxy.RecipeProvider;
import ChefClient.Proxy.RecipeReader;
import Server.RemoteServer;
import Shared.Burger;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class ChefClient implements Chef,  Runnable
{
    private boolean Working = true;
    private RemoteServer server;
    private RecipeProvider recipe;
    private ArrayList<Recipe> recipeList;



    public ChefClient(RecipeProvider rec) throws Exception {
        UnicastRemoteObject.exportObject(this, 0);
        recipe= rec;
        recipeList= new ArrayList<Recipe>();
        makeSomeRecipes();
        Registry reg = LocateRegistry.getRegistry("Localhost", 1099);
        server= (RemoteServer) reg.lookup("Server");
        System.out.println("connected to Server");
        server.addClient(this);
    }

    @Override
    public void stopWorking() throws RemoteException
    {

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


            Random rand = new Random();
            int ran = rand.nextInt((3 - 1) + 1) + 1;
            String random = Integer.toString(ran);
            Recipe rec = recipe.getRecipeById(random);
            Burger burg = rec.createBurger();
            addBurgers(burg);
            System.out.println("Added a new"+ burg.getName()+"  to the Queue");
    }

    @Override
    public void run() {
        while(Working)
        {
            try {
                createBurgers();
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ends work chef");
        System.exit(0);
    }

    public static void main(String[] args)
    {
        RecipeProvider recipeProvider = new RecipeReader("C:\\Users\\Arturas\\IdeaProjects\\Burger-Shack\\src\\recipes.txt");
        try
        {
            Chef chef = new ChefClient(recipeProvider);
            Thread chefThread = new Thread((Runnable) chef);
            chefThread.start();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
