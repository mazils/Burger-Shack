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
            Customer customer = new CustomerClient();
            //todo java.io.FileNotFoundException: recipes.txt (The system cannot find the file specified)
            RecipeProvider recipeProvider = new RecipeReader("C:\\Users\\Arturas\\IdeaProjects\\Burger-Shack\\src\\recipes.txt");

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
