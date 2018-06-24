/*  Brayden Cowell
    0844864
    CIS*3700 A1
    PuzzleProblem.java
----------------------
*/
import java.util.*;

public class PuzzleProblem extends Problem {

    // Get successors given state s.
    @SuppressWarnings("unchecked")
    public LinkedList getSuccessor(ObjectPlus s) {
        // Generate boards of next moves in LRUD
        Board board = (Board) s;
        List<Character> moves = board.genMoves();
        LinkedList ll = new LinkedList();

        for(char c: moves) {
            System.out.println("\n"+c);
            Board state = board.makeMove(c);
            state.show();
            System.out.println();
            ll.add(state);
        }
        return ll;
    }
    
    // Test if state s is a goal.
    public boolean isGoalState(ObjectPlus s) {
        Board board = (Board) s;
        if(board.isGoalState()) {
            return true;
        }
        return false;
    }
}
/*
Generate successors in the order of hole movement (Left, Right, Up, Down). 
If two nodes in fringe tie with evaluation function values (f(n)), 
they are visited in a first-in-first-out order. 
This determines how nodes are inserted into fringe.*/