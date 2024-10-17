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

    /**
     * @return Strings that indicate if date is a valid appointment date, if valid returns null
     */
    public String isValidMaster(){
        if(!this.isValidDate()) return ("Appointment date: " + this.toString() + " is not a valid calendar date.");
        if(this.isBeforeToday()) return ("Appointment date: " + this.toString() + " is today or a date before today.");
        if(this.isWeekend()) return ("Appointment date: " + this.toString() + " is Saturday or Sunday.");
        if(!this.withinSix()) return ("Appointment date: " + this.toString() + " is not within six months.");
        return null;
    }

    /**
     * @return true if leap year or false if not
     */
    public boolean isLeap(){
        if(this.year%QUADRENNIAL==0){
            if(this.year%CENTENNIAL==0){
                return this.year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * @return true if date is a valid calendar date later than the year 1900, false otherwise
     */
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

    /**
     * @return true if the date falls on a weekend, false otherwise
     */
    public boolean isWeekend(){
        Calendar date = Calendar.getInstance();
        date.set(this.year, this.month-1, this.day);
        int res = date.get(Calendar.DAY_OF_WEEK);
        return res == Calendar.SATURDAY || res == Calendar.SUNDAY;
    }

    /**
     * @return true if date is before today, false otherwise
     */
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

    /**
     * @return true if date is within six months from now, false otherwise
     */
    public boolean withinSix(){
        Calendar today = Calendar.getInstance();
        Calendar sixM = Calendar.getInstance();
        sixM.add(Calendar.MONTH, 6);

        Calendar date = Calendar.getInstance();
        date.set(this.year, this.month-1, this.day);
        return date.after(today) && date.before(sixM);
    }

    /**
     * @return true if date is a valid birth date, false otherwise
     */
    public boolean isValidBirth(){
        if(this.isValidDate()){
            return this.isBeforeToday();
        }
        return false;
    }

    /**
     * @param d the object to be compared.
     * @return -1, 0, or 1 depending on values of year, month, and day respectively
     */
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

    /**
     * @param obj to be compared with this
     * @return true if obj is equal to this when cast to Date, false otherwise
     */
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

    /**
     * @return String conversion of date object
     */
    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

}
