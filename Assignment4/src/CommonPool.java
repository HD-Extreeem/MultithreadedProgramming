import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

/**
 * Class handles when common pool with its costumers inside
 */

public class CommonPool{

    private Queue<Customer> queueCom;
    private JLabel comNbr;
    private int comCount = 0;

    /**
     * Constructor
     * @param count total of guest allowed
     * @param lblComNr lbl for common, nbr of guests
     */
    public CommonPool(int count, JLabel lblComNr){
        this.comCount = count;
        queueCom = new ArrayDeque<>(count);
        this.comNbr = lblComNr;
    }


    /**
     * Method used for moving costumer from one pool to another
     * @return
     */
    public Customer getFromComToAdv() {
        if (!queueCom.isEmpty()){
            if (queueCom.peek().getVip()){
                return queueCom.remove();
            }
            else{
                return null;
            }
        }
        return null;
    }
    /****************************************************/
    /*--------------------CommonPool--------------------*/
    /****************************************************/

    /**
     * Fetches costumer from the pool
     * @return costumer
     */
    public synchronized Customer getCustomerCom(){
        /*while (queueCom.isEmpty()){
            try {
                System.out.println("will hang now COM!");
                wait(3000);
                System.out.println("FINISHED COM???");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        if (queueCom.isEmpty()){
            return null;
        }
        Customer c = queueCom.remove();
        comNbr.setText(String.valueOf(queueCom.size()));
        //notifyAll();
        return c;
    }

    /**
     * Put costumer in queue and change the lable of waiting
     * @param costumer
     */
    public synchronized void PutCustomerCom(Customer costumer){
        while (queueCom.size() == comCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        comNbr.setText(String.valueOf(queueCom.size()));
        queueCom.add(costumer);
        notifyAll();
    }
}

