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

    private void switchCurrentPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private boolean hasWinner() {
        char[][] c = board.getCells();
        for (int i = 0; i < 3; ++i) {
            if (c[i][0] != ' ' && c[i][0] == c[i][1] && c[i][1] == c[i][2]) return true;
            if (c[0][i] != ' ' && c[0][i] == c[1][i] && c[1][i] == c[2][i]) return true;
        }
        if (c[0][0] != ' ' && c[0][0] == c[1][1] && c[1][1] == c[2][2]) return true;
        if (c[0][2] != ' ' && c[0][2] == c[1][1] && c[1][1] == c[2][0]) return true;
        return false;
    }
}
