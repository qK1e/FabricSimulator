package Fabric.Production;

/**
 * Created by potap on 27.06.2017.
 */
public class Car
{
    Engine engine;
    Body body;
    Accessory acc;

    public Car(Engine engine, Body body, Accessory acc)
    {
        this.engine = engine;
        this.body = body;
        this.acc = acc;
    }
}
