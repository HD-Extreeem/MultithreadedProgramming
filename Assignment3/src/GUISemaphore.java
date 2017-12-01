import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI for assignment 3
 */
public class GUISemaphore
{
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     * Static controls are defined inline
     */
    private JFrame frame;				// The Main window
    private JProgressBar bufferStatus;	// The progressbar, showing content in buffer

    // Data for Producer Scan
    private JButton btnStartS;			// Button start Scan
    private JButton btnStopS;			// Button stop Scan
    private JLabel lblStatusS;			// Status Scan
    // DAta for producer Arla
    private JButton btnStartA;			// Button start Arla
    private JButton btnStopA;			// Button stop Arla
    private JLabel lblStatusA;			// Status Arla
    //Data for producer AxFood
    private JButton btnStartX;			// Button start AxFood
    private JButton btnStopX;			// Button stop AxFood
    private JLabel lblStatusX;			// Status AxFood

    // Data for consumer ICA
    private JLabel lblIcaItems;			// Ica limits
    private JLabel lblIcaWeight;
    private JLabel lblIcaVolume;
    private JLabel lblIcaStatus;		// load status
    private JTextArea lstIca;			// The cargo list
    private JButton btnIcaStart;		// The buttons
    private JButton btnIcaStop;
    private JCheckBox chkIcaCont;		// Continue checkbox
    //Data for consumer COOP
    private JLabel lblCoopItems;
    private JLabel lblCoopWeight;
    private JLabel lblCoopVolume;
    private JLabel lblCoopStatus;		// load status
    private JTextArea lstCoop;			// The cargo list
    private JButton btnCoopStart;		// The buttons
    private JButton btnCoopStop;
    private JCheckBox chkCoopCont;		// Continue checkbox
    // Data for consumer CITY GROSS
    private JLabel lblCGItems;
    private JLabel lblCGWeight;
    private JLabel lblCGVolume;
    private JLabel lblCGStatus;			// load status
    private JTextArea lstCG;			// The cargo list
    private JButton btnCGStart;			// The buttons
    private JButton btnCGStop;
    private JCheckBox chkCGCont;		// Continue checkbox
    private Producer scan,axFood,arla;
    private Consumer cityGross,coop,ica;
    /**
     * Constructor, creates the window
     * Creates the producers/consumers and buffers
     */
    public GUISemaphore()
    {
        start();
        bufferStatus.setMaximum(100);
        Buffer storage = new Buffer(57,bufferStatus);
        scan = new Producer(storage,"Scan",lblStatusS);
        axFood = new Producer(storage,"AxFood",lblStatusX);
        arla = new Producer(storage,"Arla",lblStatusA);

        ica = new Consumer(storage,"Ica",17,16.5,14.7,lstIca,lblIcaItems,lblIcaWeight,lblIcaVolume,lblIcaStatus,chkIcaCont);
        coop = new Consumer(storage,"Coop",19,18.5,16.8,lstCoop,lblCoopItems,lblCoopWeight,lblCoopVolume,lblCoopStatus,chkCoopCont);
        cityGross = new Consumer(storage,"CityGross",21,20.5,18.3,lstCG,lblCGItems,lblCGWeight,lblCGVolume,lblCGStatus,chkCGCont);
    }

    /**
     * Starts the application
     */
    public void start()
    {
        frame = new JFrame();
        frame.setBounds(0, 0, 750, 526);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Food Supply System");
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
        // First create the three main panels
        JPanel pnlBuffer = new JPanel();
        pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Storage"));
        pnlBuffer.setBounds(13, 403, 693, 82);
        pnlBuffer.setLayout(null);
        // Then create the progressbar, only component in buffer panel
        bufferStatus = new JProgressBar();
        bufferStatus.setBounds(155, 37, 500, 23);
        //bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
        bufferStatus.setForeground(Color.GREEN);
        pnlBuffer.add(bufferStatus);
        JLabel lblmax = new JLabel("Max capacity: 57");
        lblmax.setBounds(10, 42, 126,13);
        pnlBuffer.add(lblmax);
        frame.add(pnlBuffer);

        JPanel pnlProd = new JPanel();
        pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producers"));
        pnlProd.setBounds(13, 13, 229, 379);
        pnlProd.setLayout(null);

        JPanel pnlCons = new JPanel();
        pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumers"));
        pnlCons.setBounds(266, 13, 470, 379);
        pnlCons.setLayout(null);

        // Now add the three panels to producer panel
        JPanel pnlScan = new JPanel();
        pnlScan.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: Scan"));
        pnlScan.setBounds(6, 19, 217, 100);
        pnlScan.setLayout(null);

        // Content Scan panel
        btnStartS = new JButton("Start Producing");
        btnStartS.setBounds(10, 59, 125, 23);
        pnlScan.add(btnStartS);
        btnStopS = new JButton("Stop");
        btnStopS.setBounds(140, 59, 65, 23);
        pnlScan.add(btnStopS);
        lblStatusS = new JLabel("Status: Stop");
        lblStatusS.setBounds(10, 31, 200, 13);
        pnlScan.add(lblStatusS);
        // Add Scan panel to producers
        pnlProd.add(pnlScan);

        // The Arla panel
        JPanel pnlArla = new JPanel();
        pnlArla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: Arla"));
        pnlArla.setBounds(6, 139, 217, 100);
        pnlArla.setLayout(null);

        // Content Arla panel
        btnStartA = new JButton("Start Producing");
        btnStartA.setBounds(10, 59, 125, 23);
        pnlArla.add(btnStartA);
        btnStopA = new JButton("Stop");
        btnStopA.setBounds(140, 59, 65, 23);
        pnlArla.add(btnStopA);
        lblStatusA = new JLabel("Status: Stop");
        lblStatusA.setBounds(10, 31, 200, 13);
        pnlArla.add(lblStatusA);
        // Add Arla panel to producers
        pnlProd.add(pnlArla);

        // The AxFood Panel
        JPanel pnlAxfood = new JPanel();
        pnlAxfood.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Producer: AxFood"));
        pnlAxfood.setBounds(6, 262, 217, 100);
        pnlAxfood.setLayout(null);

        // Content AxFood Panel
        btnStartX = new JButton("Start Producing");
        btnStartX.setBounds(10, 59, 125, 23);
        pnlAxfood.add(btnStartX);
        btnStopX = new JButton("Stop");
        btnStopX.setBounds(140, 59, 65, 23);
        pnlAxfood.add(btnStopX);
        lblStatusX = new JLabel("Status: Stop");
        lblStatusX.setBounds(10, 31, 200, 13);
        pnlAxfood.add(lblStatusX);
        // Add Axfood panel to producers
        pnlProd.add(pnlAxfood);
        // Producer panel done, add to frame
        frame.add(pnlProd);

        // Next, add the three panels to Consumer panel
        JPanel pnlICA = new JPanel();
        pnlICA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: ICA"));
        pnlICA.setBounds(19, 19,435, 100);
        pnlICA.setLayout(null);

        // Content ICA panel
        // First the limits panel
        JPanel pnlLim = new JPanel();
        pnlLim.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
        pnlLim.setBounds(6, 19, 127, 75);
        pnlLim.setLayout(null);
        JLabel lblItems = new JLabel("Items:");
        lblItems.setBounds(7, 20, 70, 13);
        pnlLim.add(lblItems);
        JLabel lblWeight = new JLabel("Weight:");
        lblWeight.setBounds(7, 35, 70, 13);
        pnlLim.add(lblWeight);
        JLabel lblVolume = new JLabel("Volume:");
        lblVolume.setBounds(7, 50, 70, 13);
        pnlLim.add(lblVolume);
        lblIcaItems = new JLabel("0");
        lblIcaItems.setBounds(60, 20, 67, 13);
        pnlLim.add(lblIcaItems);
        lblIcaWeight = new JLabel("0");
        lblIcaWeight.setBounds(60, 35, 67, 13);
        pnlLim.add(lblIcaWeight);
        lblIcaVolume = new JLabel("0");
        lblIcaVolume.setBounds(60, 50, 67, 13);
        pnlLim.add(lblIcaVolume);
        pnlICA.add(pnlLim);
        // Then rest of controls
        lstIca = new JTextArea();
        lstIca.setEditable(false);
        JScrollPane spane = new JScrollPane(lstIca);
        spane.setBounds(327, 16, 102, 69);
        spane.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlICA.add(spane);
        btnIcaStart = new JButton("Start Loading");
        btnIcaStart.setBounds(138, 64, 120, 23);
        pnlICA.add(btnIcaStart);
        btnIcaStop = new JButton("Stop");
        btnIcaStop.setBounds(260, 64, 60, 23);
        pnlICA.add(btnIcaStop);
        lblIcaStatus = new JLabel("Stop");
        lblIcaStatus.setBounds(138, 16, 150, 23);
        pnlICA.add(lblIcaStatus);
        chkIcaCont = new JCheckBox("Continue load");
        chkIcaCont.setBounds(138, 39, 130, 17);
        pnlICA.add(chkIcaCont);
        // All done, add to consumers panel
        pnlCons.add(pnlICA);

        JPanel pnlCOOP = new JPanel();
        pnlCOOP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: COOP"));
        pnlCOOP.setBounds(19, 139, 435, 100);
        pnlCOOP.setLayout(null);
        pnlCons.add(pnlCOOP);

        // Content COOP panel
        // First the limits panel
        JPanel pnlLimC = new JPanel();
        pnlLimC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
        pnlLimC.setBounds(6, 19, 127, 75);
        pnlLimC.setLayout(null);
        JLabel lblItemsC = new JLabel("Items:");
        lblItemsC.setBounds(7, 20, 70, 13);
        pnlLimC.add(lblItemsC);
        JLabel lblWeightC = new JLabel("Weight:");
        lblWeightC.setBounds(7, 35, 70, 13);
        pnlLimC.add(lblWeightC);
        JLabel lblVolumeC = new JLabel("Volume:");
        lblVolumeC.setBounds(7, 50, 70, 13);
        pnlLimC.add(lblVolumeC);
        lblCoopItems = new JLabel("0");
        lblCoopItems.setBounds(60, 20, 67, 13);
        pnlLimC.add(lblCoopItems);
        lblCoopWeight = new JLabel("0");
        lblCoopWeight.setBounds(60, 35, 67, 13);
        pnlLimC.add(lblCoopWeight);
        lblCoopVolume = new JLabel("0");
        lblCoopVolume.setBounds(60, 50, 67, 13);
        pnlLimC.add(lblCoopVolume);
        pnlCOOP.add(pnlLimC);
        // Then rest of controls
        lstCoop = new JTextArea();
        lstCoop.setEditable(false);
        JScrollPane spaneC = new JScrollPane(lstCoop);
        spaneC.setBounds(327, 16, 102, 69);
        spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlCOOP.add(spaneC);
        btnCoopStart = new JButton("Start Loading");
        btnCoopStart.setBounds(138, 64, 120, 23);
        pnlCOOP.add(btnCoopStart);
        btnCoopStop = new JButton("Stop");
        btnCoopStop.setBounds(260, 64, 60, 23);
        pnlCOOP.add(btnCoopStop);
        lblCoopStatus = new JLabel("Stop");
        lblCoopStatus.setBounds(138, 16, 150, 23);
        pnlCOOP.add(lblCoopStatus);
        chkCoopCont = new JCheckBox("Continue load");
        chkCoopCont.setBounds(138, 39, 130, 17);
        pnlCOOP.add(chkCoopCont);
        // All done, add to consumers panel
        pnlCons.add(pnlCOOP);

        JPanel pnlCG = new JPanel();
        pnlCG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Consumer: CITY GROSS"));
        pnlCG.setBounds(19, 262, 435, 100);
        pnlCG.setLayout(null);
        pnlCons.add(pnlCG);

        // Content CITY GROSS panel
        // First the limits panel
        JPanel pnlLimG = new JPanel();
        pnlLimG.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Package Limits"));
        pnlLimG.setBounds(6, 19, 127, 75);
        pnlLimG.setLayout(null);
        JLabel lblItemsG = new JLabel("Items:");
        lblItemsG.setBounds(7, 20, 70, 13);
        pnlLimG.add(lblItemsG);
        JLabel lblWeightG = new JLabel("Weight:");
        lblWeightG.setBounds(7, 35, 70, 13);
        pnlLimG.add(lblWeightG);
        JLabel lblVolumeG = new JLabel("Volume:");
        lblVolumeG.setBounds(7, 50, 70, 13);
        pnlLimG.add(lblVolumeG);
        lblCGItems = new JLabel("0");
        lblCGItems.setBounds(60, 20, 67, 13);
        pnlLimG.add(lblCGItems);
        lblCGWeight = new JLabel("0");
        lblCGWeight.setBounds(60, 35, 67, 13);
        pnlLimG.add(lblCGWeight);
        lblCGVolume = new JLabel("0");
        lblCGVolume.setBounds(60, 50, 67, 13);
        pnlLimG.add(lblCGVolume);
        pnlCG.add(pnlLimG);
        // Then rest of controls
        lstCG = new JTextArea();
        lstCG.setEditable(false);
        JScrollPane spaneG = new JScrollPane(lstCG);
        spaneG.setBounds(327, 16, 102, 69);
        spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
        pnlCG.add(spaneG);
        btnCGStart = new JButton("Start Loading");
        btnCGStart.setBounds(138, 64, 120, 23);
        pnlCG.add(btnCGStart);
        btnCGStop = new JButton("Stop");
        btnCGStop.setBounds(260, 64, 60, 23);
        pnlCG.add(btnCGStop);
        lblCGStatus = new JLabel("Stop");
        lblCGStatus.setBounds(138, 16, 150, 23);
        pnlCG.add(lblCGStatus);
        chkCGCont = new JCheckBox("Continue load");
        chkCGCont.setBounds(138, 39, 130, 17);
        pnlCG.add(chkCGCont);
        // All done, add to consumers panel
        pnlCons.add(pnlCOOP);

        // Add consumer panel to frame
        frame.add(pnlCons);
        initListeners();
    }

    /**
     * This method is used to gather all listeners
     * Listeners for producers on start and stop
     * Listeners for consumers on start and stop
     * Sets labels and check whether methods already are started or not
     * Enables and disables buttons depending on what was clicked
     */
    private void initListeners() {
        //***************************************************************//
        //--------------------Producer Listeners-------------------------//
        //***************************************************************//

        //StartListeners for producers!!!
        btnStartS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!scan.isStarted()){
                    btnStartS.setEnabled(false);
                    btnStopS.setEnabled(true);
                    scan.start();
                    Thread scanThread = new Thread(scan);
                    scanThread.start();
                    lblStatusS.setText("Status: Producing");
                }

            }
        });
        btnStartA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!arla.isStarted()) {
                    btnStartA.setEnabled(false);
                    btnStopA.setEnabled(true);
                    arla.start();
                    Thread arlaThread = new Thread(arla);
                    arlaThread.start();
                    lblStatusA.setText("Status: Producing");
                }
            }
        });
        btnStartX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!axFood.isStarted()) {
                    btnStartX.setEnabled(false);
                    btnStopX.setEnabled(true);
                    axFood.start();
                    Thread axFoodThread = new Thread(axFood);
                    axFoodThread.start();
                    lblStatusX.setText("Status: Producing");
                }
            }
        });


        //StopListeners for producers!!!
        btnStopS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scan.isStarted()) {
                    btnStopS.setEnabled(false);
                    btnStartS.setEnabled(true);
                    scan.stop();
                    lblStatusS.setText("Status: Stop");
                }
            }
        });
        btnStopA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arla.isStarted()){
                    btnStopA.setEnabled(false);
                    btnStartA.setEnabled(true);
                    arla.stop();
                    lblStatusA.setText("Status: Stop");
                }

            }
        });
        btnStopX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (axFood.isStarted()) {
                    btnStopX.setEnabled(false);
                    btnStartX.setEnabled(true);
                    axFood.stop();
                    lblStatusX.setText("Status: Stop");
                }
            }
        });

        //***************************************************************//
        //--------------------Consumer Listeners-------------------------//
        //***************************************************************//
        //StartListeners for Consumers
        btnIcaStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ica.isRunning()) {
                    btnIcaStart.setEnabled(false);
                    btnIcaStop.setEnabled(true);
                    ica.start();
                    Thread icaThread = new Thread(ica);
                    icaThread.start();
                    lblIcaStatus.setText("Transporting");
                }
            }
        });
        btnCoopStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!coop.isRunning()){
                    btnCoopStart.setEnabled(false);
                    btnCoopStop.setEnabled(true);
                    coop.start();
                    Thread coopThread = new Thread(coop);
                    coopThread.start();
                    lblCoopStatus.setText("Transporting");
                }

            }
        });
        btnCGStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cityGross.isRunning()) {
                    btnCGStart.setEnabled(false);
                    btnCGStop.setEnabled(true);
                    cityGross.start();
                    Thread cgThread = new Thread(cityGross);
                    cgThread.start();
                    lblCGStatus.setText("Transporting");
                }
            }
        });


        //StopListeners for Consumers
        btnIcaStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ica.isRunning()){
                    btnIcaStart.setEnabled(true);
                    btnIcaStop.setEnabled(false);
                    ica.stop();
                    lblIcaStatus.setText("Stop");
                }

            }
        });
        btnCoopStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coop.isRunning()){
                    btnCoopStop.setEnabled(false);
                    btnCoopStart.setEnabled(true);
                    coop.stop();
                    lblCoopStatus.setText("Stop");
                }

            }
        });
        btnCGStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cityGross.isRunning()){
                    btnCGStop.setEnabled(false);
                    btnCGStart.setEnabled(true);
                    cityGross.stop();
                    lblCGStatus.setText("Stop");
                }
            }
        });
    }
}
