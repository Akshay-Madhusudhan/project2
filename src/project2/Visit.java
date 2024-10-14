package project2;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */

public class Visit {
    private Appointment appointment; //a reference to an appointment object
    private Visit next; //a ref. to the next appointment object in the list

    public Visit(Appointment app){
        this.appointment = app;
        this.next = null;
    }

    public Visit getNext(){
        return this.next;
    }

    public Appointment getApp(){
        return this.appointment;
    }

    public void setNext(Visit vis){
        this.next = vis;
    }

}