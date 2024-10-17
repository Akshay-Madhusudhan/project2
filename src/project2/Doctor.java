package project2;
import util.*;
/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Doctor extends Provider{
    private Specialty specialty;
    private String npi;

    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * @return 0, doctors already have established rates through Specialty class
     * Method is just to satisfy abstract method requirements
     */
    @Override
    public int rate() {
        return 0;
    }

    /**
     * @return npi
     */
    public String getNpi(){
        return this.npi;
    }

    /**
     * @return specialty
     */
    public Specialty getSpecialty(){
        return this.specialty;
    }

    /**
     * @return String conversion of Doctor object
     */
    @Override
    public String toString(){
        String fName = this.getProfile().getFname().toUpperCase();
        String lName = this.getProfile().getLname().toUpperCase();
        String dob = this.getProfile().getDob().toString();
        String location = this.getLocation().toString().toUpperCase();
        String county = this.getLocation().countyString().toUpperCase();
        String zip = this.getLocation().getZip().toUpperCase();
        String specialty = this.specialty.toString().toUpperCase();
        String npi = this.npi;

        return "[" + fName + " " + lName + " " + dob + ", " + location + ", " + county + " " + zip + "] [" + specialty + ", #" + npi + "]";
    }
}
