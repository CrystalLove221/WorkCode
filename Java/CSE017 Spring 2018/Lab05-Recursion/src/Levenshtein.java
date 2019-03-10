import java.util.HashMap;

//-------------------------------------------------------------------------
/**
 * An implementation of the Levenshtein distance algorithm.
 *
 * @author  Tyler Hogue
 * @version 2018.3.2
 */
public class Levenshtein
{
    
    private String s1;
    private String s2;
    private HashMap<String, Integer> map;
    
    /**
     * @param s1 one of two strings to be compared
     * @param s2 the other string to be compared
     */
    public Levenshtein(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        map = new HashMap<String, Integer>();
    }

    /**
     * @return edit distance of s1 and s2
     */

    public int distance() {
        int sub1 = s1.length();
        int sub2 = s2.length();
        int pos1 = 0;
        int pos2 = 0;
        
        return distance(pos1, sub1, pos2, sub2);
    }
    
    /**
     * 
     * @param pos1 current index of string 1 looked at
     * @param sub1 the remaining number of chars to look at]
     * in string 1
     * 
     * @param pos2 current index looked at in string 2
     * @param sub2 remaining number of chars to look
     * at in string 2
     * @return shortest edit distance between string 1
     * and string 2
     */
    private int distance(int pos1, int sub1, 
            int pos2, int sub2) {
        
        String key = pos1 + "," + sub1 + "," + pos2 +
                "," + sub2;
        
        int x = 0;
        int y = 0;
        int z = 0;
        int n = 0;
        
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        if (sub1 == 0) {
            return sub2;
        }
        
        else if (sub2 == 0) {
            return sub1;
        }
        
        else if (s1.equals(s2)) {
            return 0;
        }
        
        if (s1.charAt(pos1) == s2.charAt(pos2)) {
            n = 0;
        }
        
        else {
            n = 1;
        }
        
        x = distance(pos1 + 1, sub1 - 1, pos2, sub2) + 1;
        y = distance(pos1, sub1, pos2 + 1, sub2 - 1) + 1;
        z = distance(pos1 + 1, sub1 - 1, pos2 + 1, sub2 - 1) + n;
        
        int num = Math.min(x, Math.min(y, z));
        map.put(key, num);
        
        return num;
    }
}
