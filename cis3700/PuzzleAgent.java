/*  Brayden Cowell
    0844864
    CIS*3700 A1
    PuzzleAgent.java
----------------------
*/
import java.io.*;
import java.util.*;

public class PuzzleAgent extends SearchAgent {

    public void showSolution() {
        LinkedList solution = super.solution;
        System.out.println("\nSolution path:"+solution.toString());
    }

    public void showTree() {
        // Print g,h,f
        //System.out.println(Arrays.toString(super.tree));
    }

    public void insertFringe(Node nd, LinkedList<Node> ll) {
        ll.add(nd);
    }

    public static int[][] build2DArrayFrom1DArray(int[] arr1) {
        int n = 0;
        int[][] arr2 = new int[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (n == arr1.length) break;
                arr2[i][j] = arr1[n];
                n++;
            }
        }
        return arr2;
    }

    public static void parseFile(String filename, PuzzleProblem problem) throws Exception {
        
        int[][] init = new int[3][3];
        int[][] goal = new int[3][3];

        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        int c,k = 0;
        int[] initArr = new int[9];
        int[] goalArr = new int[9];

        while ((c = buffer.read()) != -1){
            char character = (char) c;
            if (Character.isDigit(character)) {
                if (k < 9) {
                    initArr[k] = character-'0';
                } else {
                    goalArr[k%9] = character-'0';
                }
                k++;
            }
        }
        //System.out.println(Arrays.toString(initArr));
        //System.out.println(Arrays.toString(goalArr));
        
        buffer.close();
        init = build2DArrayFrom1DArray(initArr);
        goal = build2DArrayFrom1DArray(goalArr);
        Board start = new Board(init, goal);
        Board end = new Board(goal, goal);
    
        problem.setInitialState(start);
        problem.setGoalState(end);
    }

    public static void main(String[] args) {
        
        PuzzleAgent pAgent = new PuzzleAgent();
        PuzzleProblem pProb = new PuzzleProblem();

        try {
            parseFile(args[0], pProb);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        pAgent.setProblem(pProb);
        pAgent.search();
        pAgent.showTree();
        pAgent.showSolution();
    }
}
