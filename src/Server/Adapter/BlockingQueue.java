package Server.Adapter;

import Shared.Burger;

public class BlockingQueue implements IBlockingQueue
{

    private int capacity = 15;
    private ListADT list;
    private ArrayList arrayList;

    public BlockingQueue()
    {
        list= new ArrayList();
        arrayList = new ArrayList();
    }

    @Override
    public synchronized Burger removeBurger() {
       while (list.size()<=0)
       {
           try{
               System.out.println("Customers are waiting");
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       notifyAll();
       return (Burger) list.remove(0);
    }

    @Override
    public synchronized void addBurger(Burger burger) {
        while(list.size()>=capacity)
        {
            System.out.println("Chef is waiting for customers to eat burgers");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        list.add(burger);
    }

    public synchronized int size()
    {
        return list.size();
    }


}
