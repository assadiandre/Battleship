import java.util.Scanner;

public class Game {

    Opponent computerBoard;
    Player playerBoard;
    Scanner scanner;
    Boolean gameOver = false;
    Boolean playerTurn = true;

    public Game() {
        computerBoard = new Opponent();
        playerBoard = new Player();
        scanner = new Scanner(System.in);
    }

    public void start() {
        promptPlaceShips();
        mainGameLoop();
    }


    private void promptPlaceShips() {
        System.out.println("Welcome to Battleship!");
        System.out.println("Before we start, you need to place your ships.");
        System.out.println("Your board:");
        playerBoard.printMatrix();
        printDelimiter();

        int shipCount = playerBoard.getShipCount();
        while ( shipCount < 4) {
            System.out.println("Please enter ship coordinate " + Integer.toString(shipCount + 1) +  "/4: ");
            System.out.print("Enter Row (0 - 4):");
            int row = scanner.nextInt();
            System.out.print("Enter Column (0 - 4):");
            int column = scanner.nextInt();

            if ( playerBoard.checkIfValidShipLocation(row,column) ) {
                playerBoard.addShipLocation(row,column,shipCount);
                playerBoard.addShiptoMatrix(row,column);
                playerBoard.setShipCount( shipCount++ );
                playerBoard.printMatrix();
                printDelimiter();
            }
            else {
                System.out.println("Oops! The ship location you entered has already been chosen or is invalid.");
            }
        }
    }

    private void mainGameLoop() {
        System.out.println("Great, the game is now going to Start");
        while ( !gameOver ) {
            if ( computerBoard.hasLost() ) {
                System.out.println();
                System.out.println("YOU WON! COMPUTER LOST.");
                gameOver = true;
                break;
            }
            else if ( playerBoard.hasLost() ) {
                System.out.println();
                System.out.println("YOU LOST! COMPUTER WON.");
                gameOver = true;
                break;
            }
            else {
                if (playerTurn) {
                    System.out.println("Your Turn!");
                    System.out.println();
                    printBothBoards();
                    int[] shootCoordinates = promptUserShot();
                    int row = shootCoordinates[0];
                    int column = shootCoordinates[1];
                    if ( computerBoard.checkValidShot(row,column) ){
                        String response = computerBoard.hitSelf(row,column);
                        System.out.println();
                        computerBoard.printMatrix();
                        System.out.println(response);
                        promptEnter(2);
                        printDelimiter();
                        playerTurn = false;
                    }
                    else {
                        System.out.println("Oops! The ship location you entered has already been chosen or is invalid.");
                        printDelimiter();
                    }
                }
                else {
                    System.out.println("Opponent Turn!");
                    System.out.println();
                    System.out.println("Your Board:");
                    int[] shootCoordinates = playerBoard.generateHitCoordinates();
                    int row = shootCoordinates[0];
                    int column = shootCoordinates[1];
                    String response = playerBoard.hitSelf(row,column);
                    playerBoard.printMatrix();
                    System.out.println(response);
                    promptEnter(1);
                    printDelimiter();
                    playerTurn = true;
                }
            }
        }
    }

    private void promptEnter(int enterCount) {
        System.out.println();
        System.out.println("Press 'enter' key to continue.");
        for (int i = 0; i < enterCount; i++) {
            scanner.nextLine();
        }
    }

    private int[] promptUserShot() {
        System.out.println();
        System.out.print("Enter Row to Shoot (0 - 4):");
        int row = scanner.nextInt();
        System.out.print("Enter Column to Shoot (0 - 4):");
        int column = scanner.nextInt();
        int[] shotCoordinates = {row,column};
        return shotCoordinates;
    }


    private void printDelimiter() {
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    private void printBothBoards(){
        String[][] SSMatrix = Board.getSSMatrix(computerBoard, playerBoard);

        System.out.println(" Your Board:   Comp Board:");
        for (int i = 0; i < SSMatrix.length; i++) {
            for (int j = 0; j < SSMatrix[0].length;  j++) {
                System.out.print(SSMatrix[i][j] + " " );
            }
            System.out.println();
        }

    }









}
