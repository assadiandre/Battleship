public class Runner {


    public static void main(String[] args) {
        ComputerBoard myBoard = new ComputerBoard();
        myBoard.printMatrix();
        myBoard.hit(1,0);
        myBoard.printMatrix();
        myBoard.hit(2,0);
        myBoard.printMatrix();

    }


}
