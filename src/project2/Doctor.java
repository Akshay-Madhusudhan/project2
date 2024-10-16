package project2;
import util.*;

public class Doctor extends Provider{
    private Specialty specialty;
    private String npi;

    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return 0;
    }
}
