package project2;
import util.*;

public class Timeslot implements Comparable<Timeslot>{
    private int hour;
    private int minute;

    public Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    // Generates the list of timeslots for use in ClinicManager
    public static List<Timeslot> generateTimeslots(){
        List<Timeslot> timeslots = new List<>();

        // Morning slots (9:00 AM to 11:30 AM)
        for (int i = 9; i < 12; i++) {
            timeslots.add(new Timeslot(i, 0));
            timeslots.add(new Timeslot(i, 30));
        }

        // Afternoon slots (2:00 PM to 4:30 PM)
        for (int i = 14; i < 17; i++) {
            timeslots.add(new Timeslot(i, 0));
            timeslots.add(new Timeslot(i, 30));
        }

        return timeslots;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || getClass() != obj.getClass()) return false;
        Timeslot timeslot = (Timeslot) obj;
        return hour == timeslot.hour && minute == timeslot.minute;
    }

    @Override
    // Convert an Timeslot into a String, formatted e.g. "9:00 AM"
    public String toString(){
        int hour12;
        if (hour == 0 || hour == 12) {
            hour12 = 12;
        } else if (hour > 12) {
            hour12 = hour - 12;
        } else {
            hour12 = hour;
        }

        String amPm;
        if (hour < 12) {
            amPm = "AM";
        } else {
            amPm = "PM";
        }

        String minuteStr;
        if (minute < 10) {
            minuteStr = "0" + minute; // Add leading zero for single-digit minutes
        } else {
            minuteStr = Integer.toString(minute); // Convert minutes to string
        }

        return hour12 + ":" + minuteStr + " " + amPm;
    }

    @Override
    public int compareTo(Timeslot slot){
        if (this.hour != slot.hour) {
            return Integer.compare(this.hour, slot.hour);
        } else {
            return Integer.compare(this.minute, slot.minute);
        }
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }

}
