import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tyler Hogue
 * @version 2018.2.4
 */
public class WeeklyNewspaperTest
    extends TestCase
{
    // ----------------------------------------------------------

// ~ Fields ....................................................
    
    private WeeklyNewspaper np1;
    private WeeklyNewspaper np2;

    // ~ Methods ...............................................................
    /**
     * Create a new WeeklyNewspaperTest object.
     */
    public WeeklyNewspaperTest()
    {
        // Empty
    }


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        np1 = new WeeklyNewspaper(500, "School", 20, 1.5);
        np2 = new WeeklyNewspaper(300, "other", 10, 1);
    }
    
    /**
     * test return of monthly cost of newspapers
     */
    public void testMonthlyCost() {
        assertEquals(120, np1.monthlyCost(), 0.01);
    }
    
    /**
     * test message regarding description of bundle
     */
    public void testBundledWith() {
        assertEquals("other subscription is bundled "
                + "with School.", np1.bundledWith(np2));
    }

}
