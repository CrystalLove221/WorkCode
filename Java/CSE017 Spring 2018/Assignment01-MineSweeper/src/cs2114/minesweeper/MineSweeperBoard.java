package cs2114.minesweeper;
import java.util.Random;
/**
 * @author Tyler Hogue
 * @version 2018.2.7
 *
 */
public class MineSweeperBoard extends MineSweeperBoardBase {

    private int width;
    private int height;
    private int mines;
    private MineSweeperCell[][] cell;
    
    /**
     * @param width number of columns of board
     * @param height number of rows of board
     * @param mines number of mines on board
     */
    public MineSweeperBoard(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        cell = new MineSweeperCell[this.width][this.height];
        
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.setCell(i, j, MineSweeperCell.COVERED_CELL);
            }
        }
        Random rand = new Random();
        int numMines = 0;
        while (numMines < this.mines) {
            int x = rand.nextInt(this.width);
            int y = rand.nextInt(this.height);
            
            if (getCell(x, y) != MineSweeperCell.MINE) {
                setCell(x, y, MineSweeperCell.MINE);
                numMines++;
            }
        }
    }
    
    @Override
    public int width() {
        return this.width;
    }

    @Override
    public int height() {
        return this.height;
    }

    @Override
    public MineSweeperCell getCell(int x, int y) {
        if (x >= width() || x < 0 || y >= height() || y < 0) {
            return MineSweeperCell.INVALID_CELL;
        }
        return cell[x][y];
    }

    @Override
    public void uncoverCell(int x, int y) {
        if (getCell(x, y) == MineSweeperCell.MINE) {
            setCell(x, y, MineSweeperCell.UNCOVERED_MINE);
        }
        else if (getCell(x, y) == MineSweeperCell.FLAG
                || getCell(x, y) == MineSweeperCell.FLAGGED_MINE
                || getCell(x, y) == MineSweeperCell.INVALID_CELL) {
            return;
        }
        
        else {
            int adjMines = numberOfAdjacentMines(x, y);
            MineSweeperCell value = MineSweeperCell.adjacentTo(adjMines);
            setCell(x, y, value);
            isGameWon();
        }
        
        if (isGameLost()) {
            this.revealBoard();
        }
    }

    @Override
    public void flagCell(int x, int y) {
        if (this.getCell(x, y) == MineSweeperCell.FLAG) {
            setCell(x, y, MineSweeperCell.COVERED_CELL);
            isGameWon();
        }
        
        else if (getCell(x, y) == MineSweeperCell.FLAGGED_MINE) {
            setCell(x, y, MineSweeperCell.MINE);
        }
        
        else if (getCell(x, y) == MineSweeperCell.MINE) {
            setCell(x, y, MineSweeperCell.FLAGGED_MINE);
            isGameWon();
        }
        
        else if (getCell(x, y) == MineSweeperCell.INVALID_CELL) {
            return;
        }
        
        else {
            setCell(x, y, MineSweeperCell.FLAG);
        }
    }
    @Override
    public boolean isGameLost() {
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (getCell(x, y) == MineSweeperCell.UNCOVERED_MINE) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean isGameWon() {
        int numMines = 0;
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (getCell(x, y) == MineSweeperCell.FLAGGED_MINE) {
                    numMines++;
                }
                if (getCell(x, y) == MineSweeperCell.COVERED_CELL
                        || getCell(x, y) == MineSweeperCell.FLAG) {
                    return false;
                }
            }
        }
        
        return numMines == mines;
    }

    @Override
    public int numberOfAdjacentMines(int x, int y) {
        int numMines = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y + 1; j >= y - 1; j--) {
                if (getCell(i, j) == MineSweeperCell.MINE
                    || getCell(i, j) == MineSweeperCell.FLAGGED_MINE
                    || getCell(i, j) == MineSweeperCell.UNCOVERED_MINE) {
                    numMines++;
                }
            }
        }
        return numMines;
    }

    @Override
    public void revealBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (getCell(i, j) == MineSweeperCell.FLAG || 
                        getCell(i, j) == MineSweeperCell.COVERED_CELL) {
                    int adjMines = numberOfAdjacentMines(i, j);
                    MineSweeperCell value = 
                            MineSweeperCell.adjacentTo(adjMines);
                    setCell(i, j, value);
                } 
                
                else if (getCell(i, j) == MineSweeperCell.FLAGGED_MINE || 
                        getCell(i, j) == MineSweeperCell.MINE) {
                    setCell(i, j, MineSweeperCell.UNCOVERED_MINE);
                }
            }
        }
    }

    @Override
    protected void setCell(int x, int y, MineSweeperCell value) {
        if (x >= width() || x < 0 || y >= height() || y < 0) {
            return;
        }
        cell[x][y] = value;
    }

}