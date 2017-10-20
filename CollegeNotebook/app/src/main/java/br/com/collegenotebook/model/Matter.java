package br.com.collegenotebook.model;

/**
 * Created by GRodrigues17 on 27/09/2016.
 */
public class Matter {
    private long id;
    private String title;
    private String instructor;
    private String folder;
    private String date;
    private int color;
    private int like;


public Matter(){

}

    public Matter(long id, String title, String instructor, String folder,String date, int like,int color) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.folder = folder;
        this.date = date;
        this.like = like;
        this.color = color;


    }

    public Matter(String title, String instructor, String folder) {
        this.title = title;
        this.instructor = instructor;
    }



    @Override
    public String toString() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
