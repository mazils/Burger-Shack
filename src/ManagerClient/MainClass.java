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

            RecipeProvider recipeProvider = new RecipeReader("C:\\Users\\Emmanuel Simon\\IdeaProjects\\Burger-Shack\\src\\recipes.txt");

            Customer customer = new CustomerClient();
            Chef chef = new ChefClient(recipeProvider);
            Manager manager = new ManagerClient(customer,chef);

            Thread customerThread = new Thread((Runnable) customer);
            Thread chefThread = new Thread((Runnable) chef);
            Thread managerThread = new Thread((Runnable) manager);
            customerThread.start();
            chefThread.start();
            managerThread.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
