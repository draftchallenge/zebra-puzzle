package model;

import junit.framework.TestCase;

public class SolutionTest extends TestCase {

    public void testCreateSolution() {
        Solution solution = createSolution();
        assertEquals("pet", solution.getAllCategories()[1]);
    }

    public void testInvokeEmptyConstraint() throws Exception {
        Solution solution = createSolution();
        Solution[] solutions = solution.invokeConstraint();
        assertNull(solutions);
    }

    public void testSetValueIfLegal() throws Exception {
        Solution solution = createSolution();
        assertTrue(solution.setValueIfLegal(new Value("pet", "horse"), 1));
        assertFalse(solution.setValueIfLegal(new Value("pet", "zebra"), 1));
        assertTrue(solution.setValueIfLegal(new Value("pet", "horse"), 1));
    }

    private Solution createSolution() {
        Solution solution;
        SolutionTemplate template = new SolutionTemplate(2);
        template.createValidValue("pet", "zebra");

        solution = new Solution(template);
        return solution;
    }
}
