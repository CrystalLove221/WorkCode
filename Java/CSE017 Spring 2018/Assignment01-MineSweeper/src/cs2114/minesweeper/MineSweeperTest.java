package cs2114.minesweeper;

import student.TestCase;

/**
 * @author Tyler Hogue
 * @version 2018.2.10
 */
public class MineSweeperTest extends TestCase {

    private MineSweeperBoard board;

    /**
     * Initialize test board and other testing objects
     */
    public MineSweeperTest() {
        // not used
    }

    /**
     * sets up testign fixture
     */
    public void setUp() {
        board = new MineSweeperBoard(4, 4, 1);
    }

    /**
     * test number of mines around a cell when user clicks cell
     */
    public void testNumberOfAdjacentCells() {
        board.loadBoardState("OOOO", "O++O", "OOOO", "OOOO");

        assertEquals(2, board.numberOfAdjacentMines(1, 1));
    }

    /**
     * test the flagging of various cells on the board
     */
    public void testFlagCell() {
        board.loadBoardState("OOFO", "+OMO", "OOOO", "OOOO");
        board.flagCell(0, 0); // flag covered
        board.flagCell(2, 0); // unflag non-mine
        board.flagCell(-1, 0); // invalid cell
        board.flagCell(0, 1); // flag mine
        board.flagCell(2, 1); // unflag mine

        assertBoard(board, "FOOO", "MO+O", "OOOO", "OOOO");
    }

    /**
     * tests whether the game is lost when user uncovers mine
     */
    public void testIsGameLost() {
        board.loadBoardState("*OOO", "OOOO", "OOOO", "OOOO");

        assertTrue(board.isGameLost());

    }

    /**
     * tests return of isGameWon when
     * user uncovers all non-mine cells and flags all
     * mines
     */
    public void testIsGameWon() {

        board.loadBoardState("   M",
                             "    ",
                             "    ",
                             "    ");
        
        assertTrue(board.isGameWon());

        board.loadBoardState("OOOO",
                             "OOO ",
                             "FFOO",
                             "+OOO");

        assertFalse(board.isGameWon());
        
        board.loadBoardState("   +",
                             "    ",
                             "    ",
                             "    ");
        
        assertFalse(board.isGameWon());
    }

    /**
     * test revealing all cells on the board when called
     */
    public void testRevealBoard() {

        board.loadBoardState("OFOO",
                             "O+MO",
                             "OOOO",
                             "OOOO");

        board.revealBoard();

        assertBoard(board, "1221",
                           "1**1",
                           "1221",
                           "    ");

    }

    /**
     * test returning the value of a cell
     */
    public void testGetCell() {
        board.loadBoardState("+12 ",
                             "OOOO",
                             "MOOF",
                             "OOOO");
        
        assertEquals(MineSweeperCell.FLAG, board.getCell(3, 2));
        assertEquals(MineSweeperCell.MINE, board.getCell(0, 0));
        assertEquals(MineSweeperCell.ADJACENT_TO_1, board.getCell(1, 0));
        assertEquals(MineSweeperCell.ADJACENT_TO_2, board.getCell(2, 0));
        assertEquals(MineSweeperCell.ADJACENT_TO_0, board.getCell(3, 0));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-1, 0));
        assertEquals(MineSweeperCell.FLAGGED_MINE, board.getCell(0, 2));
        assertEquals(MineSweeperCell.COVERED_CELL, board.getCell(0, 1));
    }

    /**
     * test when a user uncovers a cell and test the new board state
     */
    public void testUncoverCell() {
        board.loadBoardState(" OOF", "OOO+", "OOOO", "OMOO");

        board.uncoverCell(-1, 0); // invalid
        board.uncoverCell(0, 0); // uncovered cell
        board.uncoverCell(3, 0); // flagged cell
        board.uncoverCell(2, 1); // covered cell
        board.uncoverCell(1, 3); // flagged mine

        assertBoard(board, " OOF", "OO1+", "OOOO", "OMOO");

        board.loadBoardState("O+OO", "O+OO", "OO+O", "O+OO");

        board.uncoverCell(1, 0);

        assertBoard(board, "2*2 ", "2*31", "23*1", "1*21");
    }

    /**
     * @param theBoard
     *            instance of Board to be tested
     * @param expected
     *            the state to test theBoard against
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected) {
        MineSweeperBoard expectedBoard = new MineSweeperBoard(
                expected[0].length(), expected.length, 1);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard); 
        // uses equals() from MineSweeperBoardBase
    }

    /**
     * test changing the value of a cell
     */
    public void testSetCell() {

        board.loadBoardState("    ",
                             "OOOO",
                             "O+OO",
                             "OOOO");

        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        board.setCell(1, 3, MineSweeperCell.ADJACENT_TO_1);
        
        board.setCell(4, 4, MineSweeperCell.FLAG);
        board.setCell(0, 4, MineSweeperCell.FLAG);
        board.setCell(4, 0, MineSweeperCell.FLAG);
        board.setCell(-1, 4, MineSweeperCell.FLAG);
        board.setCell(4, -1, MineSweeperCell.FLAG);
        
        board.setCell(-1, -1, MineSweeperCell.FLAG);
        board.setCell(0, -1, MineSweeperCell.FLAG);
        board.setCell(-1, 0, MineSweeperCell.FLAG);

        assertBoard(board, "    ",
                           "OOOO",
                           "OMOO",
                           "O1OO");

    }

    /**
     * test returned value of number of columns
     */
    public void testWidth() {
        assertEquals(4, board.width());
    }

    /**
     * test returned value of number of rows
     */
    public void testHeight() {
        assertEquals(4, board.height());
    }

    /**
     * test for adjacent to test
     */
    public void testAdjacentTo() {
        MineSweeperCell c = MineSweeperCell.ADJACENT_TO_0;
        assertNotNull(c);

        // testing for exception
        Exception thrown = null;
        try {
            c = MineSweeperCell.adjacentTo(10);
        }
        
        catch (Exception e) {
            thrown = e;
        }
        
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try {
            MineSweeperCell.adjacentTo(-1);
        }
        
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertNotNull(MineSweeperCell.values());
        assertNotNull(MineSweeperCell.valueOf(
                MineSweeperCell.ADJACENT_TO_0.toString()));

    }

    /**
     * test setting the state of board cells with string
     */
    public void testLoadBoardState() {
        MineSweeperBoard a = new MineSweeperBoard(2, 2, 1);
        Exception thrown = null;
        // loadBoardState testing
        // wrong number of rows
        try {
            a.loadBoardState("00");
        } 
        
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        thrown = null;

        // wrong number of columns
        try {
            a.loadBoardState("0000 ", " ");
        } 
        
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        // Wrong symbol in cell
        try {
            a.loadBoardState("00", "$+");
        } 
        
        catch (Exception e) {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * * This method test Equals.
     */
    public void testEqual() {
        MineSweeperBoard mBoard1 = new MineSweeperBoard(4, 4, 6);
        MineSweeperBoard mBoard2 = new MineSweeperBoard(4, 4, 6);
        mBoard1.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        mBoard2.loadBoardState("    ", "OOOO", "O++O", "OOOO");

        // test the same board same dimensions
        assertTrue(mBoard1.equals(mBoard2));

        // same board testing same board
        assertTrue(mBoard1.equals(mBoard1));

        // testing same dimensions board with different cell
        MineSweeperBoard mBoard3 = new MineSweeperBoard(4, 4, 6);
        mBoard3.loadBoardState("    ", "O+OO", "O++O", "OOOO");
        assertFalse(mBoard1.equals(mBoard3));
        MineSweeperBoard mBoard4 = new MineSweeperBoard(15, 1, 0);
        mBoard4.loadBoardState("OFM+* 123456788");
        assertFalse(mBoard1.toString().equals(mBoard3.toString()));

        // testing two string against a board
        assertFalse(mBoard4.toString().equals(mBoard2.toString()));

        // testing against a string
        assertFalse(mBoard1.equals("abc"));
        assertNotNull(mBoard1);

        // same width but different height
        MineSweeperBoard mBoard6 = new MineSweeperBoard(4, 5, 6);
        mBoard6.loadBoardState("    ", "O+OO", "O++O", "OOOO", "OOOO");
        assertFalse(mBoard6.equals(mBoard1));

        // different width same height
        MineSweeperBoard mBoard5 = new MineSweeperBoard(5, 4, 6);
        mBoard5.loadBoardState("     ", "O+OOO", "O++OO", "OOOOO");
        assertFalse(mBoard5.equals(mBoard1));
    }
}