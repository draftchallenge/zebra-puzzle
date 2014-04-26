import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class MainTest extends TestCase {

    public final void testOutput() throws Exception {
        new File(Main.OUTPUT_FILENAME).delete();

        assertTrue(new File(Main.INPUT_FILENAME).exists());
        assertFalse(new File(Main.OUTPUT_FILENAME).exists());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOutput = new PrintStream(outputStream);

        PrintStream oldOutput = System.out;
        System.setOut(newOutput);

        Main.main(null);
        System.out.flush();

        System.setOut(oldOutput);
        String actual = outputStream.toString().trim();

        System.out.println(actual);

        assertTrue(actual.contains("Number of solutions: 10"));
        assertTrue(actual.contains("Total created solution attempts (= streets): 53820"));
        assertTrue(actual.contains("Constraints invoked: 16"));

        assertTrue(new File(Main.INPUT_FILENAME).exists());
        assertTrue(new File(Main.OUTPUT_FILENAME).exists());
        assertTrue(new File(Main.OUTPUT_FILENAME).length() == 5721);
    }
}
