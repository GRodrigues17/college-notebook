package br.com.collegenotebook.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.com.collegenotebook.dao.BaseDAO;
import br.com.collegenotebook.model.User;

/**
 * Created by GRodrigues17 on 01/02/2017.
 */

public class LoginController {
    private SQLiteDatabase db;
    private BaseDAO banco;
    private User user;
    Cursor cursorUser;


    public LoginController(Context context){
        banco = new BaseDAO(context);
    }

    public void open() throws SQLException {
        db = banco.getWritableDatabase();

    }

    public void close() {
        banco.close();
    }

    public User validaLogin(String userEmail, String userPassword){
        open();
        String[] selectionArgs = new String[]{userEmail, userPassword};
        cursorUser = db.rawQuery("select * from "+BaseDAO.TABLE_USER+ " where userEmail=? and userPassword=?", selectionArgs);
        if (cursorUser.moveToNext()) {
            user = new User();
            user.setUserEmail(cursorUser.getString(cursorUser.getColumnIndex(BaseDAO.USER_EMAIL)));
            user.setUserPassword(cursorUser.getString(cursorUser.getColumnIndex(BaseDAO.USER_PASSWORD)));
        }
        return user;
    }



    public boolean insertUser(User user) {
        if (!verifyIfUserExists(user)) {
            ContentValues values = new ContentValues();
            values.put(BaseDAO.USER_EMAIL, user.getUserEmail());
            values.put(BaseDAO.USER_PASSWORD, user.getUserPassword());
            values.put(BaseDAO.USER_NAME, user.getUserName());
            values.put(BaseDAO.USER_PHOTO, user.getUserPhoto());
            values.put(BaseDAO.USER_LOCALE, user.getUserLocale());

            values.put(BaseDAO.USER_SITE, user.getUserSite());

            boolean createSuccessful = db.insert(BaseDAO.TABLE_USER, null, values) > 0;
            close();
            return createSuccessful;
        }
        return false;

    }
    public  boolean verifyIfUserExists(User user) {
        open();
        String userEmail = user.getUserEmail();
        String Query = "SELECT * FROM " +BaseDAO.TABLE_USER+ " WHERE " +BaseDAO.USER_EMAIL+ "= '" + userEmail +"'";
        cursorUser = db.rawQuery(Query, null);
        cursorUser.moveToLast();
        if(cursorUser.getCount() <= 0){
            return false;
        }
        return true;
    }



//    public void saveUser(String newEmail, String newPassword) {
//        baseController = new BaseController(context);
//
//        baseController.open();
//        user = new User();
//        user.setUserEmail(newEmail);
//        user.setUserName("");
//        user.setUserPassword(newPassword);
//        user.setUserPhoto("");
//        user.setUserLocale("");
//
//        baseController.insertUser(user);
//        baseController.close();
//    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
