package tictactoe;

public class Board {

    //Creating 2d array
    char[][] state = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public Board() {

    }

    public char[][] getState(){
        return this.state;
    }

    public void setState(int row, int col, char currPlayer){
        this.state[row][col] = currPlayer;
    }

    //creating board methods
    public void printBoard(char[][] board){
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

    public void moveCursor(int n){
        //going up with cursor to ovewrite previous table
        System.out.print("\u001B[" + n + "A");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //Checking board terminal states
    public String checkHorizontal(char[][] board){
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

    public String checkVertical(char[][] board){
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

    public String checkDiagonal(char[][] board){
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

    public boolean checkFullBoard(char[][] board){
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
    public boolean verifyState(char[][] board){
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
