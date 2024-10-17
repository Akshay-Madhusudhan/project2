package project2;

import util.Date;

import static org.junit.Assert.*;

public class ProfileTest {

    @org.junit.Test
    public void compareToTest() {
        Date xmas = new Date(12, 25, 2020);
        Date val = new Date(2, 14, 2019);
        Date val2 = new Date(2, 14, 2021);
        Profile john = new Profile("John", "Doe", xmas);
        Profile john2 = new Profile("John", "Doe", xmas);
        Profile abbie = new Profile("Abbie", "Smith", val);
        Profile abbie2 = new Profile("Abbie", "Ellington", val);
        Profile abbie3 = new Profile("Abbie", "Smith", val2);
        assertEquals(0, john.compareTo(john2));
        assertEquals(-1, john.compareTo(abbie));
        assertEquals(-1, abbie.compareTo(abbie3));
        assertEquals(-1, john.compareTo(abbie2));
        assertEquals(1, abbie.compareTo(john));
        assertEquals(1, abbie2.compareTo(john));
        assertEquals(1, abbie3.compareTo(abbie2));
    }
}