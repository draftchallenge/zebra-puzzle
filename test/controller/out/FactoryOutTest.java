package controller.out;

import controller.out.writer.AbstractWriter;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FactoryOutTest extends TestCase {

    public void testGetWriter() throws Exception {
        assertNull(FactoryOut.getWriter(null));

        AbstractWriter writer = FactoryOut.getWriter(FactoryOut.OUTPUT.TERMINAL);
        assertNotNull(writer);
        writer.setOptions("test1", "test2");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOutput = new PrintStream(outputStream);

        PrintStream oldOutput = System.out;
        System.setOut(newOutput);

        writer.write("test");
        System.out.flush();

        System.setOut(oldOutput);
        String actual = outputStream.toString().trim();
        assertEquals("test", actual);
    }

    public void testGetFormatter() throws Exception {
        assertNull(FactoryOut.getFormatter(null));

        assertNotNull(FactoryOut.getFormatter(FactoryOut.FORMAT.PLAIN));
    }
}
