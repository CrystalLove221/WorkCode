import java.util.ArrayList;
import student.TestCase;

/**
 * Part 2:
 * @author Jake Cohen 50%
 * @author Ryan Bellinger 0%
 * @author Tyler Hogue 50%
 * @version 2018.5.8
 *
 */
public class NodeTest extends TestCase {
    private Node<Point> node1;
    private Node<Point> node2;
    private ArrayList<String> streets;
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    
    /**
     * Set up testing variables
     */
    public void setUp() {
        list1 = new ArrayList<String>();
        list2 = new ArrayList<String>();
        streets = new ArrayList<String>();
        node1 = new Node<Point>(new Point(1, 1), list1);
        node2 = new Node<Point>(new Point(2, 1), list2);
    }
    
    /**
     * return the location of the node
     */
    public void testGetPoint() {
        assertEquals("(1,1)", node1.getPoint().toString());
    }
    
    /**
     * Set the location for the node
     */
    public void testSetPoint() {
        Point pt = new Point(3, 3);
        
        node1.setPoint(pt);
        
        assertEquals("(3,3)", node1.getPoint().toString());
        
        node1.setPoint(null);
        assertNull(node1.getPoint());
    }
    
    /**
     * Return list of places at location
     */
    public void testGetPlaces() {
        assertEquals(list1, node1.getPlaces());
    }
    
    /**
     * Set list of places at location
     */
    public void testSetPlaces() {
        node1.setPlaces(list2);
        
        assertEquals(list2, node1.getPlaces());
    }
    
    /**
     * Return string representation of location and information at
     * that location
     */
    public void testToString() {
        assertEquals("No places @ (1,1)", node1.toString());
        
        list1.add("Business");
        assertEquals("[Business] @ (1,1)", node1.toString());
        
        list1.add("School");
        assertEquals("[Business, School] @ (1,1)", node1.toString());     

        node1.setPoint(null);
        assertEquals("A location has not been set", node1.toString());
    }
    
    /**
     * return the list of streets next to location
     */
    public void testGetStreets() {
        assertEquals("[]", node1.getStreets().toString());
    }
    
    /**
     * set the list of streets next to location
     */
    public void testSetStreets() {
        streets.add("A");
        streets.add("B");
        
        node1.setStreets(streets);
        
        assertEquals("[A, B]", node1.getStreets().toString());
    }
    
    /**
     * test return value of distance from origin point
     */
    public void testGetDistance() {
        assertEquals(0, node1.getDistance(), 0.0);
    }
    
    /**
     * test settinig distance of the node from origin
     */
    public void testSetDistance() {
        node1.setDistance(4.1);
        assertEquals(4.1, node1.getDistance(), 0.0);
    }
    
    /**
     * test comparison of distances from the origin
     */
    public void testCompareTo() {
        assertEquals(0, node1.compareTo(node1));
        
        node1.setDistance(3);
        
        assertEquals(1, node1.compareTo(node2));
        assertEquals(-1, node2.compareTo(node1));
    }
}