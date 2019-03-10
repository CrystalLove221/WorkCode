import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.4.14
 *
 */
public class BinaryTreeTest extends TestCase {

    private BinaryTree<String> tree1;
    private BinaryTree<String> tree2;
    private BinaryTree<String> tree3;
    private BinaryTree<String> tree4;
    private BinaryTree<String> tree5;
    private BinaryTree<String> tree6;
    private BinaryTree<String> tree7;
    
    /**
     * Setting up Test Class with required constructor
     */
    public BinaryTreeTest() {
        //included for Java's sake
    }
    
    /**
     * The setup fixtures
     */
    public void setUp() {
        tree4 = new BinaryTree<String>("D");
        tree5 = new BinaryTree<String>("E");
        tree6 = new BinaryTree<String>("F");
        tree7 = new BinaryTree<String>("G");
        
        tree2 = new BinaryTree<String>("B", tree4, tree5);
        tree3 = new BinaryTree<String>("C", tree6, tree7);
        tree1 = new BinaryTree<String>("A", tree2, tree3);
        
    }
    
    /**
     * Traverse and print preorder string of
     * trees and children
     * Preorder: print parent node, then left and right
     * children.
     */
    public void testPreOrder() {
        assertEquals("(A(B(D) (E) ) (C(F) (G) ) )", tree1.toPreOrderString());
    }
    
    /**
     * Traverse and print inorder string of
     * trees and children
     */
    public void testInOrder() {
        assertEquals("(((D) B(E) ) A((F) C(G) ) )", tree1.toInOrderString());
    }
    
    /**
     * Traverse and print postorder string of
     * trees and children
     */
    public void testPostOrder() {
        assertEquals("(((D) (E) B) ((F) (G) C) A)", tree1.toPostOrderString());
    }
    
    /**
     * Test calculation for size of tree
     * number of elements in the tree
     */
    public void testSize() {
        assertEquals(7, tree1.size());
        
        BinaryTree<String> tree8 = new BinaryTree<String>("H");
        tree4 = new BinaryTree<String>("D", tree8, null);
        
        assertEquals(7, tree1.size());
    }
    
    /**
     * Test calculation of maximum number of "levels"
     * that can be traversed from root tree node to
     * external node that has no children
     */
    public void testHeight() {
        assertEquals(3, tree1.height());
        
        BinaryTree<String> tree8 = new BinaryTree<String>("H");
        tree4 = new BinaryTree<String>("D", tree8, null);
        
        assertEquals(3, tree1.height());
        
    }
    
    /**
     * Test duplicating tree structure that contains references
     * to the same instances of data elements as original tree
     */
    public void testClone() {
        assertTrue(tree1.getElement() == tree1.clone().getElement());
        assertTrue(tree1.getLeft().getElement() == 
                tree1.clone().getLeft().getElement());
        
        assertTrue(tree1.getRight().getElement() == 
                tree1.clone().getRight().getElement());
    }
}