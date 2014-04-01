package controller.in.reader;

import junit.framework.TestCase;

import java.io.File;

public class FileReaderTest extends TestCase {

    public void testGetInputMissing() {
        try {
            new FileReader(null);
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals(AbstractReader.NO_INPUT, e.getMessage());
        }
    }

    public void testGetInputNoFile() {
        try {
            new FileReader(new File("."));
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals(AbstractReader.NO_INPUT, e.getMessage());
        }
    }
}
