import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class for sending message
 */
public class ThreadPoolClient implements Runnable {
    private Socket clientSocket = null;
    private static PrintStream printStream = null;

    private  BufferedReader bufferedReader = null;
    private  boolean isRunning = false;
    private JLabel status;
    private JButton btn;
    private JTextArea textArea;

    public ThreadPoolClient(JButton btnSend, JLabel status,JTextArea textArea){
        this.btn = btnSend;
        this.status = status;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        /*
         * When everything initialized then we can send message to server
         * Is sent through socket that was opened!
         */
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), 9000);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printStream = new PrintStream(clientSocket.getOutputStream());

            ClientWorker clientWorker = new ClientWorker(getSocket(),textArea);
            clientWorker.setIsRunning(true);
            Thread workerThread = new Thread(clientWorker);

            workerThread.start();
            btn.setEnabled(true);
            status.setText(" Status: Connected! ");
            status.setBackground(Color.GREEN);
            status.setBounds(160, 5, 130, 20);

        } catch (UnknownHostException e) {
            System.err.println("No such host " + 9000);
            btn.setEnabled(false);
            status.setText("Status: No such host!");
        } catch (IOException ignored) {

        }
        if (clientSocket != null && printStream != null) {
            try {
                while (isRunning) {
                    String msg = bufferedReader.readLine().trim();
                    System.out.println(msg);
                    printStream.println(msg);
                    System.out.println("Skriver i Threadpool!");
                }
                System.out.println("Closing client from runnable");

            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    /**
     * Sets the running to on/off
     * @param isRunning
     */
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;

    }

    /**
     * Method returns socket to the server
     * @return socket to server
     */
    public Socket getSocket() {
        return clientSocket;
    }

    /**
     * Sends message to server
     * @param msg
     */
    public static void send(String msg){
        printStream.println(msg);
    }

    public void stop(){
        if (!clientSocket.isConnected()){
            printStream.close();
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Closing! bye :(");
        }

    }
}
