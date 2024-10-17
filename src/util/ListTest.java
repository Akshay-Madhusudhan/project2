package util;

import org.junit.Test;
import project2.*;

import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void addTest() {
        List<Provider> providers = new List<>();
        Date dob = new Date(10, 2, 1998);
        Profile prof = new Profile("John", "Doe", dob);
        Doctor doc = new Doctor(prof, Location.BRIDGEWATER, Specialty.ALLERGIST,"01");
        Technician tech = new Technician(prof, Location.PRINCETON, 100);
        providers.add(doc);
        providers.add(tech);
        assertSame(doc, providers.get(0));
        assertSame(tech, providers.get(1));
    }

    @Test
    public void removeTest() {
        List<Provider> providers = new List<>();
        Date dob = new Date(10, 2, 1998);
        Profile prof = new Profile("John", "Doe", dob);
        Doctor doc = new Doctor(prof, Location.BRIDGEWATER, Specialty.ALLERGIST,"01");
        Technician tech = new Technician(prof, Location.PRINCETON, 100);
        providers.add(doc);
        providers.add(tech);
        providers.remove(doc);
        providers.remove(tech);
        assertTrue(providers.isEmpty());
    }
}