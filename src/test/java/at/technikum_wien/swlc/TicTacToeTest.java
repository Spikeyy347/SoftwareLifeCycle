package at.technikum_wien.swlc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardInitialization() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j), "Board cells should be empty upon initialization.");
            }
        }
    }

    @Test
    public void testPlaceMarker() {
        board.place(1, 1, 'X');
        assertFalse(board.isCellEmpty(1, 1), "Cell (1, 1) should not be empty after placing a marker.");
        assertEquals('X', board.getCell(1, 1), "Cell should contain 'X'.");
    }

    @Test
    public void testClearBoard() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertTrue(board.isCellEmpty(i, j), "Board cells should be empty after clear.");
            }
        }
    }

    @Test
    public void testIsFull() {
        assertFalse(board.isFull(), "Board should not be full initially.");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.place(i, j, 'X');
            }
        }
        assertTrue(board.isFull(), "Board should be full.");
    }
}
