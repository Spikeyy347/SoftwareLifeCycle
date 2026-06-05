package at.technikum_wien.swlc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
        //isCellEmpty

        @Test
        void isCellEmpty_positive_emptyReturnsTrue() {
            Board board = new Board();
            board.place(0, 0, ' ');
            assertTrue(board.isCellEmpty(0, 0));
        }

        @Test
        void isCellEmpty_negative_emptyReturnsFalse() {
            Board board = new Board();
            board.place(1, 1, 'X');
            assertFalse(board.isCellEmpty(1, 1));
        }

        //place
        @Test
        void place_positive_markerPlaced() {
            Board board = new Board();
            board.place(0, 0, 'X');
            assertEquals('X', board.getCells()[0][0]);
        }

        @Test
        void place_negative_markerNotPlaced() {
            Board board = new Board();
            board.place(0, 0, 'X');
            assertNotEquals('O', board.getCells()[0][0]);
        }

        //isFull
        @Test
        void isFull_positive_FullReturnsTrue(){
            Board board = new Board();
            char[] markers = {'X', 'O'};
            for(int i = 0; i <3; i++){
                for(int j = 0; j<3; j++){
                    board.place(i, j, markers[(i *3 +j) % 2]);
                }
            }
            assertTrue(board.isFull());
        }
        @Test
        void isFull_negative_FullReturnsFalse(){
            Board board = new Board();
            board.place(0, 0,'X');
            assertFalse(board.isFull());
        }

        //getCells
        @Test
        void getCells_positive_Returns3x3(){
            Board board = new Board();
            char[][] cells = board.getCells();
            assertEquals(3, cells.length);
            assertEquals(3, cells[0].length);
        }

        @Test
        void getCells_negative_changedReferencesChangesOriginal(){
            Board board = new Board();
            board.getCells()[0][0] = 'X';
            assertEquals('X', board.getCells()[0][0],
                    "getCells gibt Referenz zurück!");
        }

        //clear
        @Test
        void clear_positive_allCellsAreEmpty(){
            Board board = new Board();
            board.place(0,0,'X');
            board.place(1,1,'O');
            board.clear();
            for(int i = 0; i <3; i++){
                for(int j = 0; j<3; j++){
                    assertEquals(' ', board.getCells()[i][j]);
                }
            }
        }

        @Test
        void clear_negative_CellsNotClear(){
            Board board = new Board();
            for(int i = 0; i <3; i++){
                for(int j = 0; j<3; j++){
                    board.place(i,j, 'X');
                }
            }
            board.clear();
            assertFalse(board.isFull());
        }




    }


