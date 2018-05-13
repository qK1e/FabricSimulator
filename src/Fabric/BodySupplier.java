package Fabric;

import Fabric.Production.Body;
import Fabric.Warehouses.BodyWarehouse;

/**
 * Created by potap on 27.06.2017.
 */
public class BodySupplier extends Thread
{
    private BodyWarehouse warehouse;
    private String ID;
    private int period;
    int made = 0;

    public void setPeriod(int newPeriod){
        period = newPeriod;
    }

    public BodySupplier(String name, BodyWarehouse bw, int period)
    {
        this.ID = name;
        this.warehouse = bw;
        this.period = period;
    }

    public void run(){
        try{
            while(true){
                Thread.sleep(period);
                made++;
                System.out.println("created body" + ID + made);
                warehouse.put(new Body(ID + made));
            }
        }
        catch(InterruptedException ex)
        {
            System.out.println("BodySupplier interupted " + ID);
        }
    }
}
