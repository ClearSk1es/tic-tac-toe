package tictactoe;

import java.util.ArrayList;

public class Minimax {

    Board board;
    private final char MAX_PLAYER = 'X';
    private final char MIN_PLAYER = 'O';


    public Minimax() {
    }

    public Minimax(Board board) {
        this.board = board;
    }

    public int minimax(Board board) {
        int value = -3;
        char[][] state = board.getState();
        char currentPlayer = player(board);

        if (board.verifyState(state)) {
            return stateValue(board);
        }

        if (currentPlayer == MAX_PLAYER) {
            //Maximize
            value = -2;
            for (int[] a : actions(board)){
                value = maxVal(value, minimax(result(board,a)));
            }
            return value;
        }

        if (currentPlayer == MIN_PLAYER) {
            //Minimze
            value = 2;
            for (int[] a : actions(board)){
                value = minVal(value, minimax(result(board,a)));
            }
            return value;
        }

        return value;
    }

    public int stateValue(Board board){
        int value = 0;
        char[][] state = board.getState();
        String winner = board.checkWinner(state);

        if (winner != null){
            char w = winner.charAt(0);
            if (w == 'X'){
                value = 1;
            } else if (w == 'O') {
                value = -1;
            }
        }
        return value;
    }

    public int maxVal(int currVal, int minimaxReturn){
        if (minimaxReturn > currVal){
            return minimaxReturn;
        }
        return currVal;
    }

    public int minVal(int currVal, int minimaxReturn){
        if (minimaxReturn < currVal){
            return minimaxReturn;
        }
        return currVal;
    }

    public char player(Board board){
        int xCounter = 0;
        int oCounter = 0;
        char[][] state = board.getState();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if(state[row][col] != ' '){
                    if(state[row][col] == 'X'){
                        xCounter += 1;
                    }
                    else{
                        oCounter += 1;
                    }
                }
            }
        }
        if(xCounter > oCounter){
            return MIN_PLAYER;
        }
        return MAX_PLAYER;
    }

    public ArrayList<int[]> actions(Board board){
        //Using arraylist as the length of the collection will depend on state
        ArrayList<int[]> possibleActions =new ArrayList<>();
        char[][] state = board.getState();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //Review if position in board is empty
                if(state[row][col] == ' '){
                    //If position is empty, it's a possible action to take in algorithm
                    //Creating anonym array and assigning respective values in one line
                    possibleActions.add(new int[]{row,col});
                }
            }
        }
        //Returns an arraylist populated with the empty positions and possible actions that can be taken in a board state
        return possibleActions;
    }

    public Board result(Board board, int[] action){
        char[][] state = board.getState();
        Board copyBoard = new Board();

        //assigning a new value in action-position to presented state
        int row = action[0];
        int column = action[1];
        //create a copy of the evaluating array as to not affect the original one
        //initialize it with its respective length of rows
        char[][] copiedState = new char[state.length][];

        //Clone each row in the copiedState array
        for (int i = 0; i < state.length; i++) {
            copiedState[i] = state[i].clone();
        }
        copiedState[row][column] = player(board);

        copyBoard.copy(copiedState);
        return copyBoard;
    }

}
