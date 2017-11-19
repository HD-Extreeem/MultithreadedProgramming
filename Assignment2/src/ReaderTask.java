import java.util.Random;

/**
 * This class is the reader task that reads what is being put in the buffer by the writer
 */
public class ReaderTask  implements Runnable{
    private boolean isRunning = false;
    private Controller controller;
    private CharBuffer buffer;
    private String readText="";
    private Random rand = new Random();

    /**
     * Constructor to ReaderTask
     * @param buffer the buffer where the reader fetches the characters
     * @param controller instance to controller
     */
    public ReaderTask(CharBuffer buffer, Controller controller) {
        this.controller = controller;
        this.buffer = buffer;
    }

    /**
     * This method runs when thread is started
     * While text still available from buffer
     * Depending on async. or sync. mode the data is being printed on the textarea and check if next char can be fetched
     */
    @Override
    public void run() {
        char chr;
        while(isRunning){
            if (controller.getMode()){
                if (buffer.getHasChar()){
                    chr=buffer.getChar();
                    controller.printChar("Reading "+chr+"\n",false);
                    readText+=chr;
                }
                else{
                    controller.printChar("No data, Reader waits\n",false);
                }
            }
            else{
                chr=buffer.getChar();
                controller.printChar("Reading "+chr+"\n",false);
                readText+=chr;
            }

            try {
                Thread.sleep(rand.nextInt((300 - 100) + 1) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for getting the text that was composed
     * @return the text that was composed from the reading of the buffer
     */
    public String getReadText(){
        return readText;
    }

    /**
     * sets the boolean for the thread to keep it alive
     * @param b boolean used for the thread
     */
    public void setReaderRunning(boolean b){
        isRunning = b;
    }

    /**
     * clears all variable that is needed again
     */
    public void clear(){
        readText="";
    }

}
