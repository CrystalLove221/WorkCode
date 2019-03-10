
/**
 * @author Tyler Hogue
 * @version 3.23.2018
 *
 */
public class CompArray {

    private SuperArray s;
    private int addCount;
    
    /**
     * no-arg constructor
     */
    public CompArray() {
        s = new SuperArray();
        addCount = 0;
    }
    
    /**
     * @param s SuperArray used to pass into
     * wrapper class CompArray for subarray
     * implementation
     */
    public CompArray(SuperArray s) {
        this.s = s;
    }
    
    /**
     * @param o Single item to add to subarray
     */
    public void add(Object o) {
        addCount++;
        s.add(o);
    }
    
    /**
     * @param o list of items to append to array
     */
    public void addAll(Object[] o) {
        addCount += o.length;
        s.addAll(o);
    }
    
    /**
     * @return get the number of items that been created since
     * start of program execution
     */
    public int getAddCount() {
        return addCount;
    }
    
}
