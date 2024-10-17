package project2;

import util.*;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Imaging extends Appointment{
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Profile patient, Provider provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    /**
     * @return radiology room (imaging type)
     */
    public Radiology getRoom(){
        return this.room;
    }

}
