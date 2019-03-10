
/**
 * @author Tyler Hogue
 * @version 3.23.2018
 *
 */
public class SubArrayTest 
    extends student.TestCase
    {

    
    private SubArray sa;
    private int num;
    Object[] arr = {2, 3, 5, 3};
    
    /**
     * empty placeholder for Java-strict
     * purposes :)
     */
    public SubArrayTest() {
        //empty
    }
    
    public void setUp() {
        sa = new SubArray();
        num = 5;
    }
    
    /**
     * test adding an element to subarray
     */
    public void testAdd() {
        sa.add(num);
        assertEquals(1, sa.getAddCount());
    }
    
    /**
     * test adding an array of elements to
     * the subarray
     */
    public void testAddAll() {
        sa.addAll(arr);
        assertEquals(4, sa.getAddCount());
    }
    
    /**
     * check return value of the number
     * of additions to subarray since
     * beginning of program execution
     */
    public void testGetAddCount() {
        assertEquals(0, sa.getAddCount());
    }
}
