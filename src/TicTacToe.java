import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        ArrayList<Integer> history = new ArrayList<Integer>();

        printGameBoard(gameBoard);
        makeTurn(history, gameBoard);

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row: gameBoard) {
            for (char col: row) {
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public static void checkWinner(char[][] gameBoard) {
        // win conditions
        boolean row1 = gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[0][2] && gameBoard[0][2] == gameBoard[0][4];
        boolean row2 = gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[2][4];
        boolean row3 = gameBoard[4][0] != ' ' && gameBoard[4][0] == gameBoard[4][2] && gameBoard[4][2] == gameBoard[4][4];
        boolean col1 = gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[2][0] && gameBoard[2][0] == gameBoard[4][0];
        boolean col2 = gameBoard[0][2] != ' ' && gameBoard[0][2] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[4][2];
        boolean col3 = gameBoard[0][4] != ' ' && gameBoard[0][4] == gameBoard[2][4] && gameBoard[2][4] == gameBoard[4][4];
        boolean cross1 = gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[4][4];
        boolean cross2 = gameBoard[0][4] != ' ' && gameBoard[0][4] == gameBoard[2][2] && gameBoard[2][2] == gameBoard[4][0];

        boolean[] winner = {row1, row2, row3, col1, col2, col3, cross1, cross2};

        for (int i = 0; i < winner.length; i++) {
            if (winner[i] == true) {
                displayWinner();
            }
        }

    }
    public static void displayWinner() {
        System.out.println("SOMEONE WON!!");
        System.exit(0);
    }

    public static void checkFullBoard(ArrayList history){
        if (history.size() >= 9){
            System.out.println("DRAW");
            System.exit(0);
        }
    }

    public static void makeTurn(ArrayList history, char[][] gameBoard){
        getMove(history, gameBoard);
        cpuMove(history, gameBoard);
        makeTurn(history, gameBoard);
        System.out.println(history.toString());
    }

    public static void cpuMove(ArrayList history, char[][] gameBoard) {
        int rand = (int) (Math.random()*9) + 1;

        while(history.contains(rand)) {
            rand = (int) (Math.random()*9) + 1;
        }
        history.add(rand);
        System.out.println(rand);
        placePiece(gameBoard, rand, "cpu");
        checkFullBoard(history);
        checkWinner(gameBoard);
    }

    public static void getMove(ArrayList history, char[][] gameBoard) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your placement (1-9): ");
        int pos = scan.nextInt();

        if (history.contains(pos)) {
            System.out.println("This move is already used \nPlease Enter another move.");
            getMove(history, gameBoard);
        } else {
            history.add(pos);
            placePiece(gameBoard, pos, "player");
        }
        checkFullBoard(history);
        checkWinner(gameBoard);
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = 'X';
        if (user.equals("cpu")){
            symbol = 'O';
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
        printGameBoard(gameBoard);
    }
}
