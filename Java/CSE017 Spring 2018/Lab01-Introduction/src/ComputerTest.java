import student.*;

// -------------------------------------------------------------------------
/**
 *  Unit tests for the {@link Computer} class.
 * 
 *  @author  Tyler Hogue
 *  @version 2018.1.26
 */
public class ComputerTest
    extends student.TestCase
{
    //~ Instance/static fields ...............................................

	/**
	 * Initializes pointer to Computer object
	 */
	private Computer comp1;

    //~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ComputerTest object.
     */
    public ComputerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        comp1 = new Computer("Intel", 4, 1.5);
    }


    /**
     * test accessing name of processor
     */
    // ----------------------------------------------------------

    public void testGetProcessor()
    {
        assertEquals("Intel", comp1.getProcessor());
    }
    
    /**
     * test Setting name of processor
     */
    public void testSetProcessor() {
    	comp1.setProcessor("AMD");
    	assertEquals("AMD", comp1.getProcessor());
    }
    
    /**
     * test getting the number of cores
     */
    public void testGetNumCores() {
    	assertEquals(4, comp1.getNumCores());
    }
    
    /**
     * test setting the number of cores
     */
    public void testSetNumCores() {
    	comp1.setNumCores(6);
    	assertEquals(6, comp1.getNumCores());
    }
    
    /**
     * test getting the speed of processor
     */
    public void testGetProcessorSpeed() {
    	assertEquals(1.5, comp1.getProcessorSpeed(), 0.01);
    }
    
    /**
     * test setting processor speed
     */
    public void testSetProcessorSpeed() {
    	comp1.setProcessorSpeed(3.5);
    	assertEquals(3.5, comp1.getProcessorSpeed(), 0.01);
    }
    
    /**
     * Test calculation of computation power
     */
    public void testComputePower() {
    	assertEquals(6, comp1.computePower(), 0.01);
    }
    
    /**
     * test transformation of data into readable string
     */
    public void testToString() {
    	assertEquals("Intel, 4 cores at 1.5 GHz", comp1.toString());
    }
    
}