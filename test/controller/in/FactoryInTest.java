package controller.in;

import junit.framework.TestCase;

public class FactoryInTest extends TestCase {

    public void testGetReaderNull() throws Exception {
        Object o = new Object();
        assertNull(FactoryIn.getReader(o));
    }

    public void testGetTransformerNull() throws Exception {
        assertNull(FactoryIn.getTransformer(null));
        assertNotNull(FactoryIn.getTransformer(FactoryIn.FORMAT.CVS));
    }
}
