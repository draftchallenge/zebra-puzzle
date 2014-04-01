package controller.out.formatter;

import model.Solution;
import model.Value;
import model.ValueContainer;

import java.util.Map;

public class PlainFormatter implements AbstractFormatter {

    @Override
    public String format(Solution[] solutions) {

        String[] categories = solutions[0].getAllCategories();

        int maxLength = getMaxLength(categories);

        StringBuilder ret = new StringBuilder();
        ret.append("Number of solutions: ").append(solutions.length).append("\n");
        int i = 1;
        for (Solution currentSolution : solutions) {
            ret.append("Solution ").append(i);
            ret.append("\n");

            for (String category : categories) {
                ret.append(String.format("%-" + maxLength + "s", category + ":"));

                for (ValueContainer valueContainer : currentSolution.getValueContainers()) {
                    Map<String, Value> valueMap = valueContainer.getValues();
                    String s = String.format("%15s", valueMap.get(category));
                    ret.append(s).append("| ");
                }
                ret.append("\n");
            }

            ret.append("\n");
            i++;
        }

        return ret.toString().trim();
    }

    private int getMaxLength(String[] categories) {
        int maxLength = 5;
        for (String category : categories) {
            if (category.length() > maxLength) {
                maxLength = category.length();
            }
        }
        maxLength += 3;
        return maxLength;
    }
}
