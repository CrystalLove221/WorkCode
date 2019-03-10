import java.util.ArrayList;

/**
 * @author Tyler Hogue (50%)
 * @author Jake Cohen  (50%)
 * @version 5.5.2018
 *
 */
public class StreetNodes implements Comparable<StreetNodes>{

    private String name;
    private ArrayList<Node<Point>> locations;
    
    /**
     * @param name Name of the street
     * O(1)
     * 
     */
    public StreetNodes(String name) {
        this.name = name;
        locations = new ArrayList<Node<Point>>();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * 
     * O(1)
     */
    @Override
    public int compareTo(StreetNodes street) {
        
        if (street == null) {
            return -1;
        }
        
        if (name.compareTo(street.getName()) < 0) {
            return -1;
        }
        
        if (name.compareTo(street.getName()) > 0) {
            return 1;
        }
        
        return 0;
    }
    
    /**
     * @return name of street
     * O(1)
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name New name of the street
     * O(1)
     */
    public void setName(String name) {
        this.name= name;
    }
    
    /**
     * @return List of nodes (Locations) the street
     * passes through
     * 
     * O(1)
     */
    public ArrayList<Node<Point>> getLocations() {
        return locations;
    }
    
    /**
     * @param locations The list of nodes the 
     * street passes through
     * 
     * O(1)
     */
    public void setLocations(ArrayList<Node<Point>> locations) {
        this.locations = locations;
    }
    
    /**
     * Adds a node that the street goes through
     * @param node the location the street will traverse through
     * @return whether or not node was added
     * 
     * O(1)
     */
    public boolean addPoint(Node<Point> node) {
        if (node == null) {
            return false;
        }
        
        locations.add(node);
        return true;
    }
    
    /**
     * Prints the name of the street, 
     * followed by the list of locations on the street
     * @return string representation of street data
     * 
     * O(n)
     */
    public String toString() {
        String nodeStr = "";
        
        //String for an empty street O(1)
        if (locations.isEmpty()) {
            return name + ": (No locations available)";
        }
        
        for(int i = 0; i < locations.size(); i++) {
            nodeStr += locations.get(i).getPoint().toString() + " ";
        }
        
        return name + ": " + nodeStr;
    }
}
