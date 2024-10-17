package project2;
import util.*;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public abstract class Provider extends Person{
    private Location location;

    public Provider(Profile profile, Location location){
        super(profile);
        this.location = location;
    }

    /**
     * @return based on type of Provider (Doctor/Technician), returns rate of provider
     */
    public abstract int rate();

    /**
     * @param obj object to be compared.
     * @return true if location and profile match between Provider obj and this, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass()!=this.getClass()){
            return false;
        }
        Provider pro = (Provider) obj;
        return this.location==pro.location && this.profile.equals(pro.profile);
    }

    /**
     * @return location of Provider
     */
    public Location getLocation(){
        return this.location;
    }

}
