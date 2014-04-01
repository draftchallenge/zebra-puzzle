package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CategoryList {

    static final String TOO_MANY_VALUES_PER_CATEGORY = "Value cannot be added";
    static final String CATEGORY_POSITION = "position";
    static final String TOO_LESS_VALUES_PER_CATEGORY = "Too less values per category";

    private final HashMap<String, ArrayList<Value>> map;
    private final int maxValuesPerCategory;

    CategoryList(int maxValuesPerCategory) {
        if (maxValuesPerCategory < 1) {
            throw new Error(TOO_LESS_VALUES_PER_CATEGORY);
        }
        this.maxValuesPerCategory = maxValuesPerCategory;
        this.map = new HashMap<String, ArrayList<Value>>();

        addPosition(maxValuesPerCategory);
    }

    private void addPosition(int maxValuesPerCategory) {
        for (int i = 0; i < maxValuesPerCategory; i++) {
            Value value = new Value(CATEGORY_POSITION, String.valueOf(i + 1));
            addValueAndCreateCategoryIfNotExisting(value);
        }
    }

    Value addValueAndCreateCategoryIfNotExisting(Value value) {
        ArrayList<Value> found = map.get(value.getCategory());

        if (found == null) {
            found = new ArrayList<Value>();
            map.put(value.getCategory(), found);
        }

        int indexOf = found.indexOf(value);
        if (indexOf >= 0) {
            return found.get(indexOf);
        }
        if (found.size() < maxValuesPerCategory) {
            found.add(value);
        } else {
            throw new Error(TOO_MANY_VALUES_PER_CATEGORY);
        }
        return value;
    }

    String[] getAllCategories() {
        Set<String> keys = map.keySet();
        return keys.toArray(new String[keys.size()]);
    }

    int getMaxValuesPerCategory() {
        return maxValuesPerCategory;
    }
}
