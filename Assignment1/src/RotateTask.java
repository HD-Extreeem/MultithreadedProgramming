

/**
 * This class handles the rotation of the drawings inside the pnlRotate in Gui
 * Every 50 ms the task calls controller asking for rotation of drawings
 */
public class RotateTask implements Runnable {
    private Controller controller;
    private boolean isMoving = false;

    /**
     * Constructor of the task
     * @param controller instance for the controller
     */
    public RotateTask(Controller controller){
        this.controller  = controller;
    }

    /**
     * Method that run for the task which will call controller asking for a rotation
     */
    @Override
    public void run() {
        //Checks flag if still wanted to rotate the drawings
        while (isMoving){
            controller.rotate();
            try {
                Thread.sleep(50);
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
