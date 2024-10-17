package project2;
import util.*;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile){
        this.profile = profile;
    }

    /**
     * @param obj object to be compared.
     * @return true if the profile of Person obj equals this.profile
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass()!=this.getClass()){
            return false;
        }
        Person p = (Person) obj;
        return p.profile.compareTo(this.profile)==0;
    }

    /**
     * @param o the object to be compared.
     * @return output of profile compareTo method between o and this
     */
    @Override
    public int compareTo(Person o) {
        return o.profile.compareTo(this.profile);
    }

    /**
     * @return profile of Person object
     */
    public Profile getProfile(){
        return this.profile;
    }

}
