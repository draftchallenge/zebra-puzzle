package model;

import java.util.HashMap;
import java.util.Map;

public class ValueContainer {

    public static final String UNKNOWN_CATEGORY_FOR_VALUE = "Unknown category for value.";

    private Map<String, Value> categoriesAndValues;

    public ValueContainer(SolutionTemplate solutionTemplate, int position) {
        Value value = solutionTemplate.createValidValue(CategoryList.CATEGORY_POSITION, String.valueOf(position));

        initCategoriesWithEmptyValues(solutionTemplate);
        setValue(value);
    }

    private ValueContainer(Map<String, Value> categoriesAndValues) {
        copyCategoriesAndValues(categoriesAndValues);
    }

    private void initCategoriesWithEmptyValues(SolutionTemplate solutionTemplate) {
        String[] allCategories = solutionTemplate.getAllCategories();

        categoriesAndValues = new HashMap<String, Value>();
        for (String category : allCategories) {
            categoriesAndValues.put(category, null);
        }
    }

    private void copyCategoriesAndValues(Map<String, Value> categoriesAndValues) {
        this.categoriesAndValues = new HashMap<String, Value>();
        for (String key : categoriesAndValues.keySet()) {
            this.categoriesAndValues.put(key, categoriesAndValues.get(key));
        }
    }

    boolean setValue(Value value) {
        if (!categoriesAndValues.containsKey(value.getCategory())) {
            throw new Error(UNKNOWN_CATEGORY_FOR_VALUE);
        }

        Value found = categoriesAndValues.get(value.getCategory());
        if (found == null) {
            categoriesAndValues.put(value.getCategory(), value);
            return true;
        }
        return value.equals(found);
    }

    public Map<String, Value> getValues() {
        return categoriesAndValues;
    }

    boolean isValue(Value value) {
        Value current = categoriesAndValues.get(value.getCategory());
        return value.equals(current);
    }

    @Override
    public ValueContainer clone() {
        return new ValueContainer(categoriesAndValues);
    }
}
