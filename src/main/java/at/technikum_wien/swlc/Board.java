package at.technikum_wien.swlc;

public class Board {
    private char[][] cells;

    public Board(){
        cells = new char[3][3];
    }

    public boolean isCellEmpty(int x, int y){
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker){
        cells[x][y] = marker;
    }

    public boolean isFull(){
        return false;
    }

    public void clear(){

    }

    public void print(){
        System.out.println("_______");
        for( int i = 0 ; i<3; i++){
            System.out.print("|");
            for(int j = 0; j<3; j++){
                System.out.print(cells[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("_______");
    }
}

