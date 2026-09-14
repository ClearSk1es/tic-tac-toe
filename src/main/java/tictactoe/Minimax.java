package tictactoe;

import java.util.ArrayList;

public class Minimax {

    Board board;
    private final char MAX_PLAYER = 'X';
    private final char MIN_PLAYER = 'O';
    char currentPlayer;


    public Minimax() {
    }

    public Minimax(Board board, char currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public int minimax(Board board){
        int value = -3;
        char[][] state = board.getState();
        if (board.verifyState(state)){
            return 0;
        }

        if (currentPlayer == MAX_PLAYER){
            //Maximize
            int value = -2;
            return value;
        }

        if (currentPlayer == MIN_PLAYER){
            //Minimze
            int value = 2;
            
            return value;
        }
        //Check player turn

        return value;
    }


    public String player(Board board){
        int maxCounter = 0;
        int minCounter = 0;
        char[][] state = board.getState();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if(state[row][col] != ' '){
                    if(state[row][col] == 'X'){
                        maxCounter += 1;
                    }
                    else{
                        minCounter += 1;
                    }
                }
            }
        }
        if(maxCounter >= minCounter){
            return "MAX";
        }
        return "MIN";
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
        //Returns an arraylist populated with the empty possitions and possible actions that can be taken in a board state
        return possibleActions;
    }

    public char[][] result(Board board, int[] action, char currentPlayer){
        char[][] state = board.getState();

        //assigning a new value in action-position to presented state
        int row = action[0];
        int column = action[1];
        state[row][column] = currentPlayer;

        return state;
    }

    public int stateValue(){

        return 0;
    }

}
