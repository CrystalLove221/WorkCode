
/**
 * @author Tyler Hogue
 * @version 3.26.2018
 *
 */
public class NotInAcademicProbationException 
    extends RuntimeException {

    
    /**
     * constructor, calls RuntimeException
     */
    public NotInAcademicProbationException() {
        super();
    }
    
    /**
     * @param msg Message to display for error
     */
    public NotInAcademicProbationException(String msg) {
        super(msg);
    }
}
