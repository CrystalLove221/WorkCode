import student.TestCase;

//-------------------------------------------------------------------------
/**
 *  Test cases for the SalariedEmployee class.
 *
 *  @author  Tyler Hogue
 *  @version 2018.2.2
 */
public class SalariedEmployeeTest extends TestCase
{
    //~ Instance/static fields ...............................................

    // TODO You may want to declare a SalariedEmployee variable here that you
    // will initialize in your setUp method.

    private SalariedEmployee emp1;
    private SalariedEmployee emp2;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
       
        emp1 = new SalariedEmployee("A", 8.5);
        emp2 = new SalariedEmployee("Z", 10);
    }

    /**
     * test obtaining weekly pay of employee
     */
    public void testWeeklyRate() {
        assertEquals(8.5, emp1.weeklyPay(), 0.01);
    }
    
    /**
     * test return of message stating that one employee joins another in a
     * conference
     */
    public void testMeetWith() {
        assertEquals("Z is joining A in a conference", emp1.meetWith(emp2));
    }
}
