import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class which listens to client when message arrives
 * Also sends message when btn send is clicked
 */
public class WorkerServer implements Runnable {
    private Socket clientSocket = null;
    private InputStream input;
    private static OutputStream output;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private JTextArea textArea;
    private static ArrayList<Socket> clientList;
    private String message;

    public WorkerServer(Socket cSocket, JTextArea textArea,ArrayList<Socket> clientList) {
        this.clientSocket = cSocket;
        this.textArea = textArea;
        this.clientList = clientList;

    }

    public void run() {
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
            printWriter = new PrintWriter(output);
            bufferedReader = new BufferedReader(new InputStreamReader(input));
            System.out.println("New Client!");
            textArea.append("New Client Connected!\n");
            while((message = bufferedReader.readLine()) != null){
                System.out.println("Received message: "+ message);
                textArea.append("Client: "+message+"\n");
            }
            System.out.println("Client left!!");
            input.close();
            output.close();
            clientSocket.close();
            bufferedReader.close();
            printWriter.close();
            clientList.remove(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void send(String message){

        try {
            for (Socket clients : clientList) {
                clients.getOutputStream().write((message + "\n").getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
