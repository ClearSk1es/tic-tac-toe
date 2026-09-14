package tictactoe;

import java.util.Scanner;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args)

    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to tic-tac-toe");
        System.out.println("Let's play");
        System.out.println("======================");

        //Creating 2d array
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        boolean terminalState = false;

        char currPlayer = 'X';
        //Printing initial board on screen:
        printBoard(board);

        while(!terminalState){
            System.out.println("Current turn is: " + currPlayer);
            System.out.println("Choose a position (1-9): ");
            int position = input.nextInt();


            //validating position inputed by user
            boolean validPosition;
            if (position < 1 || position > 9){
                validPosition = false;
            }else {
                validPosition = true;
            }
            //if not valid position, do a loop asking for correct value
            while(!(validPosition)){
                System.out.println("The number is not valid, please try another one ");
                position = input.nextInt();
                if(position >= 1 && position <= 9){
                    validPosition = true;
                }
            }

            if(validPosition){
                //Obteniendo el valor de la fila a partir de operacion con input de usuario
                int row = (int)((position - 1) / 3);
                //Obteniendo el valor de la columna a partir de operacion con input de usuario
                int col = (position - 1) % 3;

                //validation of cells not already used.
                if (board[row][col] != ' '){
                    System.out.println("Position is already being used. Try another position");
                    continue;
                }
                else {
                    //asigning an X to an available spot
                    board[row][col] = currPlayer;
                }
            }

            //valid change of players is only if inputed valid position for value
            if (currPlayer == 'X'){
                currPlayer = 'O';
            } else {
                currPlayer = 'X';
            }

            //Printing updated board on console:
            printBoard(board);

            //testing terminal state
            System.out.println();
            terminalState = verifyState(board);

        }

    }

    //Creating board functions
    public static void printBoard(char[][] board) {

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.printf(" %c ", board[row][col]);
                if (col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (row < 2) {
                System.out.println("-----------");
            }
        }
    }

    public static void moveCursor(int n){
        //going up with cursor to ovewrite previous table
        System.out.print("\u001B[" + n + "A");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String checkHorizontal(char board[][]){
        String line = "";

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                line += board[row][col];
            }
            //before advancing to next row, check if current row has 3 of the same value
            if ("OOO".equals(line)){
                //returning winner
                return "O";
            }
            else if("XXX".equals(line)){
                //returning winner
                return "X";
            }
            //if row is valid for win, return early, if not empty string and try next row
            line = "";
        }
        return null;
    }

    public static String checkVertical(char board[][]){
        String line = "";

        //using while because i want to modify the loop from the exterior
        //because i want to traverse it in reverse of normal way
        int counter = 0;
        while(counter < 3){
            //traversing only the columns of the 2D array
            for (int row = 0; row < 3; row++) {
                for (int col = counter; col <= counter; col++) {
                    line += board[row][col];
                }
            }
            if ("OOO".equals(line)){
                return "O";
            }
            else if("XXX".equals(line)){
                return "X";
            }
            line = "";

            counter++;
        }
        return null;
    }

    public static String checkDiagonal(char board[][]){
        String leftDia = "";
        String rightDia = "";

        //checking left-sided diagonal
        int counter = 0;
        while(counter < 3){
            //getting every value for left-sided diagonal
            for (int row = counter; row <= counter; row++) {
                for (int col = counter; col <= counter; col++) {
                    leftDia += board[row][col];
                }
            }
            counter++;
        }
        if ("OOO".equals(leftDia)){
            return "O";
        }
        else if("XXX".equals(leftDia)){
            return "X";
        }

        //checking right-sided diagonal
        int upCounter = 0;
        int downCounter = 2;
        while(downCounter >= 0){
            //this counter goes up so the rows go in order
            for (int row = upCounter; row <= upCounter; row++) {
                //this counter goes in reverse to get the right side diagonal
                for (int col = downCounter; col >= downCounter; col--) {
                    rightDia += board[row][col];
                }
            }
            upCounter++;
            downCounter--;
        }
        if ("OOO".equals(rightDia)){
            return "O";
        }
        else if("XXX".equals(rightDia)){
            return "X";
        }
        return null;
    }

    public static boolean checkFullBoard(char board[][]){
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    //verify if state is terminal
    public static boolean verifyState(char board[][]){
        //call every check function, if any of them have a winner, the game is in terminal state
        String winner = checkHorizontal(board);
        if(winner != null){
            System.out.println("Winner is: " + winner);
            return true;
        }

        winner = checkVertical(board);
        if(winner != null){
            System.out.println("Winner is: " + winner);
            return true;
        }

        winner = checkDiagonal(board);
        if(winner != null){
            System.out.println("Winner is: " + winner);
            return true;
        }

        if(checkFullBoard(board)){
            System.out.println("It's a draw...");
            return true;
        }
        return false;
    }

}
