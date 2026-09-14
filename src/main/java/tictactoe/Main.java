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
        Board board = new Board();

        boolean terminalState = false;

        char currPlayer = 'X';
        //Printing initial board on screen:
        board.printBoard(board.getState());

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
                if (board.getState()[row][col] != ' '){
                    System.out.println("Position is already being used. Try another position");
                    continue;
                }
                else {
                    //asigning an X or O to an available spot
                    board.setState(row, col, currPlayer);
                }
            }

            //valid change of players is only if inputed valid position for value
            if (currPlayer == 'X'){
                currPlayer = 'O';
            } else {
                currPlayer = 'X';
            }

            //Printing updated board on console:
            board.printBoard(board.getState());

            //testing terminal state
            System.out.println();
            terminalState = board.verifyState(board.getState());

        }

    }

}
