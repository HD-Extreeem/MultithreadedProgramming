import jdk.nashorn.internal.runtime.WithObject;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hadi Deknache on 08/12/2017.
 */
public class ServerGUI {

    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private JButton btnSend;
    private JTextField status;

    /**
     * Method for starting whole process
     */
    public void start() {
        initializeGUI();
        ThreadPoolServer server = new ThreadPoolServer(9000,textArea,btnSend,status);
        new Thread(server).start();
        frame.setVisible(true);
    }

    /**
     * Initializes the gui
     */
    private void initializeGUI() {
        frame = new JFrame("Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setBounds(600, 250, 480, 370);

        status = new JTextField("Status: Not Running");
        status.setBounds(170, 5, 155, 20);
        status.setBackground(Color.RED);
        status.setEditable(false);
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
        listener();
    }
    private void listener(){

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkerServer.send(textField.getText());
                textArea.append("Me: "+textField.getText()+"\n");
                textField.setText("");
            }
        });
    }

}
