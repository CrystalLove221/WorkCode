package cs2114.mazesolver;

/**
 * @author Tyler Hogue
 * @version 2018.4.13
 */
public class Location implements ILocation {

    private int x;
    private int y;
    
    /**
     * @param x xcorrdinate
     * @param y y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (this == o) {
            return true;
        }
        
        if (o instanceof Location) {
            if (this.x == ((Location) o).x && 
                    this.y == ((Location) o).y) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public ILocation north() {
        return new Location(x, y - 1);
    }

    @Override
    public ILocation south() {
        return new Location(x, y + 1);
    }

    @Override
    public ILocation west() {
        return new Location(x - 1, y);
    }

    @Override
    public ILocation east() {
        return new Location(x + 1, y);
    }

    
}
