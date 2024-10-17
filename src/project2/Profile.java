package project2;

import util.*;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        Profile pro = (Profile)obj;
        return pro.compareTo(this) == 0;
    }

    /**
     * @param o the object to be compared.
     * @return -1, 0, or 1 depending on equality
     */
    @Override
    public int compareTo(Profile o) {
        if(this.lname.equalsIgnoreCase(o.lname)){
            if(this.fname.equalsIgnoreCase(o.fname)){
                return this.dob.compareTo(o.dob);
            }
            if(this.fname.compareTo(o.fname) < 0){
                return -1;
            } else if(this.fname.compareTo(o.fname) > 0){
                return 1;
            } else {
                return 0;
            }
        }
        if(this.lname.compareTo(o.lname) < 0){
            return -1;
        } else if(this.lname.compareTo(o.lname) > 0){
            return 1;
        }
        return 0;
    }

    /**
     * @return String conversion of a Profile object
     */
    @Override
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    /**
     * @return date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @return first name
     */
    public String getFname(){ return fname; }

    /**
     * @return last name
     */
    public String getLname(){ return lname; }

}