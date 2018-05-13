package Fabric.Warehouses;

import Fabric.Fabric;
import Fabric.Production.Car;

import java.util.ArrayList;

/**
 * Created by potap on 27.06.2017.
 */
public class CarWarehouse
{
    private Controller controller;
    private Fabric fabric;

    private ArrayList<Car> cars = new ArrayList<Car>();
    private int carsCount = 0;
    private int carsMade = 0;
    private int carsSold = 0;
    private int size;


    public CarWarehouse(int size)
    {
        this.size = size;
        controller = new Controller();
        controller.start();
        System.out.println("carWarehouse controller started");
    }

    public synchronized Object put(Car car)
    {
        if(carsCount + 1 > size){
            try{
                wait();
                return put(car);
            }
            catch(Exception ex){
                System.err.println("Interrupted while wait");
                return null;
            }
        }
        else{
            cars.add(car);
            carsCount++;
            fabric.sendCarsInStorage(carsCount);

            carsMade++;
            fabric.sendCarsMade(carsMade);

            notify();
            return null;
        }
    }

    public synchronized Car get()
    {
        if(cars.isEmpty())
        {
            try{
                //System.out.println("cars is empty");
                wait();
                return get();
            }
            catch(Exception ex){
                System.err.println("cw Interrupted while wait");
                return null;
            }
        }
        else
        {
            if(carsCount > 0)
                carsCount--;
            fabric.sendCarsInStorage(carsCount);
            carsSold++;
            fabric.sendCarsSold(carsSold);

            Car e = cars.remove(0);
            controller.notify();
            notify();
            return e;
        }
    }

    public int getSize()
    {return size;}

    public int getCarsCount()
    {return carsCount;}

    public void setFabric(Fabric fabric)
    {
        this.fabric = fabric;
    }

    public int getCarsMade()
    {return carsMade;}

    public int getCarsSold()
    {return carsSold;}

    private class Controller extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                if (cars.size() <= carsCount)
                {
                    fabric.makeCar();
                }
            }
        }
    }
}
