
/**
 * @author Tyler Hogue
 * @version 3.26.2018
 *
 */
public class NotInDeansListException 
    extends RuntimeException {

    
    /**
     * constructor, calls Runtime Exception
     */
    public NotInDeansListException() {
        super();
    }
    
    /**
     * @param msg message to display for error
     */
    public NotInDeansListException(String msg) {
        super(msg);
    }
}
