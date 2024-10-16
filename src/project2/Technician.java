package project2;
import util.*;

public class Technician extends Provider{
    private int ratePerVisit;

    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    @Override
    public int rate() {
        return this.ratePerVisit;
    }
}
