import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class WaitingQueueEntrance {

    private Queue<Customer> queueAdv;
    private Queue<Customer> queueCom;
    private JLabel waitAdv,waitCom;


    public WaitingQueueEntrance(JLabel waitAdv, JLabel waitCom){
        queueAdv = new ArrayDeque<>();
        queueCom = new ArrayDeque<>();
        this.waitAdv = waitAdv;
        this.waitCom = waitCom;
    }

    /******************************************************/
    /*--------------------AdventurePool-------------------*/
    /******************************************************/

    public synchronized Customer getWaitingAdv(){
        if (queueAdv.isEmpty()){
            waitAdv.setText(String.valueOf(queueAdv.size()));
            return null;
        }
        waitAdv.setText(String.valueOf(queueAdv.size()));
        return queueAdv.remove();
    }
    public synchronized void putWaitingAdv(Customer costumer) {
        queueAdv.add(costumer);
        waitAdv.setText(String.valueOf(queueAdv.size()));
    }

    /****************************************************/
    /*--------------------CommonPool--------------------*/
    /****************************************************/

    public synchronized Customer getWaitingCom(){
        if (queueCom.isEmpty()){
            waitCom.setText(String.valueOf(queueAdv.size()));
            return null;
        }

        waitCom.setText(String.valueOf(queueCom.size()));
        return queueCom.remove();
    }
    public synchronized void putWaitingCom(Customer costumer){
        queueCom.add(costumer);
        waitCom.setText(String.valueOf(queueCom.size()));
    }


}
