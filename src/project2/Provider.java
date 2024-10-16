package project2;
import util.*;


public abstract class Provider extends Person{
    private Location location;

    public Provider(Profile profile, Location location){
        super(profile);
        this.location = location;
    }

    public abstract int rate();

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

    public Location getLocation(){
        return this.location;
    }

}
