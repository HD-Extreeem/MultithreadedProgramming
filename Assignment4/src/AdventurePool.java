import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * Class for handling the costumers in the pool
 */
public class AdventurePool {
    private JLabel advNbr;
    private int advCount = 0;
    private Random rand = new Random();
    private Queue<Customer> queueAdv;

    public AdventurePool(int advCount,JLabel lblAdvNr){
        queueAdv = new ArrayDeque<>(advCount);
        this.advNbr = lblAdvNr;
        this.advCount = advCount;
    }

    /**
     * And sometimes the costumer can move from common to adventure etc...
     * @return customer that will move
     */
    public Customer getFromAdvToCom() {
        if (!queueAdv.isEmpty()){
            if (queueAdv.peek().getVip()){
                return queueAdv.remove();
            }
            else{
                return null;
            }
        }

        return null;
    }

    /**
     * Fetches costumer from the pool
     * @return costumer
     */
    public synchronized Customer getCustomerAdv(){
        /*while (queueAdv.isEmpty()) {
            try {
                System.out.println("will hang now ADV!");
                wait(3000);
                System.out.println("FINISHED ADV???");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        if (queueAdv.isEmpty()){
            return null;
        }
        Customer c = queueAdv.remove();
        advNbr.setText(String.valueOf(queueAdv.size()));
        //notifyAll();
        return c;
    }

    /**
     * Place a new costumer in the pool adding it to the queue
     */
    public synchronized void PutCustomerAdv(Customer costumer) {
        while (queueAdv.size() == advCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queueAdv.add(costumer);
        advNbr.setText(String.valueOf(queueAdv.size()));
        notifyAll();
    }
}
