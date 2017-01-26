package br.com.collegenotebook.model;

/**
 * Created by GRodrigues17 on 24/10/2016.
 */

public class TimeSheet {

    int id;
    String timeSheet;

    public TimeSheet() {
    }
    public TimeSheet(String timeSheet) {
        this.timeSheet = timeSheet;
    }


    public String getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(String timeSheet) {
        this.timeSheet = timeSheet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
