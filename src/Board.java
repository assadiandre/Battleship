import java.util.ArrayList;

public class Board {

    private String[][] matrix;
    private int[] spotsHit;

    public Board(int size) {
        this.matrix = new String[5][5];
    }

    public void setMatrix(String[][] newMatrix){
        this.matrix = newMatrix;
    }

    public String[][] getMatrix() {
        return this.matrix;
    }

    public Boolean checkHit(int row, int column) {
        if (this.matrix[row][column].equals("<>") )  {
            return true;
        }
        return false;
    }

    public Boolean hitPlayer(int row, int column) {
        return true;
    }

    public void printMatrix() {
        for (int row = 0; row < 5; row++){
            for (int column = 0; column < 5; column++){
                System.out.println(this.matrix[row][column]);
            }
        }
    }


}


class ComputerBoard extends Board {

    public ComputerBoard() {
        super(5);
        String[][] matrixWithShips = this.automaticPlaceShips(this.getMatrix());
        this.setMatrix(matrixWithShips);
    }

    public String[][] automaticPlaceShips(String[][] matrix) {
        String[][] returnMatrix = matrix;
        ArrayList<Integer> allPositions = generateRandomPositons();
        for (int i = 0; i < allPositions.size(); i++) {
            int number = allPositions.get(i);
            int column = ( number % 5) - 1 ;
            int row = (number /5) ;

            if (column < 0 ) {
                row = row - 1;
                column = 4;
            }
            returnMatrix[row][column] = "<>";
        }
        return returnMatrix;
    }

    public ArrayList<Integer> generateRandomPositons() {
        ArrayList<Integer> positionArray  = new ArrayList<Integer>();

        while ( positionArray.size() < 4) {
            int randomPositon = (int) (Math.random() * 25) + 1 ;

            if ( !positionArray.contains(randomPositon) ){
                positionArray.add(randomPositon);
            }
        }

        return positionArray;
    }


}

class PlayerBoard extends Board {

    public PlayerBoard() {
        super(5);
    }
}

