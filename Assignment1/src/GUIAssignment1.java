import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIAssignment1
{
	/**
	 * These are the components you need to handle.
	 * You have to add listeners and/or code
	 */
	private JFrame frame;		// The Main window
	private JButton btnDisplay;	// Start thread moving display
	private JButton btnDStop;	// Stop moving display thread
	private JButton btnTriangle;// Start moving graphics thread
	private JButton btnTStop;	// Stop moving graphics thread
	private JButton btnOpen;	// Open audio file
	private JButton btnPlay;	// Start playing audio
	private JButton btnStop;	// Stop playing
	private JButton btnGo;		// Start game catch me
	private JPanel pnlMove;		// The panel to move display in
	private JpanelTriangle pnlRotate;	// The panel to move graphics in
	private JPanel pnlGame;		// The panel to play in
	private JLabel lblPlaying;	// Playing text
	private JLabel lblAudio;	// Audio file
    private JLabel movingTxt;   // Text in Display
	private JTextArea txtHits;	// Dispaly hits
	private JComboBox cmbSkill;	// Skill combo box, needs to be filled in

	private String fileName = null,path = null;
	private Controller controller;

	/**
	 * Constructor
	 */
	public GUIAssignment1()
	{
        //controller = new Controller(this);
        controller = new Controller(this);

	}

	/**
	 * Starts the application
	 */
	public void start()
	{
		frame = new JFrame();
		frame.setBounds(0, 0, 470, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Multiple Thread Demonstrator");
		initGui();					// Fill in components
		frame.setVisible(true);
		frame.setResizable(false);			// Prevent user from change size
		frame.setLocationRelativeTo(null);	// Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void initGui()
	{
		// The music player outer panel
		JPanel pnlSound = new JPanel();
		Border b1 = BorderFactory.createTitledBorder("Music Player");
		pnlSound.setBorder(b1);
		pnlSound.setBounds(12, 12, 450, 100);
		pnlSound.setLayout(null);

		// Add labels and buttons to this panel
		lblPlaying = new JLabel();	// Needs to be alteraed
		lblPlaying.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblPlaying.setBounds(90, 16, 300, 20);
		pnlSound.add(lblPlaying);
		JLabel lbl1 = new JLabel("Loaded Audio File:");
		lbl1.setBounds(10, 44, 130, 13);
		pnlSound.add(lbl1);
		lblAudio = new JLabel("...");				// Needs to be altered
		lblAudio.setBounds(128, 44, 300, 13);
		pnlSound.add(lblAudio);
		btnOpen = new JButton("Open");
		btnOpen.setBounds(6, 71, 75, 23);
		pnlSound.add(btnOpen);
		btnPlay = new JButton("Play");
		btnPlay.setBounds(88, 71, 75, 23);
		pnlSound.add(btnPlay);
		btnStop = new JButton("Stop");
		btnStop.setBounds(169, 71, 75, 23);
		pnlSound.add(btnStop);
		frame.add(pnlSound);

		//Handle the action when loading media, gives a window for choosing media
		btnOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "MP3 Files", "mp3");
                jFileChooser.setFileFilter(filter);
                int returnVal = jFileChooser.showOpenDialog(pnlSound);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    path = jFileChooser.getSelectedFile().getPath();
                    fileName = jFileChooser.getSelectedFile().getName();
                    System.out.println(path);
                    lblAudio.setText(fileName);
                }
			}
		});
		//Handle the action when clicking play which plays loaded media
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(path!=null){
					controller.startMediaPlayer(path);
					lblPlaying.setText("Now Playing");
				}
			}
		});
		//Handle the action when clicking stop for stop playing media
		btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopMediaPlayer();
                lblPlaying.setText("");
            }
        });
		// The moving display outer panel
		JPanel pnlDisplay = new JPanel();
		Border b2 = BorderFactory.createTitledBorder("Display Thread");
		pnlDisplay.setBorder(b2);
		pnlDisplay.setBounds(12, 118, 222, 269);
		pnlDisplay.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnDisplay = new JButton("Start Display");
		btnDisplay.setBounds(10, 226, 121, 23);
		pnlDisplay.add(btnDisplay);
		btnDStop = new JButton("Stop");
		btnDStop.setBounds(135, 226, 75, 23);
		pnlDisplay.add(btnDStop);
		pnlMove = new JPanel();
		pnlMove.setBounds(10,  19,  200,  200);
		Border b21 = BorderFactory.createLineBorder(Color.black);
		pnlMove.setBorder(b21);
        movingTxt = new JLabel();
        pnlMove.add(movingTxt);
		pnlDisplay.add(pnlMove);
		//Handle the action when clicked on start moving text
        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startMovingText();
            }
        });
        //Handle the action when clicked on stop for moving text
        btnDStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopMovingText();
            }
        });

		frame.add(pnlDisplay);

		// The moving graphics outer panel
		JPanel pnlTriangle = new JPanel();
		Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
		pnlTriangle.setBorder(b3);
		pnlTriangle.setBounds(240, 118, 222, 269);
		pnlTriangle.setLayout(null);

		// Add buttons and drawing panel to this panel
		btnTriangle = new JButton("Start Rotate");
		btnTriangle.setBounds(10, 226, 121, 23);
		pnlTriangle.add(btnTriangle);
		btnTStop = new JButton("Stop");
		btnTStop.setBounds(135, 226, 75, 23);
		pnlTriangle.add(btnTStop);
		pnlRotate = new JpanelTriangle();
		pnlRotate.setBounds(10,  19,  200,  200);
		Border b31 = BorderFactory.createLineBorder(Color.black);
		pnlRotate.setBorder(b31);
		pnlTriangle.add(pnlRotate);
		//Handle the action when clicked on start rotating
		btnTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startRotating();
            }
        });

		//Handle the action when clicked on stop rotating
		btnTStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stopRotating();
            }
        });
		// Add this to main window
		frame.add(pnlTriangle);

		// The game outer panel
		/*JPanel pnlCatchme = new JPanel();
		Border b4 = BorderFactory.createTitledBorder("Catch Me");
		pnlCatchme.setBorder(b4);
		pnlCatchme.setBounds(468, 12, 323, 375);
		pnlCatchme.setLayout(null);

		// Add controls to this panel
		JLabel lblSkill = new JLabel("Skill:");
		lblSkill.setBounds(26, 20, 50, 13);
		pnlCatchme.add(lblSkill);
		JLabel lblInfo = new JLabel("Hit Image with Mouse");
		lblInfo.setBounds(107, 13, 150, 13);
		pnlCatchme.add(lblInfo);
		JLabel lblHits = new JLabel("Hits:");
		lblHits.setBounds(240, 20, 50, 13);
		pnlCatchme.add(lblHits);
		cmbSkill = new JComboBox();			// Need to be filled in with data
		cmbSkill.setBounds(19, 41, 61, 23);
		pnlCatchme.add(cmbSkill);
		btnGo = new JButton("GO");
		btnGo.setBounds(129, 41, 75, 23);
		pnlCatchme.add(btnGo);
		txtHits = new JTextArea();			// Needs to be updated
		txtHits.setBounds(233, 41, 71, 23);
		Border b40 = BorderFactory.createLineBorder(Color.black);
		txtHits.setBorder(b40);
		pnlCatchme.add(txtHits);
		pnlGame = new JPanel();
		pnlGame.setBounds(19, 71, 285, 283);
		Border b41 = BorderFactory.createLineBorder(Color.black);
		pnlGame.setBorder(b41);
		pnlCatchme.add(pnlGame);
		frame.add(pnlCatchme);*/
	}

	/**
	 * Method receives new x,y values from DisplayTask which will put the text on a random place inside pnlDisplay
	 * @param x random value of x which generates during the threadDisplay
	 * @param y random value of y which generates during the threadDisplay
	 */
	public void moveTextTo(int x,int y){
	    movingTxt.setText("Moving...");
	    movingTxt.setBounds(x,y,70,20);
    }

	/**
	 * Method used for redrawing the drawings inside pnlRotate
	 * @param angle new angle for the drawings
	 * @param minX1 new xMin used for rotating the triangles
	 * @param maxX1 new xMax used for rotating the triangles
	 */
    public void rotate(int angle, double minX1, double maxX1){
        pnlRotate.redrawTriangle(angle,minX1,maxX1);
    }

	/**
	 * Enables and disables the button during thread running to prevent from clicking and handling unwanted actions
	 * @param b boolean if btn should be enabled or not
	 */
	public void displayBtnEnabled(boolean b) {
    	btnDisplay.setEnabled(b);
	}
	/**
	 * Enables and disables the button during thread running to prevent from clicking and handling unwanted actions
	 * @param b boolean if btn should be enabled or not
	 */
	public void rotateBtnEnabled(boolean b){
    	btnTriangle.setEnabled(b);
	}
	/**
	 * Enables and disables the button during thread running to prevent from clicking and handling unwanted actions
	 * @param b boolean if btn should be enabled or not
	 */
	public void mediaBtnEnabled(boolean b){
		btnPlay.setEnabled(b);
	}


	/**
	 * Class used for drawing inside the pnlRotate
	 * Used also for rotating objects and repainting during thread running
	 */
	private class JpanelTriangle extends JPanel {
        private double minX1=35,minY1=50,maxX1=165,maxY1=150,middleX1=100;
        private double angle = 0;


        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;

            //Liten triangel
            //graphics2D.draw(new Line2D.Double(50,140,150,140 ));
            //graphics2D.draw(new Line2D.Double(100,50,150,140 ));
            //graphics2D.draw(new Line2D.Double(50,140,100,50 ));

            //graphics2D.draw(new Line2D.Double(35,150,165,150 ));
            //graphics2D.draw(new Line2D.Double(100,50,165,150 ));
            //graphics2D.draw(new Line2D.Double(35,150,100,50 ));

            graphics2D.draw(new Line2D.Double(minX1,maxY1,maxX1,maxY1 ));
            graphics2D.draw(new Line2D.Double(middleX1,minY1,maxX1,maxY1 ));
            graphics2D.draw(new Line2D.Double(minX1,maxY1,middleX1,minY1 ));

            //int [] x = {35,100,165};
            //int [] y = {150,50,150};
            //graphics2D.drawPolygon(x,y,3);

            //Roterar kring en punkt
            /*AffineTransform at =
                    AffineTransform.getRotateInstance(
                            Math.toRadians(angle), 100, 100);
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(35,150,165,150)));
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(100,50,165,150)));
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(35,150,100,50 )));*/

            //AffineTransform used for 3dRotating new Triangle inside the pnlRotate
			//Angle added every run until 360 degrees
            AffineTransform at =
                    AffineTransform.getRotateInstance(
                            Math.toRadians(angle), 100, 100);

            //Drawing new Lines for the triangle
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(minX1,maxY1,maxX1,maxY1 )));
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(middleX1,minY1,maxX1,maxY1 )));
            graphics2D.draw(at.createTransformedShape(new Line2D.Double(minX1,maxY1,middleX1,minY1 )));

            //Used for rotating the square frame for the pnlRotate
            graphics2D.rotate(Math.toRadians(angle),100,100);
        }

		/**
		 * Method setting the values of new xmin and xmax
		 * Repaiting it later which calls paintcomponent
		 * @param angle Angle of the current rotation
		 * @param minX1 lower x value used for the triangle base
		 * @param maxX1 higher x value used for the triangle base
		 */
        public void redrawTriangle(int angle, double minX1, double maxX1){
            this.angle = angle;
            this.minX1 = minX1;
            this.maxX1 = maxX1;
            pnlRotate.repaint();
        }
    }

}
