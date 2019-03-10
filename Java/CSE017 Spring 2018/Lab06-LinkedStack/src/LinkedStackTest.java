import java.util.EmptyStackException;

import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link LinkedStack} class.
 *
 * @author  Tyler Hogue
 * @version 2018.4.4
 */
public class LinkedStackTest
    extends TestCase
{
    //~ Fields ................................................................

    private LinkedStack<String> stack;
    
    private Node<String> node1;
    private Node<String> node2;


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new LinkedStack<String>();
        
        node1 = new Node<String>("node1");
        node2 = new Node<String>("node2");
                
    }


    /**
     * taking test nodes at top of stack
     */
    public void testPop() {
        Exception thrown = null;
        
        try {
            stack.pop();
        }
        
        catch (Exception e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
        
        stack.push(node1.data());
        stack.push(node2.data());
        
        stack.pop();
        assertEquals("node1", stack.peek());
        
        stack.pop();
        
        thrown = null;
        
        try {
            stack.peek();
        }
        
        catch (Exception e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
        
    }
    
    /**
     * Putting nodes onto top of stack
     */
    public void testPush() {
        assertTrue(stack.isEmpty());
        
        stack.push(node1.data());
        assertFalse(stack.isEmpty());
        assertEquals(null, node1.next());
        assertEquals("node1", stack.peek());
        
        stack.push(null);
        assertEquals("node1", stack.peek());
        
        stack.push(node2.data());
        assertEquals("node2", stack.peek());
        
    }
    
    /**
     * test checking size of stack
     */
    public void testIsEmpty() {
        
        assertTrue(stack.isEmpty());
        
        stack.push("aaa");
        
        assertFalse(stack.isEmpty());
        
    }
    
    
    /**
     * returning the node at top of stack
     */
    public void testPeek() {
        
        Exception thrown = null;
        
        try {
            stack.peek();
        }
        
        catch (Exception e) {
            thrown = e;
        }
        
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);
        
        stack.push(node1.data());
        assertEquals("node1", stack.peek());
        
    }
}