package cs2114.mazesolver;
import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 4.11.2018
 *
 */
public class MazeTest extends TestCase {

    private Maze maze;
    private Location loc1;
    private Location loc2;
    private Location loc3;
    private Location loc4;
    private Location loc5;
    private Location loc6;
    private Location loc7;
    private Location loc8;

    
    /**
     * empty constructor for the sake of Java
     */
    public MazeTest() {
        //placeholder for Compiler
    }
    
    /**
     * Initialize testing variables
     */
    public void setUp() {
        maze = new Maze(4);
        loc1 = new Location(3, 3);
        loc2 = new Location(3, -1);
        loc3 = new Location(-3, 3);
        loc4 = new Location(5, 3);
        loc5 = new Location(3, 5);
        loc6 = new Location(1, 1);
        loc7 = new Location(2, 2);
        loc8 = new Location(0, 0);
        
    }
    
    /**
     * Return cell type at a location
     */
    public void testGetCell() {
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc1));
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc8));
        
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(loc2));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(loc3));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(loc4));
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(loc5));
    }
    
    /**
     * Set a cell to a wall
     */
    public void testSetCell() {
        maze.setCell(loc6, MazeCell.CURRENT_PATH);
        assertEquals(MazeCell.CURRENT_PATH, maze.getCell(loc6));
        
        maze.setCell(loc6, MazeCell.FAILED_PATH);
        assertEquals(MazeCell.FAILED_PATH, maze.getCell(loc6));
        
        maze.setCell(loc7, MazeCell.WALL);
        assertEquals(MazeCell.WALL, maze.getCell(loc7));
        maze.setCell(loc7, MazeCell.UNEXPLORED);
        assertEquals(MazeCell.WALL, maze.getCell(loc7));
        
        maze.setCell(loc1, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc1));
        
        maze.setCell(loc8, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc8));
        
        maze.setCell(loc2, MazeCell.WALL);
        assertEquals(MazeCell.INVALID_CELL, maze.getCell(loc2));
    }
    
    /**
     * return start position of maze
     */
    public void testGetStartLocation() {
        assertEquals("(0, 0)", maze.getStartLocation().toString());
        maze.setStartLocation(loc7);
        assertEquals("(2, 2)", maze.getStartLocation().toString());
        
    }
    
    /**
     * set new start position for maze
     */
    public void testSetStartLocation() {
        maze.setCell(loc6, MazeCell.WALL);
        maze.setStartLocation(loc6);
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc6));
        
        assertTrue(maze.getStartLocation().equals(loc6));
    }
    
    /**
     * Set the position of the goal
     */
    public void testSetGoalLocation() {
        
        maze.setCell(loc7, MazeCell.WALL);
        maze.setGoalLocation(loc7);
        assertEquals(MazeCell.UNEXPLORED, maze.getCell(loc7));
        
        assertTrue(maze.getGoalLocation().equals(loc7));
    }
    
    /**
     * return position of maze goal
     */
    public void testGetGoalLocation() {
        assertEquals("(3, 3)", maze.getGoalLocation().toString());
        maze.setGoalLocation(loc7);
        assertEquals("(2, 2)", maze.getGoalLocation().toString());
    }
    
    /**
     * return size of maze (square side length)
     */
    public void testSize() {
        assertEquals(4, maze.size());
    }
    
    /**
     * Solve the maze and test output
     */
    public void testSolve() {
        Maze maze2 = new Maze(2);
        Location wall21 = new Location(0, 1);
        Location wall22 = new Location(1, 0);
        maze2.setCell(wall21, MazeCell.WALL);
        maze2.setCell(wall22, MazeCell.WALL);
        
        Location wall1 = new Location(1, 1);
        Location wall2 = new Location(0, 2);
        Location wall3 = new Location(2, 3);
        Location wall4 = new Location(3, 1);
        Location newstart = new Location(0, 1);
        
        maze.setCell(wall1, MazeCell.WALL);
        maze.setCell(wall2, MazeCell.WALL);
        maze.setCell(wall3, MazeCell.WALL);
        maze.setCell(wall4, MazeCell.WALL);
        
        maze.setStartLocation(newstart);
        
        assertEquals("(3, 3) (3, 2) (2, 2) "
                + "(2, 1) (2, 0) (1, 0) (0, 0) (0, 1) ", maze.solve());
        
        assertNull(maze2.solve());
        
    }
    
}
