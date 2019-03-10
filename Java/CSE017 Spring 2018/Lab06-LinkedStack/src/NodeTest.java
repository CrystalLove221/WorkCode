import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link Node} class.
 *
 * @author  Tyler Hogue
 * @version 2018.4.4
 */
public class NodeTest
    extends TestCase
{
    //~ Fields ................................................................

    // TODO: Depending on how you test, you might need more, or fewer, of
    // these.
    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Create some new nodes for each test method.
     */
    public void setUp()
    {
        node1 = new Node<String>("node1");
        node2 = new Node<String>("node2");
        node3 = new Node<String>("node3");
    }


    /**
     * return next
     */
    public void testNext() {
        
        assertNull(node1.next());
        
        node1.join(node2);
        
        assertEquals(node2, node1.next());
        assertNull(node2.next());
        
        node2.join(node3);
        
        assertEquals(node3, node2.next());
        assertNull(node3.next());
        
    }
    
    /**
     * return previous node
     */
    public void testPrevious() {
        
        assertNull(node1.previous());
        
        node1.join(node2);
        
        assertEquals(node1, node2.previous());
        
        node2.join(node3);
        
        assertEquals(node2, node3.previous());
        
        node1.split();
        
        assertNull(node2.previous());
        
    }
    
    /**
     * connect two nodes (A <-> B)
     */
    public void testJoin() {
        assertEquals(node1, node1.join(node2));
        
        assertEquals(node2, node2.join(null));
        
        assertEquals(node1, node2.previous());
        assertNull(node2.next());
        
        assertNull(node1.previous());
        assertEquals(node2, node1.next());
        
        node2.join(node3);
        
        Exception exc = null;
        
        try {
            node3.join(node2);
        }
        
        catch (IllegalStateException e) {
            exc = e;
        }
        
        assertTrue(exc instanceof IllegalStateException);
        
        assertEquals("Desired node is already "
                + "joined to a node.", exc.getMessage());
        
        exc = null;
        
        try {
            node2.join(node1);
        }
        
        catch (Exception e) {
            exc = e;
        }
        
        assertTrue(exc instanceof IllegalStateException);
        assertEquals("The node you want to"
                    + "join is already joined.", exc.getMessage());
    }
    
    /**
     * Disconnect two nodes
     */
    public void testSplit() {
        
        assertNull(node1.split());
        
        node1.join(node2);
        
        assertNull(node2.next());
        
        assertEquals(node2, node1.split());
        assertNull(node1.next());
        assertNull(node2.previous());
        
        node2.join(node1);
        
        assertEquals(node1, node2.next());
        assertEquals(node2, node1.previous());
        
        assertEquals(node1, node2.split());
        assertNull(node2.next());
        assertNull(node2.split());
        assertNull(node1.previous());
    }
    
    /**
     * Returning the data of a node to the user
     */
    public void testData() {
        
        assertEquals("node1", node1.data());
        
        node1.setData("aaa");
        
        assertEquals("aaa", node1.data());
    }
    
    /**
     * Setting the data field of a node
     */
    public void testSetData() {
        
        assertEquals("node1", node1.data());
        
        node1.setData("aaa");
        
        assertEquals("aaa", node1.data());
    }
    
}