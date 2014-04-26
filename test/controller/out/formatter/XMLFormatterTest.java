package controller.out.formatter;

import controller.in.reader.StringReader;
import controller.in.transformer.CSVTransformer;
import junit.framework.TestCase;
import model.Solution;
import model.SolutionTemplate;

public class XMLFormatterTest extends TestCase {

    public void testFormatHappyPath() throws Exception {
        String testInput = "5\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog\nSAME;drink;Coffee;color;Green\nTO_THE_LEFT_OF;color;Ivory;color;Green\nSAME;drink;Tea;nationality;Ukrainian\nSAME;smoke;Old gold;pet;Snails\nSAME;smoke;Kools;color;Yellow\nNEXT_TO;smoke;Chesterfields;pet;Fox\nSAME;nationality;Norwegian;position;1\nSAME;drink;Milk;position;3\nNEXT_TO;smoke;Kools;pet;Horse\nSAME;smoke;Parliaments;nationality;Japanese\nSAME;smoke;Lucky strike;drink;Orange juice";

        Solution[] solutions = createSolution(testInput);

        String result = new XMLFormatter().format(solutions);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"stylesheet.xsl\" ?><solutions>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"english\" color=\"red\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"4\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"5\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"english\" color=\"red\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"4\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"5\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"english\" color=\"red\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"4\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"5\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"4\" nationality=\"english\" color=\"red\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"5\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"4\" nationality=\"\" color=\"\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t\t<house position=\"5\" nationality=\"english\" color=\"red\" pet=\"\" drink=\"\" smoke=\"\" />\n" +
                "\t</solution>\n" +
                "</solutions>", result);
    }

    public void testFormatHappyPath2() throws Exception {
        String testInput = "3\nSAME;nationality;English;color;Red\nSAME;nationality;Spaniard;pet;Dog;Size;tall;Smoke;none;Fun;no";

        Solution[] solutions = createSolution(testInput);

        String result = new XMLFormatter().format(solutions);

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"stylesheet.xsl\" ?><solutions>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"english\" color=\"red\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"english\" color=\"red\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t</solution>\n" +
                "\t<solution>\n" +
                "\t\t<house position=\"1\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"2\" nationality=\"\" color=\"\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t\t<house position=\"3\" nationality=\"english\" color=\"red\" pet=\"\" fun=\"\" smoke=\"\" size=\"\" />\n" +
                "\t</solution>\n" +
                "</solutions>", result);
    }

    private Solution[] createSolution(String tests) throws Exception {
        StringReader reader = new StringReader(tests);
        CSVTransformer transformer = new CSVTransformer(";", "\\r?\\n");
        SolutionTemplate template = transformer.parse(reader);
        Solution solution = new Solution(template);
        return solution.invokeConstraint();
    }
}
