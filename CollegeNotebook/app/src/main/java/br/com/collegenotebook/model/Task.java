package br.com.collegenotebook.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jessica Mendes on 13/10/2017.
 */

public class Task implements Serializable{
    int id;
    String task;
    String taskDescription;
    boolean isFinalized;
    String hour;
    String date;

    public Task(){}

    public Task(String date, String hour, String task, String taskDescription) {
        this.task = task;
        this.taskDescription = taskDescription;
        this.hour = hour;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void setFinalized(boolean finalized) {
        isFinalized = finalized;
    }


    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
