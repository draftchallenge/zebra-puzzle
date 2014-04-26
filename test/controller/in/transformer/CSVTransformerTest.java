package controller.in.transformer;

import controller.in.reader.StringReader;
import junit.framework.TestCase;
import model.SolutionTemplate;

public class CSVTransformerTest extends TestCase {

    public void testParseHappyPath() throws Exception {
        String testInput = "5\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog\nSAME;drink;Coffee;color;Green\nTO_THE_LEFT_OF;color;Ivory;color;Green\nSAME;drink;Tea;nationality;Ukrainian\nSAME;smoke;Old gold;pet;Snails\nSAME;smoke;Kools;color;Yellow\nNEXT_TO;smoke;Chesterfields;pet;Fox\nSAME;nationality;Norwegian;position;1\nSAME;drink;Milk;position;3\nNEXT_TO;smoke;Kools;pet;Horse\nSAME;smoke;Parliaments;nationality;Japanese\nSAME;smoke;Lucky strike;drink;Orange juice";

        createTransformer(testInput);
    }

    public void testFirstLineError() {
        String[] testInput = {"\nSAME;nationality;English;", "SAME;nationality;English;", "Test\n"};

        for (String tests : testInput) {
            try {
                createTransformer(tests);
                fail("Exception expected");
            } catch (Exception e) {
                assertEquals(CSVTransformer.CSVParseError.FIRST_LINE_ERROR, e.getMessage());
            }
        }
    }

    public void testTooLessConditions() {
        String testInput = "5\nSAME;nationality;";

        try {
            createTransformer(testInput);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals("Rule in line number 1 is not formatted well: same;nationality;", e.getMessage());
        }
    }

    public void testWrongAmountOfConditions() {
        String testInput = "5\nSAME;nationality;English;color;";

        try {
            createTransformer(testInput);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals("Rule in line number 1 is not formatted well: same;nationality;english;color;", e.getMessage());
        }

        testInput += "Red;size;";

        try {
            createTransformer(testInput);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals("Rule in line number 1 is not formatted well: same;nationality;english;color;red;size;", e.getMessage());
        }


        testInput += "tall;";
        try {
            SolutionTemplate transformer = createTransformer(testInput);
            assertEquals(1, transformer.getConstraintCount());
        } catch (Exception e) {
            fail("No exception expected.");
        }
    }

    public void testNoValidConstraintType() {
        String testInput = "5\nnationality;bla;bla";

        try {
            createTransformer(testInput);
            fail("Exception expected");
        } catch (Exception e) {
            assertEquals("No enum constant model.Constraint.TYPE.NATIONALITY", e.getMessage());
        }
    }

    private SolutionTemplate createTransformer(String tests) throws Exception {
        StringReader reader = new StringReader(tests);
        CSVTransformer transformer = new CSVTransformer(";", "\\r?\\n");
        return transformer.parse(reader);
    }
}
