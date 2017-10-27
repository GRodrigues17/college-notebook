package br.com.collegenotebook.model;

/**
 * Created by Jessica Mendes on 19/10/2017.
 */

public class Comment {
    String comment;
    String date;

    public Comment(){

    }


    public Comment(String comment, String date) {
        this.comment = comment;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
