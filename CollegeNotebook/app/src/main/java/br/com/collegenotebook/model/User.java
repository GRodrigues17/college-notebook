package br.com.collegenotebook.model;


/**
 * Created by GRodrigues17 on 28/01/2017.
 */

public class User {

    private long id;
    private String userEmail;
    private String userPassword;

    private String userName;
    private String userPhoto;
    private String userLocale;
    private String userSite;

    public User(){

    }

    public User(long id, String userEmail, String userPassword,String userName, String userPhoto, String userSite,String userLocale) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.userLocale = userLocale;
        this.userLocale = userSite;
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

    public String getUserLocale() {
        return userLocale;
    }

    public void setUserLocale(String userLocale) {
        this.userLocale = userLocale;
    }

    public String getUserSite() {
        return userSite;
    }

    public void setUserSite(String userSite) {
        this.userSite = userSite;
    }
}
