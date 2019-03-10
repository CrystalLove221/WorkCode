import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author eric
 * @version Jan 2, 2016
 */

public class SuperArray
{
    private static final int INIT_SIZE = 10;
    private Object[]         arr;
    private int              size;


    // ----------------------------------------------------------
    /**
     * Create a new SuperArray object.
     */
    SuperArray()
    {
        arr = new Object[INIT_SIZE];
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Create a new SuperArray object.
     * @param arr
     */
    SuperArray(Object[] arr)
    {
        this.arr = arr;
        size = arr.length;
    }


    // ----------------------------------------------------------
    /**
     * Insert an object into a SuperArray object.
     * @param anEntry the object to be added
     */
    public void add(Object anEntry)
    {

        // Make sure there is room
        if (size >= this.arr.length)
        {
            reallocate();
        }

        // insert item
        this.arr[size] = anEntry;
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Insert all the elements of an array into a SuperArray object.
     * @param c the array
     */
    public void addAll(Object[] c)
    {

        for (int i = 0; i < c.length; i++)
        {
            add(c[i]);
        }
    }


    // ----------------------------------------------------------
    /**
     * getter of field size.
     * @return the size of the superArray
     */
    public int getSize(){
      return size;
    }

 // ----------------------------------------------------------
    /**
     * Private method that doubles the size of the SuperArray object
     * Copies all the elements into the new array, to avoid any loss of data.
     *
     */
    private void reallocate()
    {
        int capacity = this.arr.length * 2;
        this.arr = Arrays.copyOf(this.arr, capacity);
    }


}
