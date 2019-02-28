import java.util.ArrayList;

class Player extends Board {

    private int shipCount;

    public Player( ) {
        super();
        String[][] filledMatrix = this.fillMatrix( (this.getMatrix()) );
        this.setMatrix(filledMatrix);
        this.shipCount = 0;
        this.setShipLocations( this.generateShipLocationsArray() );
    }

    public int getShipCount() {
        return this.shipCount;
    }

    public void setShipCount(int newShipCount) {
        this.shipCount = newShipCount;
    }


    public void addShiptoMatrix(int row, int column) {
        this.addToMatrix(row,column,"*");
    }

    public Boolean checkIfValidShipLocation(int row, int column) {
        if (row > 4 || column > 4) { return false; }
        int[][] shipLocations = getShipLocations();

        for (int i = 0; i < shipLocations.length; i++) {
            if ( shipLocations[i][0] == row && shipLocations[i][1] == column ) {
                return false;
            }
        }
        return true;
    }

    public String hitSelf(int row, int column) {
        if ( checkHit(row,column) ) {
            this.hit(row,column);
            return "Opponent HIT you at: [" + Integer.toString(row) + "," +  Integer.toString(column) + "]!";
        }
        else {
            this.miss(row,column);
            return "Opponent MISSED you at: [" + Integer.toString(row) + "," +  Integer.toString(column) + "]";
        }
    }

    public int[] generateHitCoordinates() {
        Boolean foundPosition = false;
        while (!foundPosition) {
            int randomRow = (int) (Math.random() * 4) + 1;
            int randomColumn = (int) (Math.random() * 4) + 1;
            String[][] matrix = this.getMatrix();
            String chosenString = matrix[randomRow][randomColumn];

            if ( !chosenString.equals("x") && !chosenString.equals("o") ) {
                int[] returnValue = {randomRow, randomColumn};
                foundPosition = true;
                return returnValue;
            }

        }

        int[] returnValue = {0, 0};
        return returnValue;
    }


    private int[][] generateShipLocationsArray() {
        int[][] prelimShipLocations =  new int[4][2];
        for (int i = 0; i < prelimShipLocations.length; i++) {
            prelimShipLocations[i][0] = -1;
            prelimShipLocations[i][1] = -1;
        }
        return prelimShipLocations;
    }


}