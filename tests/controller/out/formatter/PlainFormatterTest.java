package controller.out.formatter;

import controller.in.reader.StringReader;
import controller.in.transformer.CSVTransformer;
import junit.framework.TestCase;
import model.Solution;
import model.SolutionTemplate;

public class PlainFormatterTest extends TestCase {


    public void testFormatHappyPath() throws Exception {
        String testInput = "5\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog\nSAME;drink;Coffee;color;Green\nTO_THE_LEFT_OF;color;Ivory;color;Green\nSAME;drink;Tea;nationality;Ukrainian\nSAME;smoke;Old gold;pet;Snails\nSAME;smoke;Kools;color;Yellow\nNEXT_TO;smoke;Chesterfields;pet;Fox\nSAME;nationality;Norwegian;position;1\nSAME;drink;Milk;position;3\nNEXT_TO;smoke;Kools;pet;Horse\nSAME;smoke;Parliaments;nationality;Japanese\nSAME;smoke;Lucky strike;drink;Orange juice";

        Solution[] solutions = createSolution(testInput);

        String result = new PlainFormatter().format(solutions);

        assertEquals("Number of solutions: 5\n" +
                "Solution 1\n" +
                "position:                   1|               2|               3|               4|               5| \n" +
                "nationality:          english|            null|            null|            null|            null| \n" +
                "color:                    red|            null|            null|            null|            null| \n" +
                "pet:                     null|            null|            null|            null|            null| \n" +
                "drink:                   null|            null|            null|            null|            null| \n" +
                "smoke:                   null|            null|            null|            null|            null| \n" +
                "\n" +
                "Solution 2\n" +
                "position:                   1|               2|               3|               4|               5| \n" +
                "nationality:             null|         english|            null|            null|            null| \n" +
                "color:                   null|             red|            null|            null|            null| \n" +
                "pet:                     null|            null|            null|            null|            null| \n" +
                "drink:                   null|            null|            null|            null|            null| \n" +
                "smoke:                   null|            null|            null|            null|            null| \n" +
                "\n" +
                "Solution 3\n" +
                "position:                   1|               2|               3|               4|               5| \n" +
                "nationality:             null|            null|         english|            null|            null| \n" +
                "color:                   null|            null|             red|            null|            null| \n" +
                "pet:                     null|            null|            null|            null|            null| \n" +
                "drink:                   null|            null|            null|            null|            null| \n" +
                "smoke:                   null|            null|            null|            null|            null| \n" +
                "\n" +
                "Solution 4\n" +
                "position:                   1|               2|               3|               4|               5| \n" +
                "nationality:             null|            null|            null|         english|            null| \n" +
                "color:                   null|            null|            null|             red|            null| \n" +
                "pet:                     null|            null|            null|            null|            null| \n" +
                "drink:                   null|            null|            null|            null|            null| \n" +
                "smoke:                   null|            null|            null|            null|            null| \n" +
                "\n" +
                "Solution 5\n" +
                "position:                   1|               2|               3|               4|               5| \n" +
                "nationality:             null|            null|            null|            null|         english| \n" +
                "color:                   null|            null|            null|            null|             red| \n" +
                "pet:                     null|            null|            null|            null|            null| \n" +
                "drink:                   null|            null|            null|            null|            null| \n" +
                "smoke:                   null|            null|            null|            null|            null|", result);
    }

    public void testFormatHappyPath2() throws Exception {
        String testInput = "3\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog;Size;tall;Smoke;none;Fun;no";

        Solution[] solutions = createSolution(testInput);

        String result = new PlainFormatter().format(solutions);

        assertEquals("Number of solutions: 3\n" +
                "Solution 1\n" +
                "position:                   1|               2|               3| \n" +
                "nationality:          english|            null|            null| \n" +
                "color:                    red|            null|            null| \n" +
                "pet:                     null|            null|            null| \n" +
                "fun:                     null|            null|            null| \n" +
                "smoke:                   null|            null|            null| \n" +
                "size:                    null|            null|            null| \n" +
                "\n" +
                "Solution 2\n" +
                "position:                   1|               2|               3| \n" +
                "nationality:             null|         english|            null| \n" +
                "color:                   null|             red|            null| \n" +
                "pet:                     null|            null|            null| \n" +
                "fun:                     null|            null|            null| \n" +
                "smoke:                   null|            null|            null| \n" +
                "size:                    null|            null|            null| \n" +
                "\n" +
                "Solution 3\n" +
                "position:                   1|               2|               3| \n" +
                "nationality:             null|            null|         english| \n" +
                "color:                   null|            null|             red| \n" +
                "pet:                     null|            null|            null| \n" +
                "fun:                     null|            null|            null| \n" +
                "smoke:                   null|            null|            null| \n" +
                "size:                    null|            null|            null|", result);
    }

    private Solution[] createSolution(String tests) throws Exception {
        StringReader reader = new StringReader(tests);
        CSVTransformer transformer = new CSVTransformer(";", "\\r?\\n");
        SolutionTemplate template = transformer.parse(reader);
        Solution solution = new Solution(template);
        return solution.invokeConstraint();
    }
}
