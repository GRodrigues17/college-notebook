package br.com.collegenotebook.model;

import java.io.Serializable;

/**
 * Created by GRodrigues17 on 28/01/2017.
 */

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhoto;

    public User(){

    }

    public User(long id, String userName, String userEmail, String userPassword,String userPhoto) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoto = userPhoto;
    }

    public User(long id, String userEmail, String userPassword) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userLogin) {
        this.userEmail = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }


}
