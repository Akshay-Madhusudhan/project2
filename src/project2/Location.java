package project2;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public enum Location {
    BRIDGEWATER("Somerset County", "08807"),
    EDISON("Middlesex County", "08817"),
    PISCATAWAY("Middlesex County", "08854"),
    PRINCETON("Mercer County", "08540"),
    MORRISTOWN("Morris County", "07960"),
    CLARK("Union County", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * @return String conversion of county
     */
    public String countyString(){
        String[] separated_county = county.split(" ");
        String countyOnly = separated_county[0];
        return countyOnly.substring(0, 1).toUpperCase() + countyOnly.substring(1).toLowerCase();
    }

    /**
     * @return Longer string version of county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @return String version of zip
     */
    public String getZip() {
        return zip;
    }

}

