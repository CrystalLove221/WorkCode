package cs2114.mazesolver;
import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.4.8
 *
 */
public class LocationTest 
    extends TestCase {

    
    private Location loc;
    private Location loc2; //same x, diff y
    private Location loc3; // same y, diff x
    private Location loc4; //same x and y
    private Object notLoc; // diff type
    
    /**
     * Constructor to keep Java happy
     */
    public LocationTest() {
        //empty for tests
    }
    
    /**
     * set up test variables
     */
    public void setUp() {
        loc = new Location(0, 0);
        loc2 = new Location(0, 1);
        loc3 = new Location(1, 0);
        loc4 = new Location(0, 0);
        notLoc = new Object();
    }
    
    /**
     * return x coordinate
     */
    public void testX() {
        assertEquals(0, loc.x());
    }
    
    /**
     *  return y coordinate
     */
    public void testY() {
        assertEquals(0, loc.y());
    }
    
    /**
     *  move one unit up on maze
     */
    public void testNorth() {
        Location newloc = (Location) loc.north();
        assertEquals(-1, newloc.y());
        assertEquals(0, newloc.x());
    }
    
    /**
     *  move one unit right
     */
    public void testEast() {
        Location newloc = (Location) loc.east();
        assertEquals(1, newloc.x());
        assertEquals(0, newloc.y());
    }
    
    /**
     * move one unit left
     */
    public void testWest() {
        Location newloc = (Location) loc.west();
        assertEquals(-1, newloc.x());
        assertEquals(0, newloc.y());
    }
    
    /**
     * move one unit down
     */
    public void testSouth() {
        Location newloc = (Location) loc.south();
        assertEquals(1, newloc.y());
        assertEquals(0, newloc.x());
    }
    
    /**
     * convert coordinate of location
     * to string
     */
    public void testToString() {
        
        assertEquals("(0, 0)", loc.toString());
    }
    
    /**
     * check if two locations are the same
     */
    public void testEquals() {
        
        assertTrue(loc.equals(loc));
        assertTrue(loc.equals(loc4));
        
        assertFalse(loc.equals(loc2));
        assertFalse(loc.equals(loc3));
        assertFalse(loc.equals(notLoc));
        assertFalse(loc == null);
        
    }
}
