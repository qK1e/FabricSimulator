import Fabric.Fabric;

/**
 * Created by potap on 27.06.2017.
 */
public class FabricController
{
    Fabric fabric = null;

    public FabricController(Fabric fabric)
    {
        System.out.println("FController constructor entered");
        this.fabric = fabric;
        System.out.println("FController constructor ended");
    }
    public void setFabric(Fabric fabric)
    {
        this.fabric = fabric;
    }

    public void setAccPeriod(int period)
    {
        fabric.setAccPeriod(period);
    }
    public void setEngPeriod(int period)
    {
        fabric.setEngPeriod(period);
    }
    public void setBodyPeriod(int period)
    {
        fabric.setBodyPeriod(period);
    }
    public void setDealerPeriod(int period)
    {
        fabric.setDealerPeriod(period);
    }

    public void requestInformation()
    {
        fabric.sendInformation();
    }
}
