/**
 * Buffer class used for reading/writing next char by the threads
 */
public class CharBuffer {
    private Character c;
    private boolean hasChar = false;

    /**
     * Method used for reading the chra
     * @return next char that needs to be read
     */
    public synchronized Character getChar(){
        if (hasChar){
            hasChar=false;
            return c;
        }else{
            return null;
        }

    }

    /**
     * Method for setting next char to be read later
     * @param chr the next char that is going to be set
     */
    public synchronized boolean setChar(char chr){
        if (!hasChar){
            this.c=chr;
            hasChar = true;
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Method for getting status of buffer
     * @return if buffer already have a char
     */
    public boolean getHasChar() {
        return hasChar;
    }

    public Character getCharAsync() {
        hasChar=false;
        return c;
    }

    public void setCharAsync(char chr){
        c=chr;
        hasChar=true;
    }

    public void clear() {
        hasChar=false;
        c=null;
    }
}
