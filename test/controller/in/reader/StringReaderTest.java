package controller.in.reader;

import junit.framework.TestCase;

public class StringReaderTest extends TestCase {

    public void testGetInputMissing() {
        try {
            new StringReader(null);
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals(AbstractReader.NO_INPUT, e.getMessage());
        }
    }

    public void testGetInputNoFile() {
        try {
            new StringReader("");
            fail("Exception expected.");
        } catch (Exception e) {
            assertEquals(AbstractReader.NO_INPUT, e.getMessage());
        }
    }
}
