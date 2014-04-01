package model;

import junit.framework.TestCase;

public class ValueContainerTest extends TestCase {
    public void testSetValueError() throws Exception {
        SolutionTemplate template = new SolutionTemplate(3);
        template.createValidValue("pet", "whatever");

        ValueContainer container = new ValueContainer(template, 1);
        try {
            container.setValue(new Value("color", "red"));
            fail("Error expected.");
        } catch (Error e) {
            assertEquals(ValueContainer.UNKNOWN_CATEGORY_FOR_VALUE, e.getMessage());
        }
    }

    public void testClone() throws Exception {
        SolutionTemplate template = new SolutionTemplate(3);
        template.createValidValue("pet", "whatever");
        ValueContainer container = new ValueContainer(template, 1);
        ValueContainer clone = container.clone();

        assertEquals(container.getValues(), clone.getValues());

        container.setValue(new Value("pet", "zebra"));
        assertNotSame(container.getValues(), clone.getValues());
    }
}
