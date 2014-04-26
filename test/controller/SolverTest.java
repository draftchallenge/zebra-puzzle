package controller;

import controller.in.FactoryIn;
import controller.in.reader.AbstractReader;
import controller.in.transformer.AbstractTransformer;
import junit.framework.TestCase;
import model.Solution;
import model.SolutionTemplate;

import java.util.ArrayList;

public class SolverTest extends TestCase {

    private String testInput = "5\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog\nSAME;drink;Coffee;color;Green\nTO_THE_LEFT_OF;color;Ivory;color;Green\nSAME;drink;Tea;nationality;Ukrainian\nSAME;smoke;Old gold;pet;Snails\nSAME;smoke;Kools;color;Yellow\nNEXT_TO;smoke;Chesterfields;pet;Fox\nSAME;nationality;Norwegian;position;1\nSAME;drink;Milk;position;3\nNEXT_TO;smoke;Kools;pet;Horse\nSAME;smoke;Parliaments;nationality;Japanese\nSAME;smoke;Lucky strike;drink;Orange juice";

    public void testHappyPath() throws Exception {
        AbstractReader reader = FactoryIn.getReader(testInput);
        AbstractTransformer transformer = FactoryIn.getTransformer(FactoryIn.FORMAT.CVS);
        SolutionTemplate template = transformer.parse(reader);

        ArrayList<Solution> solutions = new Solver(template).start();

        assertEquals(55, solutions.size());
        assertEquals(53390, template.getTotalSolutions());
        assertEquals(13, template.getConstraintCount());
    }

    public void testInvalidStats() {
        try {
            Solver solver = new Solver(null);
            solver.printStats();
            fail("Error expected.");
        } catch (Error e) {
            assertEquals(Solver.SOLUTIONS_NOT_AVAILABLE, e.getMessage());
        }
    }
}
