package at.technikum_wien.swlc;

import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            board.clear();
            currentPlayer = player1;
            boolean gameEnded = false;

            while (!gameEnded) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                board.print();

                int row = -1;
                int column = -1;
                boolean validMove = false;

                while (!validMove) {
                    System.out.print("row (0-2): ");
                    if (sc.hasNextInt()) {
                        row = sc.nextInt();
                    } else {
                        System.out.println("Invalid input. Please enter a number between 0 and 2.");
                        sc.next();
                        continue;
                    }

                    System.out.print("column (0-2): ");
                    if (sc.hasNextInt()) {
                        column = sc.nextInt();
                    } else {
                        System.out.println("Invalid input. Please enter a number between 0 and 2.");
                        sc.next();
                        continue;
                    }

                    if (row >= 0 && row < 3 && column >= 0 && column < 3) {
                        if (board.isCellEmpty(row, column)) {
                            validMove = true;
                        } else {
                            System.out.println("Choose another cell, already occupied!");
                        }
                    } else {
                        System.out.println("Invalid coordinates. Row and column must be between 0 and 2.");
                    }
                }

                board.place(row, column, currentPlayer.getMarker());

                if (hasWinner()) {
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameEnded = true;
                } else if (board.isFull()) {
                    board.print();
                    System.out.println("The game is a draw!");
                    gameEnded = true;
                } else {
                    switchCurrentPlayer();
                }
            }

            System.out.print("Do you want to play again? (y/n): ");
            String choice = sc.next().trim().toLowerCase();
            if (!choice.equals("y") && !choice.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Thanks for playing!");
        sc.close();
    }

    public void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public boolean hasWinner() {
        char m = currentPlayer.getMarker();
        
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board.getCell(i, 0) == m && board.getCell(i, 1) == m && board.getCell(i, 2) == m) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board.getCell(0, j) == m && board.getCell(1, j) == m && board.getCell(2, j) == m) {
                return true;
            }
        }
        
        // Check diagonals
        if (board.getCell(0, 0) == m && board.getCell(1, 1) == m && board.getCell(2, 2) == m) {
            return true;
        }
        if (board.getCell(0, 2) == m && board.getCell(1, 1) == m && board.getCell(2, 0) == m) {
            return true;
        }
        
        return false;
    }
}