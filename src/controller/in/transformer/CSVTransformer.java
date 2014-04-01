package controller.in.transformer;

import controller.in.reader.AbstractReader;
import model.Constraint;
import model.SolutionTemplate;
import model.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVTransformer implements AbstractTransformer {
    private final String delimiter;
    private final String newline;

    public CSVTransformer(String delimiter, String newline) {
        this.delimiter = delimiter;
        this.newline = newline;
    }

    @Override
    public SolutionTemplate parse(AbstractReader reader) throws Exception {
        String[] contents = reader.getInput().split(newline);

        SolutionTemplate solutionTemplate = initWithFirstLine(contents[0]);

        for (int i = 1; i < contents.length; i++) {
            String line = contents[i].toLowerCase().trim();
            if (line.length() == 0) {
                break;
            }
            try {
                String[] values = getValues(line);
                Constraint constraint = getConstraint(solutionTemplate, values);
                solutionTemplate.addConstraint(constraint);
            } catch (Error e) {
                throw new CSVParseError(i, line);
            }
        }

        return solutionTemplate;
    }

    private SolutionTemplate initWithFirstLine(String line) throws CSVParseError {
        int maxNumberOfValuesPerCategory;
        try {
            maxNumberOfValuesPerCategory = Integer.valueOf(line);
        } catch (Exception e) {
            throw new CSVParseError(CSVParseError.FIRST_LINE_ERROR);
        }

        return new SolutionTemplate(maxNumberOfValuesPerCategory);
    }

    private String[] getValues(String line) {
        String[] values = line.split(String.valueOf(delimiter));
        List<String> tmpValues = Arrays.asList(values);

        for (String value : values) {
            if (value.length() == 0) {
                tmpValues.remove(value);
            }
        }
        values = tmpValues.toArray(new String[tmpValues.size()]);
        if (values.length % 2 == 0 || values.length < 3) {
            throw new Error();
        }
        return values;
    }

    private Constraint getConstraint(SolutionTemplate solutionTemplate, String[] values) throws Exception {
        ArrayList<Value> conditions = new ArrayList<Value>();
        for (int j = 1; j < values.length; j += 2) {
            String categoryName = values[j];
            String value1 = values[j + 1];

            Value value = solutionTemplate.createValidValue(categoryName, value1);
            conditions.add(value);
        }

        String constraintType = values[0];
        Value[] tmpConditions = conditions.toArray(new Value[conditions.size()]);
        return new Constraint(constraintType, tmpConditions);
    }

    public class CSVParseError extends Exception {

        public static final String FIRST_LINE_ERROR = "First line has to be the number of houses";

        public CSVParseError(int lineNumber, String line) {
            super("Rule in line number " + lineNumber + " is not formatted well: " + line);
        }

        public CSVParseError(String message) {
            super(message);
        }
    }
}
