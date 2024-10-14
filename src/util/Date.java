package util;
import java.util.Calendar;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public Date(int m, int d, int y){
        this.month = m;
        this.day = d;
        this.year = y;
    }

    public boolean isLeap(){
        if(this.year%QUADRENNIAL==0){
            if(this.year%CENTENNIAL==0){
                return this.year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    public boolean isValidDate(){
        if(this.year < 1900){
            return false;
        }

        if(this.month < 1 || this.month > 12){
            return false;
        }
        if(this.day < 1 || this.day > 31){
            return false;
        }

        int[] thirty_one = {1, 3, 5, 7, 8, 10, 12};
        int[] thirty = {4, 6, 9, 11};

        for(int month : thirty_one){
            if(month == this.month){
                return true;
            }
        }

        for(int month : thirty){
            if(month == this.month && this.day <= 30){
                return true;
            }
        }

        if(this.month == 2){
            if(this.isLeap()){
                return this.day <= 29;
            }
            return this.day <= 28;
        }
        return false;
    }

    public boolean isWeekend(){
        Calendar date = Calendar.getInstance();
        date.set(this.year, this.month-1, this.day);
        int res = date.get(Calendar.DAY_OF_WEEK);
        return res == Calendar.SATURDAY || res == Calendar.SUNDAY;
    }

    public boolean isBeforeToday(){
        Calendar cal = Calendar.getInstance();
        int Year = cal.get(Calendar.YEAR);
        int Month = cal.get(Calendar.MONTH) + 1;
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        if(this.year < Year){
            return true;
        } else if(this.year == Year && this.month < Month){
            return true;
        } else return this.year == Year && this.month == Month && this.day < Day;
    }

    public boolean withinSix(){
        Calendar today = Calendar.getInstance();
        Calendar sixM = Calendar.getInstance();
        sixM.add(Calendar.MONTH, 6);

        Calendar date = Calendar.getInstance();
        date.set(this.year, this.month-1, this.day);
        return date.after(today) && date.before(sixM);
    }

    //check if the date is a valid calendar date
    public boolean isValidAppointment(){
        if(this.isValidDate()) {
            if(this.withinSix()) {
                return !this.isWeekend();
            }
        }
        return false;
    }

    public boolean isValidBirth(){
        if(this.isValidDate()){
            return this.isBeforeToday();
        }
        return false;
    }

    @Override
    public int compareTo(Date d) {
        if(this.year == d.year){
            if(this.month == d.month){
                return Integer.compare(this.day, d.day);
            }
            return Integer.compare(this.month, d.month);
        }
        return Integer.compare(this.year, d.year);
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        Date d = (Date)obj;
        return d.compareTo(this) == 0;
    }

    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static void main(String[] args){
        Date inv1 = new Date(13, 2, 1906);
        Date inv2 = new Date(2, 29, 2001);
        Date inv3 = new Date(12, 32, 1762);
        Date inv4 = new Date(1, 15, -1);
        Date val1 = new Date(2, 29, 2000);
        Date val2 = new Date(8, 31, 2026);
        System.out.println(inv1.isValidDate());
        System.out.println(inv2.isValidDate());
        System.out.println(inv3.isValidDate());
        System.out.println(inv4.isValidDate());
        System.out.println(val1.isValidDate());
        System.out.println(val2.isValidDate());
    }

}
