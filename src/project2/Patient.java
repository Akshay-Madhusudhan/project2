package project2;

public class Patient extends Person{
    private Visit visit;

    public Patient(Profile profile, Visit visit){
        super(profile);
        this.visit = visit;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass()!=this.getClass()){
            return false;
        }
        Patient p = (Patient)obj;
        return p.profile.equals(this.profile);
    }

}
