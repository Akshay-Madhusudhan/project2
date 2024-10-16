package project2;
import util.*;
import java.text.DecimalFormat;

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

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#,###.00");

        String fName = this.getProfile().getFname().toUpperCase();
        String lName = this.getProfile().getLname().toUpperCase();
        String dob = this.getProfile().getDob().toString();
        String location = this.getLocation().toString().toUpperCase();
        String county = this.getLocation().countyString().toUpperCase();
        String zip = this.getLocation().getZip().toUpperCase();
        String rate = df.format(this.ratePerVisit);

        return "[" + fName + " " + lName + " " + dob + ", " + location + ", " + county + " " + zip + "] [rate: $" + rate + "]";
    }
}
