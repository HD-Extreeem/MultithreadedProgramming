import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The GUI for assignment 2
 * Starts the gui by adding components and listeners
 */
public class GUIMutex 
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;			// The Main window
	private JLabel lblTrans;		// The transmitted text
	private JLabel lblRec;			// The received text
	private JRadioButton bSync;		// The sync radiobutton
	private JRadioButton bAsync;	// The async radiobutton
	private JTextField txtTrans;	// The input field for string to transfer
	private JButton btnRun;         // The run button
	private JButton btnClear;		// The clear button
	private JPanel pnlRes;			// The colored result area
	private JLabel lblStatus;		// The status of the transmission
	private JTextArea listW;		// The write logger pane
	private JTextArea listR;		// The read logger pane

	Controller controller;
	
	/**
	 * Constructor
	 */
	public GUIMutex()
	{
		controller = new Controller(this);
	}
	
	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 601, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Concurrent Read/Write");
		initializeGUI();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}
	
	/**
	 * Sets up the GUI with components
	 */
	private void initializeGUI()
	{
		// First, create the static components
		// First the 4 static texts
		JLabel lab1 = new JLabel("Writer Thread Logger");
		lab1.setBounds(18, 29, 128, 13);
		frame.add(lab1);
		JLabel lab2 = new JLabel("Reader Thread Logger");
		lab2.setBounds(388, 29, 128, 13);
		frame.add(lab2);
		JLabel lab3 = new JLabel("Transmitted:");
		lab3.setBounds(13, 394, 100, 13);
		frame.add(lab3);
		JLabel lab4 = new JLabel("Received:");
		lab4.setBounds(383, 394, 100, 13);
		frame.add(lab4);
		// Then add the two lists (of string) for logging transfer
		listW = new JTextArea();
        JScrollPane scrollW = new JScrollPane(listW);//Scrollepane for the Writer window that prints statuses
        scrollW.setBounds(13,45,197,342);
        scrollW.setBorder(BorderFactory.createLineBorder(Color.black));
		listW.setEditable(false);

		frame.add(scrollW);
		listR = new JTextArea();
        JScrollPane scrollR = new JScrollPane(listR);//Scrollepane for the Reader window that prints statuses
        scrollR.setBounds(386, 45, 183, 342);
        scrollR.setBorder(BorderFactory.createLineBorder(Color.black));
		listR.setEditable(false);

		frame.add(scrollR);

		// Next the panel that holds the "running" part
		JPanel pnlTest = new JPanel();
		pnlTest.setBorder(BorderFactory.createTitledBorder("Concurrent Tester"));
		pnlTest.setBounds(220, 45, 155, 342);
		pnlTest.setLayout(null);
		frame.add(pnlTest);
		lblTrans = new JLabel("");	// Replace with sent string
		lblTrans.setBounds(13, 415, 200, 13);
		frame.add(lblTrans);
		lblRec = new JLabel("");		// Replace with received string
		lblRec.setBounds(383, 415, 200, 13);
		frame.add(lblRec);
		
		// These are the controls on the user panel, first the radiobuttons
		bSync = new JRadioButton("Sync. Mode", false);
		bSync.setBounds(8, 37, 131, 17);
		pnlTest.add(bSync);
		bAsync = new JRadioButton("Async. Mode", true);
		bAsync.setBounds(8, 61, 141, 17);
		pnlTest.add(bAsync);
		ButtonGroup grp = new ButtonGroup();
		grp.add(bSync);
		grp.add(bAsync);
		// then the label and textbox to input string to transfer
		JLabel lab5 = new JLabel("String to Transfer:");
		lab5.setBounds(6, 99, 141, 13);
		pnlTest.add(lab5);
		txtTrans = new JTextField();
		txtTrans.setBounds(6, 124, 123, 20);
		pnlTest.add(txtTrans);
		// The run button
		btnRun = new JButton("Run");
		btnRun.setBounds(26, 150, 75, 23);
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                btnRun.setEnabled(false);
                controller.run(bAsync.isSelected(),txtTrans.getText());
			}
		});
		pnlTest.add(btnRun);
		JLabel lab6 = new JLabel("Running status:");
		lab6.setBounds(23, 199, 110, 13);
		pnlTest.add(lab6);
		// The colored rectangle holding result status
		pnlRes = new JPanel();
		pnlRes.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlRes.setBounds(26, 225, 75, 47);
		pnlRes.setBackground(Color.GREEN);
		pnlTest.add(pnlRes);
		// also to this text
		lblStatus = new JLabel("");
		lblStatus.setBounds(23, 275, 100, 13);
		pnlTest.add(lblStatus);
		// The clear input button, starts disabled
		btnClear = new JButton("Clear");
		btnClear.setBounds(26, 303, 75, 23);
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listR.setText("");
                listW.setText("");
                lblRec.setText("");
                lblTrans.setText("");
                lblStatus.setText("");
                btnClear.setEnabled(false);
            }
        });
		pnlTest.add(btnClear);
	}

    /**
     * Method is used for writing the status of thread in its own JTextarea
     * @param str the string that is wanted to be print
     * @param isWriter check if writer or reader
     */
	public void setTexts(String str,boolean isWriter) {
		if (isWriter){
			listW.append(str);
		}
		else {
			listR.append(str);
		}

	}

    /**
     * Method sets the result on the window when finished as text that was sent/received and if they are same
     * @param writerText the text that writer wrote
     * @param readerText the text that reader read from the buffer
     * @param isSame used for checking if the texts are the same
     */
	public void setResult(String writerText,String readerText,String isSame){
        lblTrans.setText(writerText);
        lblRec.setText(readerText);
        btnRun.setEnabled(true);
        btnClear.setEnabled(true);
        lblStatus.setText(isSame);
        if (isSame.equals("FAILED")){
            pnlRes.setBackground(Color.RED);
        }
        else{
            pnlRes.setBackground(Color.GREEN);
        }
    }
}
