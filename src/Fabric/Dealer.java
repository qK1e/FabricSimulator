package Fabric;

import Fabric.Warehouses.CarWarehouse;

/**
 * Created by potap on 27.06.2017.
 */
public class Dealer extends Thread
{
    int period;
    boolean isLogged;
    CarWarehouse cw;
    String name;

    public Dealer(String name, boolean isLogged, CarWarehouse cw, int period)
    {
        this.name = name;
        this.isLogged = isLogged;
        this.cw = cw;
        this.period = period;
    }

    public void setPeriod(int period)
    {
        this.period = period;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(period);
                System.out.println("dealer" + name + "trying to got a car");
                cw.get();
                System.out.println("dealer" + name + "got a car");
                //TODO: запись в лог будет здесь
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
    }
}
