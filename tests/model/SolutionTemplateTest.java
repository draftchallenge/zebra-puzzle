package model;

import junit.framework.TestCase;

public class SolutionTemplateTest extends TestCase {

    public void testCreateTemplate() {
        int maxNumberOfValuesPerCategory = 100;
        SolutionTemplate template = new SolutionTemplate(maxNumberOfValuesPerCategory);

        assertEquals(maxNumberOfValuesPerCategory, template.getMaxValuesPerCategory());
        assertEquals(0, template.getTotalSolutions());

        try {
            new SolutionTemplate(0);
            fail("Error expected.");
        } catch (Error e) {
            assertEquals(SolutionTemplate.TOO_LESS_VALUES_PER_CATEGORY, e.getMessage());
        }

    }

    public void testGetConstraint() throws Exception {
        SolutionTemplate template = new SolutionTemplate(3);

        Value[] conditions = {new Value("pet", "horse")};
        template.addConstraint(new Constraint("same", conditions));

        assertEquals(1, template.getConstraintCount());
        assertEquals("SAME - premises: (none) => conclusion: (horse)", template.getConstraint(0).toString());
        assertNull(template.getConstraint(1));
        assertNull(template.getConstraint(2));
    }
}
