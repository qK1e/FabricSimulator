package Fabric;

import Fabric.Production.Car;
import Fabric.Warehouses.AccessoriesWarehouse;
import Fabric.Warehouses.BodyWarehouse;
import Fabric.Warehouses.CarWarehouse;
import Fabric.Warehouses.EngineWarehouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by potap on 27.06.2017.
 */
public class Fabric
{
    AccessoriesWarehouse aw;
    BodyWarehouse bw;
    EngineWarehouse ew;
    CarWarehouse cw;

    MyThreadPool workers;
    AccSupplier[] as;
    BodySupplier bs;
    EngineSupplier es;

    Dealer[] dealers;

    int dealerPeriod;
    int accPeriod;
    int bodyPeriod = 1000;
    int engPeriod = 2000;

    ArrayList<FabricListener> listeners = new ArrayList<FabricListener>();

    /**
     * Created by potap on 27.06.2017.
     */

    public Fabric()
    {
        System.out.println("Fabric constructor started");
        Scanner scn = null;
        try
        {
            scn = new Scanner( new File("C:\\Users\\potap\\IdeaProjects\\FabricSimulator\\src\\config1.txt") );

            int bodyStorageSize = scn.nextInt();
            System.out.println("read body storage: " + bodyStorageSize);
            int engineStorageSize = scn.nextInt();
            System.out.println("read engine storage: " + engineStorageSize);
            int accStorageSize = scn.nextInt();
            System.out.println("read acc storage: " + accStorageSize);
            int carStorageSize = scn.nextInt();
            System.out.println("read car storage: " + carStorageSize);
            int workersCount = scn.nextInt();
            System.out.println("read workers count: " + workersCount);
            int accProviderPeriod = scn.nextInt();
            System.out.println("read acc provider period: " + accProviderPeriod);
            int accessoryProvidersCount = scn.nextInt();
            System.out.println("read acc providers count: " + accessoryProvidersCount);
            int dealersCount = scn.nextInt();
            System.out.println("read dealers count: " + dealersCount);
            int dealerPeriod = scn.nextInt();
            System.out.println("read dealers period: " + dealerPeriod);

            boolean isLogged;
            if (scn.nextInt() == 1) { isLogged = true; }
            else { isLogged = false; }

            aw = new AccessoriesWarehouse(accStorageSize);
            bw = new BodyWarehouse(bodyStorageSize);
            ew = new EngineWarehouse(engineStorageSize);

            workers = new MyThreadPool(workersCount);
            cw = new CarWarehouse(carStorageSize);

            cw.setFabric(this);
            aw.setFabric(this);
            ew.setFabric(this);
            bw.setFabric(this);


            this.as = new AccSupplier[accessoryProvidersCount];
            for (int i = 0; i < accessoryProvidersCount; i++)
            {
                this.as[i] = new AccSupplier(Integer.toString(i), aw, accProviderPeriod);
                this.as[i].start();
                System.out.println("accSupplier " + i + "started");
            }
            bs = new BodySupplier("bodySupplier1", bw, bodyPeriod);
            bs.start();
            es = new EngineSupplier("engSupplier1", ew, engPeriod);
            es.start();

            this.dealers = new Dealer[dealersCount];
            for (int i = 0; i < dealersCount; i++)
            {
                this.dealers[i] = new Dealer("Dealer " + i, isLogged, cw, dealerPeriod);
                this.dealers[i].start();
                System.out.println("dealer " + i + "started");
            }

            System.out.println("Fabric constructor ended");

            scn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void makeCar()
    {
        workers.execute(new MakeCarTask());
    }

    public void setAccPeriod(int accPeriod)
    {
        this.accPeriod = accPeriod;
        for(AccSupplier acS: as)
        {
            acS.setPeriod(accPeriod);
        }
    }

    public void setEngPeriod(int period)
    {
        this.engPeriod = period;
        es.setPeriod(period);
    }

    public void setBodyPeriod(int period)
    {
        this.bodyPeriod = period;
        bs.setPeriod(period);
    }

    public void setDealerPeriod(int period)
    {
        this.dealerPeriod = period;
        for(Dealer d: dealers)
        {
            d.setPeriod(period);
        }
    }


    public class MakeCarTask implements Runnable
    {
        @Override
        public void run()
        {
            cw.put(new Car(ew.get(),bw.get(),aw.get()));
        }
    }

    public void addListener(FabricListener fl)
    {
        listeners.add(fl);
        sendInformation();
    }

    public void sendCarsInStorage(int cars)
    {
        for(FabricListener fl: listeners)
        {
            fl.setCars(cars);
        }
    }
    public void sendCarsMade(int cars)
    {
        for(FabricListener fl: listeners)
        {
            fl.setCarMade(cars);
        }
    }
    public void sendCarsSold(int cars)
    {
        for(FabricListener fl: listeners)
        {
            fl.setCarSold(cars);
        }
    }
    public void sendAccs(int accs)
    {
        for(FabricListener fl: listeners)
        {
            fl.setAccessories(accs);
        }
    }
    public void sendEngines(int engs)
    {
        for(FabricListener fl: listeners)
        {
            fl.setEngines(engs);
        }
    }
    public void sendBodies(int bodies)
    {
        for(FabricListener fl: listeners)
        {
            fl.setBodies(bodies);
        }
    }
    public void sendInformation()
    {
        for(FabricListener fl: listeners)
        {
            fl.setAccessories(aw.getNumber());
            fl.setAccStorage(aw.getSize());
            fl.setAccSuppliers(as.length);

            fl.setBodies(bw.getNumber());
            fl.setBodyStorage(bw.getSize());

            fl.setEngines(ew.getNumber());
            fl.setEngStorage(ew.getSize());

            fl.setCarMade(cw.getCarsMade());
            fl.setCarSold(cw.getCarsSold());
            fl.setCars(cw.getCarsCount());
            fl.setCarStorage(cw.getSize());

            fl.setDealers(dealers.length);
        }
    }
}
