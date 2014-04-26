package model;

import junit.framework.TestCase;

public class ValueTest extends TestCase {

    public void testEqualsAndHashCode() {
        Value testCat1 = new Value("testCat");
        Value testCat2 = new Value("testCat");
        Value testCat3 = new Value("testCat", "withValue");

        assertEquals(testCat1, testCat2);
        assertNotSame(testCat1, testCat3);

        assertTrue(testCat1.hashCode() == testCat2.hashCode());
        assertFalse(testCat1.hashCode() == testCat3.hashCode());
    }

    public void testEqualsAndHashCode2() {
        Value testCat1 = new Value("testCat", "withValue");
        Value testCat2 = new Value("testCat", "withValue");
        Value testCat3 = new Value("testCat", "withValueDifferent");

        assertEquals(testCat1, testCat2);
        assertNotSame(testCat1, testCat3);

        assertTrue(testCat1.hashCode() == testCat2.hashCode());
        assertFalse(testCat1.hashCode() == testCat3.hashCode());
    }
}
