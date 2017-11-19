/**
 * Buffer class used for reading/writing next char by the threads
 */
public class CharBuffer {
    private char c;
    private boolean hasChar = false;

    /**
     * Method used for reading the chra
     * @return next char that needs to be read
     */
    public synchronized char getChar(){
        hasChar=false;
        return c;
    }

    /**
     * Method for setting next char to be read later
     * @param chr the next char that is going to be set
     */
    public synchronized void setChar(char chr){
        this.c=chr;
        hasChar = true;
    }

    /**
     * Method for getting status of buffer
     * @return if buffer already have a char
     */
    public boolean getHasChar() {
        return hasChar;
    }
}
