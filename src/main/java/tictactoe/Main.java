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
        Minimax minmax = new Minimax();

        boolean terminalState = false;

        char currPlayer = 'X';
        //Printing initial board on screen:
        board.printBoard(board.getState());

        while(!terminalState){
            //state current board
            char[][] boardState = board.getState();

            System.out.println("Current turn is: " + currPlayer);
            if (currPlayer == 'X') {
                System.out.println("Choose a position (1-9): ");
                int position = input.nextInt();


                //validating position inputed by user
                boolean validPosition;
                if (position < 1 || position > 9) {
                    validPosition = false;
                } else {
                    validPosition = true;
                }
                //if not valid position, do a loop asking for correct value
                while (!(validPosition)) {
                    System.out.println("The number is not valid, please try another one ");
                    position = input.nextInt();
                    if (position >= 1 && position <= 9) {
                        validPosition = true;
                    }
                }

                if (validPosition) {
                    //Obtaining the value of row
                    int row = (int) ((position - 1) / 3);
                    //Obtaining the value of column
                    int col = (position - 1) % 3;

                    //validation of cells not already used.
                    if (boardState[row][col] != ' ') {
                        System.out.println("Position is already being used. Try another position");
                        continue;
                    } else {
                        //Asigning an X
                        board.setState(row, col, currPlayer);
                    }
                }
            }
            else if (currPlayer == 'O'){
                int[] recordedAction = minmax.selectedAction(board);
                board.setState(recordedAction[0], recordedAction[1],currPlayer);
            }
            //Valid change of players is only if inputed valid position for value
            if (currPlayer == 'X'){
                currPlayer = 'O';
            } else {
                currPlayer = 'X';
            }

            //Printing updated board on console:
            board.printBoard(boardState);

            //testing terminal state
            System.out.println();
            terminalState = board.verifyState(boardState);
        }
        String winner = board.checkWinner(board.getState());
        if (!("draw".equals(winner))){
            System.out.println("The winner is " + winner);
        }
        else{
            System.out.println("It's a draw");
        }

    }

}
