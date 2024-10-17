package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void isValidDateTest() {
        Date inv1 = new Date(13, 2, 1906);
        Date inv2 = new Date(2, 29, 2001);
        Date inv3 = new Date(12, 32, 1762);
        Date inv4 = new Date(1, 15, -1);
        Date val1 = new Date(2, 29, 2000);
        Date val2 = new Date(8, 31, 2026);
        assertFalse(inv1.isValidDate());
        assertFalse(inv2.isValidDate());
        assertFalse(inv3.isValidDate());
        assertFalse(inv4.isValidDate());
        assertTrue(val1.isValidDate());
        assertTrue(val1.isValidDate());
    }
}