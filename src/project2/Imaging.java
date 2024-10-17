package project2;

import util.*;

public class Imaging extends Appointment{
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Profile patient, Provider provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    public Radiology getRoom(){
        return this.room;
    }

}
