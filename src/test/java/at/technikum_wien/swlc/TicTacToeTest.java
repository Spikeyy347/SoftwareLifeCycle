package at.technikum_wien.swlc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe game;

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
    }

    //Constructor Tests
    @Test
    void constructor_initializesGameWithoutErrors() {
        // Positive case
        assertDoesNotThrow(() -> new TicTacToe());
    }
    @Test
    void constructor_createsNotNullInstance() {
        //negativ case: result should not be null
        TicTacToe newGame = new TicTacToe();
        assertNotNull(newGame);

    }

    //switchCurrentPlayer Tests
    @Test
    void switchCurrentPlayer_canBeCalled() throws Exception {
        //positive case
        assertDoesNotThrow(() -> game.switchCurrentPlayer());
    }

    @Test
    void switchCurrtenPlayer_NotThrownMultipleCalls() {
        //negativ case: should not break

        assertDoesNotThrow(() -> {
            for (int i = 0; i < 10; ++i) {
                game.switchCurrentPlayer();
            }
        });
    }

    //hasWinner tests
    @Test
    void hasWinner_returnsFalseForEmptyBoard() {
        //positive case: empty boards creates no winner
        assertFalse(game.hasWinner());
    }

    @Test
    void hasWinner_doesNotReturnForNewGame() {
        //negativ case: a fresh game should not have a winner
        assertNotEquals(true, game.hasWinner());
    }

    //getValidMove Tests
    @Test
    void getValidMove_returnsValidCordinates() {
        //positiv case: valid input returns coordinates
        Scanner sc = new Scanner("1\n2\n");
        int[] move = game.getValidMove(sc);
        assertEquals(1, move[0]);
        assertEquals(2, move[1]);
    }

    @Test
    void getValidMove_rejectOutOfBound() {
        //negativ case: invalid input followed by valid input
        Scanner sc = new Scanner("5\n5\n0\n0\n0");

        int[] move = game.getValidMove(sc);
        assertNotEquals(5, move[0]);
        assertEquals(0, move[0]);
    }

    //tests playSingleGame()
    @Test
    void playSingleGame_completesWinner() {
        //positiv case: a winning game ends
        Scanner sc = new Scanner("0 0 1 0 0 1 1 1 0 2 0 0 0 0 0 0 0 0");

        assertDoesNotThrow(() -> game.playSingleGame(sc));
    }

    @Test
    void playSingleGame_endsWithWinnerDetected() {
        //negativ case: after a game ends, hasWinner should not be false
        Scanner sc = new Scanner("0\n0\n1\n0\n0\n1\n1\n1\n0\n2\n");
        game.playSingleGame(sc);
        assertNotEquals(false, game.hasWinner());
    }
    //tests for start
    @Test
    void start_completesSuccessfully() {
        //positive case: game ends without crash
       String input = "0 0 1 0 0 1 1 1 0 2 n n n n n n n n n n";
       InputStream originalIn = System.in;
       try {
           System.setIn(new ByteArrayInputStream(input.getBytes()));
           new TicTacToe().start();
           assertTrue(true);
       } catch (Exception e) {
           fail("start() threw unexpected exeption: " + e.getMessage());
       } finally {
           System.setIn(originalIn);
       }
    }

    @Test
    void start_completesUnsuccessfully() {
        //positive case: game ends without crash
        String input = " abc xyz 0 0 1 0 0 1 1 1 0 2 n n n n n n n n n n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            new TicTacToe().start();
            assertTrue(true);
        } catch (Exception e) {
            fail("start() threw unexpected exeption: " + e.getMessage());
        } finally {
            System.setIn(originalIn);
        }
    }


}
