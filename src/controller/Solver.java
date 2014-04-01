package controller;

import model.Solution;
import model.SolutionTemplate;

import java.util.ArrayList;

public class Solver {

    public static final String SOLUTIONS_NOT_AVAILABLE = "No solutions available yet. Call start() before.";
    private final SolutionTemplate template;
    private ArrayList<Solution> solutions;
    private long estimatedTime;

    public Solver(SolutionTemplate template) {
        this.template = template;
    }


    public ArrayList<Solution> start() {
        solutions = new ArrayList<Solution>();

        long startTime = System.nanoTime();

        Solution rootSolution = template.initRootSolution();
        backtracking(rootSolution);

        estimatedTime = System.nanoTime() - startTime;
        return solutions;
    }

    private void backtracking(Solution currentSolution) {
        Solution[] childSolutions = currentSolution.invokeConstraint();

        if (childSolutions != null) {
            for (Solution child : childSolutions) {
                backtracking(child);
            }
        } else {
            if (!solutions.contains(currentSolution)) {
                solutions.add(currentSolution);
            }
        }
    }

    public void printStats() {
        if (solutions == null) {
            throw new Error(SOLUTIONS_NOT_AVAILABLE);
        }

        double inSec = estimatedTime / (double) 1000000000;

        System.out.println("Done!");
        System.out.println("Number of solutions: " + solutions.size());
        System.out.println("Working time: " + inSec + "s");
        System.out.println("Total created solution attempts (= streets): " + template.getTotalSolutions());
        System.out.println("Constraints invoked: " + template.getConstraintCount());
    }
}
