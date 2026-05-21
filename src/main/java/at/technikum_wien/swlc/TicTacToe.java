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
        while (true) {
            board.print();
            System.out.println("Current Player: " + currentPlayer.getMarker());
            System.out.println("row (0-2): ");
            int row = sc.nextInt();
            System.out.println("column (0-2): ");
            int column = sc.nextInt();

            if (board.isCellEmpty(row, column)) {
                board.place(row, column, currentPlayer.getMarker());
            } else {
                System.out.println("Choose another cell, already occupied!");
                continue;
            }
            if (hasWinner()) {
                board.print();
                System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                break;
            }
            if (board.isFull()) {
                board.print();
                System.out.println("It's a draw!");
                break;
            }
        }
        switchCurrentPlayer();
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
