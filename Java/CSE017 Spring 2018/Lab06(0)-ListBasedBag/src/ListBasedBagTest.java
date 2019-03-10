import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link ListBasedBag} class.
 *
 * @author Tyler Hogue
 * @version 3.30.2018
 */
public class ListBasedBagTest
    extends TestCase
{
    //~ Instance/static variables .............................................

    private ListBasedBag<Book> bag1;
    private Book book1;
    private ListBasedBag<String> bag2;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public ListBasedBagTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty bag and a new 6-sided die as the test
     * fixture for each test method.
     */
    public void setUp()
    {
        bag1 = new ListBasedBag<Book>();
        bag2 = new ListBasedBag<String>();
        
        book1 = new Book(
            "Data Structures: Abstraction and Design Using Java",
            "Elliot B. Koffman and Paul A. T. Wolfgang",
            "978-0-470-12870-1");
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#ListBasedBag()}.
     */
    public void testArrayBag()
    {
        // Check that a new bag is empty

        // Initially, its size should be zero
        assertEquals(0, bag1.size());

        // It shouldn't contain our test die
        assertFalse(bag1.contains(book1));

        // If we try to pull out an element, nothing should come out
        assertNull(bag1.removeRandom());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#add(java.lang.Object)}.
     */
    public void testAdd()
    {
        bag2.add("go");
        
        assertEquals(1, bag2.size());
        
        bag2.add("go1");
        
        assertEquals(2, bag2.size());
        
        bag2.add(null);
        
        assertEquals(2, bag2.size());
        
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#remove(java.lang.Object)}.
     */
    public void testRemove()
    {
        
        assertNull(bag2.remove("go"));
        
        bag2.add("go");
        
        assertEquals(1, bag2.size());
        
        bag2.add("go1");
        
        assertEquals(2, bag2.size());
        
        
        bag2.remove("go");
        
        assertEquals(1, bag2.size());
        
        bag2.remove("go1");
        
        assertEquals(0, bag2.size());
        
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#removeRandom()}.
     */
    public void testRemoveRandom()
    {
        bag2.add("go");
        
        assertEquals(1, bag2.size());
        
        bag2.add("go1");
        
        assertEquals(2, bag2.size());
        
        
        bag2.removeRandom();
        
        assertEquals(1, bag2.size());
        
        bag2.removeRandom();
        
        assertEquals(0, bag2.size());

    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#contains(java.lang.Object)}.
     */
    public void testContains()
    {
        assertFalse(bag2.contains("go"));
        
        bag2.add("go");
        
        assertTrue(bag2.contains("go"));
        
        assertFalse(bag2.contains(null));
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#isEmpty()}.
     */
    public void testIsEmpty()
    {
        assertTrue(bag2.isEmpty());
        
        bag2.add("go");
        
        assertFalse(bag2.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#size()}.
     */
    public void testSize()
    {
        assertEquals(0, bag2.size());
        assertEquals(0, bag1.size());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link ListBasedBag#toString()}.
     */
    public void testToString()
    {
        assertEquals("{}", bag2.toString());
        
        bag2.add("go");
        
        assertEquals("{go}", bag2.toString());
        
        bag2.add("go1");
        
        assertEquals("{go, go1}", bag2.toString());
    }

}
