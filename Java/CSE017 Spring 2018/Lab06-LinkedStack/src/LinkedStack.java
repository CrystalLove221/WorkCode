import java.util.EmptyStackException;

//---------------------------------------------------------------
/**
 * An implementation of the stack data type that uses a 
 * linked chain of
 * {@code Node<E>} objects to store its contents.
 *
 * This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <E>
 *            the type of elements stored in the stack
 *
 * @author Tony Allevato (authored class skeleton)
 * @author Tyler Hogue
 * @version 2018.4.4
 */
public class LinkedStack<E> implements StackInterface<E> {
    // ~ Fields ...............................................

    // TODO: declare fields here
    private Node<E> top;

    // ~ Constructors .........................................................

    // ----------------------------------------------------------
    /**
     * Create a new linked node stack
     */
    public LinkedStack() {

        top = new Node<E>(null);

    }

    // ~ Methods ...........................................

    // ----------------------------------------------------------
    /**
     * @param item
     *            the Node to be pushed onto top of stack
     * 
     */
    public void push(E item) {

        if (isEmpty()) {

            Node<E> newnode = new Node<E>(item);
            top.join(newnode);
            return;
        }
        
        if(item == null) {
            return;
        }

        Node<E> tmp = top.next();

        top.split();

        Node<E> newnode = new Node<E>(item);

        top.join(newnode.join(tmp));
    }

    // ----------------------------------------------------------
    /**
     * Takes the top Node off the stack and disconnects it 
     * from the node below it
     */
    public void pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> tmp = top.next();

        top.split();
        top.join(tmp.split());

    }

    // ----------------------------------------------------------
    /**
     * @return The Node at the top of the stack
     * 
     */
    public E peek() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.next().data();
    }

    // ----------------------------------------------------------
    /**
     * @return true if the head node points to no other node 
     * false otherwise
     * 
     */
    public boolean isEmpty() {

        return top.next() == null;
    }
}
