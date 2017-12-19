import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class AdvPoolWaitingQueue implements Runnable {

    private Boolean isRunning = false;
    private Random rand = new Random();
    private WaitingQueueEntrance entrance;
    private CommonPool commonPool;
    private AdventurePool adventurePool;


    /**
     * Constructor
     * @param entrance
     * @param adventurePool
     * @param commonPool
     */
    public AdvPoolWaitingQueue(WaitingQueueEntrance entrance,AdventurePool adventurePool,CommonPool commonPool){
        this.entrance = entrance;
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
    }

    /**
     * Thread that is run when starting it
     * Randomly chooses where to get costumer from which queue
     */
    @Override
    public void run() {
        Customer c;
        while(isRunning) {

            //Get some from the adventure pool
            if (rand.nextBoolean()) {

                    c = entrance.getWaitingAdv();
                    if (c==null){

                    }
                    else {
                        adventurePool.PutCustomerAdv(c);
                    }
            }
            //Get some from the common pool if they are vip
            else {
                c = commonPool.getFromComToAdv();
                if (c==null){

                }
                else {
                    adventurePool.PutCustomerAdv(c);
                }
            }

            sleep(rand.nextInt(1000) + 1000);
        }
    }
    /******************************************************/
    /*--------------------AdventurePool-------------------*/
    /******************************************************/


    /**
     * Sleep method using thread.sleep
     * @param time
     */
    private void sleep(int time){

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setRunning(Boolean running) {
        this.isRunning = running;
    }

    public boolean getRunning() {
        return isRunning;
    }
}
