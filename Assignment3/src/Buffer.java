import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * This class represents a buffer which is a storage for fooditems
 * Stores a fooditem for the consumers and producers put in new fooditems
 */
public class Buffer {

    private LinkedList<Grocery> groceries = new LinkedList<>();
    private Semaphore reader = new Semaphore(0);
    private Semaphore writer = new Semaphore(57);
    private Semaphore mutex = new Semaphore(1);
    private Grocery grocery;
    private int storageSize;
    private boolean full = false;
    private JProgressBar bufferStatus;
    private int percent;

    /**
     * Constructor for buffer
     * @param storageSize the size of how much the buffer can hold
     * @param bufferStatus progressbar to update for the ui
     */
    public Buffer(int storageSize, JProgressBar bufferStatus){
        this.storageSize = storageSize;
        this.bufferStatus = bufferStatus;
    }

    /**
     * Mutex Method which let producers put in items
     * @param grocery the object that will be put in
     * @return if it was success or not to put in item
     */
    public boolean putGrocery(Grocery grocery){
        try {
            writer.acquire();
            /*if (groceries.size()<storageSize){
                System.out.println("Putting Groceries: "+grocery.getName());
                mutex.acquire();
                groceries.push(grocery);
                updateProgress();
                full = false;
                mutex.release();
            }else {
                System.out.println("Hold your horses, can't you see it's fullllll: "+storageSize);
                full = true;
            }*/
            System.out.println("Putting Groceries: "+grocery.getName());
            mutex.acquire();
            groceries.push(grocery);
            updateProgress();
            mutex.release();
            System.out.println("Hold your horses, can't you see it's fullllll: "+storageSize);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            reader.release();
            full = false;
        }
        return full;

    }
    /**
     * Method for "producing" grocery returns the item from the grocery linkedlist
     * @return the grocery in list
     */
    public Grocery getGrocery(){
        try {
            reader.acquire();
            mutex.acquire();
            grocery = groceries.pop();
            mutex.release();
            System.out.println("Putting Groceries: "+grocery.getName());
            updateProgress();
            writer.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return grocery;
    }


    /**
     * Method for updating the progressbar in the ui
     */
    public void updateProgress() {
        System.out.println("The size: "+groceries.size());
        System.out.println("The storage size: "+storageSize);
        percent = (((groceries.size()*100)/storageSize));
        System.out.println("Processing " + percent + "%");
        bufferStatus.setValue(percent);
        bufferStatus.setString(String.valueOf(percent));
    }
}
