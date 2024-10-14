package project2;

public class Timeslot implements Comparable<Timeslot>{
    private int hour;
    private int minute;

    @Override
    public boolean equals(Object obj){
        return false;
    }

    @Override
    public int compareTo(Timeslot slot){
        return 0;
    }

    @Override
    public String toString(){
        return "";
    }
}
