package model;

public class HoursOfOperation {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;


    /* REQUIRES: 0 <= startHour < 23
                 0 <= startMinute < 59
                 0 <= endHour < 23
                 0 <= endMinute < 59
       EFFECTS: assigns respective numbers to hours and minutes
     */

    public HoursOfOperation(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    //getters

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    @Override

    //EFFECTS: returns string representation of hours of operation

    public String toString() {
        return "from " + startHour + ":" + startMinute + " to " + endHour + ":" + endMinute;
    }
}
