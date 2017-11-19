import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


/**
 * Controller class which handles all logic for the threads
 * Handling the 2 thread for displaying text,drawings and also media
 */
public class Controller {
    private GUIAssignment1 gui;
    private boolean textIsMoving = false,mediaIsPlaying = false,triangleIsRotating=false;
    private DisplayTask displayTask;
    private RotateTask rotateTask;
    private MediaPlayer mediaPlayer;
    private int angle =0,i=0;
    private double minX1=35,minY1=50,maxX1=165,maxY1=150,middleX1=100;

    /**
     * Constructor for controller class
     * Starting the threads
     * @param guiAssignment1 instance for the gui
     */
    public Controller(GUIAssignment1 guiAssignment1) {
        gui = guiAssignment1;

        //RotateTask//
        rotateTask = new RotateTask(this);

        //DisplayText Thread//
        displayTask = new DisplayTask(this);

    }

    /**
     *Method for starting the process of the thread for moving the text aroudn the displayPnl
     * Checks whether the text already is moving
     * if not starts the thread and set boolean for continuously moving the text
     */
    public void startMovingText() {
        if (!textIsMoving){
            Thread displayThread = new Thread(displayTask);
            displayThread.start();
            textIsMoving = true;
            displayTask.setIsMoving(textIsMoving);
            gui.displayBtnEnabled(false);
        }

    }

    /**
     * Method for stopping existing thread that is running and movin g text
     */
    public void stopMovingText(){
        if (textIsMoving){
            textIsMoving = false;
            displayTask.setIsMoving(textIsMoving);
            gui.displayBtnEnabled(true);
        }

    }

    /**
     * Method used for setting text with the x,y value inside the gui
     * @param x value where to put text inside the displayPnl
     * @param y value where to put text inside the displayPnl
     */
    public void move(int x, int y) {
        gui.moveTextTo(x,y);
    }

    /**
     * Method checks whether the media is playing or not
     * plays media if not being played already
     * @param path the play path where to find media which is wanted to be played
     */
    public void startMediaPlayer(String path) {
        if (!mediaIsPlaying){
            Media media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            mediaIsPlaying = true;
            gui.mediaBtnEnabled(false);
        }

    }

    /**
     * Method stops media when btn stop is pressed, only if media is being played
     */
    public void stopMediaPlayer(){
        if(mediaIsPlaying){
            mediaPlayer.stop();
            mediaIsPlaying = false;
            gui.mediaBtnEnabled(true);
        }

    }

    /**
     * Method starts the thread for rotating the triangles inside the pnlRotate
     */
    public void startRotating(){
        if (!triangleIsRotating){
            Thread rotateThread = new Thread(rotateTask);
            rotateThread.start();
            triangleIsRotating = true;
            rotateTask.setIsMoving(triangleIsRotating);
            gui.rotateBtnEnabled(false);
        }
    }

    /**
     * Stops the rotation when button stop is pressed only if rotation thread are active
     */
    public void stopRotating(){
        if (triangleIsRotating){
            triangleIsRotating = false;
            rotateTask.setIsMoving(triangleIsRotating);
            gui.rotateBtnEnabled(true);
        }
    }

    /**
     * Method Calculates the new angle, maxX and minX
     * Checks also whether already passed the values for triangle base resets values
     */
    public void rotate() {
        angle = (angle + 3)%360;
        if (minX1 == 165){
            minX1 = 35;
            maxX1 = 165;
        }

        minX1++;
        maxX1--;


        gui.rotate(angle,minX1,maxX1);
    }
}
