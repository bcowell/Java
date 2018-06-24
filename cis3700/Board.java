/*  Brayden Cowell
    0844864
    CIS*3700 A1
    Board.java
----------------------
*/
import java.util.*;

class Board extends ObjectPlus {
    
    int board[][];
    int goal[][];

    // Store where current hole is, i.e. board[holeRow][holeCol] = 0
    private int holeRow;
    private int holeCol;
    // Previous move i.e. "U","L","D","R"
    char prevMove;
    
    // Constructor
    public Board(int[][] puzzle, int[][] goal) {
        this.board = new int[3][3];
        this.goal = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == 0) {
                    holeRow = i;
                    holeCol = j;
                }
                this.board[i][j] = puzzle[i][j];
                this.goal[i][j] = goal[i][j];
            }
        }
    }

    // Determine new board state from legal move
    public Board makeMove(char move) {
        int[][] copy = deepCopy(this.board);
        int square;
        List<Character> legalMoves = genMoves();

        if (legalMoves.contains(move)) {
            if (move == 'U') {
                // swap hole with row-1
                square = copy[holeRow-1][holeCol];
                copy[holeRow][holeCol] = square;
                copy[holeRow-1][holeCol] = 0;
            }
            else if (move == 'D') {
                // swap hole with row+1
                square = copy[holeRow+1][holeCol];
                copy[holeRow][holeCol] = square;
                copy[holeRow+1][holeCol] = 0;
            }
            else if (move == 'L') {
                // swap hole with col-1
                square = copy[holeRow][holeCol-1];
                copy[holeRow][holeCol] = square;
                copy[holeRow][holeCol-1] = 0;
            }
            else if (move == 'R') {
                // swap hole with col+1
                square = copy[holeRow][holeCol+1];
                copy[holeRow][holeCol] = square;
                copy[holeRow][holeCol+1] = 0;
            }
            else {
                System.out.println("Error trying to make invalid move!");
            }
        }
        Board b = new Board(copy, this.getGoalState());
        return b;
    }

    // Return all possible legal moves at current state
    public List<Character> genMoves() {
        List<Character> moves = new ArrayList<Character>();
        if (isLegalMove('L')) moves.add('L');
        if (isLegalMove('R')) moves.add('R');
        if (isLegalMove('U')) moves.add('U');
        if (isLegalMove('D')) moves.add('D');
        return moves;
    }
    

    // Manhattan heuristic
    // Determine the heuristic function value of current state
    // i.e. num moves to goal state
    public int heuristic() {
        int square;
        int total = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square = board[i][j];
                if (square != 0) {
                    if (square != goal[i][j]) {
                        total += 1;
                    }
                }
            }
        }
        return total;
    }

    // Manhattan heuristic
    // Sum of distance of tiles at state n from their goal positions.
    public int manhattan() {
        int square;
        int total = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square = board[i][j];
                if (square != 0) {
                    total += manhattanDistance(square, i, j);
                }
            }
        }
        return total;
    }

    // Determine the displacement of a square at x,y
    // to it's goalX, goalY
    private int manhattanDistance(int square, int currRow, int currCol) {
        int goalRow = 0;
        int goalCol = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goal[i][j] == square) {
                    goalRow = i;
                    goalCol = j;
                }
            }
        }
        return Math.abs(goalRow - currRow) + Math.abs(goalCol - currCol);
    }

    public static int[][] deepCopy(int[][] original) {
    if (original.length < 1) {
        return null;
    }

    final int[][] result = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
        result[i] = Arrays.copyOf(original[i], original[i].length);
        // For Java versions prior to Java 6 use the next:
        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
    }
    return result;
}

    // Checks if the current board is in the goal state
    public boolean isGoalState() {
        return Arrays.deepEquals(this.board, this.goal);
    }

    // Test whether move is legal
    public boolean isLegalMove(char move) {
        if (move == 'U') {
            if (holeRow > 0) {
                return true;
            }
        }
        else if (move == 'D') {
            if (holeRow < 2) {
                return true;
            }
        }
        else if (move == 'L') {
            if (holeCol > 0) {
                return true;
            }
        }
        else if (move == 'R') {
            if (holeCol < 2) {
                return true;
            }
        } else {
            System.out.println("Invalid move please use U,D,L,R.");
            return false;
        }
        return false;
    }

    public int[][] getState() {
        return this.board;
    }

    public int[][] getGoalState() {
        return this.goal;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setPrevMove(char move) {
        this.prevMove = move;
    }

    // Prints the current board configuration to screen
    public void show() {
        int x,y;
        for (x=0; x<3; x++){ 
            for (y=0; y<3; y++) {
                System.out.print(this.board[x][y]);
            }
            System.out.println();
        }
    }

    public void showPart(int index) {
        int row = index/3;
        int col = (index-1)%3;
        System.out.println(board[row][col]);
    }

/* Board should include methods that test whether a state is the goal, 
test whether a move is legal, 
determine the new state from a legal move, 
and determine the heuristic function value of a state. 
*/

}