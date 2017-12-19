import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm;
import sun.awt.WindowClosingListener;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Hadi Deknache on 08/12/2017.
 */
public class ClientGUI {

    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;
    private JButton btnSend;
    private ThreadPoolClient poolClient;
    private JLabel status;

    /**
     * Method for starting whole process
     */
    public void start() {
        initializeGUI();

        poolClient = new ThreadPoolClient(btnSend,status,textArea);
        poolClient.setIsRunning(true);
        Thread threadPoolClient  =  new Thread(poolClient);
        threadPoolClient.start();

        frame.setVisible(true);
    }

    /**
     * Initializes the gui
     */
    private void initializeGUI() {
        frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setBounds(600, 250, 480, 370);

        status = new JLabel(" Status: Not Connected! ");
        status.setBounds(150, 5, 160, 20);
        status.setBackground(Color.RED);
        status.setOpaque(true);
        frame.add(status);

        textField = new JTextField();
        textField.setBounds(10, 310, 380, 20);
        textField.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        frame.add(textField);

        btnSend = new JButton("Send");
        btnSend.setBounds(390, 305, 80, 30);
        btnSend.setEnabled(false);
        frame.add(btnSend);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(10, 30, 450, 270);
        JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(10, 30, 450, 270);
        scroll.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        frame.add(scroll);
        listeners();

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                poolClient.setIsRunning(false);
                poolClient.stop();
                System.out.println("Closing client bye bye");
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    /**
     * Listener for when sending message
     */
    private void listeners(){
        btnSend.addActionListener(e -> {
            ThreadPoolClient.send(textField.getText());
            textArea.append("Me: "+textField.getText()+"\n");
            textField.setText("");
        });
    }

}
