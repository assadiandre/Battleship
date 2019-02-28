import java.util.ArrayList;

class Opponent extends Board {

    public Opponent() {
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

    public Boolean checkValidShot(int row, int column) {
        String[][] displayMatrix = this.getMatrix();
        if ( (row <= 4 && column <= 4) && (displayMatrix[row][column].equals(".") ) ) {
            return true;
        }
        return false;
    }

    public String hitSelf(int row, int column) {
        if ( checkHit(row,column) ) {
            this.hit(row,column);
            return "You HIT your opponent at: [" + Integer.toString(row) + "," +  Integer.toString(column) + "]!";
        }
        else {
            this.miss(row,column);
            return "You MISSED your opponent at: [" + Integer.toString(row) + "," +  Integer.toString(column) + "]";
        }
    }



}