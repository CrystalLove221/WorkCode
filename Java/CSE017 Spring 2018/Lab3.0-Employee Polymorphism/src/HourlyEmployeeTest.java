import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Test cases for the HourlyEmployee class.
 *
 * @author Tyler Hogue
 * @version 2018.2.2
 */
public class HourlyEmployeeTest extends TestCase {
    // ~ Instance/static fields ...............................................

    // TODO You may want to declare a HourlyEmployee variable here that you
    // will initialize in your setUp method.

    private HourlyEmployee emp1;
    private HourlyEmployee emp2;

    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp() {
        emp1 = new HourlyEmployee("B", 12.5);
        emp2 = new HourlyEmployee("C", 10.5);
        // TODO Add your initialization code here.
    }

    /**
     * test return value of employee's weekly pay
     */
    public void testWeeklyPay() {
        assertEquals(500, emp1.weeklyPay(), 0.01);
    }

    /**
     * test returning name of employee
     */
    public void testGetName() {
        assertEquals("B", emp1.getName());
    }

    /**
     *  test obtaining hourly pay of employee
     */
    public void testGetPayRate() {
        assertEquals(12.5, emp1.getPayRate(), 0.01);
    }

    /**
     * test String output telling who is meeting who
     */
    public void testMeetWith() {
        assertEquals("B is meeting with C", emp1.meetWith(emp2));
    }

    // TODO Add your test case methods here.
}
