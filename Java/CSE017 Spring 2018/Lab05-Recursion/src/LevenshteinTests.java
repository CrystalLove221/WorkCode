import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the Levenshtein distance algorithm.
 *
 * @author Tyler Hogue
 * @version 2018.3.2
 */
public class LevenshteinTests
    extends TestCase
{
    
    private Levenshtein str;
    private Levenshtein str2;
    private Levenshtein str3;
    private Levenshtein str4;
    private Levenshtein str5;
    private Levenshtein str6;
    private Levenshtein str7;
    private Levenshtein str8;
    private Levenshtein str9;
    
    /**
     * required constructor for setup
     */
    public LevenshteinTests() {
        //empty
    }
    
    /**
     * Set up testing variables and constructs
     */
    public void setUp() {
        str = new Levenshtein("Say", "Lay");
        str2 = new Levenshtein("Hello", "Hi");
        str3 = new Levenshtein("Start", "Cart");
        str4 = new Levenshtein("Strt", "Cart");
        str5 = new Levenshtein("Long string", "Short string");
        str6 = new Levenshtein("", "Message");
        str7 = new Levenshtein("Long string", "");
        str8 = new Levenshtein("Start", "Start");
        str9 = new Levenshtein("This is a long string",
                "This string is long");
    }
    
    /**
     * test return values of edit distances
     */
    public void testDistance() {
        assertEquals(1, str.distance());
        assertEquals(4, str2.distance());
        assertEquals(2, str3.distance());
        assertEquals(2, str4.distance());
        assertEquals(4, str5.distance());
        
        assertEquals(7, str6.distance());
        assertEquals(11, str7.distance());
        assertEquals(0, str8.distance());
        assertEquals(10, str9.distance());
    }
}