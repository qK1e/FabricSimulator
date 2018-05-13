package Fabric.Warehouses;

import Fabric.Fabric;
import Fabric.Production.Accessory;

import java.util.ArrayList;

/**
 * Created by potap on 27.06.2017.
 */
public class AccessoriesWarehouse
{
    private ArrayList<Accessory> accessories = new ArrayList<Accessory>();
    private int size;
    private int number = 0;
    Fabric fabric;
    public AccessoriesWarehouse(int size){
        this.size = size;
    }
    public void setFabric(Fabric f)
    {fabric = f;}

    public synchronized Object put(Accessory detail){
        if(number + 1 > size){
            try{
                wait();
                return put(detail);
            }
            catch(Exception ex){
                System.err.println("Interrupted while wait");
                return null;
            }
        }
        else{
            accessories.add(detail);
            number++;
            fabric.sendAccs(number);

            notify();
            return null;
        }
    }

    public synchronized Accessory get()
    {
        if(accessories.isEmpty())
        {
            try
            {
                wait();
                return get();
            }
            catch(Exception ex)
            {
                System.err.println("aw Interrupted while wait");
                return null;
            }
        }
        else
        {
            if(number > 0)
                number--;

            fabric.sendAccs(number);

            Accessory acc = accessories.remove(0);
            notify();
            return acc;
        }
    }

    public int getNumber(){
        return number;
    }
    public int getSize(){
        return size;
    }
}
