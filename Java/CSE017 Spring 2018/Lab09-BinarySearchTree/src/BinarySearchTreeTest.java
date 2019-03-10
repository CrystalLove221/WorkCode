import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.4.23
 *
 */
public class BinarySearchTreeTest extends TestCase {

    private BinarySearchTree<String> bst;

    /**
     * Unused constructor for sake of Java
     */
    public BinarySearchTreeTest() {
        // empty
    }

    /**
     * Setup testing environment
     */
    public void setUp() {
        bst = new BinarySearchTree<String>();
    }

    /**
     * insert nodes into tree
     */
    public void testInsert() {
        bst.insert("D");
        bst.insert("B");
        bst.insert("F");

        assertEquals("B", bst.find("B"));
        assertEquals("F", bst.find("F"));

        Exception thrown = null;

        try {
            bst.insert("F");
        }

        catch (Exception e) {
            thrown = e;
        }

        assertTrue(thrown instanceof DuplicateItemException);
        assertEquals("F already exists", thrown.getMessage());
    }

    /**
     * test if tree is empty
     */
    public void testIsEmpty() {
        assertTrue(bst.isEmpty());

        bst.insert("D");
        assertFalse(bst.isEmpty());

    }

    /**
     * make tree empty
     */
    public void testMakeEmpty() {
        bst.insert("D");
        bst.insert("B");
        bst.insert("F");

        bst.makeEmpty();
        assertTrue(bst.isEmpty());

    }

    /**
     * find a string in the binary search tree
     */
    public void testFind() {
        assertNull(bst.find("D"));

        bst.insert("D");
        bst.insert("B");
        bst.insert("F");

        assertEquals("B", bst.find("B"));
        bst.remove("B");
        assertNull(bst.find("B"));
    }

    /**
     * Find string whose letters come first in the alphabet
     */
    public void testFindMin() {
        assertNull(bst.findMin());

        bst.insert("D");
        bst.insert("B");
        bst.insert("F");

        assertEquals("B", bst.findMin());
        bst.remove("B");
        assertEquals("D", bst.findMin());
    }

    /**
     * Find string whose letters come last in alphabet
     */
    public void testFindMax() {

        assertNull(bst.findMax());

        bst.insert("D");
        bst.insert("B");
        bst.insert("F");

        assertEquals("F", bst.findMax());
        bst.remove("F");
        assertEquals("D", bst.findMax());
    }

    /**
     * remove specified string
     */
    public void testRemove() {
        bst.insert("H"); // root

        bst.insert("D"); // far left children
        bst.insert("B");
        bst.insert("A");

        bst.insert("L"); // far-right children
        bst.insert("N");
        bst.insert("O");

        bst.insert("C"); // right child of B

        bst.insert("F"); // right children of D
        bst.insert("E");
        bst.insert("G");

        bst.insert("M"); // left child of N

        bst.insert("J"); // left children of L
        bst.insert("I");
        bst.insert("K");

        bst.remove("A");
        assertEquals("B", bst.findMin());

        bst.remove("E");
        assertNull(bst.find("E"));
        assertEquals("F", bst.find("F"));

        bst.remove("D");
        assertNull(bst.find("D"));
        assertEquals("F", bst.find("F"));
        assertEquals("G", bst.find("G"));

        bst.remove("O");
        assertEquals("N", bst.findMax());

        bst.remove("L");
        assertEquals("J", bst.find("J"));
        assertEquals("I", bst.find("I"));
        assertEquals("K", bst.find("K"));

        Exception thrown = null;

        try {
            bst.remove("L");
        }

        catch (Exception e) {
            thrown = e;
        }

        assertTrue(thrown instanceof ItemNotFoundException);
        assertEquals("L", thrown.getMessage());
    }
}
