import java.util.Arrays;

/**
 * @author Tyler Hogue
 * @version 3.26.2018
 *
 */
public class StudentTest extends student.TestCase {

    
    private Student[] stus;
    private Student bad;
    private Student good;
    
    /**
     * placeholder constructor
     */
    public StudentTest() {
        //empty
    }
    
    public void setUp() {
        stus = new Student[5];
        stus[0] = new Student("ss", 34, 3.8);
        stus[1] = new Student("st", 35, 3.4);
        stus[2] = new Student("su", 36, 3.2);
        stus[3] = new Student("sv", 37, 2.0);
        stus[4] = new Student("sx", 38, 2.4);
        
        Arrays.sort(stus);
        
        good = new Student("sy", 39, 3.9);
        bad = new Student("sz", 40, 1.8);
    }
    
    /**
     * test exception for deans list method
     */
    public void testNotInDeansListException() {
        Exception thrown = null;
        try
        {
            bad.isInDeansList();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NotInDeansListException);

        }
    
    /**
     * test exception return for 
     * academic probation
     */
    public void testNotInAcademicProbationException() {
        
        Exception thrown = null;
        try
        {
            good.isInAcademicProbation();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof NotInAcademicProbationException);

        }
}
