import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link ArrayListStack} class.
 *
 * @author Tyler Hogue
 * @version 3.31.2018
 */

public class ArrayListStackTest
    extends TestCase
{
    //~ Instance/static variables .............................................

    private ArrayListStack<String> stack;


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new ArrayListStack<String>();
    }


    /**
     * test psuhing item onto stack
     */
    public void testPush() {
        
        stack.push("Hello");
        
        assertEquals(1, stack.size());
        
        stack.push("World");
        
        assertEquals(2, stack.size());
        
    }
    
    /**
     * test pulling item off stack
     */
    public void testPop() {
        
    }
    
    /**
     * test return item for top of stack
     */
    public void testTop() {
        
    }
    
    /**
     * test calculating size of stack
     */
    public void testSize() {
        
    }
    
    /**
     * test checking for empty stack
     */
    public void testIsEmpty() {
        
    }
}
