import javax.swing.*;
import java.util.Random;


/**
 * Class that is used for creating costumers and adding them to a queue for entering the swimmingpools
 */
public class Reception  implements Runnable {
    private Boolean isRunning = false;
    private WaitingQueueEntrance entrance;
    private Random rand = new Random();


    public Reception(WaitingQueueEntrance waitingQueueEntrance){
        this.entrance = waitingQueueEntrance;
    }

    /**
     * Runmethod when starting thread runs
     * Creates new costumers and add them to entrancequeue
     */
    @Override
    public void run() {
        while (true){
            if (isRunning){
                if (rand.nextBoolean()){
                    entrance.putWaitingAdv(new Customer(true));
                }
                else{
                    entrance.putWaitingCom(new Customer(rand.nextBoolean()));

                }
            }
            sleep(rand.nextInt(500)+500);
        }
    }


    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
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
}
