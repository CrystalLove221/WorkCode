import java.util.ArrayList;
import java.util.EmptyStackException;

//-------------------------------------------------------------------------
/**
 * An implementation of the stack data type that adapts an ArrayList to
 * store its contents.
 *
 * This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <T> the type of elements stored in the stack
 *
 * @author Tony Allevato (authored class skeleton)
 * @author Tyler Hogue
 * @version 3.31.2018
 */
public class ArrayListStack<T> implements SimpleStack<T>
{
	
	//~ Instance/static variables ............................................

	// TODO: declare fields here


	//~ Constructors ........................................

	// ----------------------------------------------------------
    
    /**
     * initialize stack as empty
     */
	public ArrayListStack()
	{
        // TODO: implement
	}


	//~ Methods ..............................................................

	// ----------------------------------------------------------
	
	/**
	 * @param item Item to be placed on stack
	 * 
	 */
	public void push(T item)
	{
        // TODO: remove this throw statement when you complete this method
        throw new UnsupportedOperationException(
            "You have not implemented push() yet");
	}


	// ----------------------------------------------------------
	
	/**
	 * Remove the top item off the stack
	 */
	public void pop()
	{
        // TODO: remove this throw statement when you complete this method
        throw new UnsupportedOperationException(
            "You have not implemented pop() yet");
	}


	// ----------------------------------------------------------
	
	/**
	 * @return item on the top of stack
	 * 
	 */
	public T top()
	{
        // TODO: remove this throw statement when you complete this method
        throw new UnsupportedOperationException(
            "You have not implemented top() yet");
	}


	// ----------------------------------------------------------
	
	/**
	 * @return number of items on stack
	 */
	public int size()
	{
        // TODO: remove this throw statement when you complete this method
        throw new UnsupportedOperationException(
            "You have not implemented size() yet");
	}


	// ----------------------------------------------------------
	
	/**
	 * @return whether or not stack has items
	 */
	public boolean isEmpty()
	{
        // TODO: remove this throw statement when you complete this method
        throw new UnsupportedOperationException(
            "You have not implemented isEmpty() yet");
	}
}
