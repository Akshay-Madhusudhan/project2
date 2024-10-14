package project2;

import util.Date;

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

    @Override
    public int compareTo(Profile o) {
        if(this.lname.equals(o.lname)){
            if(this.fname.equals(o.fname)){
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

    @Override
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    public static void main(String[] args){
        Date xmas = new Date(12, 25, 2020);
        Date val = new Date(2, 14, 2019);
        Date val2 = new Date(2, 14, 2021);
        Profile john = new Profile("John", "Doe", xmas);
        Profile john2 = new Profile("John", "Doe", xmas);
        Profile abbie = new Profile("Abbie", "Smith", val);
        Profile abbie2 = new Profile("Abbie", "Ellington", val);
        Profile abbie3 = new Profile("Abbie", "Smith", val2);
        System.out.println(john.compareTo(john2));
        System.out.println(john.compareTo(abbie));
        System.out.println(john.compareTo(abbie2));
        System.out.println(abbie.compareTo(abbie3));
        System.out.println(abbie.compareTo(john));
        System.out.println(abbie2.compareTo(john));
        System.out.println(abbie3.compareTo(abbie));
    }

    public Date getDob() {
        return dob;
    }

    public String getFname(){ return fname; }
    public String getLname(){ return lname; }

}