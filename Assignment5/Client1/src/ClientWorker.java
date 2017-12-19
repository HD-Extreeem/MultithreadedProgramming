import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Class which reads from server when server sends something
 */
public class ClientWorker implements Runnable {

    private Socket clientSocket = null;
    private  BufferedReader bufferedReader = null;
    private JTextArea textArea;
    private  boolean isRunning = false;

    public ClientWorker(Socket clientSocket, JTextArea textArea){
        this.clientSocket = clientSocket;
        this.textArea = textArea;
    }
    public void run() {
        String responseLine;
        /*
         * Keep on reading from the socket
         */
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println("Skriver i clientworker: "+responseLine);
                textArea.append("Server: "+responseLine+"\n");
            }
            //isRunning = false;
            bufferedReader.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
}
