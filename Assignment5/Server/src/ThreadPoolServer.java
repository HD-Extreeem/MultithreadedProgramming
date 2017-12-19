import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class which handles client connections to server
 * Whenever a client connects creates a new worker for the client
 */
public class ThreadPoolServer implements Runnable{
   private int port;
   private ServerSocket serverSocket;
   private boolean isRunning = true;
   private JTextArea textArea;
   private JButton btn;
   private Thread thread;
   private JTextField status;
   private ArrayList<Socket> clientList;
   private int connectedClients =0;
   private ExecutorService executorService = Executors.newFixedThreadPool(8);

   public ThreadPoolServer(int port, JTextArea textArea,JButton btn,JTextField status){
       this.port = port;
       this.btn = btn;
       this.textArea = textArea;
       this.status = status;
       clientList  = new ArrayList<>(8);
   }

    /**
     * Thread that is run
     */
    @Override
    public void run() {
        synchronized(this){
            this.thread = Thread.currentThread();
        }
       runServerSocket();
       while(isRunning) {
           Socket cSocket = null;
           try {
               cSocket = serverSocket.accept();
               System.out.println("New Client interesting?!");
               btn.setEnabled(true);
               if (connectedClients<9) {
                   clientList.add(cSocket);
                   executorService.execute(new WorkerServer(cSocket, textArea,clientList));
                   connectedClients++;
               }
               else{
                   System.out.println("Maximum connections reached");
               }
           } catch (IOException e) {
               e.printStackTrace();
           }


       }
       executorService.shutdown();
       stop();
       System.out.println("Server Stopped Now!") ;

    }

    /**
     * Method for starting connection to a port and setup server
     */
    public void runServerSocket(){
       try{
           this.serverSocket = new ServerSocket((port));
           status.setText("Status: Server Running!");
           status.setBackground(Color.GREEN);
           status.setBounds(160, 5, 155, 20);

       }catch (IOException e){
           throw new RuntimeException("Cannot open port: "+port,e);
       }
    }

    /**
     * Stop method when server closing
     */
    public synchronized void stop(){
        this.isRunning = false;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    /**
     * Method for checking wheter its running or not
     * @return boolean if running
     */
    private synchronized boolean isRunning() {
        return this.isRunning;
    }

}
