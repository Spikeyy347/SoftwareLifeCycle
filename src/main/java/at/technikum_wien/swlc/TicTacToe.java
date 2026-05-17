package at.technikum_wien.swlc;

import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe(){
        player1 = new Player('X');
        player2 = new Player('O');

        currentPlayer = player1;

        board = new Board();
    }

    public void start(){
        Scanner sc = new Scanner(System.in);

        board.print();

        System.out.println("Current player: " + currentPlayer.getMarker());

        System.out.println("row(0-2): ");
        int row = sc.nextInt();

        System.out.println("column(0-2): ");
        int column = sc.nextInt();

        if(board.isCellEmpty(row, column)){
            board.place(row, column, currentPlayer.getMarker());
        }
        else {
            System.out.println("Choose another cell, already occupied!");
        }
        board.print();
        sc.close();
    }


    private void switchCurrentPlayer(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        } else{
            currentPlayer = player1;
        }
    }

    private boolean hasWinner(){
        return false;
    }
}