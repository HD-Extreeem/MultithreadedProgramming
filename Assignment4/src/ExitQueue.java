import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class ExitQueue implements Runnable{
    private Queue<Customer> exitQueueA;
    private Queue<Customer> exitQueueC;
    private Boolean isRunning = false;
    private int aCount=0,cCount=0;
    private AdventurePool adventurePool;
    private CommonPool commonPool;
    private JLabel aExit,cExit;
    private Random rand = new Random();

    /**
     * Constructor
     * @param adventurePool
     * @param commonPool
     * @param lblAexit
     * @param lblCexit
     */
    public ExitQueue(AdventurePool adventurePool,CommonPool commonPool, JLabel lblAexit, JLabel lblCexit){
        this.adventurePool = adventurePool;
        this.commonPool = commonPool;
        this.aExit = lblAexit;
        this.cExit = lblCexit;
        exitQueueA = new ArrayDeque<>();
        exitQueueC = new ArrayDeque<>();
    }


    /**
     * RunMethod runs when thread started
     * Used for exiting costumer from one of the pools randomly
     */
    @Override
    public void run() {
        Customer c;
        while(isRunning){
            System.out.println("Exit!");
            if (rand.nextBoolean()){
                c = adventurePool.getCustomerAdv();
                if (c != null){
                    exitQueueA.add(c);
                    //aCount++;
                    aExit.setText(String.valueOf(exitQueueA.size()));
                }

            }
            else{
                c = commonPool.getCustomerCom();
                if (c!=null){
                    exitQueueC.add(c);
                    //cCount++;
                    cExit.setText(String.valueOf(exitQueueC.size()));
                }

            }
            sleep(rand.nextInt(3000)+1000);
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
