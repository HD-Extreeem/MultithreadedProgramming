import javax.swing.*;
import java.util.Random;

/**
 * This class represents a producer which produce fooditem and load them to the "storage"(Buffer)
 * Runs in a thread when clicked start
 */
public class Producer implements Runnable{

    private String producerName;
    private Grocery[] foodList;
    private Buffer storage;
    private boolean isRunning;
    private Random rand = new Random();
    private JLabel status;

    /**
     * Constructor for using
     * @param storage instance to the buffer
     * @param producerName name of the producer
     * @param lblStatus status of the process
     */
    public Producer(Buffer storage, String producerName,JLabel lblStatus){
        this.producerName  = producerName;
        this.storage = storage;
        this.status = lblStatus;
        initFoodItems();
    }

    /**
     * This Method runs when a thread is started
     * "Create" a grocery item and put it in the buffer storage
     */
    @Override
    public void run() {
        while(isRunning){
            if (storage.putGrocery(foodList[rand.nextInt(20)])){
                status.setText("Idle");
            }
            else{
                status.setText("Producing");
            }
            try {
                Thread.sleep(rand.nextInt(2000)+600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initiates test groceryitems
     */
    private void initFoodItems(){
        foodList = new Grocery[21];
        foodList[0]= new Grocery("Pasta",1.0,0.1);
        foodList[1]= new Grocery("Milk",1.5,0.3);
        foodList[2]= new Grocery("Egg",1.2,0.4);
        foodList[3]= new Grocery("Broccoli",0.9,0.25);
        foodList[4]= new Grocery("Tomato",2.0,0.32);
        foodList[5]= new Grocery("Bread",0.5,0.22);
        foodList[6]= new Grocery("Butter",0.5,0.14);
        foodList[7]= new Grocery("Jam",0.3,0.2);
        foodList[8]= new Grocery("Sugar",1.0,0.42);
        foodList[9]= new Grocery("Coffee",0.5,0.32);
        foodList[10]= new Grocery("Yogurt",1.0,0.1);
        foodList[11]= new Grocery("Apple",1.0,0.12);
        foodList[12]= new Grocery("Chicken",1.1,0.45);
        foodList[13]= new Grocery("Flour",1.0,0.32);
        foodList[14]= new Grocery("Cabbage",2.0,0.52);
        foodList[15]= new Grocery("Donuts",0.6,0.2);
        foodList[16]= new Grocery("Soda",1.3,0.37);
        foodList[17]= new Grocery("Cheese",0.60,0.42);
        foodList[18]= new Grocery("Orange",1.1,0.29);
        foodList[19]= new Grocery("Pear",1.3,0.31);
        foodList[20]= new Grocery("Flower",0.9,0.16);
    }

    /**
     * Method for changing the boolean for stopping producers
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Method for changing the boolean for starting producers
     */
    public void start() {
        isRunning = true;
    }

    /**
     * Method which is used for checking if the producer is running
     * @return if producer is running
     */
    public boolean isStarted() {
        return isRunning;
    }
}
