import javax.swing.*;

/**
 * This class is the controller of the whole program that handles the whole logic of the thread
 */
public class Controller {
    private CharBuffer buffer;
    private ReaderTask readerTask;
    private WriterTask writerTask;
    private GUIMutex gui;
    private boolean isAsync = true;
    private String writeText;

    /**
     * Controller constructor
     * Creating the each task for the threads and the buffer
     * @param gui
     */
    public Controller(GUIMutex gui){
        this.gui = gui;
        buffer = new CharBuffer();
        writerTask = new WriterTask(buffer,this);
        readerTask = new ReaderTask(buffer,this);
    }

    /**
     * Method handle the start of the processes for the threads
     * @param isAsync boolean which tell if asynchronous or synchronous mode
     * @param txt the text that are going to be print
     */
    public void run(boolean isAsync, String txt) {
        this.isAsync = isAsync;
        writeText = txt;
        writerTask.setWriterRunning(true);
        readerTask.setReaderRunning(true);
        writerTask.setWriterText(txt);
        Thread threadWriter = new Thread(writerTask);
        Thread threadReader = new Thread(readerTask);
        threadReader.start();
        threadWriter.start();
    }

    /**
     *
     * @param str the string that is wanted to be print on the gui that was recently sent
     * @param isWriter boolean for which tell which of the threads want to print
     */
    public void printChar(String str,boolean isWriter){
        gui.setTexts(str,isWriter);
    }

    /**
     * Method stops task, sets the text if writer/reader got same text and clears variables
     */
    public void setWritingFinished() {
        String isSame="";
        writerTask.setWriterRunning(false);
        readerTask.setReaderRunning(false);
        if (writeText.equals(readerTask.getReadText())){
            isSame="SUCCESS";
        }
        else{
            isSame="FAILED";
        }
        gui.setResult(writeText,readerTask.getReadText(),isSame);
        readerTask.clear();
        writerTask.clear();
        buffer.clear();
    }

    /**
     *
     * @return the mode of the process asynchronous or synchronous mode
     */
    public boolean getMode() {
        return isAsync;
    }
}
