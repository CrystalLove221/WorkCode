import student.TestCase;

//------------------------------------------------------------
/**
 * Tests for the {@link HistoricEvent} class.
 * @author Tyler Hogue
 * @version 2018.2.23
 */
public class HistoricEventTest
    extends TestCase
{
    //~ Instance/static variables .............................

    private HistoricEvent event;
    private HistoricEvent event2;
    private HistoricEvent event3;

    private static final String TITLE = "CS 2114 Lab";
    private static final String MESSAGE =
        "I went to lab today, and I didn't even get a T-shirt.";


    //~ Constructor ...........................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public HistoricEventTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Starting conditions for all tests in this class.
     */
    public void setUp()
    {
        event = new HistoricEvent(2010, TITLE, MESSAGE);
        event2 = new HistoricEvent(2012, "Doomsday", "Never happened");
        event3 = new HistoricEvent(2010, "DS day", "Never happened");
    }


    // ----------------------------------------------------------
    /**
     * Test the constructor and accessors.
     */
    public void testAccessors()
    {
        assertEquals(2010, event.getYear());
        assertEquals(TITLE, event.getTitle());
        assertEquals(MESSAGE, event.getDescription());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on the event created in setUp().
     */
    public void testToString()
    {
        assertEquals("[2010] " + TITLE + ": " + MESSAGE, event.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null title.
     */
    public void testToString2()
    {
        event = new HistoricEvent(2010, MESSAGE);
        assertEquals("[2010] " + MESSAGE, event.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null description.
     */
    public void testToString3()
    {
        event = new HistoricEvent(2010, TITLE, null);
        assertEquals("[2010] " + TITLE, event.toString());
    }
    
    /**
     * tests the implemented compareTo()
     * 0: identical events
     * 1 or -1: different events
     */
    public void testCompareTo() {
        
        assertEquals(0, event.compareTo(event));
        assertEquals(1, event2.compareTo(event));
        assertEquals(-1, event.compareTo(event2));
        
        assertEquals(-1, event.compareTo(event3));
        assertEquals(1, event3.compareTo(event));
    }
    
    /**
     * test initializing final
     * list of computer history
     */
    public void testHistoricEvents() {
        HistoricEvent[] events;
        events = HistoricEvents.TIMELINE;
        
        assertEquals(HistoricEvents.TIMELINE, events);
    }
}
