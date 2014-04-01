package model;

import java.util.ArrayList;

public class Constraint {

    static final String TOO_LESS_CONDITIONS = "Too less conditions";
    private TYPE type;
    private Value premises[];
    private Value conclusion;

    public Constraint(String type, Value[] conditions) throws Exception {
        this.type = TYPE.valueOf(type.toUpperCase());

        if (conditions.length < 1 || conditions[0] == null) {
            throw new Exception(TOO_LESS_CONDITIONS);
        }

        if (conditions.length == 1) {
            conditions = new Value[]{null, conditions[0]};
        }

        this.premises = new Value[conditions.length - 1];
        System.arraycopy(conditions, 0, this.premises, 0, premises.length);
        this.conclusion = conditions[conditions.length - 1];
    }

    Solution[] evaluate(Solution solution) {
        ArrayList<Solution> results = null;
        switch (type) {
            case SAME:
                results = same(solution);
                break;
            case NEXT_TO:
                results = next(solution);
                break;
            case TO_THE_LEFT_OF:
                results = left(solution);
                break;
            case TO_THE_RIGHT_OF:
                results = right(solution);
                break;
        }

        return results.toArray(new Solution[results.size()]);
    }

    private ArrayList<Solution> right(Solution initialSolution) {
        ArrayList<Solution> results = new ArrayList<Solution>();
        // to who's right of: position i ...
        int max = initialSolution.getValueContainers().length;
        for (int i = 0; i < max - 1; i++) {
            // to set value to: position j
            for (int j = i + 1; j < max; j++) {
                Solution tempSolution = new Solution(initialSolution);
                if (checkPremisesAndSetValue(tempSolution, premises, conclusion, i, j)) {
                    results.add(tempSolution);
                }
            }
        }
        return results;
    }

    private ArrayList<Solution> left(Solution initialSolution) {
        ArrayList<Solution> results = new ArrayList<Solution>();

        // to who's left of: position i ...
        for (int i = 1; i < initialSolution.getValueContainers().length; i++) {
            // to set value to: position j
            for (int j = 0; j <= i - 1; j++) {
                Solution tempSolution = new Solution(initialSolution);
                if (checkPremisesAndSetValue(tempSolution, premises, conclusion, i, j)) {
                    results.add(tempSolution);
                }
            }
        }
        return results;
    }

    private ArrayList<Solution> next(Solution initialSolution) {
        ArrayList<Solution> results = new ArrayList<Solution>();

        // who has a neighbor: position i ...
        for (int i = 1; i < initialSolution.getValueContainers().length - 1; i++) {
            // to set value to: position j
            Integer neighbors[] = new Integer[]{i - 1, i + 1};
            for (int j : neighbors) {
                Solution tempSolution = new Solution(initialSolution);
                if (checkPremisesAndSetValue(tempSolution, premises, conclusion, i, j)) {
                    results.add(tempSolution);
                }
            }
        }
        return results;
    }

    private ArrayList<Solution> same(Solution initialSolution) {
        ArrayList<Solution> results = new ArrayList<Solution>();

        // simply each: position i ...
        for (int i = 0; i < initialSolution.getValueContainers().length; i++) {
            // to set value to: still position i
            Solution tempSolution = new Solution(initialSolution);
            if (checkPremisesAndSetValue(tempSolution, premises, conclusion, i, i)) {
                results.add(tempSolution);
            }
        }
        return results;
    }

    private boolean checkPremisesAndSetValue(Solution solution, Value[] premises, Value conclusion, int from, int to) {
        for (Value currentValue : premises) {
            if (currentValue == null) {
                continue;
            }
            boolean successfullySet = solution.setValueIfLegal(currentValue, from);
            if (!successfullySet) {
                return false;
            }
        }

        return solution.setValueIfLegal(conclusion, to);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(type.name()).append(" - premises: (");

        for (Value current : premises) {
            if (current == null) {
                ret.append("none ");
            } else {
                ret.append(current).append(" ");
            }
        }
        ret.deleteCharAt(ret.length() - 1);
        ret.append(") => conclusion: (").append(conclusion).append(")");

        return ret.toString();
    }

    public static enum TYPE {
        SAME, NEXT_TO, TO_THE_LEFT_OF, TO_THE_RIGHT_OF
    }
}
