package Fabric;

/**
 * Created by potap on 27.06.2017.
 */
public class MyThreadPool
{
    private MyArrayList<Runnable> tasks;
    private boolean isActive;

    public MyThreadPool(int n)
    {
        tasks = new MyArrayList<Runnable>();
        isActive = true;
        for (int i = 0; i < n; i++)
        {
            ( new MyThread() ).start();
        }
    }

    public synchronized void execute(Runnable task)
    {
        this.tasks.add(task);
    }

    private class MyThread extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                if (isActive)
                {
                    tasks.pull().run();
                }
            }
        }
    }
}
