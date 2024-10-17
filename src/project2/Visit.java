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

    /**
     * @return next visit in linked list
     */
    public Visit getNext(){
        return this.next;
    }

    /**
     * @return appointment in the current node (Visit)
     */
    public Appointment getApp(){
        return this.appointment;
    }

    /**
     * @param vis Visit to be set
     * Method to set the next node (Visit) as vis
     */
    public void setNext(Visit vis){
        this.next = vis;
    }

}