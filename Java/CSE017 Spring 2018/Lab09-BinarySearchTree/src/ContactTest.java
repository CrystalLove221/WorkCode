import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.4.24
 */
public class ContactTest extends TestCase {

    private Contact c1;
    private Contact c2;
    private Contact c3;
    
    /**
     * empty
     */
    public ContactTest() {
        //empty
    }
    
    /**
     * Setup testing
     */
    public void setUp() {
        c1 = new Contact("Joe", "Deer", "1234");
        c2 = new Contact("Abstain", "Sex", "6666");
        c3 = new Contact("Xoo", "Man", "8278");
    }
    
    /**
     * return first name
     */
    public void testGetFirst() {
        assertEquals("Joe", c1.getFirst());
    }
    
    /**
     * return last name
     */
    public void testGetLast() {
        assertEquals("Deer", c1.getLast());
    }
    
    /**
     * return phone number
     */
    public void testGetPhone() {
        assertEquals("8278", c3.getPhone());
    }
    
    /**
     * set first name
     */
    public void testSetFirst() {
        c2.setFirst("Love");
        assertEquals("Love", c2.getFirst());
    }
    
    /**
     * set last name
     */
    public void testSetLast() {
        c2.setLast("sexy");
        assertEquals("sexy", c2.getLast());
    }
    
    /**
     * set phone number
     */
    public void testSetPhone() {
        c2.setPhone("7777");
        assertEquals("7777", c2.getPhone());
    }
    
    /**
     * compare two contacts
     */
    public void testCompareTo() {
        
        assertEquals(0, c1.compareTo(c1));
        
    }
}
