import Fabric.Fabric;

/**
 * Created by potap on 27.06.2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        Fabric fabric = new Fabric();
        UI view = new UI(new FabricController(fabric));
        fabric.addListener(view);
    }
}
