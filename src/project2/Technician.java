package project2;
import util.*;
import java.text.DecimalFormat;
/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Technician extends Provider{
    private int ratePerVisit;

    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * @return rate per visit of Technicians
     * method overriding abstract method in parent class
     */
    @Override
    public int rate() {
        return this.ratePerVisit;
    }

    /**
     * @return String conversion of technician
     */
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
