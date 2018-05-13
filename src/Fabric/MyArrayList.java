package Fabric;

import java.util.ArrayList;

/**
 * Created by potap on 24.06.2017.
 */

public class MyArrayList<E> extends ArrayList<E>
{
    @Override
    public synchronized boolean add(E r)
    {
        this.add(0, r);
        notify();
        return true;
    }

    public synchronized E pull()
    {
        while (true)
        {
            if (this.size() != 0)
            {
                E copy = this.get( this.size() - 1 );
                this.remove( this.size() - 1 );
                return copy;
            }
            else
            {
                try
                {
                    wait();
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
