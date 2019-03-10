
// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Varun
 *  @version Feb 5, 2016
 */
public class SuperArrayTest 
    extends student.TestCase
{

    private SuperArray SuA;

    public void setUp()
    {
        String[] a = { "a", "b", "c", "d" };
        SuA = new SuperArray(a);
    }

    // ----------------------------------------------------------
    /**
     * Test {@link SuperArray#addAll(Object[])}.
     */
    public void testAddAll()
    {
        //The original size of SuA is 4
        assertEquals(4, SuA.getSize());
        String test = "testString";
        SuA.add(test);
        //After adding the string test, the size should be 5
        assertEquals(5, SuA.getSize());
    }
    
    /**
     * test return value of the size of array
     */
    public void testGetSize() {
        assertEquals(4, SuA.getSize());
    }

}
