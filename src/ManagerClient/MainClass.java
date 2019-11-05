package ManagerClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MainClass {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Thread t= new Thread(new ManagerClient());
        t.start();
    }
}
