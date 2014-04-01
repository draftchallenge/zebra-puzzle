package model;

import junit.framework.TestCase;

public class ConstraintTest extends TestCase {

    public void testCreateConstraintUnknownType() {
        try {
            new Constraint("TestValue", null);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals("No enum constant model.Constraint.TYPE.TESTVALUE", e.getMessage());
        }
    }

    public void testParseConstraintConditions() throws Exception {
        try {
            Value[] conditions = {};
            new Constraint("Same", conditions);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals(Constraint.TOO_LESS_CONDITIONS, e.getMessage());
        }

        Value[] conditions = new Value[]{new Value("pet", "zebra")};
        Constraint same = new Constraint("Same", conditions);
        assertEquals("SAME - premises: (none) => conclusion: (zebra)", same.toString());

        conditions = new Value[]{new Value("color", "blue"), new Value("pet", "zebra")};
        same = new Constraint("Same", conditions);
        assertEquals("SAME - premises: (blue) => conclusion: (zebra)", same.toString());
    }

    public void testEvaluateSameHappyPath() throws Exception {
        String type = "Same";
        Solution[] solutions = createSolutions(type);

        assertEquals(2, solutions.length);
        assertEquals("{position:1,color:blue,pet:zebra},{position:2,color:null,pet:null}", solutions[0].toString());
        assertEquals("{position:1,color:null,pet:null},{position:2,color:blue,pet:zebra}", solutions[1].toString());
    }

    public void testEvaluateNextToHappyPath() throws Exception {
        String type = "next_to";
        Solution[] solutions = createSolutions(type);

        assertEquals(0, solutions.length);
    }

    public void testEvaluateRightOfHappyPath() throws Exception {
        String type = "to_the_right_of";
        Solution[] solutions = createSolutions(type);

        assertEquals(1, solutions.length);
        assertEquals("{position:1,color:blue,pet:null},{position:2,color:null,pet:zebra}", solutions[0].toString());
    }

    public void testEvaluateLeftOfHappyPath() throws Exception {
        String type = "to_the_left_of";
        Solution[] solutions = createSolutions(type);

        assertEquals(1, solutions.length);
        assertEquals("{position:1,color:null,pet:zebra},{position:2,color:blue,pet:null}", solutions[0].toString());
    }

    private Solution[] createSolutions(String type) throws Exception {
        SolutionTemplate template = new SolutionTemplate(2);
        Value validValue1 = template.createValidValue("color", "blue");
        Value validValue2 = template.createValidValue("pet", "zebra");

        Value[] conditions = new Value[]{validValue1, validValue2};
        Constraint constraint = new Constraint(type, conditions);

        return constraint.evaluate(new Solution(template));
    }
}
