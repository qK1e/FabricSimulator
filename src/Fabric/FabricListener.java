package Fabric;

/**
 * Created by potap on 27.06.2017.
 */
public interface FabricListener
{
    public void setAccStorage(int size);
    public void setAccessories(int accs);

    public void setEngines(int engines);
    public void setEngStorage(int size);

    public void setBodies(int bodies);
    public void setBodyStorage(int size);

    public void setCarMade(int made);
    public void setCarSold(int sold);
    public void setCars(int cars);
    public void setCarStorage(int size);

    public void setDealers(int dealers);
    public void setAccSuppliers(int suppliers);
}
