package cs2114.mazesolver;

import java.util.Stack;

/**
 * @author Tyler Hogue
 * @version 2018.4.13
 *
 */
public class Maze implements IMaze {
    
    private MazeCell[][] board;
    private int size;
    private Location start;
    private Location goal;
    private Stack<Location> solution;
    
    /**
     * @param size dimensions of the square maze
     */
    public Maze(int size) {
        solution = new Stack<Location>();
        this.size = size;
        board = new MazeCell[size][size];
        start = new Location(0, 0);
        goal = new Location(size - 1, size - 1);
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Location newloc = new Location(i, j);
                this.setCell(newloc, MazeCell.UNEXPLORED);
            }
        }
        
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILocation getStartLocation() {
        return start;
    }

    @Override
    public void setStartLocation(ILocation loc) {
        if (this.getCell(loc) == MazeCell.WALL) {
            board[loc.x()][loc.y()] = MazeCell.UNEXPLORED;
        }
        
        start = (Location) loc;
    }

    @Override
    public ILocation getGoalLocation() {
        return goal;
    }

    @Override
    public void setGoalLocation(ILocation loc) {
        if (this.getCell(loc) == MazeCell.WALL) {
            board[loc.x()][loc.y()] = MazeCell.UNEXPLORED;
        }
        
        goal = (Location) loc;
    }

    @Override
    public MazeCell getCell(ILocation loc) {
        if (loc.y() < 0 || loc.y() > size - 1 || loc.x() < 0 ||
                loc.x() > size - 1) {
            return MazeCell.INVALID_CELL;
        }
        
        return board[loc.x()][loc.y()];
    }

    @Override
    public void setCell(ILocation loc, MazeCell cell) {
        if (this.getCell(loc) == MazeCell.WALL || 
                this.getCell(loc) == MazeCell.INVALID_CELL) {
            return;
        }
        
        if ((loc.equals(start) || loc.equals(goal)) &&
                cell == MazeCell.WALL) {
            return;
        }
        
        board[loc.x()][loc.y()] = cell;
        
    }

    @Override
    public String solve() {
        solution.push(start);
        Location curr = null;
        
        while (!solution.empty()) {
            curr = solution.peek();
            this.setCell(curr, MazeCell.CURRENT_PATH);
            
            if (curr.equals(goal)) {
                return solutionToString();
            }
            
            if (this.getCell(curr.west()) == MazeCell.UNEXPLORED) {
                solution.push((Location) curr.west());
            }
            
            else if (this.getCell(curr.north()) == MazeCell.UNEXPLORED) {
                solution.push((Location) curr.north());
            }
            
            else if (this.getCell(curr.east()) == MazeCell.UNEXPLORED) {
                solution.push((Location) curr.east());
            }
            
            else if (this.getCell(curr.south()) == MazeCell.UNEXPLORED) {
                solution.push((Location) curr.south());
            }
            
            else {
                this.setCell(curr, MazeCell.FAILED_PATH);
                solution.pop();
            }
        }
        
        return null;
    }
        
    
    /**
     * @return Solution (start to goal
     */
    public String solutionToString() {
        String str = "";
        return recurSolToStr(str);
    }
     
    /**
     * @param str coordinates representation of
     * solution found
     * @return the string of steps for solution found
     */
    public String recurSolToStr(String str) {
        if (solution.empty()) {
            return str;
        }
        
        str += solution.pop().toString() + " ";
        
        return recurSolToStr(str);  
    }
}