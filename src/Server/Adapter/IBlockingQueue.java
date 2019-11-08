package Server.Adapter;

import Server.Server;
import Shared.Burger;

public interface IBlockingQueue
{
    Burger removeBurger();
    void addBurger(Burger burger);
    int size();

    

}
