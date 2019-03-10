import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Tyler Hogue 50%
 * @author Jake 50%
 * @version 2018.05.5
 */

/*
 * 2 Point variables data fields: topLeft and botRight, representing the top left and 
 * bottom right points respectively.
 * 1 Node variable data field to store the coordinates and additional information 
 * about the location (type of location: restaurant, school, etc.)
 * 4 Quad variables data fields: topLeftTree , topRightTree, botLeftTree,botRightTree; 
 * representing the top left, top right, bottom left, and bottom right 
 * quadrants of the map respectively.
 * 
 * Constructor like so: 
 * public Quad(Point topLeft, Point botRight)
 */
public class Quad {
    private Point topLeft;
    private Point botRight;
    
    private Node<Point> location;
    
    private Quad topLeftTree;
    private Quad topRightTree;
    private Quad botLeftTree;
    private Quad botRightTree;
    
    private BinarySearchTree<StreetNodes> BST;
    

    /**
     * @param topLeft top left node of the map
     * @param botRight bottom right  node of the map
     */
    public Quad(Point topLeft, Point botRight) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        location = null;
        topLeftTree = null;
        topRightTree = null;
        botLeftTree = null;
        botRightTree = null;
        BST = new BinarySearchTree<StreetNodes>();
    }

    /**
     * O(1)
     * Are the bounds inclusive or exclusive?
     * It will be helpful to create a method that takes a Point variable as parameter 
     * and return true if a node is within the bounds of a quadrant or false otherwise.
     * 
     * @param p a point
     * @return true if the point is in bounds
     */
    public boolean inQuad(Point p) {
        if (p.getX() >= topLeft.getX() &&
                p.getY() >= topLeft.getY() &&
                p.getX() <= botRight.getX() &&
                p.getY() <= botRight.getY()) {
            //recall botRight has larger y than topLeft
            return true;
        }
        return false;
    }

    /**
     * O(log(n))
     * This method takes the x and y coordinates of the location and a description of the 
     * location. In this assignment we will limit the description to the type of business or 
     * organization. E.g. school, restaurant, home, bank, etc.
     * This method will create a new Node (newNode) object with the parameters, and call 
     * the recursive insert method (described below) that takes a newNode object as a 
     * parameter
     * 
     * @param x of location
     * @param y of location
     * @param description of location E.g. school, restaurant, home, bank, etc.
     */
    public void insert(int x, int y, String description) {
        ArrayList<String> newList = new ArrayList<String>();
        newList.add(description);
        Node<Point> newNode = new Node<Point>(new Point(x, y), newList);
        insert(newNode);
    }

    /**
     * O(log(n))
     * This is the recursive helper method for insert
     * 
     * Base cases
     * (1) If newNode is null then end recursion (return)
     * (2) Current quad cannot contain newNode 
     * - outside of the boundaries
     * - end recursion (return)
     * (3) If we are at a quad of unit area (size = 1) 
     * -we cannot subdivide this quad further
     * - if the node field of quad null, set it to newNode
     * - else add newnode description to the list of places of the node and end the recursion
     * 
     * Recursive calls
     * 4 for each direction, topleft topright botleft botright
     * Example:
     * if newNode is in topLeftTree:
     * ((topLeft.X + botRight.X) / 2 >= newNode.X) and
     * ((topLeft.Y + botRight.Y) / 2 >= newNode.Y)
     * - if topLeftTree is null:
     * Set topLeftTree to a new Quad with bounds:
     * (topLeft.X, topLeft.Y) and (topLeft.X + botRight.X) / 2,(topLeft.Y + botRight.Y) / 2)
     * - call insert on topLeftTree with newNode as a parameter. Ex topLeftTree.insert(newNode)
     * 
     * @param newNode the node to insert
     */
    public void insert(Node<Point> newNode) {
        if (newNode == null) {
            return;
        }
        if (!inQuad(newNode.getPoint())) {
            return;
        }
        //if size is 1, cannot subdivide further
        if (topLeft.equals(botRight)) {
            if (location == null) {
                location = newNode;
            } else {
                location.getPlaces().addAll(newNode.getPlaces());
            }
            return;
        }
        //recursive calls:
        //The ceil notaiton is super gross. but necessary to avoid floor (ints)
        //dividing by 2d in order to avoid the floor, then manually use ceiling.
        //topLeftTree
        if ((topLeft.getX() + botRight.getX()) / 2 >= newNode.getPoint().getX() && 
                (topLeft.getY() + botRight.getY()) / 2 >= newNode.getPoint().getY()) {
            if (topLeftTree == null) {
                Point mid = new Point((topLeft.getX() + botRight.getX()) / 2, (topLeft.getY() + botRight.getY()) / 2);
                topLeftTree = new Quad(topLeft, mid);
            }
            topLeftTree.insert(newNode);
            return;
        }
        //botLeftTree
        if ((topLeft.getX() + botRight.getX()) / 2 >= newNode.getPoint().getX()) {
            if (botLeftTree == null) {
                botLeftTree = new Quad(new Point(topLeft.getX(), (int) Math.ceil((topLeft.getY() + botRight.getY()) / 2d)), 
                        new Point((topLeft.getX() + botRight.getX()) / 2, botRight.getY()));
            }
            botLeftTree.insert(newNode);
            return;
        }
        //topRightTree
        if ((topLeft.getY() + botRight.getY()) / 2 >= newNode.getPoint().getY()) {
            if (topRightTree == null) {
                topRightTree = new Quad(new Point((int) Math.ceil((topLeft.getX() + botRight.getX()) / 2d), topLeft.getY()), 
                        new Point(botRight.getX(), (topLeft.getY() + botRight.getY()) / 2));
            }
            topRightTree.insert(newNode);
            return;
        }
        
        //botRightTree
        if (botRightTree == null) {
            Point mid = new Point((int) Math.ceil((topLeft.getX() + botRight.getX()) / 2d), (int) Math.ceil((topLeft.getY() + botRight.getY()) / 2d));
            botRightTree = new Quad(mid, botRight);
        }
        botRightTree.insert(newNode);
        return;
    }
    
    
    /**
     * This method expands on the above insert method by taking into
     * account streets that pass through Node<Point> objects.
     * 
     * It first creates Node and StreetNode instances to search for
     * in the quad tree. If Node is non-existent, it will be added
     * to the quad.
     * For each StreetNode with name from streets ArrayList not found in
     * the binarysearch tree, it will be added after the node has been
     * added to its list of locations that it passes through.
     * Every street in streets ArrayList will be added to the
     * Node's list of streets adjacent to it.
     * 
     * If StreetNode is found in BST, the method will simply
     * add Node<Point> object to StreetNode's list of locations.
     * 
     * If Node<Point> is found, then nothing happens (return)
     * 
     * O(n log n)
     * 
     * @param x x coordinate of the new node
     * @param y y coordinate of the new node
     * @param description the type of place to be added to location
     * @param streets the list of streets the node is adjacent to
     */
    public void insert(int x, int y, String description, String...streets)
    {
    	Point p = new Point(x, y);
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(description);
    	Node<Point> newNode = new Node<Point>(p, list);
    	// if the point is not in the quad end method
    	if (!inQuad(p)) {
    		return;
    	}   	
    	// if the node hasn't been added to the quad previously
    	if (search(newNode.getPoint()) == null) {
    		insert(newNode);
    	}
    	// ends if the node has already been added
    	else {
    		return;
    	}
    	// traverses the array of strings
    	for(int i = 0; i < streets.length; i++) {
    		newNode.getStreets().add(streets[i]);
    		StreetNodes sNode = new StreetNodes(streets[i]);
    		// if the street node isn't already in the BST add it
    		if (BST.find(sNode) == null) {
    			sNode.addPoint(newNode);
    			BST.insert(sNode);
    		}
    		// if the street node is in the BST add the newNode into it
    		else {
    			BST.find(sNode).addPoint(newNode);
    		}
    	}
    }
    
    
    /**
     * Takes a street name and returns an ArrayList of locations
     * that are on the street.
     * 
     * Null if street is not found in the binary search tree (thus not on map)
     * 
     * O(log n)
     * 
     * @param streetName the name of the street to search for and 
     * return list of locations.
     * @return the list of locations the street passes through
     */
    public ArrayList<Node<Point>> streetSearch(String streetName) {
    	ArrayList<Node<Point>> result = new ArrayList<Node<Point>>();
    	StreetNodes sNode = new StreetNodes(streetName);
    	if (BST.find(sNode) != null) {
            //we've reached a leaf node and the node is not empty and the street node can be found
            result.addAll(BST.find(sNode).getLocations());
            System.out.println("base");
        }
        if (topLeftTree != null) {
             result.addAll(topLeftTree.streetSearch(streetName));
        }
        if (botLeftTree != null) {
            result.addAll(botLeftTree.streetSearch(streetName));
        }
        if (topRightTree != null) {
            result.addAll(topRightTree.streetSearch(streetName));
        }
        if (botRightTree != null) {
            result.addAll(botRightTree.streetSearch(streetName));
        }
        return result;
    }
    
    
    /**
     * Taking a street name and type_of_place to search for,
     * this method returns all the locations on the street that
     * contains the desired place the user wants to find
     * (stored in an ArrayList)
     * 
     * 
     * O(n^2)
     * 
     * @param streetName the street to search for type_of_place on
     * @param type_of_place the business/organization/place to look for
     * on street
     * @return ArrayList of locations containing type_of_place
     */
    public ArrayList<Node<Point>>streetSearch(String streetName, String type_of_place) {
    	ArrayList<Node<Point>> list = new ArrayList<Node<Point>>();
    	ArrayList<Node<Point>> result = new ArrayList<Node<Point>>();
    	list = streetSearch(streetName);
    	//traverses the array of node points (locations) and checks to see if their locations
    	//array list contains the type of place. If so it adds them to the result and then returns it
    	for (int i = 0; i < list.size(); i++) {
    		if(list.get(i).getPlaces().contains(type_of_place)) {
    			result.add(list.get(i));
    		}	
    	}
    	return result;
    }
    
    
    /**
     * Taking a street name and type_of_place to search for,
     * this method returns all the locations on the street that
     * contains the desired place the user wants to find
     * and takes the coordinates of an origin point to calculate distance
     * returns the list in order of increasing distance
     * from origin
     * (stored in an ArrayList)
     * 
     * 
     * O(n^2)
     * 
     * @param streetName the street to search for type_of_place on
     * @param type_of_place the business/organization/place to look for
     * on street
     * @param originX the point which to use to find distance
     * @param originY the point which to use to find distance
     * @return ArrayList of locations containing type_of_place in order of ascending distances
     */
	public ArrayList<Node<Point>> streetSearch(int originX, int originY, String streetName, String type_of_place) {
    	ArrayList<Node<Point>> list = new ArrayList<Node<Point>>();
    	ArrayList<Node<Point>> result = new ArrayList<Node<Point>>();
    	ArrayList<Comparable<Node<Point>>> result1 = new ArrayList<Comparable<Node<Point>>>();
     	//create an empty array
    	list = streetSearch(streetName, type_of_place);
    	Comparable[] list2 = new Comparable[list.size()];
    	MinHeap heap = new MinHeap(list2, 0, list.size());
    	for (int i = 0; i < list.size(); i++) {
    		int x = list.get(i).getPoint().getX();
    		int y = list.get(i).getPoint().getY();
    		list.get(i).setDistance(Math.sqrt(((originX - x) * (originX - x)) + ((originY - y) * (originY - y))));
    	}
    	//adds manually into the heap rather than builds it
    	for (int j = 0; j < list.size(); j++) {
    		heap.insert(list.get(j));
    	}
    	//We get a casting warning when trying to add the results from remove min into the arraylist
    	for (int k = 0; k < list.size(); k++) {
    		result.add((Node<Point>)heap.removemin());
    	}
    	return result;
    }

    /**
     * O(log(n))
     * This method takes the x and y coordinates of the location and return the Node 
     * containing all the data/places related to that location. This method creates a Point 
     * object (P) and calls the recursive search method with P as parameter
     * 
     * @param x of location
     * @param y of location
     * @return node which has all data/places related to that location
     */
    public Node<Point> search(int x, int y) {
        Point newPoint = new Point(x, y);
        return search(newPoint);
    }

    /**
     * O(log(n))
     * Base cases
     * (1) Current quad cannot contain P 
     * - outside of the boundaries
     * - end recursion (return null)
     * (2) If node field of current quad not null end recursion (return the node field)
     * 
     * Recursive calls
     * 4 for each direction selected if p is in that tree:
     * Example:
     * if P is in topLeftTree
     * - if topLeftTree is null end recursion (return null)
     * - else call search on topLeftTree with P as parameter
     * 
     * @param p the location to search for
     * @return node containing all data/places for that location
     */
    public Node<Point> search(Point p) {
        if (!inQuad(p)) {
            return null;
        }
        if (location != null) {
            return location;
        }
        
        //recursive calls:
        //topLeftTree
        if ((topLeft.getX() + botRight.getX()) / 2 >= p.getX() && 
                (topLeft.getY() + botRight.getY()) / 2 >= p.getY()) {
            if (topLeftTree == null) {
                return null;
            }
            return topLeftTree.search(p);
        }
        //botLeftTree
        if ((topLeft.getX() + botRight.getX()) / 2 >= p.getX()) {
            if (botLeftTree == null) {
                return null;
            }
            return botLeftTree.search(p);
        }
        //topRightTree
        if ((topLeft.getY() + botRight.getY()) / 2 >= p.getY()) {
            if (topRightTree == null) {
                return null;
            }
            return topRightTree.search(p);
        }
        //botRightTree
        if (botRightTree == null) {
            return null;
        }
        return botRightTree.search(p);
    }

    /**
     * O(n)
     * This method takes a String representing the type of place/business the user is 
     * interested in (bank, hospital, school, etc.), and return a list containing all the nodes 
     * with such places. 
     * This method will call a modified version of the above search method 
     * that will take a String and return an ArrayList of Node.
     * The design and implementation of this recursive (other) search method is entirely 
     * up to you.
     * 
     * The idea is this recursion will split 4 times and search every node in the Quad.
     * It will add to the arraylist and return it as a base case when there's nothing left
     * (Quad size gets to one or next quads are all null?)
     * 
     * @param type_of_place we are looking for
     * @return list containing all the nodes with such places
     */
    public ArrayList<Node<Point>> search(String type_of_place) {
        ArrayList<Node<Point>> foundPlaces = new ArrayList<Node<Point>>();
        if (location != null && location.getPlaces().contains(type_of_place)) {
            //we've reached the end of the tree
            foundPlaces.add(location);
        }
        if (topLeftTree != null) {
             foundPlaces.addAll(topLeftTree.search(type_of_place));
        }
        if (botLeftTree != null) {
            foundPlaces.addAll(botLeftTree.search(type_of_place));
        }
        if (topRightTree != null) {
            foundPlaces.addAll(topRightTree.search(type_of_place));
        }
        if (botRightTree != null) {
            foundPlaces.addAll(botRightTree.search(type_of_place));
        }
        return foundPlaces;
    }
    
}