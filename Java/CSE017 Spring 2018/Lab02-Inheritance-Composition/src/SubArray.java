
/**
 * @author Tyler Hogue
 * @version 3.23.2018
 *
 */
public class SubArray extends SuperArray {
    
    private int addCount;
    
    /**
     * initialize subarray
     * and counter to keep track of additions
     * to array
     */
    public SubArray() {
        super();
        addCount = 0;
    }
    
    /**
     * @return the number of additions made
     * to array since beginning of program
     * execution
     */
    public int getAddCount() {
        return addCount;
    }
    
    public void add(Object entry) {
        addCount++;
        super.add(entry);
    }
    
    public void addAll(Object[] entries) {
        addCount += entries.length;
        super.addAll(entries);
    }

}
