import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tyler Hogue
 * @version 2018.2.4
 */
public class DailyNewspaperTest
    extends TestCase
{
    // ----------------------------------------------------------

    // ~ Fields ...............................................
    
    private DailyNewspaper np1;
    private DailyNewspaper np2;

    // ~ Methods ...............................................................
    /**
     * Create a new DailyNewspaperTest object.
     */
    public DailyNewspaperTest()
    {
        // Empty
    }


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        np1 = new DailyNewspaper(1000, "Home", 10, 2.5);
        np2 = new DailyNewspaper(200, "other", 5, 2);
    }
    
    /**
     * test return of ID of daily newspaper
     */
    public void testGetIdNumber() {
        assertEquals(1000, np1.getIdNumber());
    }
    
    /**
     * test return of Title of daily newspaper
     */
    public void testGetTitle() {
        assertEquals("Home", np1.getTitle());
    }
    
    /**
     * test return of the number of copies of
     * daily newspapers
     */
    public void testGetNumberCopies() {
        assertEquals(10, np1.getNumberCopies());
        
    }
    
    /**
     * test return of price of daily newspaper
     */
    public void testGetPrice() {
        assertEquals(2.5, np1.getPrice(), 0.01);
    }
    
    /**
     * test return of monthly cost of daily newspapers
     */
    public void testMonthlyCost() {
        assertEquals(750, np1.monthlyCost(), 0.01);
    }
    
    /**
     * test the returned message stating a bundled newspaper
     */
    public void testBundledWith() {
        assertEquals("Home and other subscriptions are bundled.",
                np1.bundledWith(np2));
    }
}
