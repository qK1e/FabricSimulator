package Fabric.Warehouses;

import Fabric.Fabric;
import Fabric.Production.Body;

import java.util.ArrayList;

/**
 * Created by potap on 27.06.2017.
 */
public class BodyWarehouse
{
    private ArrayList<Body> bodies = new ArrayList<Body>();
    private int size;
    private int number = 0;
    Fabric fabric;
    public BodyWarehouse(int size){
        this.size = size;
    }
    public void setFabric(Fabric f)
    {
        fabric = f;
    }
    public synchronized Body get(){
        if(bodies.isEmpty()){
            try{
                //System.out.println("carbodies is empty");
                wait();
                return get();
            }
            catch(Exception ex){
                System.err.println("(carbody) Interrupted while wait");
                return null;
            }
        }
        else{
            if(number > 0)
                number--;
            fabric.sendBodies(number);
            Body cb = bodies.remove(0);
            notify();
            return cb;
        }
    }
    public synchronized Object put(Body detail){
        if(number + 1> size){
            try{
                wait();
                return put(detail);
            }
            catch(Exception ex){
                System.err.println("bw Interrupted while wait");
                return null;
            }
        }
        else{
            bodies.add(detail);
            number++;
            fabric.sendBodies(number);
            notify();
            return null;
        }
    }
    public int getNumber(){
        return number;
    }
    public int getSize(){
        return size;
    }
}
