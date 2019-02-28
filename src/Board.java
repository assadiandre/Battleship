public class Board {

    private String[][] displayMatrix;
    private int[][] shipLocations;

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

    public int[][] getShipLocations() {
        return this.shipLocations;
    }

    public void addToMatrix(int row, int column, String value) {
        this.displayMatrix[row][column] = value;
    }

    public void addShipLocation(int row, int column, int position) {
        this.shipLocations[position][0] = row;
        this.shipLocations[position][1] = column;
    }

    public void hit(int row, int column) {
        this.addToDisplayMatrix(row,column,"x"); //hit
    }

    public void miss(int row, int column) {
        this.addToDisplayMatrix(row,column,"o"); // didn't hit
    }

    public void printMatrix() {
        System.out.print( "  "  );
        for (int row = 0; row < 6; row++){
            if (row >= 1) {
                System.out.print(Integer.toString( row - 1 ) + " "  );
            }
            for (int column = 0; column < 5; column++){
                if (row == 0) {
                    System.out.print( Integer.toString(column) + " ");
                } else {
                    System.out.print( this.displayMatrix[row - 1][column] + " ");
                }

            }
            System.out.println();
        }
    }

    public String[][] fillMatrix(String[][] matrix) {
        for (int row = 0; row < 5; row++){
            for (int column = 0; column < 5; column++){
                if (matrix[row][column] == null){
                    matrix[row][column] = ".";
                }
            }
        }
        return matrix;
    }

    public Boolean checkHit(int row, int column){
        for (int i = 0; i < this.shipLocations.length; i++) {
            if (this.shipLocations[i][0] == row && this.shipLocations[i][1] == column) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasLost() {
        int[][] allShipLocations = this.shipLocations;
        for (int i = 0; i < allShipLocations.length; i++) {
            if ( !this.displayMatrix[ allShipLocations[i][0]][allShipLocations[i][1]].equals("x")  ) {
                return false;
            }
        }
        return true;
    }

    public static String[][] getSSMatrix( Board computerBoard, Board playerBoard ) {
        int[] startPos1 = {1,1};
        int[] startPos2 = {1,8};
        String[][] computerMatrix = computerBoard.getMatrix();
        String[][] playerMatrix = playerBoard.getMatrix();
        String[][] sideBySideMatrix = {
                {" ","0","1","2","3","4"," "," ","0","1","2","3","4"},
                {"0","","","","",""," ","0","","","","",""},
                {"1","","","","",""," ","1","","","","",""},
                {"2","","","","",""," ","2","","","","",""},
                {"3","","","","",""," ","3","","","","",""},
                {"4","","","","",""," ","4","","","","",""}
        };
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                sideBySideMatrix[row + startPos1[0] ][ column + startPos1[1] ] = playerMatrix[row][column];
                sideBySideMatrix[row + startPos2[0] ][ column + startPos2[1] ] = computerMatrix[row][column];
            }
        }
        return sideBySideMatrix;
    }


    private void addToDisplayMatrix(int row, int column, String value) {
        this.displayMatrix[row][column] = value;
    }


}




