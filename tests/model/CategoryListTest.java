package model;

import junit.framework.TestCase;

public class CategoryListTest extends TestCase {

    public void testCreateCategoryList() {
        int maxValuesPerCategory = 2;
        CategoryList categoryList = new CategoryList(maxValuesPerCategory);

        assertEquals(maxValuesPerCategory, categoryList.getMaxValuesPerCategory());
        assertEquals(1, categoryList.getAllCategories().length);
        assertEquals(CategoryList.CATEGORY_POSITION, categoryList.getAllCategories()[0]);

        try {
            new CategoryList(0);
            fail("Error expected.");
        } catch (Error e) {
            assertEquals(CategoryList.TOO_LESS_VALUES_PER_CATEGORY, e.getMessage());
        }
    }

    public void testAddValueAndCreateCategoryIfNotExisting() throws Exception {
        String testCat = "testCat";
        int maxValuesPerCategory = 2;
        Value value = new Value(testCat, "test1");
        Value value2 = new Value(testCat, "test2");
        Value value3 = new Value(testCat, "test3");

        CategoryList categoryList = new CategoryList(maxValuesPerCategory);

        categoryList.addValueAndCreateCategoryIfNotExisting(value);
        categoryList.addValueAndCreateCategoryIfNotExisting(value);
        categoryList.addValueAndCreateCategoryIfNotExisting(value2);

        try {
            categoryList.addValueAndCreateCategoryIfNotExisting(value3);
            fail("Error expected.");
        } catch (Error e) {
            assertEquals(CategoryList.TOO_MANY_VALUES_PER_CATEGORY, e.getMessage());
        }

        categoryList.addValueAndCreateCategoryIfNotExisting(value);
        categoryList.addValueAndCreateCategoryIfNotExisting(value2);

        assertEquals(CategoryList.CATEGORY_POSITION, categoryList.getAllCategories()[0]);
        assertEquals(value.getCategory(), categoryList.getAllCategories()[1]);
        assertEquals(maxValuesPerCategory, categoryList.getMaxValuesPerCategory());
        assertEquals(2, categoryList.getAllCategories().length);
    }

    public void testGetAllCategories() throws Exception {
        CategoryList categoryList = new CategoryList(1);
        Value test = new Value("test");
        categoryList.addValueAndCreateCategoryIfNotExisting(test);

        assertEquals(CategoryList.CATEGORY_POSITION, categoryList.getAllCategories()[0]);
        assertEquals(test.getCategory(), categoryList.getAllCategories()[1]);
    }
}
