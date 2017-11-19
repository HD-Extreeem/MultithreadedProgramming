import java.util.Random;

/**
 * This class handles the thread tasks that should run when a thread is created and started
 * Calculates new x,y values and move the text, sleeps the rest of the time
 */
public class DisplayTask implements Runnable {
    private Controller controller;
    private Random rand = new Random();
    private int x,y;
    private boolean isMoving = false;

    /**
     * Constructor of the task
     * @param controller instance for the controller
     */
    public DisplayTask(Controller controller) {
        this.controller = controller;
    }

    /**
     * Method that run for the task which get new Random values and move text through controller
     */
    @Override
    public void run() {

        //Checks flag if still wanted to move the text
        while (isMoving) {
            x = rand.nextInt(160);
            y = rand.nextInt(150);
            controller.move(x, y);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Sets a flag for the task to run or not
     * @param isMoving flag used for the task
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
