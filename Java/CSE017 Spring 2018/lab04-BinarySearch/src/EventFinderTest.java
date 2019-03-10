import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.2.23
 *
 */
public class EventFinderTest
       extends TestCase {
    
    private HistoricEvent[] events;
    
    private EventFinder scan;
    
    /**
     * 
     */
    public EventFinderTest() {
        //empty
    }
    
    /**
     * sets up testing
     */
    public void setUp() {
        events = HistoricEvents.TIMELINE;
        scan = new EventFinder();
  
    }
    
    /**
     * 
     */
    public void testFind1() {
        
        assertEquals(0, scan.find(events[0], events));
        assertEquals(1, scan.find(events[1], events));
        
        assertEquals(events.length - 1, scan.find(
                events[events.length - 1], events));
        
    }
    
    /**
     * test calling the 4-arg find()
     */
    public void testFind2() {
        int index = scan.find(events[3], events, 0, 127);
        
        assertEquals(3, index);
    }
    
}
