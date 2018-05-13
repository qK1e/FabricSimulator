package Fabric.Warehouses;

import Fabric.Fabric;
import Fabric.Production.Engine;

import java.util.ArrayList;

/**
 * Created by potap on 27.06.2017.
 */
public class EngineWarehouse
{
    private ArrayList<Engine> engines = new ArrayList<Engine>();
    private int size;
    private int number = 0;
    Fabric fabric;
    public EngineWarehouse(int size){
        this.size = size;
    }
    public void setFabric(Fabric fabric)
    {
        this.fabric = fabric;
    }

    public synchronized Object put(Engine engine){
        if(number + 1 > size){
            try{
                wait();
                return put(engine);
            }
            catch(Exception ex){
                System.err.println("Interrupted while wait");
                return null;
            }
        }
        else{
            engines.add(engine);
            number++;
            fabric.sendEngines(number);
            notify();
            return null;
        }
    }

    public synchronized Engine get()
    {
        if(engines.isEmpty())
        {
            try
            {
                wait();
                return get();
            }
            catch(Exception ex)
            {
                System.err.println("ew Interrupted while wait");
                return null;
            }
        }
        else
        {
            if(number > 0)
                number--;
            fabric.sendEngines(number);
            Engine eng = engines.remove(0);
            notify();
            return eng;
        }
    }

    public int getNumber(){
        return number;
    }
    public int getSize(){
        return size;
    }
}
