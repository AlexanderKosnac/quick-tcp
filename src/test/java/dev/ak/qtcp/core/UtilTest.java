package dev.ak.qtcp.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UtilTest extends TestCase {

    public UtilTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(UtilTest.class);
    }

    public void testGetStringAsIntOrZero()
    {
        assertEquals(1, Util.getStringAsIntOrZero("1"));
        assertEquals(-1, Util.getStringAsIntOrZero("-1"));
        assertEquals(1, Util.getStringAsIntOrZero("+1"));
        assertEquals(1, Util.getStringAsIntOrZero(" 1"));
        assertEquals(1, Util.getStringAsIntOrZero("1 "));
        assertEquals(1, Util.getStringAsIntOrZero(" 1 "));
        assertEquals(0, Util.getStringAsIntOrZero(" "));
        assertEquals(0, Util.getStringAsIntOrZero("   "));
        assertEquals(0, Util.getStringAsIntOrZero("Abc"));
    }

    public void testGetStringAsIntOrDefault()
    {
        assertEquals(1, Util.getStringAsIntOrDefault("1", 2));
        assertEquals(-1, Util.getStringAsIntOrDefault("-1", 2));
        assertEquals(1, Util.getStringAsIntOrDefault("+1", 2));
        assertEquals(1, Util.getStringAsIntOrDefault(" 1", 2));
        assertEquals(1, Util.getStringAsIntOrDefault("1 ", 2));
        assertEquals(1, Util.getStringAsIntOrDefault(" 1 ", 2));
        assertEquals(2, Util.getStringAsIntOrDefault(" ", 2));
        assertEquals(2, Util.getStringAsIntOrDefault("   ", 2));
        assertEquals(2, Util.getStringAsIntOrDefault("Abc", 2));
    }
}

