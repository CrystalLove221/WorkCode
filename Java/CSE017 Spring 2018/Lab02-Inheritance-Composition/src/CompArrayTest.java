
/**
 * @author Tyler Hogue
 * @version 3.24.2018
 */
public class CompArrayTest extends student.TestCase

{

    
    private CompArray arr;
    private int num;
    private Object[] nums = {1, 2, 3, 4};
    
    /**
     * PLaceholder constructor for test
     * class
     */
    public CompArrayTest() {
        //empty
    }
    
    public void setUp() {
        arr = new CompArray();
        num = 5;
    }
    
    /**
     * checks return value of
     * addCount, or number
     * of elements added to Superarray
     */
    public void testAdd() {
        arr.add(num);
        assertEquals(1, arr.getAddCount());
    }
    
    /**
     * checks the return value of adding
     * an array of elements to SuperArray
     */
    public void testAddAll() {
        arr.addAll(nums);
        assertEquals(4, arr.getAddCount());
    }
    
    /**
     * checks the return of getting the 
     * number of elements added to SuperArray
     * since beginning of program
     * execution
     */
    public void testGetAddCount() {
        assertEquals(0, arr.getAddCount());
    }
}
