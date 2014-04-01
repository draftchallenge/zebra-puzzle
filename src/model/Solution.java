package model;

import java.util.Map;

public class Solution {
    private SolutionTemplate template;
    private ValueContainer[] valueContainers;
    private int level;

    public Solution(SolutionTemplate template) {
        template.totalSolutions = 1;

        this.level = 0;
        this.template = template;
        this.valueContainers = new ValueContainer[template.getMaxValuesPerCategory()];
        for (int i = 0; i < template.getMaxValuesPerCategory(); i++) {
            this.valueContainers[i] = new ValueContainer(template, i + 1);
        }
    }

    public Solution(Solution parent) {
        this.template = parent.template;
        this.template.totalSolutions++;

        this.valueContainers = new ValueContainer[parent.valueContainers.length];
        for (int i = 0; i < this.valueContainers.length; i++) {
            this.valueContainers[i] = parent.valueContainers[i].clone();
        }

        this.level = parent.level + 1;
    }

    public Solution[] invokeConstraint() {
        if (template.getConstraint(level) == null) {
            return null;
        }

        Constraint constraint = template.getConstraint(level);
        return constraint.evaluate(this);
    }

    public String[] getAllCategories() {
        return template.getAllCategories();
    }

    public boolean setValueIfLegal(Value value, int setAt) {
        for (int i = 0; i < valueContainers.length; i++) {
            if (i != setAt && valueContainers[i].isValue(value)) {
                return false;
            }
        }
        return valueContainers[setAt].setValue(value);
    }

    public ValueContainer[] getValueContainers() {
        return valueContainers;
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for (ValueContainer currentValueContainer : valueContainers) {
            ret.append("{");
            Map<String, Value> values = currentValueContainer.getValues();
            for (String current : values.keySet()) {
                ret.append(current).append(":").append(values.get(current)).append(",");
            }
            ret.deleteCharAt(ret.length() - 1);
            ret.append("},");
        }
        ret.deleteCharAt(ret.length() - 1);
        return ret.toString();
    }
}
