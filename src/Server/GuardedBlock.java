package Server;

import Shared.Burger;

public interface GuardedBlock {
    Burger removeBurger();
    void addBurger(Burger burger);
    int size();


}
