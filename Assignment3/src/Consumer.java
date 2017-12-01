import javax.swing.*;
import java.util.Random;

public class Consumer implements Runnable{
    private String consumerName;
    private JTextArea lst;
    private JLabel lblStoreItems,lblWeight,lblVolume,status;
    private JCheckBox chkCont;
    private boolean isStarted;
    private int tot,totLoad;
    private Buffer storage;
    private Grocery grocery;
    private int sleepTime = 0;
    private double weight,volume,totWeight,totVolume;
    private Random rand = new Random();

    /**
     * Constructor of the consumer
     * @param storage instance to buffer
     * @param consumerName the name of the consumer factory
     * @param tot total items the truck can hold
     * @param weight the total weight the truck can hold
     * @param volume the total volume the truck can hold
     * @param lst JTextArea for adding items which are loaded
     * @param lblStoreItems JLabel for setting how many items that are loaded
     * @param lblWeight JLabel for setting total weight loaded
     * @param lblVolume JLabel for setting total volume loaded
     * @param status JLabel for setting status of truck
     * @param chkCont JCheckBox for checking if to continue loading items after it's full
     */
    public Consumer(Buffer storage, String consumerName, int tot, double weight, double volume, JTextArea lst, JLabel lblStoreItems, JLabel lblWeight, JLabel lblVolume, JLabel status, JCheckBox chkCont) {
        this.storage = storage;
        this.consumerName = consumerName;
        this.tot = tot;
        this.weight = weight;
        this.volume = volume;
        this.lst = lst;
        this.lblStoreItems = lblStoreItems;
        this.lblWeight = lblWeight;
        this.lblVolume = lblVolume;
        this.status = status;
        this.chkCont = chkCont;
    }

    /**
     * This is run when the consumerthread is started
     * Method checks first if there is more room in the "truck"
     * Gets new item and update the progress and data for the volume and weight and total items
     * If the checkbox is checked the truck loads and unloads with a sleep time between
     */
    @Override
    public void run() {
        while(isStarted){
            if (tot>totLoad&&weight>totWeight&&volume>totVolume){
               grocery = storage.getGrocery();
               if (grocery==null){
                status.setText("Storage is empty!");
               }
               else{
                   status.setText("Transporting");
                   totLoad++;
                   totVolume+=grocery.getVolume();
                   totWeight+=grocery.getWeight();
                   lst.append(grocery.getName()+"\n");
                   lblStoreItems.setText(String.valueOf(totLoad));
                   lblVolume.setText(String.valueOf(totVolume));
                   lblWeight.setText(String.valueOf(totWeight));
               }
               sleepTime = rand.nextInt(3000);
            }
            else if(chkCont.isSelected()){
                status.setText("Idle");
                sleep(3000);
                lst.setText("");
                totLoad =0;
                totWeight = 0;
                totVolume = 0;
                lblStoreItems.setText(String.valueOf(totLoad));
                lblVolume.setText(String.valueOf(totVolume));
                lblWeight.setText(String.valueOf(totWeight));
                sleepTime = 2000;
            }

            sleep(sleepTime);
        }
    }

    /**
     * Method for sleeping the thread
     * @param sleep the time the thread will sleep
     */
    private void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method is used to change the boolean when the user clicked on stop button for the consumer panel
     */
    public void stop() {
        isStarted=false;
    }

    /**
     * Method is used to change the boolean when the user clicked on start button for the consumer panel
     */
    public void start() {
        isStarted = true;
    }

    /**
     * Method for checking whether a consumer is running or not...
     */
    public boolean isRunning() {
        return isStarted;
    }
}
