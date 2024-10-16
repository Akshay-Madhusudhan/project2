package project2;

import util.Date;

public class Imaging extends Appointment{
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        super(date, timeslot, patient, provider);
    }
}
