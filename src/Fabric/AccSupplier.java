package Fabric;

import Fabric.Production.Accessory;
import Fabric.Warehouses.AccessoriesWarehouse;

/**
 * Created by potap on 27.06.2017.
 */
public class AccSupplier extends Thread
{
    private AccessoriesWarehouse warehouse;
    private String ID;
    private int period;
    int made = 0;

    public void setPeriod(int newPeriod){
        period = newPeriod;
    }

    public AccSupplier(String name, AccessoriesWarehouse aw, int period)
    {
        this.ID = name;
        this.warehouse = aw;
        this.period = period;
    }

    public void run(){
        try{
            while(true){
                Thread.sleep(period);
                made++;
                System.out.println("created accessorie" + ID + made);
                warehouse.put(new Accessory(ID + made));
            }
        }
        catch(InterruptedException ex)
        {
            System.out.println("AccSupplier interupted " + ID);
        }
    }
}
