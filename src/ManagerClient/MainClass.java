package ManagerClient;

import ChefClient.Chef;
import ChefClient.ChefClient;
import ChefClient.Proxy.RecipeProvider;
import ChefClient.Proxy.RecipeReader;
import CustomerClient.Customer;
import CustomerClient.CustomerClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainClass {
    public static void main(String[] args) throws RemoteException, NotBoundException
    {


        try
        {

            RecipeProvider recipeProvider = new RecipeReader("C:\\Users\\Arturas\\IdeaProjects\\Burger-Shack\\src\\recipes.txt");

            Customer customer = new CustomerClient();
            Chef chef = new ChefClient(recipeProvider);


            Thread customerThread = new Thread((Runnable) customer);
            Thread chefThread = new Thread((Runnable) chef);

            customerThread.start();
            chefThread.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
