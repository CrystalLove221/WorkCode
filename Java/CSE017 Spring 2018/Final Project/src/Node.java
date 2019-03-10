import java.util.ArrayList;

/**
 * Part 2:
 * @author Tyler Hogue 50%
 * @author Jake Cohen 50%
 * @author Ryan Bellinger 0%
 * @version 2018.05.8
 * 
 * @param <E> a generic, in this case will be Point
 */
public class Node<E> implements Comparable<Node<E>> {
    private E data;
    private ArrayList<String> list;
    private ArrayList<String> streets;
    private double distance;
    
    /**
     * O(1)
     * @param data the information describing the location (coordinate)
     * @param list the list of places that can be explored at the 
     * specified location
     */
    public Node(E data, ArrayList<String> list) {
        this.data = data;
        this.list = list;
        this.setDistance(0);
        this.setStreets(new ArrayList<String>());
    }

    /**
     * O(1): returns the private Node.list field
     * @return the list of places at this location
     */
    public ArrayList<String> getPlaces() {
        return list;
    }
    
    /**
     * O(1)
     * @param places the list of places to set for this location
     */
    public void setPlaces(ArrayList<String> places) {
        list = places;
    }

    /**
     * O(1)
     * @return Point data about location (x and y coordinates)
     */
    public E getPoint() {
        return data;
    }

    /**
     * O(1)
     * @param point The point data to be associated with the node
     */
    public void setPoint(E point) {
        data = point;
    }
    
    /**
     * O(n): Iterate through the entire list and append list into
     * output string using ArrayList's toString()
     * @return String representation of the data and list of
     * places in the node
     */
    public String toString() {
        
        if(list.isEmpty()) {
            return "No places @ " + data.toString();
        }
        
        if (data == null) {
            return "A location has not been set";
        }
        
        return list.toString() + " @ " + data.toString();
    }

	/**
	 * @return how far node is from the origin point
	 * 
	 * O(1)
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance how far the node is from the origin
	 * point
	 * 
	 * O(1)
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the list of streets the node is 
	 * adjacent to
	 * 
	 * O(1)
	 */
	public ArrayList<String> getStreets() {
		return streets;
	}

	/**
	 * @param streets the list of streets node is 
	 * adjacent to
	 * 
	 * O(1)
	 */
	public void setStreets(ArrayList<String> streets) {
		this.streets = streets;
	}

	@Override
	public int compareTo(Node<E> node) {
	    
	    if (distance < node.getDistance()) {
	        return -1;
	    }
	    
	    if (distance > node.getDistance()) {
	        return 1;
	    }
	    
		return 0;
	}
}