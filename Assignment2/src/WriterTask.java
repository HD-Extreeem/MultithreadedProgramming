import java.util.Random;

/**
 * This Writer class is used for writing a specific char of a string to buffer, one by one
 * Writer class implements runnable used by a thread
 */
public class WriterTask implements Runnable {
    private boolean isRunning = false;
    private Controller controller;
    private CharBuffer buffer;
    private char[] strChar;
    private boolean isNext = true;
    private int i=0;
    private Random rand = new Random();
    /**
     * Constructor for the WriterTask Class
     * @param buffer instance to the buffer class
     * @param controller instance to the controller class
     */
    public WriterTask(CharBuffer buffer, Controller controller) {
        this.controller = controller;
        this.buffer = buffer;
    }

    /**
     * Run method which is run every time thread is started
     * Check which mode is chosen (sync,async), and checks whether the writer can write next char
     *
     */
    @Override
    public void run() {

        while(isRunning){
            checkNext(controller.getMode());

            try {
                Thread.sleep(rand.nextInt((300 - 100) + 1) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method which check if next char can be written to the buffer
     * else waits for next run
     */
    private void checkNext(boolean isAsync) {

        if (strChar.length==i){
            if (!buffer.getHasChar()){
                controller.setWritingFinished();
            }

        }
        else {
            System.out.println(isAsync);
            if (isAsync){
                buffer.setCharAsync(strChar[i]);
                controller.printChar("Writing " + strChar[i]+"\n", true);
                i++;
            }
            else{
                isNext = buffer.setChar(strChar[i]);
                if (isNext){
                    controller.printChar("Writing " + strChar[i]+"\n", true);
                    i++;
                }
                else{
                    controller.printChar("Data exists, Writer waits\n",true);
                }
            }


        }

    }

    /**
     * Method used for setting if thread need to be run or not
     * @param b boolean for setting the run process of the thread
     */
    public void setWriterRunning(boolean b){
        isRunning=b;
    }

    /**
     * Method gets the input text and convert it to a byte array
     * @param txt the text that needs to be written to the buffer
     */
    public void setWriterText(String txt){
        strChar = new char[txt.length()];
        strChar = txt.toCharArray();
    }

    /**
     * Method for clearing all after thread have finished
     */
    public void clear(){
        i=0;
        strChar=null;
    }
}
