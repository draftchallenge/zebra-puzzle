package model;

import java.util.ArrayList;
import java.util.List;

public class SolutionTemplate {

    public static final String TOO_LESS_VALUES_PER_CATEGORY = "There must be at least one house";

    private final CategoryList initialCategories;
    long totalSolutions = 0;
    private List<Constraint> constraints;

    public SolutionTemplate(int maxNumberOfValuesPerCategory) {
        if (maxNumberOfValuesPerCategory < 1) {
            throw new Error(TOO_LESS_VALUES_PER_CATEGORY);
        }

        this.constraints = new ArrayList<Constraint>();
        this.initialCategories = new CategoryList(maxNumberOfValuesPerCategory);
    }

    public Solution initRootSolution() {
        return new Solution(this);
    }

    public Value createValidValue(String category, String value) {
        return initialCategories.addValueAndCreateCategoryIfNotExisting(new Value(category, value));
    }

    String[] getAllCategories() {
        return initialCategories.getAllCategories();
    }

    int getMaxValuesPerCategory() {
        return initialCategories.getMaxValuesPerCategory();
    }

    public void addConstraint(Constraint constraint) {
        constraints.add(constraint);
    }

    Constraint getConstraint(int level) {
        return constraints.size() > level ? constraints.get(level) : null;
    }

    public int getConstraintCount() {
        return constraints.size();
    }

    public long getTotalSolutions() {
        return totalSolutions;
    }
}
