package project2;
import util.*;

public class Patient extends Person{
    private Visit visit;

    public Patient(Profile profile){
        super(profile);
        this.visit = null;
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

    public void add(Appointment app){
        Visit newVisit = new Visit(app);
        if(this.visit == null){
            this.visit = newVisit;
        } else {
            Visit curr = this.visit;
            while(curr.getNext() != null){
                curr = curr.getNext();
            }
            curr.setNext(newVisit);
        }
    }

    public void remove(Appointment app){
        if(this.visit==null){
            return;
        }
        if(this.visit.getNext()==null){
            this.visit = this.visit.getNext();
            return;
        }
        Visit temp = this.visit;
        Visit prev = null;
        if(temp!=null && temp.getApp().equals(app)){
            this.visit = temp.getNext();
        }
        while(temp != null && !temp.getApp().equals(app)){
            prev = temp;
            temp = temp.getNext();
        }
        if(temp==null){
            return;
        }
        if(prev==null){
            return;
        }
        prev.setNext(temp.getNext());
    }

    public int charge() {
        if(this.visit==null){
            return 0;
        }
        Visit ptr = this.visit;
        int tot = 0;
        while(ptr!=null){
            if(ptr.getApp().getProvider().getClass()==Doctor.class) {
                tot += ((Doctor) (ptr.getApp().getProvider())).getSpecialty().getCharge();
            } else {
                tot += ((Technician)(ptr.getApp().getProvider())).rate();
            }
            if(ptr.getNext() != null){
                ptr = ptr.getNext();
            }
            else break;
        }
        return tot;
    }

}
