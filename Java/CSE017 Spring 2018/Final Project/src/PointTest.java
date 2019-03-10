import student.TestCase;

/**
 * @author Evan 33%
 * @author Cam 33%
 * @author Tyler 33%
 * @version 2018.04.18
 */
public class PointTest extends TestCase {
    private Point a;
    private Point b;

    /**
     * testing environment for point
     */
    public void setUp() {
        a = new Point(6, 7);
        b = new Point(3, 7);
    }

    /**
     * test for getters/setters
     */
    public void testGetSet() {
        assertEquals(6, a.getX());
        assertEquals(7, a.getY());
        a.setX(8);
        assertEquals(8, a.getX());
        a.setY(9);
        assertEquals(9, a.getY());
    }

    /**
     * test for equals method
     */
    public void testEquals() {
        assertFalse(a.equals(b));
        b.setX(6);
        assertTrue(a.equals(b));
        b.setY(9);
        assertFalse(a.equals(b));
        Point c = null;
        assertFalse(a.equals(c));
        //Evan: added this for coverage
        assertFalse(a.equals(123));
    }

    /**
     * test for toString
     */
    public void testToString() {
        assertEquals("(6,7)", a.toString());
    }
}
