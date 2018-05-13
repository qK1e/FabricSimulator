package Fabric;

import Fabric.Production.Engine;
import Fabric.Warehouses.EngineWarehouse;

/**
 * Created by potap on 27.06.2017.
 */
public class EngineSupplier extends Thread
{
    private EngineWarehouse warehouse;
    private String ID;
    private int period;
    int made = 0;

    public void setPeriod(int newPeriod){
        period = newPeriod;
    }

    public EngineSupplier(String name, EngineWarehouse ew, int period)
    {
        this.ID = name;
        this.warehouse = ew;
        this.period = period;
    }

    public void run(){
        try{
            while(true){
                Thread.sleep(period);
                made++;
                System.out.println("created engine" + ID + made);
                warehouse.put(new Engine(ID + made));
            }
        }
        catch(InterruptedException ex)
        {
            System.out.println("EngineSupplier interupted " + ID);
        }
    }
}
