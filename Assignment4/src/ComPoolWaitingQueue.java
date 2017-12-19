import java.util.Random;

public class ComPoolWaitingQueue implements Runnable{

    private Boolean isRunning = false;
    private Random rand = new Random();
    private WaitingQueueEntrance entrance;
    private CommonPool commonPool;
    private AdventurePool adventurePool;

    public ComPoolWaitingQueue( WaitingQueueEntrance entrance, AdventurePool adventurePool, CommonPool commonPool){

        this.entrance = entrance;
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
    }

    /**
     * Thread that runs when started
     * Handles the costumers in pool
     * Randlomly move from pool to pool and also let new costumers in
     */
    @Override
    public void run() {
        Customer c;
        while (isRunning) {

            /*Used for letting in new costumer from entrance*/
            if (rand.nextBoolean()) {
                    c = entrance.getWaitingCom();
                    if (c == null){

                    }
                    else {
                        commonPool.PutCustomerCom(c);

                    }
             /*And sometimes let people from the adventurepool*/
            } else {
                    c = adventurePool.getFromAdvToCom();
                    if (c == null){

                    }
                    else {
                        commonPool.PutCustomerCom(c);

                    }
            }

            sleep(rand.nextInt(1000) + 1000);

        }
    }

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
