
/**
 * the point class
 * @author Evan 33%
 * @author Cam 33%
 * @author Tyler 33%
 * @version 2018.04.18
 */
public class Point {
    private int x;
    private int y;

    /**
     * O(1)
     * @param x the x value
     * @param y the y value
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate of the location
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate of the location
     */
    public int getY() {
        return y;
    }

    /**
     * @param x x coordinate of location
     * @return x coordinate
     */
    public int setX(int x) {
        return this.x = x;
    }

    /**
     * @param y y coordinate
     * @return y coordinate
     */
    public int setY(int y) {
        return this.y = y;
    }

    /**
     * @param o the location to compare to
     * @return whether or not two locations are the same
     * 
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Point) {
            return this.x == ((Point) o).getX() && this.y == ((Point) o).getY();
        }
        return false;
    }

    /**
     * @return the string representation of the coordinate (x, y)
     * 
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}