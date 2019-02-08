import java.util.ArrayList;

public class Board {

    private String[][] displayMatrix;
    private int[][] shipLocations;
    private int[] spotsHit;

    public Board() {
        this.displayMatrix = new String[5][5] ;
    }

    public void setMatrix(String[][] newMatrix){
        this.displayMatrix = newMatrix;
    }


    public void setShipLocations(int[][] shipLocations) {
        this.shipLocations = shipLocations;
    }

    public String[][] getMatrix() {
        return this.displayMatrix;
    }

    public Boolean checkHit(int row, int column){
        for (int i = 0; i < this.shipLocations.length; i++) {
            if (this.shipLocations[i][0] == row && this.shipLocations[i][1] == column) {
                return true;
            }
        }
        return false;
    }

    public Boolean hit(int row, int column) {
        if ( checkHit(row,column) ) {
            this.displayMatrix[row][column] = "x";
            return true;
        }else {
            this.displayMatrix[row][column] = "*";
            return false;
        }

    }

    public void printMatrix() {
        System.out.println();
        for (int row = 0; row < 5; row++){
            for (int column = 0; column < 5; column++){
                System.out.print( this.displayMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    public String[][] fillMatrix(String[][] matrix) {
        for (int row = 0; row < 5; row++){
            for (int column = 0; column < 5; column++){
                if (matrix[row][column] == null){
                    matrix[row][column] = "~";
                }
            }
        }
        return matrix;
    }


}


class ComputerBoard extends Board {

    public ComputerBoard() {
        super();
        String[][] filledMatrix = this.fillMatrix( (this.getMatrix()) );
        this.setMatrix(filledMatrix);
        this.setShipLocations( this.getShipCoordinates() );
    }

    public int[][] getShipCoordinates() {
        ArrayList<Integer> allPositions = generateRandomPositons();
        int[][] returnLocations = new int[allPositions.size()][2];
        for (int i = 0; i < allPositions.size(); i++) {
            int number = allPositions.get(i);
            int column = ( number % 5) - 1 ;
            int row = (number /5) ;

            if (column < 0 ) {
                row = row - 1;
                column = 4;
            }
            returnLocations[i][0] = row;
            returnLocations[i][1] = column;
        }
        return returnLocations;
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
        super();
    }
}

